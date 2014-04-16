package com.ameron32.tileactivitystub.tiled;

import java.util.ArrayList;
import java.util.List;

import com.ameron32.tileactivitystub.tiled.TileMap.TilesetWrapper;

import android.graphics.Bitmap;

public class Tileset {
  
  private static final String TILESET_NAME = "name";
  private static final String TILE_WIDTH   = "tilewidth";
  private static final String TILE_HEIGHT  = "tileheight";
  private static final String IMAGE_NAME   = "source";
  private static final String IMAGE_WIDTH  = "width";
  private static final String IMAGE_HEIGHT = "height";
  
  public String               name;
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
  
  private TilesetWrapper tilesetWrapper = null;
  public void setWrapper(TilesetWrapper tilesetWrapper) {
    this.tilesetWrapper = tilesetWrapper;
  }
  
  public TilesetWrapper getWrapper() {
    return tilesetWrapper;
  }
  
  public int getMaxColumns() {
    return imgWidth / tileWidth;
  }
  
  public int getMaxRows() {
    return imgHeight / tileHeight;
  }
  
  public int getMaxTiles() {
    return getMaxColumns() * getMaxRows();
  }
  
  public Bitmap getTileAt(int size, int x, int y, Bitmap tileset) {
    
    // sanity check
    int maxX = getMaxColumns();
    int maxY = getMaxRows();
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
    int maxX = getMaxColumns();
    int maxY = getMaxRows();
    int maxGID = maxX * maxY;
    if (gid > maxGID - 1) { return null; }
    
    if (gid == 0) { return null; }
    
    gid = gid - 1;
    int colNumber = gid % maxX;
    int rowNumber = gid / maxX;
    
    return getTileAt(size, colNumber, rowNumber, tileset);
  }
  
  public static class ComponentHandler {
    
		TileMap tileMap;
		String httpPrefix;
		int maxSize;
		String extension;
    
    public ComponentHandler(TileMap tileMap, String httpPrefix, int maxSize, String extension) {
      this.tileMap = tileMap;
      this.httpPrefix = httpPrefix;
      this.maxSize = maxSize;
      this.extension = extension;
    }
    
    /**
     * 
     * @param size Width in pixels of unique tile size. Square tiles only.
     * @return Coded pseudo-url for DetailLevelUrl input. 
     * @note  not compatible as a standard url
     */
    public String getDetailLevelUrl(int size) {
      // http://www.myurl.com/whatever
      //     /
      //     #tilemap_600_%col%_%row%_jpg
      String detailLevelUrl = httpPrefix + "/" + "#" + tileMap + "_" + size 
          + "_" + "%col%" + "_" + "%row%" + "_" + extension;
      return detailLevelUrl;
    }
    
    public static ComponentHolder extractHolder(TileMap tileMap, String decodeUrl) {

      String[] parts = decodeUrl.split("#");
      String httpPrefix = parts[0];
      String fileName = parts[1];
      
      String[] components = fileName.split("_");
      String tilemap = components[0];
      String size = components[1];
      String column  = components[2];
      String row = components[3];
      String extension = components[4];
      
      ComponentHolder ch = ComponentHolder.newInstance(httpPrefix, 
          tileMap, Integer.valueOf(size), 
          Integer.valueOf(column), Integer.valueOf(row), extension);
      
      return ch;
    }
  }
  
  public static class ComponentHolder {

		public TileMap tilemap;

		public String httpPrefix;
		public String filename;
		public String extension;
		public String url;

		// public String file;
		public int size;
		public int column;
		public int row;
    
    private ComponentHolder() {}
    
    public static ComponentHolder newInstance(String httpPrefix, 
        TileMap tilemap, int size,
        int column, int row,
        String extension) {
      ComponentHolder ch = new ComponentHolder();
      
      ch.httpPrefix = httpPrefix;
      ch.extension = extension;
      
      ch.tilemap = tilemap;
      ch.size = size;
      ch.column = column;
      ch.row = row;
      
      return ch;
    }
    
    private String getFilename(String tileset) {
      return tileset + this.size + "." + this.extension;
    }
    
    public String makeUrl(String tileset) {
      return this.httpPrefix + getFilename(tileset);
    }

    @Override
    public String toString() {
      return "ComponentHolder [httpPrefix=" + httpPrefix + ", filename=" + filename + ", extension=" + extension
          + ", url=" + url + ", tilemap=" + tilemap.toString() + ", size=" + size + ", column=" + column + ", row=" + row + "]";
    }
  }
}
