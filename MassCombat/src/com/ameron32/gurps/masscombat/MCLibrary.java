package com.ameron32.gurps.masscombat;

import java.util.Map;
import java.util.TreeMap;

import com.ameron32.gurps.masscombat.Element.Mobility;
import com.ameron32.gurps.masscombat.Element.SpecialClass;

public class MCLibrary {

  static Map<SpecialClass.Type, SpecialClass> standardSpecialClassTypes = new TreeMap<SpecialClass.Type, SpecialClass>();
  static Map<Mobility.Type, Mobility> standardMobilityTypes = new TreeMap<Mobility.Type, Mobility>();
  
  static Map<Integer, Element> standardElements = new TreeMap<Integer, Element>();
  
  // create standard Elements
  static {
    standardSpecialClassTypes.put(SpecialClass.Type.Rec, SpecialClass.newInstance("Recon", SpecialClass.Type.Rec));
    standardMobilityTypes.put(Mobility.Type.Foot, Mobility.newInstance("Foot", Mobility.Type.Foot));
    
    standardElements.put(1, Element.newLibraryStandard(
        "Light Infantry", 2, 
        new SpecialClass[] { getSpecialClass(SpecialClass.Type.Rec) },
        1, getMobility(Mobility.Type.Foot), 
        40, 8, 1));
  }
  
  static SpecialClass getSpecialClass(SpecialClass.Type type) {
    return standardSpecialClassTypes.get(type);
  }
  
  static Mobility getMobility(Mobility.Type type) {
    return standardMobilityTypes.get(type);
  }
}
