package com.ameron32.tileactivitystub;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;

import com.ameron32.tileactivitystub.interfaces.HttpDecoderCallbacks;
import com.ameron32.tileactivitystub.tiled.Tile;
import com.ameron32.tileactivitystub.tiled.TileMap;
import com.ameron32.tileactivitystub.tiled.Tileset.ComponentHolder;
import com.qozix.tileview.TileView;


public class MyFOWTileBitmapDecoder extends MyTileBitmapDecoderHttp {

  boolean[][] broken = null;
  private TileView tileView;
  
  public MyFOWTileBitmapDecoder(TileView tileView, TileMap tileMap, HttpDecoderCallbacks context) {
    super(tileMap, context);
    this.tileView = tileView;
    
    Tile[][] tiles = tileMap.getTiles(0);
    broken = new boolean[tiles.length][tiles[0].length];
//    broken[2][2] = true;
//    broken[4][3] = true;
  }

  @Override
  protected Bitmap decode(ComponentHolder holder, Context context) {
    
    boolean breakMe = false;
    if (broken[holder.column][holder.row]) {
      breakMe = true;
    }
    
    if (breakMe) {
      return getBitmapCache().loadErrorTile(holder.size, holder.size, context);
    }
    else {
      return super.decode(holder, context);
    }
  }
}
