package com.ameron32.gurps.masscombat;

import java.util.Map;
import java.util.TreeMap;

import com.ameron32.gurps.masscombat.Element.Feature;
import com.ameron32.gurps.masscombat.Element.Mobility;
import com.ameron32.gurps.masscombat.Element.SpecialClass;
import com.ameron32.gurps.masscombat.modifiers.Modifications.SimpleModifier;
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
  
  
  public static MCLibrary newInstance() {
    return new MCLibrary();
  }
  
  // create standard Elements
  private MCLibrary() {
    
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
    storeMM(MobileModifier.Type.Flying, "Flying", 
        new SimpleModifier()); // TODO: adjust placeholder SimpleModifier
    // TODO: replace null with functioning "modifiers"
    
    // GENERATE ENCAMPED FORCE MODIFIERS
    storeEM(EncampedModifier.Type.Bunkered, "Bunkered", 
        new SimpleModifier()); // TODO: adjust placeholder SimpleModifier
    // TODO: replace null with functioning "modifiers"

    // GENERATE SPECIALCLASSES FROM MASS COMBAT BOOK
    storeSpecialClass(SpecialClass.Type.Air, "Air Combat");
    storeSpecialClass(SpecialClass.Type.Arm, "Armor");
    storeSpecialClass(SpecialClass.Type.Art, "Artillery");
    storeSpecialClass(SpecialClass.Type.Cv, "Cavalry");
    storeSpecialClass(SpecialClass.Type.C3I, "Command, Control, Communications, and Intelligence");
    storeSpecialClass(SpecialClass.Type.Eng, "Engineering");
    storeSpecialClass(SpecialClass.Type.F, "Fire");
    storeSpecialClass(SpecialClass.Type.Nav, "Naval");
    storeSpecialClass(SpecialClass.Type.Rec, "Recon");
    storeSpecialClass(SpecialClass.Type.T, "Transport");
    storeSpecialClass(SpecialClass.Type.None, "None");
    
    // GENERATE FEATURE TYPES FROM MASS COMBAT BOOK
    storeFeature(Feature.Type.Airborne, "Airborne", 20 , 20, null);
    storeFeature(Feature.Type.AllWeather, "All-Weather", 20 , 20, null);
    storeFeature(Feature.Type.Disloyal, "Disloyal", 0, 0, null);
    storeFeature(Feature.Type.Fanatic, "Fanatic", 0, 0, null);
    storeFeature(Feature.Type.Flagship, "Flagship", 0, 0, null);
    storeFeature(Feature.Type.Hero, "Hero", 0 , 0, null);
    storeFeature(Feature.Type.Hovercraft, "Hovercraft", 80 , 80, null);
    storeFeature(Feature.Type.Impetuous, "Impetuous",0 , 0, null);
    storeFeature(Feature.Type.Levy, "Levy", 0 , 0, null);
    storeFeature(Feature.Type.Marine, "Marine", 20 , 20, null);
    storeFeature(Feature.Type.Mercenary, "Mercenary", 0 , 0, null);
    storeFeature(Feature.Type.Night, "Night", 20 , 20, null);
    storeFeature(Feature.Type.Nocturnal, "Nocturnal", 0 , 0, null);
    storeFeature(Feature.Type.Neutralize, "Neutralize", 25, 25, null);
    storeFeature(Feature.Type.Sealed, "Sealed", 20 , 20, null);
    storeFeature(Feature.Type.SuperSoldier, "Super-Soldier", 200 , 200, null);
    storeFeature(Feature.Type.TerrainArctic, "Terrain: Arctic", 20 , 20, null);
    storeFeature(Feature.Type.TerrainDesert, "Terrain: Desert", 20 , 20, null);
    storeFeature(Feature.Type.TerrainJungle, "Terrain: Jungle", 20 , 20, null);
    storeFeature(Feature.Type.TerrainMountain, "Terrain: Mountain", 20 , 20, null);
    storeFeature(Feature.Type.TerrainSwampland, "Terrain: Swampland", 20 , 20, null);
    storeFeature(Feature.Type.TerrainWoodlands, "Terrain: Woodlands", 20 , 20, null);
  	  
    // GENERATE MOBILITY TYPES FROM MASS COMBAT BOOK
    storeMobility(Mobility.Type.None, "Must be transported", "la0");
    storeMobility(Mobility.Type.Foot, "Foot", "dr20, la10");
    storeMobility(Mobility.Type.Mech, "Mechanized", "dr80, la60");
    storeMobility(Mobility.Type.Motor, "Motorized", "gr120, dr60, la20");
    storeMobility(Mobility.Type.Mtd, "Mounted", "dr30, la15");
    storeMobility(Mobility.Type.Coast, "Coast", "co160");
    storeMobility(Mobility.Type.Sea, "Sea", "wa160");
    storeMobility(Mobility.Type.FA, "Fast Air", "dr20, la10");
    storeMobility(Mobility.Type.SA, "Slow Air", "dr20, la10");
      
    // GENERATE ELEMENTS FROM MASS COMBAT BOOK
    storeElement("Balloon; (1); Air; 2; 0; 50K; 5K; 5");
    storeElement("Bowmen; 2; F; 1; Foot; 40K; 8K; 2");
    storeElement("Cavalry Pistols 4; 3; Cv,F; 2; Mtd; 100K; 20K; 4");
    storeElement("Cavalry Pistols 5; 6; Cv,F; 2; Mtd; 100K; 20K; 5");
    storeElement("Draft Team; 0; T2; 2; Mtd; 10K; 1K; 1");
    storeElement("Heavy Artillery 2; (2.5); Art; 2; Foot; 100K; 10K; 2");
    storeElement("Heavy Artillery 3; (5); Art; 2; Foot; 100K; 10K; 3");
    storeElement("Heavy Artillery 4; (10); Art; 2; Foot; 100K; 10K; 4");
    storeElement("Heavy Artillery 5; (20); Art; 2; Foot; 100K; 10K; 5");
    storeElement("Heavy Cavalry; 5; Cv; 2; Mtd; 200K; 40K; 2");
    storeElement("Heavy Chariots; 4; Cv; 4; Mtd; 160K; 32K; 1");
    storeElement("Heavy Infantry; 4; –; 1; Foot; 40K; 8K; 2");
    storeElement("Horse Archers; 2; Cv,F,Rec; 2; Mtd; 120K; 24K; 2");
    storeElement("Horse Artillery; 10; Art; 2; Mtd; 150K; 30K; 5");
    storeElement("Light Artillery 2; (1); Art; 1; Foot; 40K; 8K; 2");
    storeElement("Light Artillery 3; (2); Art; 1; Foot; 40K; 8K; 3");
    storeElement("Light Artillery 4; (4); Art; 1; Foot; 40K; 8K; 4");
    storeElement("Light Artillery 5; (8); Art; 1; Foot; 40K; 8K; 5");
    storeElement("Light Cavalry; 2; Cv,Rec; 2; Mtd; 100K; 20K; 2");
    storeElement("Light Chariots; 2; Cv,F; 4; Mtd; 100K; 20K; 1");
    storeElement("Light Infantry; 2; Rec; 1; Foot; 40K; 8K; 1");
    storeElement("Line Infantry; 3; F; 1; Foot; 30K; 6K; 5");
    storeElement("Medium Cavalry; 3; Cv,F; 2; Mtd; 150K; 30K; 2");
    storeElement("Medium Infantry; 3; –; 1; Foot; 30K; 6K; 1");
    storeElement("Miners 2; 0.5; Eng; 1; Foot; 30K; 6K; 2");
    storeElement("Miners 3; 1; Eng; 1; Foot; 30K; 6K; 3");
    storeElement("Miners 4; 2; Eng; 1; Foot; 30K; 6K; 4");
    storeElement("Miners 5; 4; Eng; 1; Foot; 30K; 6K; 5");
    storeElement("Mounts; 0; T1; 1; Mtd; 60K; 12K; 1");
    storeElement("Musketeers; 2; F; 1; Foot; 30K; 6K; 4");
    storeElement("Pikemen; 4; (Cv); 1; Foot; 60K; 12K; 2"); // test me
    storeElement("Skirmishers; 2; F,Rec; 1; Foot; 30K; 6K; 5");
    storeElement("Stone-Age Warriors; 1; Rec; 1; Foot; 25K; 5K; 0");
    storeElement("War Beast; 20; Arm; 4; Foot; 400K; 80K; 2");
    
    storeElement("Amphibious Warriors; 2; Nav; 1; Coast,Foot; 60K; 12K; 0");
    storeElement("Aquatic Warriors; 2; Nav; 1; Coast; 30K; 6K; 0");
    storeElement("Battle Mages; 5; Art,C3I,F,Rec; 1; Foot; 200K; 40K; 0");
    storeElement("Beasts; 1; Cv,Rec; 2; Mtd; 50K; 10K; 0");
    storeElement("Flying Beasts; 1; Air,T1; 2; SA; 120K; 40K; 0");
    storeElement("Flying Cavalry; 2; Air,F; 2; SA; 600K; 60K; 1");
    storeElement("Flying Infantry; 2; Air,Rec; 1; Foot,SA; 60K; 20K; 0");
    storeElement("Flying Leviathan; 150; Air,T10; –; SA; 1M; 40K; 0");
    storeElement("Flying Mages; 5; Air,Art,C3I,F,Rec; 1; Foot,SA; 300K; 60K; 1");
    storeElement("Giant Flying Monster; 15; Air; 8; SA; 800K; 40K; 0");
    storeElement("Giant Monster; 40; Arm; 8; Foot; 800K; 40K; 0");
    storeElement("Giants; 20; Arm,Art,Eng; 8; Foot; 800K; 40K; 0");
    storeElement("Leviathan; 250; Nav,T10; –; Sea; 10M; 400K; 0");
    storeElement("Ogres; 8; –; 4; Foot; 80K; 8K; 0");
    storeElement("Sea Monster; 20; Nav,T1; –; Sea; 1M; 20K; 0");
    storeElement("Titan; 400; Arm,Art,Eng; –; Foot; 800K; 40K; 0");

    storeElement("Boat; 0; T1; 1; Coast; 5K; 0.5K; 1");
    storeElement("Brig; 6; Nav,Art,T6; –; Sea; 150K; 15K; 4");
    storeElement("Cog; 4; T5; –; Sea; 75K; 7.5K; 3");
    storeElement("Frigate; 150; Nav,Art,T4; –; Sea; 1M; 50K; 5");
    storeElement("Galleon; 30; Nav,Art,T6; –; Sea; 750K; 75K; 4");
    storeElement("Large Boat; 0; T2; 1; Coast; 10K; 1K; 1");
    storeElement("Light Galley; 3; T1; –; Coast; 70K; 14K; 1");
    storeElement("Longship; 3; T7; –; Coast; 50K; 10K; 2");
    storeElement("Merchant Galley; 4; Nav,T5; –; Coast; 600K; 60K; 2");
    storeElement("Ship-of-the-Line; 300; Nav,Art,T10; –; Sea; 4M; 400K; 5");
    storeElement("War Galley; 10; Nav,T3; –; Coast; 500K; 100K; 2");
  }
  
  
  
  
  
  
  
  
  
  
  void storeCondition(Condition.Type type, String name, Condition condition) {
    
    standardConditions.put(type, condition);
  }
  
  void storeEffect(Effect.Type type, String name, Effect effect) {
    
    standardEffects.put(type, effect);
  }
  
  
  void storeMM(MobileModifier.Type type, String name, Modifier... modifiers) {
    
    standardMobileModifiers.put(type, MobileModifier.newMobileModifier(name, type, modifiers));
  }
  
  void storeEM(EncampedModifier.Type type, String name, Modifier... modifiers) {
    
    standardEncampedModifiers.put(type, EncampedModifier.newEncampedModifier(name, type, modifiers));
  }
  
  /**
   * Stores a SpecialClass and an AntiSpecialClass for each defined SpecialClass Type
   * 
   * @param type
   * @param name
   */
  void storeSpecialClass(SpecialClass.Type type, String name) {
    
    // store the SpecialClass
    standardSpecialClassTypes.put(type, SpecialClass.newInstance(name, type, false));
    
    // store the AntiSpecialClass
    standardAntiSpecialClassTypes.put(type, SpecialClass.newInstance(name, type, true));
  }
  
  /**
   * Stores a Feature for each defined Feature Type
   * 
   * @param type
   * @param name
   * @param costToRaise
   * @param costToMaintain
   * @param description
   */
  void storeFeature(Feature.Type type, String name, int costToRaise, int costToMaintain, String description) {
    
    standardFeatures.put(type, Feature.newInstance(name, type, costToRaise, costToMaintain, description));
  }
  
  /**
   * Store a Mobility for retrieval later.
   * 
   * @param type
   * @param name
   * @param speeds in MilesPerDay: off-road, standard road, good road 
   */
  Mobility storeMobility(Mobility.Type type, String name, String sSpeeds) {

    int[] speeds = Element.Auto.generateSpeedsFromInput(sSpeeds);
    Mobility m = Mobility.newInstance(name, type, speeds);
    standardMobilityTypes.put(type, m);
    return m;
  }
  
  int elementSequenceKey = 0;
  void storeElement(String elementCode) {
    
    standardElements.put(elementSequenceKey, Element.Auto.generateLibFrom(elementCode));
    elementSequenceKey++;
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
