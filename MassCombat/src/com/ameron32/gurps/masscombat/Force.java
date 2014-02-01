package com.ameron32.gurps.masscombat;

import java.util.ArrayList;
import java.util.List;

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
  
  
  
  
  
  
  /** Units allow descriptive subgroups for Forces */
  public static class Unit {
    
    int     quantity;
    Element element;
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
