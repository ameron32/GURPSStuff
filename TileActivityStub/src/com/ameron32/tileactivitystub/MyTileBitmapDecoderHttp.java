package com.ameron32.tileactivitystub;

import android.content.Context;
import android.graphics.Bitmap;

import com.ameron32.tileactivitystub.interfaces.HttpDecoderCallbacks;
import com.ameron32.tileactivitystub.tiled.Tile;
import com.ameron32.tileactivitystub.tiled.TileMap;
import com.ameron32.tileactivitystub.tiled.TileMap.TilesetWrapper;
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
  
  private TileMap tileMap;
  public MyTileBitmapDecoderHttp(TileMap tileMap, HttpDecoderCallbacks listener) {
    super(listener);
    this.tileMap = tileMap;
    DEBUG = true;
  }
  
  public Bitmap decode(String fileName, Context context) {
    
    // Read FileName into components
    ComponentHolder holder = Tileset.ComponentHandler.extractHolder(tileMap, fileName);
    
    return decode(holder, context);
  }
  
  private Tile tile;
  protected Bitmap decode(ComponentHolder holder, Context context) {

		Bitmap bitmap = null;

		try {
			tile = extractTile(holder.column, holder.row);
			if (tile.gid() != 0) {
				Tileset tileset = findTileset();
				String sTileset = tileset.name;
				String url = holder.makeUrl(sTileset);

				// Extract from tileset
				bitmap = bitmapCache.getTilesetImage(context, sTileset, url,
						holder.size);

				// try to redraw tile from tileset
				// get the tile image from the tileset
				// Tile tile = tileMap.getTiles(0)[holder.column][holder.row];
				// Tile tile = extractTile(holder.column, holder.row);
				bitmap = imageFromTile(tile, bitmap, tileset, holder.size);
				bitmap = tile.rotateFromTile(bitmap);
			} else {
				// gid IS 0, load generic "blank tile"
				bitmap = getBitmapCache().loadBitmapAssets("black.png",
						holder.size, holder.size, context);
			}
			tile = null;
		}
		// and catch: load a broken tile no real tile is pulled
		catch (NullPointerException npe) {
			npe.printStackTrace();
			bitmap = getBitmapCache().loadErrorTile(holder.size, holder.size,
					context);
		}

		return bitmap;
  }
  
  protected Tileset findTileset() {
	  Tileset ts = tileMap.getTilesetFromGID(tile.gid());
	  log("findTileset: ", "tile.gid: " + tile.gid() + " ts.name" + ts.name);
	  return ts;
  }
  
  protected Tile extractTile(int column, int row) {
    Tile tile = tileMap.getTiles(0)[column][row];
    log("pullTile", "gid: " + tile.gid());
    log("pullTile", "column/row: " + column + "/" + row);
    return tile;
  }
  
  protected Bitmap imageFromTile(Tile tile, Bitmap tilesetImage, Tileset tileset, int size) {
    Tileset ts = tileset; 
    // Tileset ts = tileMap.getTilesetFromGID(tile.gid());
    int actualGid = tile.gid() - ts.getWrapper().getFirstGid() +1;
    Bitmap src = ts.getTileFromGID(size, actualGid, tilesetImage);
    log("pullTile", "tilesetImage: " + tilesetImage);
    log("pullTile", "tileset: " + ts.name + " max: " + ts.getMaxTiles());
    log("pullTile", "size: " + size);
    return src;
  }
  
  protected TileMap getTileMap() {
    return tileMap;
  }

}
