package com.ameron32.gurps.masscombat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ameron32.gurps.masscombat.Terrain.InvalidTerrainException;

import android.util.Log;


public class Element {
  public static final String TAG = "Element";
  private static final boolean mDebug = true;
  

    String name = "";
   String customElementType = "";
  public String elementType = "";
   String description = "";
   
  boolean isCustomElement() {
    return !(customElementType.equalsIgnoreCase(""));
  }
  
  float ts;
  float supportTS;
  final List<SpecialClass> specialClasses = new ArrayList<SpecialClass>();
  int wt;
  final List<Mobility> mobilities = new ArrayList<Mobility>();
  int costToRaise;
  int costToMaintain;
  int tl;
  
  int carryingCapacity = 0;
  
   final List<Feature> features = new ArrayList<Feature>();
   float equipQuality = 0.0f;
   float troopQuality = 0.0f;
  
  /**
   * Intended factory for standard GURPS-MC elements from book.
   * 
   * @param elementType: GURPS defined element name
   * @param ts: Troop Strength
   * @param specialClasses: SpecialClasses to add
   * @param wt: 
   * @param mobility: All mobility types/speeds usable by this element
   * @param costToRaise: Cost to raise $ (K should be x1,000, M should be x1,000,000)
   * @param costToMaintain: Cost to maintain $ (K should be x1,000, M should be x1,000,000)
   * @param tl: Tech Level
   * 
   * @return Returns standard GURPS-MC elements with typical parameters from GURPS book.
   */
  public static Element newLibraryStandard(String elementType, float ts, float supportTS, SpecialClass[] specialClasses, int wt, Mobility[] mobilities, int costToRaise, int costToMaintain, int tl) {
    Element standard = new Element(elementType, ts, supportTS, specialClasses, wt, mobilities, costToRaise, costToMaintain, tl);
    return standard;
  }
  
  /**
   * Intended factory for custom GURPS-MC elements from book.
   * 
   * @param customElementType
   * @param elementType: GURPS defined element name
   * @param description
   * @param ts: Troop Strength
   * @param specialClasses: SpecialClasses to add
   * @param wt: 
   * @param mobility: All mobility types/speeds usable by this element
   * @param costToRaise: Cost to raise $ (K should be x1,000, M should be x1,000,000)
   * @param costToMaintain: Cost to maintain $ (K should be x1,000, M should be x1,000,000)
   * @param tl: Tech Level
   * @param features
   * @param equipQuality
   * @param troopQuality
   * 
   * @return Returns a custom GURPS-MC element with special parameters from GURPS book.
   */
  public static Element newLibraryCustom(String customElementType, String elementType, String description, float ts, float supportTS, SpecialClass[] specialClasses, int wt, Mobility[] mobilities, int costToRaise, int costToMaintain, int tl, Feature[] features, float equipQuality, float troopQuality) {
    Element custom = new Element(elementType, ts, supportTS, specialClasses, wt, mobilities, costToRaise, costToMaintain, tl);
    custom.initCustom(customElementType, description, features, equipQuality, troopQuality);
    return custom;
  }
  
  /**
   * Intended factory for on-the-fly creation of new units LIKE an existing unit
   * in the library. Designed to be edited after using this method to copy it.
   * 
   * @param element: Source element to create the element from.
   * @param name: Name of the new element to be created.
   * 
   * @return Returns a new GURPS-MC element based on the source with a new name.
   */
  public static Element newCopyStandard(Element element, String name) {
    Element copy = new Element(element);
    copy.name = name;
    return copy;
  }
  
  // STANDARD CONSTRUCTOR
  private Element(String elementType, float ts, float supportTS, SpecialClass[] specialClasses, int wt, Mobility[] mobilities, int costToRaise, int costToMaintain, int tl) {
    super();
    this.elementType = elementType;
    this.ts = ts;
    this.supportTS = supportTS;
    setSpecialClasses(specialClasses);
    this.wt = wt;
    setMobilities(mobilities);
    this.costToRaise = costToRaise;
    this.costToMaintain = costToMaintain;
    this.tl = tl;
  }
  
  // CLONE CONSTRUCTOR
  private Element(Element element) {
    super();
    this.name = element.name;
    this.customElementType = element.customElementType;
    this.elementType = element.elementType;
    this.description = element.description;
    this.ts = element.ts;
    this.supportTS = element.supportTS;
    setSpecialClasses(element.specialClasses);
    this.wt = element.wt;
    setMobilities(element.mobilities);
    this.costToRaise = element.costToRaise;
    this.costToMaintain = element.costToMaintain;
    this.tl = element.tl;
    setFeatures(element.features);
    this.equipQuality = element.equipQuality;
    this.troopQuality = element.troopQuality;
  }

  private void setSpecialClasses(SpecialClass...specialClasses) {
    if (specialClasses != null && specialClasses.length != 0) 
      this.specialClasses.addAll(Arrays.asList(specialClasses));
  }
  
  private void setSpecialClasses(List<SpecialClass> specialClasses) {
    if (specialClasses != null && specialClasses.size() != 0) 
      this.specialClasses.addAll(specialClasses);
  }
  
  private void setMobilities(Mobility...mobilities) {
    if (mobilities != null && mobilities.length != 0) 
      this.mobilities.addAll(Arrays.asList(mobilities));
  }
  
  private void setMobilities(List<Mobility> mobilities) {
    if (mobilities != null && mobilities.size() != 0) 
      this.mobilities.addAll(mobilities);
  }
  
  public void setCarryingCapacity(int carryingCapacity) { this.carryingCapacity = carryingCapacity; }
  
  private void initCustom(String customElementType, String description, Feature[] features, float equipQuality, float troopQuality) {
    this.customElementType = customElementType;
    this.description = description;
    setFeatures(features);
    this.equipQuality = equipQuality;
    this.troopQuality = troopQuality;
  }
  
  private void setFeatures(Feature...features) {
    if (features != null && features.length != 0) 
      this.features.addAll(Arrays.asList(features));
  }
  
  private void setFeatures(List<Feature> features) {
    if (features != null && features.size() != 0) 
      this.features.addAll(features);
  }



	public static final float QUALITY_TROOP_ELITE = 1.0f;
	public static final float QUALITY_TROOP_GOOD = 0.5f;
	public static final float QUALITY_TROOP_AVERAGE = 0.0f;
	public static final float QUALITY_TROOP_INFERIOR = -0.5f;

	public static final float QUALITY_EQUIPMENT_VERY_FINE = 1.5f;
	public static final float QUALITY_EQUIPMENT_FINE = 1.0f;
	public static final float QUALITY_EQUIPMENT_GOOD = 0.5f;
	public static final float QUALITY_EQUIPMENT_BASIC = 0.0f;
	public static final float QUALITY_EQUIPMENT_POOR = -0.25f;

	
	
	
	
	
	
	
	
  // SUBCLASSES
  //****************************************

  public static class Feature {
  	// NOTE: Neutralizes is currently "AntiSpecialClass"
    
    String       name;
    Feature.Type type;
    int          customCostToRaise;
    int          customCostToMaintain;
    String       description = "";
    float        tsMod = 1.0f;
    // Requirement: not sure how to implement
    // Advantage: needs implementation of unique advantages
    
    /**
     * 
     * @param name User-displayed name of Feature
     * @param type Type category of Feature
     * @param customCostToRaise Amount to modify Cost-to-Raise (e.g. 80 for +80%)
     * @param customCostToMaintain Amount to modify Cost-to-Maintain (e.g. -40 for -40%)
     * 
     * @return Returns a new Feature with input parameters.
     */
    public static Feature newInstance(String name, Feature.Type type, int customCostToRaise, int customCostToMaintain, String description) {
      Feature f = new Feature(type, name, customCostToRaise, customCostToMaintain, description);
      return f;
    }
    
    private Feature(Feature.Type type, String name, int customCostToRaise, int customCostToMaintain, String description) {
      this.type = type;
      this.name = name;
      this.customCostToRaise = customCostToRaise;
      this.customCostToMaintain = customCostToMaintain;
      this.description = (description == null) ? "" : description;
    }
    
    public enum Type {
      Airborne, AllWeather, Disloyal, Fanatic, Flagship, Hero, 
      Hovercraft, Impetuous, Levy, Marine, Mercenary, 
   // Neutralize: see Anti-SpecialClass
      Neutralize,
      Night, Nocturnal, Sealed, SuperSoldier, 
      TerrainArctic, TerrainDesert, TerrainJungle, TerrainMountain, TerrainSwampland, TerrainWoodlands 
    }
    
    void setTsMod(float tsMod) { this.tsMod = tsMod; }
    
    @Override
    public String toString() {
      // TODO elaborate Feature toString
      return name + "/[more]";
    }
  }
  
  public static class SpecialClass {

    String name;
    SpecialClass.Type type;
    boolean isAnti;
    
    public static SpecialClass newInstance(String name, SpecialClass.Type type, boolean isAnti) {
      SpecialClass sc = new SpecialClass(type, name);
      sc.setAnti(isAnti);
      return sc; 
    }
    
    public void setAnti(boolean isAnti) {
      this.isAnti = isAnti;
    }
    
    private SpecialClass(SpecialClass.Type type, String name) {
      this.type = type;
      this.name = name;
    }
    
    public enum Type {
      Air, Arm, Art, Cv, C3I, Eng, F, Nav, Rec, T, None
    }
    
    @Override
    public String toString() {
      return ((isAnti) ? "Anti-" + name + "/" + "(" + type.name() + ")" : name + "/" + type.name());
    }
  }
  
  public static class Mobility {
    
    public static int SPEED_GOOD_ROAD = 0;
    public static int SPEED_DIRT_ROAD = 1;
    public static int SPEED_LAND      = 2;
    public static int SPEED_COAST     = 3;
    public static int SPEED_OPENWATER = 4;
    
    String name;
    Mobility.Type type;
    int[] speeds;
    
    public static Mobility newInstance(String name, Mobility.Type type, int[] speeds) {
      return new Mobility(type, name, speeds);
    }
    
    private Mobility(Mobility.Type type, String name, int[] speeds) {
      this.type = type;
      this.name = name;
      this.speeds = speeds;
    }
    
    public enum Type {
      Foot, Mech, Motor, Mtd, Coast, Sea, FA, SA, None
    }
    
    @Override
    public String toString() {
      return name + "/" + type.name();
    }
    
    int determineBestSpeed(Terrain overTerrain) {
      int value = 0;
      try {
        if (overTerrain.hasRoad()) {
          value = Terrain.getRoadValue(overTerrain);
        }
      } catch (InvalidTerrainException te) {
        if (mDebug) {
          MassCombatActivity.log(Log.ERROR, "Terrain [" + overTerrain.toString() + "] Failed.");
        }
        te.printStackTrace();
      }
      return speeds[value];
    }
  }
  
  public int getCarryingCapacity() {
    return carryingCapacity;
  }
  
  public boolean isSpecialClass(SpecialClass.Type type) {
    for (SpecialClass sc : specialClasses) {
      if (sc.type == type) return true;
    }
    return false;
  }
  
  
  
  @Override
  public String toString() {
    if (isCustomElement()) { 
      return "Element(Custom)\n  [name=" + name + "\n   customElementType=" + customElementType + "\n   elementType="
        + elementType + "\n   description=" + description + "\n   ts=" + ts + "\n   supportTS=" + supportTS
        + "\n   specialClasses=" + Arrays.toString(specialClasses.toArray()) + "\n   wt=" + wt + "\n   mobilities=" + Arrays.toString(mobilities.toArray())
        + "\n   costToRaise=" + costToRaise + "\n   costToMaintain=" + costToMaintain + "\n   tl=" + tl
        + "\n   carryingCapacity=" + carryingCapacity + "\n   features=" + Arrays.toString(features.toArray()) + "\n   equipQuality="
        + equipQuality + "\n   troopQuality=" + troopQuality + "]";
    } else {
      return "Element(Standard)\n  [name=" + name + "\n   elementType="
          + elementType + "\n   description=" + description + "\n   ts=" + ts + "\n   supportTS=" + supportTS
          + "\n   specialClasses=" + Arrays.toString(specialClasses.toArray()) + "\n   wt=" + wt + "\n   mobilities=" + Arrays.toString(mobilities.toArray())
          + "\n   costToRaise=" + costToRaise + "\n   costToMaintain=" + costToMaintain + "\n   tl=" + tl
          + "\n   carryingCapacity=" + carryingCapacity + "\n   features=" + Arrays.toString(features.toArray()) + "\n   equipQuality="
          + equipQuality + "\n   troopQuality=" + troopQuality + "]";
    }
  }
  
  
  public static class Auto {
    
    
    /**
     * Hand it a human-readable string and it builds and hands back the corresponding Element.
     * Example "Bowmen; 2; F; 1; Foot; 40K; 8K; 2"
     * 
     * Currently requires exactly 8 properties (or 7 ';'). Otherwise, will return null.
     * 
     * @param elementCode requires ';' for property separator and ',' for list-property
     * @return Element created from string. Or null if malformed String.
     */
    public static Element generateLibFrom(String elementCode) {
      final int element = 0;
      final int ts = 1;
      final int specialClasses = 2;
      final int wt = 3;
      final int mob = 4;
      final int raise = 5;
      final int maintain = 6;
      final int tl = 7;
      
      String mElement;
      float mTS;
      float mSupportTS;
      SpecialClass[] mSpecialClasses;
      int mCarryingCapacity;
      int mWT;
      Mobility[] mMob;
      int mRaise;
      int mMaintain;
      int mTL;
      
      
      // convert elementCode string into pieces for processing
      String[] pieces = elementCode.split(";");
      for (int i = 0; i < pieces.length; i++) { pieces[i] = pieces[i].trim(); }
      
      
      // malformed String error checking
      if (pieces.length != 8) return null;
      
      
      // DEBUG
      if (mDebug) {
        StringBuilder sb = new StringBuilder();
        for (String piece: pieces) { sb.append(piece + "\n"); }
        Log.d(TAG, sb.toString());
      }
  
      
      // ELEMENT
      mElement = pieces[element];
      
      
      // TS & SUPPORTTS
      // decode ts & supportTS
      mTS = 0.0f;
      mSupportTS = 0.0f;
      if (pieces[ts].contains("+")) {
        String[] tsFormula = pieces[ts].split("+");
        for (String s : tsFormula) {
          s = s.trim();
          boolean stop = false;
          
          // test for standard TS
          if (!stop) {
            try {
              mTS += Float.parseFloat(s);
              stop = true;
            }
            catch (NumberFormatException e) {}
          }
          
          // test for support TS
          if (!stop) {
            if (s.contains("(") && s.contains(")")) {
              s = s.substring(1, s.length() - 1);
              try {
                mSupportTS += Float.parseFloat(s);
                stop = true;
              }
              catch (NumberFormatException e) {}
            }
          }
          
          // log failure
          if (!stop) {
            String error = "decode TS found an irregular number";
            Log.d(TAG, error);
          }
        }
      } else {
        String s = pieces[ts];
        s = s.trim();
        boolean stop = false;
        
        // test for standard TS
        if (!stop) {
          try {
            mTS += Float.parseFloat(s);
            stop = true;
          }
          catch (NumberFormatException e) {}
        }
        
        // test for support TS
        if (!stop) {
          if (s.contains("(") && s.contains(")")) {
            s = s.substring(1, s.length() - 1);
            try {
              mSupportTS += Float.parseFloat(s);
              stop = true;
            }
            catch (NumberFormatException e) {}
          }
        }
        
        // log failure
        if (!stop) {
          String error = "decode TS found an irregular number";
          Log.d(TAG, error);
        }
      }
  
  
      // SPECIALCLASSES
      // split specialClasses if more than 1 separated by comma
      String[] sSpecialClasses;
      List<SpecialClass.Type> scSpecialClasses = new ArrayList<SpecialClass.Type>();
      List<SpecialClass.Type> scAntiSpecialClasses = new ArrayList<SpecialClass.Type>();
      sSpecialClasses = pieces[specialClasses].split(",");
//      scSpecialClasses = new SpecialClass.Type[sSpecialClasses.length];
      mCarryingCapacity = 0;
      for (int k = 0; k < sSpecialClasses.length; k++) {
        sSpecialClasses[k] = sSpecialClasses[k].trim();
        if (sSpecialClasses[k].startsWith("T")) {
          scSpecialClasses.add(SpecialClass.Type.valueOf("T"));
          mCarryingCapacity = Integer.decode(sSpecialClasses[k].substring(1));
        } 
        else if (sSpecialClasses[k].equalsIgnoreCase("–")) {
          scSpecialClasses.add(SpecialClass.Type.None);
        } 
        else {
          if (sSpecialClasses[k].contains("(") && sSpecialClasses[k].contains(")")) {
            // TODO fix me
            scAntiSpecialClasses.add(SpecialClass.Type.valueOf(sSpecialClasses[k].substring(1, sSpecialClasses[k].length() - 1)));
          } 
          else {
            scSpecialClasses.add(SpecialClass.Type.valueOf(sSpecialClasses[k]));
          }
        }
      }
      List<SpecialClass> combiner = new ArrayList<SpecialClass>();
      for (int i = 0; i < scSpecialClasses.size(); i++) {
        combiner.add(MCLibrary.getSpecialClass(scSpecialClasses.get(i)));
      }
      for (int j = 0; j < scAntiSpecialClasses.size(); j++) {
        combiner.add(MCLibrary.getAntiSpecialClass(scAntiSpecialClasses.get(j)));
      }
      mSpecialClasses = combiner.toArray(new SpecialClass[combiner.size()]);
      
      
      // WT
      // uncarriable WT
      mWT = 0;
      if (pieces[wt].equalsIgnoreCase("–")) {
        mWT = 10000;
      } else {
        mWT = Integer.decode(pieces[wt]);
      }
  
      
      // MOBILITIES
      // branch for T + carryingCapacity
      String[] sMobilities = pieces[mob].split(",");
      Mobility.Type[] mobilities = new Mobility.Type[sMobilities.length];
      for (int l = 0; l < sMobilities.length; l++) {
        sMobilities[l] = sMobilities[l].trim();
        if (sMobilities[l].equalsIgnoreCase("0")) {
          sMobilities[l] = "None";
        }
        mobilities[l] = Mobility.Type.valueOf(sMobilities[l]);
      }
      mMob = MCLibrary.getMobility(mobilities);
      
      
      // RAISE
      mRaise = 0;
      mRaise = convertIfNeeded(pieces[raise]);
      
      
      // MAINTAIN
      mMaintain = 0;
      mMaintain = convertIfNeeded(pieces[maintain]);
      
          
      // TL
      mTL = Integer.decode(pieces[tl]);
      
      
      // Generate the LibraryElement
      Element e = Element.newLibraryStandard(
          mElement, mTS, mSupportTS, mSpecialClasses, mWT, 
          mMob, mRaise, mMaintain, mTL);
      e.setCarryingCapacity(mCarryingCapacity);
      return e;
    }
    
  
    
    /**
     * Helper method.
     * @param intToConvert String containing a number with trailing K or M 
     * to convert to 1,000 or 1,000,000. Or no K, that's fine too.
     * @return Returns cropped Integer multiplied by 1,000 or 1,000,000
     */
    static int convertIfNeeded(String intToConvert) {
      if (intToConvert.contains("M") || intToConvert.contains("m")) {
        intToConvert = intToConvert.substring(0, intToConvert.length() - 1);
        return Math.round((Float.parseFloat(intToConvert) * 1000000));
      }
      if (intToConvert.contains("K") || intToConvert.contains("k")) {
        intToConvert = intToConvert.substring(0, intToConvert.length() - 1);
        return Math.round((Float.parseFloat(intToConvert) * 1000));
      }
      return Math.round((Float.parseFloat(intToConvert)) * 1);  
    }
    
    
    static int[] generateSpeedsFromInput(String input) {
      /* REMOVE
          int offroadOrOpenwater = -1;
          int dirtRoad = -1;
          int goodRoad = -1;
          
          // reverse input
          int[] revInput = new int[input.length];
          for (int i = 0; i < input.length; i++) {
            revInput[input.length - i - 1] = input[i]; 
          }
          input = revInput;
          revInput = null;
          
          if (input.length > 0) 
            offroadOrOpenwater = dirtRoad = goodRoad = input[0];
          if (input.length > 1) 
            dirtRoad = goodRoad = input[1];
          if (input.length > 2) 
            goodRoad = input[2];
        */
  
 
      
      int goodRoad = 0;
      int dirtRoad = 0;
      int land = 0;
      int coast = 0;
      int water = 0;
      
      String[] sSpeeds = input.split(",");
      for (int i = sSpeeds.length - 1; i > -1; i--) {
        String sSpeed = sSpeeds[i];
        sSpeed = sSpeed.trim();
        String type = sSpeed.substring(0, 2);
        int speed = Integer.decode(sSpeed.substring(2));
        
        if (type.equalsIgnoreCase("la")) land = dirtRoad = goodRoad = speed;
        if (type.equalsIgnoreCase("co")) coast = water = speed;
        if (type.equalsIgnoreCase("dr")) dirtRoad = goodRoad = speed;
        if (type.equalsIgnoreCase("gr")) goodRoad = speed;
        if (type.equalsIgnoreCase("wa")) water = speed;
        
        if (mDebug)
            MassCombatActivity.log(Log.INFO, goodRoad + "," + dirtRoad + "," + land + "," + coast + "," + water);
      }
      
      if (mDebug)
          MassCombatActivity.log(Log.INFO, "**********************************");
      return new int[] { goodRoad, dirtRoad, land, coast, water }; 
    }
    
  }
}
