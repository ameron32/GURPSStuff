package com.ameron32.gurps.masscombat;

import java.util.Map;
import java.util.TreeMap;


public class BattleEnvironment {
  Map<String, Player> activePlayers = new TreeMap<String, Player>();
  
  public static class Player {
    Map<String, Force> participatingForces = new TreeMap<String, Force>();
  }
}
