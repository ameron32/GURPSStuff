package com.ameron32.gurps.masscombat;


public class CampaignManager {
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  public static class Operation {
    
    String description;
    Type type;
    
    public Operation newInstance(Type type, String description) {
      return new Operation(type, description);
    }
    
    private Operation(Type type, String description) {
      this.description = description;
      this.type = type;
    }
    
    public enum Type {
      SeekAndDestroy, Hide, Advance,
      Retreat, Defend, Escort,
      PatrolOrSecure, Ravage,
      PerformFieldExercizes,
      RallyRestTrain,
      Disorganized
    }
  }
}
