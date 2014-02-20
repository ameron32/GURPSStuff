package com.ameron32.gurps.masscombat;


public class Calculator {
  
  public static int findStrategySkillBonus(BattleEnvironment environment, Force forceA, Force forceB) {
    float tsA = forceA.getTroopStrengthWithoutSupport(environment);
    float tsB = forceB.getTroopStrengthWithoutSupport(environment);
    
    boolean isALarger = (tsA > tsB);
    
    float oddsFactor = (isALarger) ? tsA / tsB
        : tsB / tsA;
    
    int stratBonusRelativeTroopStrength = relativeStrategySkillBonus(oddsFactor);

    // TODO: superiority accumulation from all possible superiority classes
    // for each force against each other force (easier if just 2 forces)
    int stratBonusAllSuperiority;
    
    
    // TODO: I don't know what to return yet.
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


}
