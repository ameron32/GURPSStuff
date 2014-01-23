package com.ameron32.gurps.masscombat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Element {
  
     String name = "";
   String customElementType = "";
  String elementType = "";
   String description = "";
  
  int ts;
  final List<SpecialClass> specialClasses = new ArrayList<SpecialClass>();
  int wt;
  Mobility mobility;
  int costToRaiseK;
  int costToMaintainK;
  int tl;
  
   final List<Feature> features = new ArrayList<Feature>();
   float equipQuality = 0.0f;
   float troopQuality = 0.0f;
  
  public static Element newLibraryStandard(String elementType, int ts, SpecialClass[] specialClasses, int wt, Mobility mobility, int costToRaiseK, int costToMaintainK, int tl) {
    Element standard = new Element(elementType, ts, specialClasses, wt, mobility, costToRaiseK, costToMaintainK, tl);
    return standard;
  }
  
  public static Element newLibraryCustom(String customElementType, String elementType, String description, int ts, SpecialClass[] specialClasses, int wt, Mobility mobility, int costToRaiseK, int costToMaintainK, int tl, Feature[] features, float equipQuality, float troopQuality) {
    Element custom = new Element(elementType, ts, specialClasses, wt, mobility, costToRaiseK, costToMaintainK, tl);
    custom.initCustom(customElementType, description, features, equipQuality, troopQuality);
    return custom;
  }
  
  public static Element newCopyStandard(Element element, String name) {
    Element copy = new Element(element);
    copy.name = name;
    return copy;
  }
  
  // STANDARD CONSTRUCTOR
  private Element(String elementType, int ts, SpecialClass[] specialClasses, int wt, Mobility mobility, int costToRaiseK, int costToMaintainK, int tl) {
    super();
    this.elementType = elementType;
    this.ts = ts;
    setSpecialClasses(specialClasses);
    this.wt = wt;
    this.mobility = mobility;
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
    setSpecialClasses(element.specialClasses);
    this.wt = element.wt;
    this.mobility = element.mobility;
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
      Air, Arm, Art, Cv, C3I, Eng, F, Nav, Rec, T
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
      Foot, Mech, Motor, Mtd, Coast, Sea, FA, SA
    }
  }
}
