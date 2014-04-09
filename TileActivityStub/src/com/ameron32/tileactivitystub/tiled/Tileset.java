package com.ameron32.tileactivitystub.tiled;

import android.content.Context;
import android.graphics.Bitmap;


public class Tileset {
  
  private static final String TILESET_NAME = "name";
  private static final String TILE_WIDTH   = "tilewidth";
  private static final String TILE_HEIGHT  = "tileheight";
  private static final String IMAGE_NAME   = "source";
  private static final String IMAGE_WIDTH  = "width";
  private static final String IMAGE_HEIGHT = "height";
  
  public String              name;
  private int                 tileWidth;
  private int                 tileHeight;
  
  private String              imageSource;
  private int                 imgWidth;
  private int                 imgHeight;
  
  Tileset() {
    name = "";
    tileWidth = tileHeight = 0;
    
    imageSource = "";
    imgWidth = imgHeight = 0;
  }
  
  public void setAttribute(String tag, String name, String value) {
    if (usesTag(tag)) {
      if (name.equalsIgnoreCase(TILESET_NAME)) {
        this.name = value;
      }
      
      if (name.equalsIgnoreCase(TILE_WIDTH)) {
        tileWidth = Integer.valueOf(value);
      }
      
      if (name.equalsIgnoreCase(TILE_HEIGHT)) {
        tileHeight = Integer.valueOf(value);
      }
      
      if (name.equalsIgnoreCase(IMAGE_NAME)) {
        imageSource = value;
      }
      
      if (name.equalsIgnoreCase(IMAGE_WIDTH)) {
        imgWidth = Integer.valueOf(value);
      }
      
      if (name.equalsIgnoreCase(IMAGE_HEIGHT)) {
        imgHeight = Integer.valueOf(value);
      }
      
      {
        // do nothing }
      }
    }
    else {
      System.out.println("Tileset DOES NOT USE THIS TAG: [" + tag + "]");
    }
  }
  
  public static boolean usesTag(String name) {
    return (name.equalsIgnoreCase("tileset")
        || name.equalsIgnoreCase("image"));
  }
  
  @Override
  public String toString() {
    return "Tileset [name=" + name + ", tileWidth=" + tileWidth + ", tileHeight=" + tileHeight + ", imageSource="
        + imageSource + ", imgWidth=" + imgWidth + ", imgHeight=" + imgHeight + "]";
  }

  public Tileset(Tileset tileset) {
    this.name = tileset.name;
    this.tileWidth = tileset.tileWidth;
    this.tileHeight = tileset.tileHeight;
    this.imageSource = tileset.imageSource;
    this.imgWidth = tileset.imgWidth;
    this.imgHeight = tileset.imgHeight;
  }
  
  public Bitmap getTileAt(int size, int x, int y, Bitmap tileset) {
    
    // sanity check
    int maxX = imgWidth / tileWidth;
    int maxY = imgHeight / tileHeight;
    if (x > maxX - 1 || y > maxY - 1) { return null; }
    
    int startX = x * tileWidth;
    int startY = y * tileHeight;
    
    float scaleX = ((float)size) / ((float)tileWidth);
    float scaleY = ((float)size) / ((float)tileHeight);
    startX = (int) (((float)startX) * scaleX);
    startY = (int) (((float)startY) * scaleY);
    int scaledWidth = (int) (tileWidth * scaleX);
    int scaledHeight = (int) (tileHeight * scaleY);
    
    return Bitmap.createBitmap(tileset, startX, startY, scaledWidth, scaledHeight);
  }
  
  public Bitmap getTileFromGID(int size, int gid, Bitmap tileset) {
    
    // sanity check
    int maxX = imgWidth / tileWidth;
    int maxY = imgHeight / tileHeight;
    int maxGID = maxX * maxY;
    if (gid > maxGID - 1) { return null; }
    
    if (gid == 0) { return null; }
    
    gid = gid - 1;
    int colNumber = gid % maxX;
    int rowNumber = gid / maxX;
    
    return getTileAt(size, colNumber, rowNumber, tileset);
  }
  
  
  
}
