package com.ameron32.gurps.masscombat;

import com.ameron32.gurps.masscombat.Element.SpecialClass;


public class Calculator {
  
  public static int findStrategySkillBonusFromRelativeTsForForceA(BattleEnvironment environment, Force forceA, Force forceB) {
    
    float tsA = forceA.getTroopStrengthWithoutSupport(environment);
    float tsB = forceB.getTroopStrengthWithoutSupport(environment);
    float oddsFactor = tsA / tsB;
    
    int stratBonusRelativeTroopStrength = relativeStrategySkillBonus(oddsFactor);
    return stratBonusRelativeTroopStrength;
  }
  
  public static int findStrategySkillBonusFromSuperiorityForForceA(BattleEnvironment environment, Force forceA, Force forceB) {
    // TODO: determine isEncounterBattle, isBadTerrain
    boolean isEncounterBattle = false;
    boolean isBadTerrain = false;
    // TODO: determine Engineering, Naval, Recon Superiority
    boolean includeEngineering = false;
    boolean includeNaval = false;
    boolean includeRecon = false;
    
    int skillBonus = 0;
    for (SpecialClass sc : forceA.getSpecialClasses()) {
      SpecialClass.Type type = sc.type;
      skillBonus += specialClassSuperiority(type, forceA, forceB, isEncounterBattle, isBadTerrain, includeEngineering, includeNaval, includeRecon);
    }
    return skillBonus;
  }
  
  private static int relativeStrategySkillBonus(float oddsFactor) {
    
    if (oddsFactor > 50.0f) return 20;
    if (oddsFactor > 30.0f) return 18;
    if (oddsFactor > 20.0f) return 16;
    if (oddsFactor > 15.0f) return 14;
    if (oddsFactor > 10.0f) return 12;
    if (oddsFactor >  7.0f) return 10;
    if (oddsFactor >  5.0f) return  8;
    if (oddsFactor >  3.0f) return  6;
    if (oddsFactor >  2.0f) return  4;
    if (oddsFactor >  1.5f) return  2;
    return 0;
  }
  
  private static int specialClassSuperiority(SpecialClass.Type type, Force forceA, Force forceB, 
      boolean isEncounterBattle, boolean isBadTerrain,
      boolean includeEngineering, boolean includeNaval, boolean includeRecon) {
    
    float forceASTS = forceA.getTroopStrengthForSuperiority(type);
    float forceBSTS = forceB.getTroopStrengthForSuperiority(type);
    
    float oddsFactorFromType = forceASTS / forceBSTS;
    
    int value = 0;
    switch (type) {
    // full-time advantage
    case Arm:
    case F:
      value = strategySkillBonusFromTable(oddsFactorFromType);
      break;
      
    // less bonus
    case Air:
    case Art:
    case C3I:
      value = (isEncounterBattle) 
          ? strategySkillBonusFromTable(oddsFactorFromType) - 1
          : strategySkillBonusFromTable(oddsFactorFromType);
      break;
    
    // restrictions apply
    case Cv:
      // in bad terrain, do not count mounted or motorized mobility /sigh
      if (isBadTerrain) {
        forceASTS = forceA.getTroopStrengthForSuperiorityExcluding(type, Element.Mobility.Type.Mtd, Element.Mobility.Type.Motor);
        forceBSTS = forceB.getTroopStrengthForSuperiorityExcluding(type, Element.Mobility.Type.Mtd, Element.Mobility.Type.Motor);
      }
      oddsFactorFromType = forceASTS / forceBSTS;
      
      value = strategySkillBonusFromTable(oddsFactorFromType);
      break;
      
    // TODO: review special circumstances. 
    // remove TODO when handled here or in findStrategySkillBonusFromSuperiorityForForceA
    case Eng:
      if (!includeEngineering) { break; } // or return 0?
      else { /* fall through */ }
    case Nav:
      if (!includeNaval) { break; } // or return 0?
      else { /* fall through */ }
    case Rec:
      if (!includeRecon) { break; } // or return 0?
      else { /* fall through */ }
      
      value = strategySkillBonusFromTable(oddsFactorFromType);
      break;
      
    // rare or never, currently never
    case T:
      break;
    case None:
      break;
    default:
      break;
    }
    
    return (value > 0) ? value : 0;
  }
  
  private static int strategySkillBonusFromTable(float oddsFactor) {
    
    if (oddsFactor > 5.0f) return 3;
    if (oddsFactor > 3.0f) return 2;
    if (oddsFactor > 2.0f) return 1;
    return 0;
  }
  
  /**
   * 
   * @param numberOfElementsInSmallerForce The number of elements in the smaller fighting force.
   * @return Length of time consumed during the entirety of battle in minutes.
   */
  public static int determineLengthOfBattleFromTable(int numberOfElementsInSmallerForce) {
    
    if (numberOfElementsInSmallerForce > 9999) return 60 * 4;
    if (numberOfElementsInSmallerForce >  999) return 60 * 2;
    if (numberOfElementsInSmallerForce >   99) return 60 * 1;
    if (numberOfElementsInSmallerForce >    9) return 30;
    if (numberOfElementsInSmallerForce >    0) return 15;
    return 0;
  }

  /**
   * 
   * @param margin
   * @param isWinner true if this PB Shift is for the winner.
   * @return An int of the Winner's Position Bonus Shift.  
   */
  public static int determineCombatResultsPBShift(int margin, boolean isWinner) {
    
    if (isWinner) {
      if (margin > 19) return 4;
      if (margin > 9) return 3;
      if (margin > 3) return 2;
      if (margin >  0) return 1;
    }
    return 0;
  }
  
  /**
   *  
   * 
   * @param margin 
   * @return A float[2] containing Loser's Casualties first [0], then Winner's Casualties second [1].
   */
  public static float[] determineCasualties(int margin) {
    
    float loserCasualties = 0.0f;
    float winnerCasualties = 0.0f;
    
    if (margin > 19) {
      loserCasualties = 40.0f;
      winnerCasualties = 0.0f;
    } else if (margin > 14) {
      loserCasualties = 35.0f;
      winnerCasualties = 0.0f;
    } else if (margin >  9) {
      loserCasualties = 30.0f;
      winnerCasualties = 5.0f;
    } else if (margin >  6) {
      loserCasualties = 25.0f;
      winnerCasualties = 5.0f;
    } else if (margin >  3) {
      loserCasualties = 20.0f;
      winnerCasualties = 10.0f;
    } else if (margin >  0) {
      loserCasualties = 15.0f;
      winnerCasualties = 10.0f;
    } else if (margin == 0){
      loserCasualties = 10.0f;
      winnerCasualties = 10.0f;
    }
    
    return new float[] { loserCasualties, winnerCasualties };
  }
  
}
