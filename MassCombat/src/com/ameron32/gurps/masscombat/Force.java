package com.ameron32.gurps.masscombat;

import java.util.ArrayList;
import java.util.List;

import com.ameron32.gurps.masscombat.Characters.Leader;
import com.ameron32.gurps.masscombat.Element.Mobility;

public class Force {
  
  // *** FIGHTING FORCE
  // *********************************
  List<Unit>    units = new ArrayList<Unit>();
  // *** LEADERS *********************
  Leader        commander;
  Leader        intelligenceChief;
  Leader        quartermaster;
  // *** SUPPORT *********************
  LogisticForce tail;
  
  int getSpeed(Terrain.Type overTerrain) {
    int speed = 9999;
    for (Unit unit : units) {
      for (Mobility mobility : unit.element.mobilities) {
        // determine the fastest speed this unit can travel over this terrain
        int bestSpeed = mobility.determineBestSpeed(overTerrain);
        // if this is the slowest unit in the force, this becomes the base speed
        if (bestSpeed < speed) speed = bestSpeed;
      }
    }
    
    // the fastest eligible speed of the slowest unit sets the speed
    return speed;
  }
  
  
  
  
  /** Units allow descriptive subgroups for Forces */
  public static class Unit {
    
      String name;
    int      quantity;
    Element  element;
  }
  
  public static class LogisticForce {
    
    // *** LOGISTIC STRENGTH ***********
    int landLS;
    int navalLS;
    int airLS;
    
    public int getCostToRaise() {
      int landCTR = 5000 * landLS;
      int navalCTR = landCTR * 2;
      int airCTR = landCTR * 4;
      
	  // return the combined cost of all LSs
      return ((landLS > 0) ? landCTR : 0) + ((navalLS > 0) ? navalCTR : 0) + ((airLS > 0) ? airCTR : 0);
    }
    
    public int getCostToMaintain() {
      int landCTM = (5000 * landLS) / 10;
      int navalCTM = landCTM * 2;
      int airCTM = landCTM * 4;
      
	  // return the combined cost of all LSs
      return ((landLS > 0) ? landCTM : 0) + ((navalLS > 0) ? navalCTM : 0) + ((airLS > 0) ? airCTM : 0);
    }
  }
}
