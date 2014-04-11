package com.ameron32.tileactivitystub;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

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
  
  public SmartTileBitmapDecoder() {
    bitmapCache = new BitmapCache();
  }
  
  protected static class BitmapCache {
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
    public Bitmap getTilesetImage(Context context, ComponentHolder holder, int size) {
      
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
      if (tilesetMapForSize.containsKey(holder.tileset)) {
        bitmap = tilesetMapForSize.get(holder.tileset);
      } else {
        bitmap = loadBitmapHttp(holder.url, context);
        tilesetMapForSize.put(holder.tileset, bitmap);
      }
      
      return bitmap;
    }
    
    /**
     * Simple bitmap downloader method
     */
    protected Bitmap loadBitmapHttp(String fileName, Context context) {
      log("loadBitmapHttp", "fileName: " + fileName);
      try {
        URL url = new URL(fileName);
        try {
          HttpURLConnection connection = (HttpURLConnection) url.openConnection();
          InputStream input = connection.getInputStream();
          if ( input != null ) {
            try {
              return BitmapFactory.decodeStream( input, null, OPTIONS );                    
            } catch ( OutOfMemoryError oom ) {
              // oom - you can try sleeping (this method won't be called in the UI thread) or try again (or give up)
              log("ERROR:", "Out of Memory");
              oom.printStackTrace();
            } catch ( Exception e ) {
              // unknown error decoding bitmap
            }
          }
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
      return null;
    }
    
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
