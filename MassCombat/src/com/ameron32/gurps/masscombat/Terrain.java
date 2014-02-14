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
  
  public enum Type {
    OpenWater, OffRoad, Road, GoodRoad, Coast
  }
}
