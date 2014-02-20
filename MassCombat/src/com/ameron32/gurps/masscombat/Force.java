package com.ameron32.gurps.masscombat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.ameron32.gurps.masscombat.Characters.Leader;
import com.ameron32.gurps.masscombat.Element.Mobility;

public class Force {
  
  // *** FIGHTING FORCE
  // *********************************
  final List<Unit> units = new ArrayList<Unit>();
  // *** LEADERS *********************
  Leader           commander;
  Leader           intelligenceChief;
  Leader           quartermaster;
  // *** SUPPORT *********************
  LogisticForce    tail;
  


  
  
  int getSpeed(Terrain overTerrain) {
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
  
  
  // *** ENCAMPMENT VS MOBILE
  // ***************************************************************
  
  private boolean isEncamped = false;
  boolean isEncamped() { return isEncamped; }
  void setEncamp(boolean encamp) { this.isEncamped = encamp; }
  
  final Map<MobileModifier.Type, MobileModifier>     mobileModifiers   = new TreeMap<MobileModifier.Type, MobileModifier>();
  final Map<EncampedModifier.Type, EncampedModifier> encampedModifiers = new TreeMap<EncampedModifier.Type, EncampedModifier>();
  
  public static class MobileModifier {
    
    String               name;
    MobileModifier.Type  type;
    final List<Modifier> modifiers = new ArrayList<Modifier>();

    public static MobileModifier newMobileModifier(String name, Type type, Modifier...modifiers) {
      MobileModifier mm = new MobileModifier(name, type);
      for (Modifier m : modifiers) {
        mm.modifiers.add(m);
      }
      return mm;
    }
    
    private MobileModifier(String name, Type type) {
      this.name = name;
      this.type = type;
    }

    public enum Type {
      Flying, ForcedMarch, NoSecurity, ReconInLandBattle, RelationsWithLocals, Roads, Speed, Terrain
    }
  }
  
  public static class EncampedModifier{
    
    String                name;
    EncampedModifier.Type type;
    final List<Modifier>  modifiers = new ArrayList<Modifier>();
    
    public static EncampedModifier newEncampedModifier(String name, Type type, Modifier...modifiers) {
      EncampedModifier em = new EncampedModifier(name, type);
      for (Modifier m : modifiers) {
        em.modifiers.add(m);
      }
      return em;
    }
    
    private EncampedModifier(String name, Type type) {
      this.name = name;
      this.type = type;
    }
    
    public enum Type {
      Bunkered, NoSecurity, RelationsWithLocals
    }
  }
  
  public static class Modifier {
    
    int       amount;
    Condition condition;
  }
  
  public static class Condition {}
  
}
