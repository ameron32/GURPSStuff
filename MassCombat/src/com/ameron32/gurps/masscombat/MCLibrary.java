package com.ameron32.gurps.masscombat;

import java.util.Map;
import java.util.TreeMap;

import com.ameron32.gurps.masscombat.Element.Feature;
import com.ameron32.gurps.masscombat.Element.Mobility;
import com.ameron32.gurps.masscombat.Element.SpecialClass;
import com.ameron32.gurps.masscombat.modifiers.Modifications.Condition;
import com.ameron32.gurps.masscombat.modifiers.Modifications.Effect;
import com.ameron32.gurps.masscombat.modifiers.Modifications.EncampedModifier;
import com.ameron32.gurps.masscombat.modifiers.Modifications.MobileModifier;
import com.ameron32.gurps.masscombat.modifiers.Modifications.Modifier;

public class MCLibrary {
  public static final String TAG = "MCLibrary";
  public static final boolean DEBUG = false;
  
  
  static Map<SpecialClass.Type, SpecialClass> standardSpecialClassTypes = new TreeMap<SpecialClass.Type, SpecialClass>();
  static Map<SpecialClass.Type, SpecialClass> standardAntiSpecialClassTypes = new TreeMap<SpecialClass.Type, SpecialClass>();
  static Map<Mobility.Type, Mobility> standardMobilityTypes = new TreeMap<Mobility.Type, Mobility>();
  static Map<Feature.Type, Feature> standardFeatures = new TreeMap<Feature.Type, Feature>();
  static Map<Integer, Element> standardElements = new TreeMap<Integer, Element>();
  static Map<MobileModifier.Type, MobileModifier> standardMobileModifiers = new TreeMap<MobileModifier.Type, MobileModifier>();
  static Map<EncampedModifier.Type, EncampedModifier> standardEncampedModifiers = new TreeMap<EncampedModifier.Type, EncampedModifier>();
  static Map<Condition.Type, Condition> standardConditions = new TreeMap<Condition.Type, Condition>();
  static Map<Effect.Type, Effect> standardEffects = new TreeMap<Effect.Type, Effect>();
  
  // create standard Elements
  public MCLibrary() {
    
    // GENERATE CONDITIONS
    storeCondition(Condition.Type.AlwaysTrue, "Always True", new Condition() {
      
      @Override
      public boolean met(BattleEnvironment environment) {
        // always true
        return true;
      }
    });
    
    // GENERATE EFFECTS
    storeEffect(Effect.Type.DoNothing, "Do Nothing", new Effect() {
      
      @Override
      public void applyEffect(BattleEnvironment environment) {
        // do nothing
      }
    });
    
    // GENERATE MOBILE FORCE MODIFIERS
    storeMM(MobileModifier.Type.Flying, "Flying", null);
    // TODO: replace null with functioning "modifiers"
    
    // GENERATE ENCAMPED FORCE MODIFIERS
    storeEM(EncampedModifier.Type.Bunkered, "Bunkered", null);
    // TODO: replace null with functioning "modifiers"

    // GENERATE SPECIALCLASSES FROM MASS COMBAT BOOK
    storeSC(SpecialClass.Type.Air, "Air Combat");
    storeSC(SpecialClass.Type.Arm, "Armor");
    storeSC(SpecialClass.Type.Art, "Artillery");
    storeSC(SpecialClass.Type.Cv, "Cavalry");
    storeSC(SpecialClass.Type.C3I, "Command, Control, Communications, and Intelligence");
    storeSC(SpecialClass.Type.Eng, "Engineering");
    storeSC(SpecialClass.Type.F, "Fire");
    storeSC(SpecialClass.Type.Nav, "Naval");
    storeSC(SpecialClass.Type.Rec, "Recon");
    storeSC(SpecialClass.Type.T, "Transport");
    storeSC(SpecialClass.Type.None, "None");
    
    // GENERATE FEATURE TYPES FROM MASS COMBAT BOOK
    storeF(Feature.Type.Airborne, "Airborne", 20 , 20, null);
    storeF(Feature.Type.AllWeather, "All-Weather", 20 , 20, null);
    storeF(Feature.Type.Disloyal, "Disloyal", 0, 0, null);
    storeF(Feature.Type.Fanatic, "Fanatic", 0, 0, null);
    storeF(Feature.Type.Flagship, "Flagship", 0, 0, null);
    storeF(Feature.Type.Hero, "Hero", 0 , 0, null);
    storeF(Feature.Type.Hovercraft, "Hovercraft", 80 , 80, null);
    storeF(Feature.Type.Impetuous, "Impetuous",0 , 0, null);
    storeF(Feature.Type.Levy, "Levy", 0 , 0, null);
    storeF(Feature.Type.Marine, "Marine", 20 , 20, null);
    storeF(Feature.Type.Mercenary, "Mercenary", 0 , 0, null);
    storeF(Feature.Type.Night, "Night", 20 , 20, null);
    storeF(Feature.Type.Nocturnal, "Nocturnal", 0 , 0, null);
    storeF(Feature.Type.Neutralize, "Neutralize", 25, 25, null);
    storeF(Feature.Type.Sealed, "Sealed", 20 , 20, null);
    storeF(Feature.Type.SuperSoldier, "Super-Soldier", 200 , 200, null);
    storeF(Feature.Type.TerrainArctic, "Terrain: Arctic", 20 , 20, null);
    storeF(Feature.Type.TerrainDesert, "Terrain: Desert", 20 , 20, null);
    storeF(Feature.Type.TerrainJungle, "Terrain: Jungle", 20 , 20, null);
    storeF(Feature.Type.TerrainMountain, "Terrain: Mountain", 20 , 20, null);
    storeF(Feature.Type.TerrainSwampland, "Terrain: Swampland", 20 , 20, null);
    storeF(Feature.Type.TerrainWoodlands, "Terrain: Woodlands", 20 , 20, null);
  	  
    // GENERATE MOBILITY TYPES FROM MASS COMBAT BOOK
    storeM(Mobility.Type.None, "Must be transported", "la0");
    storeM(Mobility.Type.Foot, "Foot", "dr20, la10");
    storeM(Mobility.Type.Mech, "Mechanized", "dr80, la60");
    storeM(Mobility.Type.Motor, "Motorized", "gr120, dr60, la20");
    storeM(Mobility.Type.Mtd, "Mounted", "dr30, la15");
    storeM(Mobility.Type.Coast, "Coast", "co160");
    storeM(Mobility.Type.Sea, "Sea", "wa160");
    storeM(Mobility.Type.FA, "Fast Air", "dr20, la10");
    storeM(Mobility.Type.SA, "Slow Air", "dr20, la10");
      
    // GENERATE ELEMENTS FROM MASS COMBAT BOOK
    store("Balloon; (1); Air; 2; 0; 50K; 5K; 5");
    store("Bowmen; 2; F; 1; Foot; 40K; 8K; 2");
    store("Cavalry Pistols 4; 3; Cv,F; 2; Mtd; 100K; 20K; 4");
    store("Cavalry Pistols 5; 6; Cv,F; 2; Mtd; 100K; 20K; 5");
    store("Draft Team; 0; T2; 2; Mtd; 10K; 1K; 1");
    store("Heavy Artillery 2; (2.5); Art; 2; Foot; 100K; 10K; 2");
    store("Heavy Artillery 3; (5); Art; 2; Foot; 100K; 10K; 3");
    store("Heavy Artillery 4; (10); Art; 2; Foot; 100K; 10K; 4");
    store("Heavy Artillery 5; (20); Art; 2; Foot; 100K; 10K; 5");
    store("Heavy Cavalry; 5; Cv; 2; Mtd; 200K; 40K; 2");
    store("Heavy Chariots; 4; Cv; 4; Mtd; 160K; 32K; 1");
    store("Heavy Infantry; 4; –; 1; Foot; 40K; 8K; 2");
    store("Horse Archers; 2; Cv,F,Rec; 2; Mtd; 120K; 24K; 2");
    store("Horse Artillery; 10; Art; 2; Mtd; 150K; 30K; 5");
    store("Light Artillery 2; (1); Art; 1; Foot; 40K; 8K; 2");
    store("Light Artillery 3; (2); Art; 1; Foot; 40K; 8K; 3");
    store("Light Artillery 4; (4); Art; 1; Foot; 40K; 8K; 4");
    store("Light Artillery 5; (8); Art; 1; Foot; 40K; 8K; 5");
    store("Light Cavalry; 2; Cv,Rec; 2; Mtd; 100K; 20K; 2");
    store("Light Chariots; 2; Cv,F; 4; Mtd; 100K; 20K; 1");
    store("Light Infantry; 2; Rec; 1; Foot; 40K; 8K; 1");
    store("Line Infantry; 3; F; 1; Foot; 30K; 6K; 5");
    store("Medium Cavalry; 3; Cv,F; 2; Mtd; 150K; 30K; 2");
    store("Medium Infantry; 3; –; 1; Foot; 30K; 6K; 1");
    store("Miners 2; 0.5; Eng; 1; Foot; 30K; 6K; 2");
    store("Miners 3; 1; Eng; 1; Foot; 30K; 6K; 3");
    store("Miners 4; 2; Eng; 1; Foot; 30K; 6K; 4");
    store("Miners 5; 4; Eng; 1; Foot; 30K; 6K; 5");
    store("Mounts; 0; T1; 1; Mtd; 60K; 12K; 1");
    store("Musketeers; 2; F; 1; Foot; 30K; 6K; 4");
    store("Pikemen; 4; (Cv); 1; Foot; 60K; 12K; 2"); // test me
    store("Skirmishers; 2; F,Rec; 1; Foot; 30K; 6K; 5");
    store("Stone-Age Warriors; 1; Rec; 1; Foot; 25K; 5K; 0");
    store("War Beast; 20; Arm; 4; Foot; 400K; 80K; 2");
    
    store("Amphibious Warriors; 2; Nav; 1; Coast,Foot; 60K; 12K; 0");
    store("Aquatic Warriors; 2; Nav; 1; Coast; 30K; 6K; 0");
    store("Battle Mages; 5; Art,C3I,F,Rec; 1; Foot; 200K; 40K; 0");
    store("Beasts; 1; Cv,Rec; 2; Mtd; 50K; 10K; 0");
    store("Flying Beasts; 1; Air,T1; 2; SA; 120K; 40K; 0");
    store("Flying Cavalry; 2; Air,F; 2; SA; 600K; 60K; 1");
    store("Flying Infantry; 2; Air,Rec; 1; Foot,SA; 60K; 20K; 0");
    store("Flying Leviathan; 150; Air,T10; –; SA; 1M; 40K; 0");
    store("Flying Mages; 5; Air,Art,C3I,F,Rec; 1; Foot,SA; 300K; 60K; 1");
    store("Giant Flying Monster; 15; Air; 8; SA; 800K; 40K; 0");
    store("Giant Monster; 40; Arm; 8; Foot; 800K; 40K; 0");
    store("Giants; 20; Arm,Art,Eng; 8; Foot; 800K; 40K; 0");
    store("Leviathan; 250; Nav,T10; –; Sea; 10M; 400K; 0");
    store("Ogres; 8; –; 4; Foot; 80K; 8K; 0");
    store("Sea Monster; 20; Nav,T1; –; Sea; 1M; 20K; 0");
    store("Titan; 400; Arm,Art,Eng; –; Foot; 800K; 40K; 0");

    store("Boat; 0; T1; 1; Coast; 5K; 0.5K; 1");
    store("Brig; 6; Nav,Art,T6; –; Sea; 150K; 15K; 4");
    store("Cog; 4; T5; –; Sea; 75K; 7.5K; 3");
    store("Frigate; 150; Nav,Art,T4; –; Sea; 1M; 50K; 5");
    store("Galleon; 30; Nav,Art,T6; –; Sea; 750K; 75K; 4");
    store("Large Boat; 0; T2; 1; Coast; 10K; 1K; 1");
    store("Light Galley; 3; T1; –; Coast; 70K; 14K; 1");
    store("Longship; 3; T7; –; Coast; 50K; 10K; 2");
    store("Merchant Galley; 4; Nav,T5; –; Coast; 600K; 60K; 2");
    store("Ship-of-the-Line; 300; Nav,Art,T10; –; Sea; 4M; 400K; 5");
    store("War Galley; 10; Nav,T3; –; Coast; 500K; 100K; 2");
  }
  
  
  
  int elementSequenceKey = 0;
  void store(String elementCode) {
    
    standardElements.put(elementSequenceKey, Element.Auto.generateLibFrom(elementCode));
    elementSequenceKey++;
  }
  
  /**
   * Stores a SpecialClass and an AntiSpecialClass for each defined SpecialClass Type
   * 
   * @param type
   * @param name
   */
  void storeSC(SpecialClass.Type type, String name) {
    
    // store the SpecialClass
    standardSpecialClassTypes.put(type, SpecialClass.newInstance(name, type, false));
    
    // store the AntiSpecialClass
    standardAntiSpecialClassTypes.put(type, SpecialClass.newInstance(name, type, true));
  }
  
  void storeF(Feature.Type type, String name, int costToRaise, int costToMaintain, String description) {
    
    standardFeatures.put(type, Feature.newInstance(name, type, costToRaise, costToMaintain, description));
  }
  
  /**
   * Store a Mobility for retrieval later.
   * 
   * @param type
   * @param name
   * @param speeds in MilesPerDay: off-road, standard road, good road 
   */
  Mobility storeM(Mobility.Type type, String name, String sSpeeds) {

    int[] speeds = Element.Auto.generateSpeedsFromInput(sSpeeds);
    Mobility m = Mobility.newInstance(name, type, speeds);
    standardMobilityTypes.put(type, m);
    return m;
  }
  
  void storeMM(MobileModifier.Type type, String name, Modifier... modifiers) {
    
    standardMobileModifiers.put(type, MobileModifier.newMobileModifier(name, type, modifiers));
  }
  
  void storeEM(EncampedModifier.Type type, String name, Modifier... modifiers) {
    
    standardEncampedModifiers.put(type, EncampedModifier.newEncampedModifier(name, type, modifiers));
  }
  
  void storeCondition(Condition.Type type, String name, Condition condition) {
    
    standardConditions.put(type, condition);
  }
  
  void storeEffect(Effect.Type type, String name, Effect effect) {
    
    standardEffects.put(type, effect);
  }
  
  
  
  
  public Element getElement(int key) {
    
    return standardElements.get(key);
  }
  
  public int getStandardElementsSize() {
    
    return standardElements.size();
  }

  public static SpecialClass getSpecialClass(SpecialClass.Type type) {
    
    return standardSpecialClassTypes.get(type);
  }
  
  public static SpecialClass getAntiSpecialClass(SpecialClass.Type type) {
    
    return standardAntiSpecialClassTypes.get(type);
  }
  
  public static Mobility[] getMobility(Mobility.Type... types) {
    
    Mobility[] mobilities = new Mobility[types.length];
    for (int i = 0; i < types.length; i++) {
      mobilities[i] = standardMobilityTypes.get(types[i]);
    }
    return mobilities;
  }
  
}
