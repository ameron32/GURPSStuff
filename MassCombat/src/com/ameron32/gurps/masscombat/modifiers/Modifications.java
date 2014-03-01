package com.ameron32.gurps.masscombat.modifiers;

import java.util.ArrayList;
import java.util.List;

import com.ameron32.gurps.masscombat.BattleEnvironment;

public class Modifications {
  
  public static class MobileModifier {
    
    String               name;
    MobileModifier.Type  type;
    final List<Modifier> modifiers = new ArrayList<Modifier>();
    
    public static MobileModifier newMobileModifier(String name, Type type, Modifier... modifiers) {
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
    
    public int amount(BattleEnvironment environment) {
      int amount = 0;
      for (Modifier m : modifiers) {
        amount += m.apply(environment);
      }
      return amount;
    }
  }
  
  public static class EncampedModifier {
    
    String                name;
    EncampedModifier.Type type;
    final List<Modifier>  modifiers = new ArrayList<Modifier>();
    
    public static EncampedModifier newEncampedModifier(String name, Type type, Modifier... modifiers) {
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
    
    public int amount(BattleEnvironment environment) {
      int amount = 0;
      for (Modifier m : modifiers) {
        amount += m.apply(environment);
      }
      return amount;
    }
  }
  
  public abstract static class Modifier {

    BattleEnvironment environment;
    Condition[] condition;
    
    // int for SimpleModifier only
    int         amount;
    // Effect[] for CEModifier only
    Effect[]    effect;
    
    public abstract int apply(BattleEnvironment environment);
    
    protected boolean allConditionsMet(BattleEnvironment environment) {
      for (Condition c : condition) {
        if (!c.met(environment)) return false;
      }
      return true;
    }
    
    protected void applyAllEffects(BattleEnvironment environment) {
      for (Effect e : effect) {
        e.applyEffect(environment);
      }
    }
  }
  
  public static class SimpleModifier extends Modifier {

    public SimpleModifier() {}
    
    // input null
    // expect int return
    public int apply(BattleEnvironment environment) {
      if (allConditionsMet(environment)) return amount;
      else return 0;
    }
  }
  
  public static class CEModifier extends Modifier {
    
    // input environment to modify
    // expect return 0
    public int apply(BattleEnvironment environment) {
      if (allConditionsMet(environment)) {
        applyAllEffects(environment);
      }
      return 0;
    }
  }
  
  // TODO: fill in Condition class
  public static abstract class Condition {
    
    public abstract boolean met(BattleEnvironment environment);
    
    public enum Type {
      AlwaysTrue
    }
  }
  
  public static abstract class Effect {
    
    public abstract void applyEffect(BattleEnvironment environment);
    
    public enum Type {
      DoNothing
    }
  }
}
