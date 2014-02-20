package com.ameron32.gurps.masscombat;


public class Terrain {
  String name;
  Terrain.Type terrainType;
  Road.Type roadType;
  Water.Type waterType;
  
  // *** CONSTRUCTORS
  // ***********************************************************************************
  /**
   * Full constructor.
   * 
   * @param name User-understandable string to describe this terrain.
   * @param type Category of base terrain.
   * @param road Type of road on terrain, or none.
   * @param water Type of water terrain, or none.
   * 
   * @return Returns new terrain with the given attributes.
   */
  Terrain newTerrainInstance(String name, Terrain.Type type, Road.Type road, Water.Type water) {
    return new Terrain(name, type, road, water);
  }
  // TODO: easy constructors for no water and/or road
  
  private Terrain(String name, Terrain.Type terrain, Road.Type road, Water.Type water) {
    this.name = name;
    this.terrainType = terrain;
    this.roadType = road;
    this.waterType = water;
  }
  
  // *** STATIC CALLS
  // ***********************************************************************************
  
  static int getRoadValue(Terrain terrain) throws InvalidTerrainException {
    Road.Type type = terrain.roadType;
    
    // TODO: I was in a hurry. Review RoadValue
    int value = -1;
    switch (type) {
//    case OpenWater:
//    case Coast:
//    case OffRoad:
    case None:
      // all three worth 0
      value = 0;
      break;
    case DirtRoad:
      value = 1;
      break;
    case GoodRoad:
      value = 2;
      break;
    // new terrains? forgot to update?
    default:
      throw new InvalidTerrainException();
    }

    return value;
  }
  
  static int getWaterValue(Terrain terrain) throws InvalidTerrainException {
    Water.Type type = terrain.waterType;
    
    // TODO: I was in a hurry. Review WaterValue
    int value = -1;
    switch (type) {
    case OpenWater:
    case Coast:
      value = 0;
      break;
    case None:
      value = -1;
      break;
    default:
      throw new InvalidTerrainException();
    }
    
    return value;
  }
  
  static int getTerrainRating(final Terrain terrain) throws InvalidTerrainException {
    Terrain.Type type = terrain.terrainType;
    
    // TODO: I was in a hurry. Review TerrainRating
    int value = -1;
    switch (type) {
//    case OpenWater:
    case Plains:
      value = 8;
      break;
    case Desert:
      value = 7;
      break;
    case Arctic:
    case BuiltUpAreas:
    case Hills:
    case Island:
    case Beach:
//    case Coast:
    case Rural:
      value = 6;
      break;
    case Woodlands:
    case RuralHedgerows:
      value = 5;
      break;
    case Mountain:
    case Swampland:
    case Underwater:
    case TracklessWoodlands:
      value = 4;
      break;
    case BuiltUpAreasFromWithinArea:
    case Jungle:
      value = 3;
      break;
    // no references directly to Road, goodRoad, while OffRoad should be one of the above
    default:
      throw new InvalidTerrainException();
    }
    return value;
  }
  

  
  // *** SUB-CLASSES
  // ***********************************************************************************
  
  public enum Type {
//    OpenWater, OffRoad, Road, GoodRoad, Coast,
      Arctic, BuiltUpAreas, BuiltUpAreasFromWithinArea, Desert, Hills, Island, Beach, Jungle, 
      Mountain, Plains, Rural, RuralHedgerows, Swampland, Underwater, Woodlands, TracklessWoodlands
  }
  
  public static class Road {
    public enum Type {
      None, DirtRoad, GoodRoad
    }
  }
  
  public static class Water {
    public enum Type {
      None, Coast, OpenWater
    }
  }
  
  public static class InvalidTerrainException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -1159241532473004286L;
    
    
  }
  
  
  // *** INSTANCE CALLS
  // ***********************************************************************************
  
  boolean hasRoad() {
    return (!(roadType == Road.Type.None));
  }
  
  boolean isWater() {
    return (!(waterType == Water.Type.None));
  }
  

  
  // *** OBJECT / FIELD CALLS
  // ***********************************************************************************
  
  @Override
  public String toString() {
    return "Terrain:"+ name+ " type:" + terrainType.name() + " road:" + roadType.name() + " water:" + waterType.name();
  }
    
}
