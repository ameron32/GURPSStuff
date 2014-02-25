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
    
    int skillBonus = 0;
    for (SpecialClass sc : forceA.getSpecialClasses()) {
      SpecialClass.Type type = sc.type;
      skillBonus += specialClassSuperiority(type, forceA, forceB, isEncounterBattle, isBadTerrain);
    }
    return skillBonus;
  }
  
  static int relativeStrategySkillBonus(float oddsFactor) {
    
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
  
  static int specialClassSuperiority(SpecialClass.Type type, Force forceA, Force forceB, boolean isEncounterBattle, boolean isBadTerrain) {
    
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
      
    // TODO: special circumstances
    case Eng:
      break;
    case Nav:
      break;
    case Rec:
      break;
      
    // rare or never
    case T:
      break;
    case None:
      break;
    default:
      break;
    }
    
    return (value > 0) ? value : 0;
  }
  
  static int strategySkillBonusFromTable(float oddsFactor) {
    
    if (oddsFactor > 5.0f) return 3;
    if (oddsFactor > 3.0f) return 2;
    if (oddsFactor > 2.0f) return 1;
    return 0;
  }


}
