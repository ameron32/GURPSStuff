package com.ameron32.gurps.masscombat;


public class Terrain {
  
  
  
  static int getValue(Terrain.Type type) {
    int value = -1;
    switch (type) {
    case OpenWater:
    case Coast:
    case OffRoad:
      // all three worth 0
      value = 0;
      break;
    case Road:
      value = 1;
      break;
    case GoodRoad:
      value = 2;
      break;
    default:
      // new terrains? forgot to update?
      value = -2;
      break;
    }
    return value;
  }
  
  static int getTerrainRating(Terrain.Type type) {
    int value = -1;
    switch (type) {
    case OpenWater:
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
    case Coast:
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
      value = 0;
      break;
    }
    return value;
  }
  
  public enum Type {
    OpenWater, OffRoad, Road, GoodRoad, Coast,
      Arctic, BuiltUpAreas, BuiltUpAreasFromWithinArea, Desert, Hills, Island, Beach, Jungle, 
      Mountain, Plains, Rural, RuralHedgerows, Swampland, Underwater, Woodlands, TracklessWoodlands
  }
}
