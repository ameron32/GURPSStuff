package com.ameron32.tileactivitystub;

import android.content.Context;
import android.graphics.Bitmap;

import com.ameron32.tileactivitystub.tiled.Tile;
import com.ameron32.tileactivitystub.tiled.TileMap;
import com.ameron32.tileactivitystub.tiled.Tileset;
import com.ameron32.tileactivitystub.tiled.Tileset.ComponentHolder;

public class MyTileBitmapDecoderHttp extends SmartTileBitmapDecoder {
  
  // the name of the file in assets which will load 
  // if the specified tile cannot be found
  private static final String ERROR_TILE = "tvstatic.png";
  // standard divider character
  private static final String DIVIDER_CHARACTER = "_";
  // http prefixes
  private static final String HTTP_PREFIX       
      = "https://dl.dropboxusercontent.com/u/949753/ANDROID/TileView/indoor/";
  private static final String TILESET_FOLDER    
      = "tileset/";
  
  TileMap tileMap;
  public MyTileBitmapDecoderHttp(TileMap tileMap, Context context) {
    super();
    this.tileMap = tileMap;
    DEBUG = true;
  }
  
  public Bitmap decode(String fileName, Context context) {
    
    // Read FileName into components
    ComponentHolder holder = Tileset.ComponentHandler.extractHolder(fileName);
    
    return decode(holder, context);
  }
  
  protected Bitmap decode(ComponentHolder holder, Context context) {
    
    // Extract from tileset
    Bitmap bitmap = bitmapCache.getTilesetImage(context, holder, holder.size);
    
    // try to redraw tile from tileset
    // get the tile image from the tileset
    try {
      // Tile tile = tileMap.getTiles(0)[holder.column][holder.row];
      Tile tile = extractTile(holder.column, holder.row);
      bitmap = imageFromTile(tile, bitmap, holder.tileset, holder.size);
      bitmap = tile.rotateFromTile(bitmap);
    }
    // and catch: load a broken tile no real tile is pulled
    catch (NullPointerException npe) {
      npe.printStackTrace();
      bitmap = getBitmapCache().loadErrorTile(holder.size, holder.size, context);
    }
    
    return bitmap;
  }
  
  
  
  private Tile extractTile(int column, int row) {
    Tile tile = tileMap.getTiles(0)[column][row];
    log("pullTile", "gid: " + tile.gid());
    log("pullTile", "column/row: " + column + "/" + row);
    return tile;
  }
  
  private Bitmap imageFromTile(Tile tile, Bitmap tilesetImage, String tilesetName, int size) {
    Bitmap src = tileMap.getTileset(tilesetName).getTileFromGID(size, tile.gid(), tilesetImage);
    log("pullTile", "tilesetImage: " + tilesetImage);
    log("pullTile", "size: " + size);
    return src;
  }

}
