package com.ameron32.tileactivitystub;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.ameron32.tileactivitystub.interfaces.HttpDecoderCallbacks;
import com.ameron32.tileactivitystub.tiled.Tileset.ComponentHolder;
import com.qozix.tileview.graphics.BitmapDecoderHttp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;


public abstract class SmartTileBitmapDecoder extends BitmapDecoderHttp {
  
  // <OPTIMIZATION SETTINGS>
  private static final BitmapFactory.Options OPTIONS = new BitmapFactory.Options();
  static {
    OPTIONS.inPreferredConfig = Bitmap.Config.RGB_565;
  }
  // </OPTIMIZATION SETTINGS>


  protected static boolean DEBUG = true;
  
  BitmapCache bitmapCache;
  HttpDecoderCallbacks listener;
  
  public SmartTileBitmapDecoder(HttpDecoderCallbacks listener) {
	  this.listener = listener;
    bitmapCache = new BitmapCache();
  }
  
  protected class BitmapCache {
    // SparseArray<Integer>: Integer: SIZES
    // HashMap    <String>:  String:  TILESET NAME
    // HashMap    <Bitmap>:  Bitmap:  TILESET IMAGE
    private Map<Integer, Map<String, Bitmap>> sizesMap;
    private Bitmap error;
    
    public BitmapCache() {
      sizesMap = new HashMap<Integer, Map<String, Bitmap>>();
    }
    
    /**
     * Simple bitmap cache or download splitter
     */
    public Bitmap getTilesetImage(Context context, String tileset, String url, int size) {
      
      // find the HashMap of tileset bitmaps for the given size
      Map<String, Bitmap> tilesetMapForSize;
      if (sizesMap.get(size) != null) {
        tilesetMapForSize = sizesMap.get(size);
      } else {
        // if needed, generate a new HashMap for this size
        tilesetMapForSize = new HashMap<String, Bitmap>();
        sizesMap.put(size, tilesetMapForSize);
      }
      
      // retrieve the tileset bitmap
      Bitmap bitmap;
      if (tilesetMapForSize.containsKey(tileset)) {
        bitmap = tilesetMapForSize.get(tileset);
      } else {
        bitmap = loadBitmapHttp(url, context);
        tilesetMapForSize.put(tileset, bitmap);
      }
      
      return bitmap;
    }
    
    /**
     * Simple bitmap downloader method
     */
    protected Bitmap loadBitmapHttp(String fileName, Context context) {
      log("loadBitmapHttp", "fileName: " + fileName);
      listener.onDecodeStart(fileName);
      try {
        URL url = new URL(fileName);
        int count = 0;
        try {
          HttpURLConnection connection = (HttpURLConnection) url.openConnection();
          listener.onDecodeUpdate(0);
          
          // getting file length
          int lengthOfFile = connection.getContentLength();

          // input stream to read file - with 8k buffer
          InputStream input = new BufferedInputStream(url.openStream(), 8192);

          // Output stream to write file
          OutputStream output = new FileOutputStream(context.getCacheDir() 
        		  + "img" + new Random().nextInt(1000));

          byte data[] = new byte[1024];

          long total = 0;

          while ((count = input.read(data)) != -1) {
              total += count;
              // publishing the progress....
              // After this onProgressUpdate will be called
              listener.onDecodeUpdate(((int) ((total*100)/lengthOfFile)));

              // writing data to file
              output.write(data, 0, count);
          }

          // flushing output
          output.flush();

          // closing streams
          output.close();
          input.close();
          
          InputStream fis = new FileInputStream(context.getCacheDir() + "img");
          listener.onDecodeUpdate(100);
          if ( input != null ) {
            try {
              return BitmapFactory.decodeStream( fis, null, OPTIONS );                    
            } catch ( OutOfMemoryError oom ) {
              // oom - you can try sleeping (this method won't be called in the UI thread) or try again (or give up)
              log("ERROR:", "Out of Memory");
              oom.printStackTrace();
            } catch ( Exception e ) {
              // unknown error decoding bitmap
            }
          }
          fis.close();
        } catch ( IOException e ) {
          // io error
          log("ERROR:", "IOException");
          e.printStackTrace();
        }     
      } catch ( MalformedURLException e1 ) {
        // bad url
        log("ERROR:", "Bad URL Structure");
        e1.printStackTrace();
      }
      listener.onDecodeComplete();
      return null;
    }
    
    /**
     * Simple bitmap asset loader method
     */
    protected Bitmap loadBitmapAssets(String fileName, int tileSizeX, int tileSizeY, Context context) {
      Bitmap assetBitmap = null;
      try {
        assetBitmap = BitmapFactory.decodeStream(context.getAssets().open(fileName));
        assetBitmap = Bitmap.createScaledBitmap(assetBitmap, tileSizeX, tileSizeY, false);
      }
      catch (IOException e) {
        e.printStackTrace();
      }
      return assetBitmap;
    }
    
    /**
     * Load a basic cached bitmap
     */
    protected Bitmap loadErrorTile(int tileSizeX, int tileSizeY, Context context) {
      // load the error tile if it has never been loaded before
      if (error == null) {
        try {
          error = BitmapFactory.decodeStream(context.getAssets().open("tvstatic.png"));
          error = Bitmap.createScaledBitmap(error, tileSizeX, tileSizeY, false);
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }
      return error;
    }
  }
  
  
  protected static void log(String tag, String message) {
    if (DEBUG) {
      Log.i(tag, message);
    }
  }
  
  protected BitmapCache getBitmapCache() {
    return bitmapCache;
  }
}
