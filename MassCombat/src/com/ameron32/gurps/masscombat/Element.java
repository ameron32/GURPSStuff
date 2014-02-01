package com.ameron32.gurps.masscombat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Element {
  public static final String TAG = "Element";
  public static final boolean DEBUG = true;
  
  
     String name = "";
   String customElementType = "";
  public String elementType = "";
   String description = "";
  
  float ts;
  float supportTS;
  final List<SpecialClass> specialClasses = new ArrayList<SpecialClass>();
  int wt;
  final List<Mobility> mobilities = new ArrayList<Mobility>();
  int costToRaiseK;
  int costToMaintainK;
  int tl;
  
  int carryingCapacity = 0;
  
   final List<Feature> features = new ArrayList<Feature>();
   float equipQuality = 0.0f;
   float troopQuality = 0.0f;
  
   /**
    * Intended factory for standard GURPS units from book.
    * 
    * @param elementType
    * @param ts
    * @param specialClasses
    * @param wt
    * @param mobility
    * @param costToRaiseK
    * @param costToMaintainK
    * @param tl
    * @return
    */
  public static Element newLibraryStandard(String elementType, float ts, float supportTS, SpecialClass[] specialClasses, int wt, Mobility[] mobilities, int costToRaiseK, int costToMaintainK, int tl) {
    Element standard = new Element(elementType, ts, supportTS, specialClasses, wt, mobilities, costToRaiseK, costToMaintainK, tl);
    return standard;
  }
  
  /**
   * Intended factory for custom GURPS units from book.
   * 
   * @param customElementType
   * @param elementType
   * @param description
   * @param ts
   * @param specialClasses
   * @param wt
   * @param mobility
   * @param costToRaiseK
   * @param costToMaintainK
   * @param tl
   * @param features
   * @param equipQuality
   * @param troopQuality
   * @return
   */
  public static Element newLibraryCustom(String customElementType, String elementType, String description, float ts, float supportTS, SpecialClass[] specialClasses, int wt, Mobility[] mobilities, int costToRaiseK, int costToMaintainK, int tl, Feature[] features, float equipQuality, float troopQuality) {
    Element custom = new Element(elementType, ts, supportTS, specialClasses, wt, mobilities, costToRaiseK, costToMaintainK, tl);
    custom.initCustom(customElementType, description, features, equipQuality, troopQuality);
    return custom;
  }
  
  /**
   * Intended factory for on-the-fly creation of new units LIKE an existing unit in the library.
   * 
   * @param element
   * @param name
   * @return
   */
  public static Element newCopyStandard(Element element, String name) {
    Element copy = new Element(element);
    copy.name = name;
    return copy;
  }
  
  // STANDARD CONSTRUCTOR
  private Element(String elementType, float ts, float supportTS, SpecialClass[] specialClasses, int wt, Mobility[] mobilities, int costToRaiseK, int costToMaintainK, int tl) {
    super();
    this.elementType = elementType;
    this.ts = ts;
    this.supportTS = supportTS;
    setSpecialClasses(specialClasses);
    this.wt = wt;
    setMobilities(mobilities);
    this.costToRaiseK = costToRaiseK;
    this.costToMaintainK = costToMaintainK;
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
    this.costToRaiseK = element.costToRaiseK;
    this.costToMaintainK = element.costToMaintainK;
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
  	
	String name;
	float customCostToRaiseMod;
	float customCostToMaintainMod;
	String description;
	float tsMod;
	// Requirement: not sure how to implement
	// Advantage: needs implementation of unique advantages
  }
  
  public static class SpecialClass {

	String name;
    SpecialClass.Type type;
    
    public static SpecialClass newInstance(String name, SpecialClass.Type type) {
      return new SpecialClass(type, name);
    }
    
    private SpecialClass(SpecialClass.Type type, String name) {
      this.type = type;
      this.name = name;
    }
    
    public enum Type {
      Air, Arm, Art, Cv, C3I, Eng, F, Nav, Rec, T, None
    }
  }
  
  public static class Mobility {
    String name;
    Mobility.Type type;
    
    public static Mobility newInstance(String name, Mobility.Type type) {
      return new Mobility(type, name);
    }
    
    private Mobility(Mobility.Type type, String name) {
      this.type = type;
      this.name = name;
    }
    
    public enum Type {
      Foot, Mech, Motor, Mtd, Coast, Sea, FA, SA, None
    }
  }
  
  public int getCarryingCapacity() {
    return carryingCapacity;
  }
}
