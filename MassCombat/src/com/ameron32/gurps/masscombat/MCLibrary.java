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

	  standardSpecialClassTypes.put(SpecialClass.Type.Air,
									SpecialClass.newInstance("Air Combat", 
															 SpecialClass.Type.Air));
	  standardSpecialClassTypes.put(SpecialClass.Type.Arm,
									SpecialClass.newInstance("Armor", 
															 SpecialClass.Type.Arm));
	  standardSpecialClassTypes.put(SpecialClass.Type.Art,
									SpecialClass.newInstance("Artillery", 
															 SpecialClass.Type.Art));
	  standardSpecialClassTypes.put(SpecialClass.Type.Cv,
									SpecialClass.newInstance("Cavalry", 
															 SpecialClass.Type.Cv));
	  standardSpecialClassTypes.put(SpecialClass.Type.C3I,
									SpecialClass.newInstance("Command, Control, Communications, and Intelligence", 
															 SpecialClass.Type.C3I));
	  standardSpecialClassTypes.put(SpecialClass.Type.Eng,
									SpecialClass.newInstance("Engineering", 
															 SpecialClass.Type.Eng));
	  standardSpecialClassTypes.put(SpecialClass.Type.F,
									SpecialClass.newInstance("Fire", 
															 SpecialClass.Type.F));
	  standardSpecialClassTypes.put(SpecialClass.Type.Nav,
									SpecialClass.newInstance("Naval", 
															 SpecialClass.Type.Nav));
    standardSpecialClassTypes.put(SpecialClass.Type.Rec,
	  SpecialClass.newInstance("Recon", 
	  SpecialClass.Type.Rec));
	standardSpecialClassTypes.put(SpecialClass.Type.T,
									SpecialClass.newInstance("Transport", 
															 SpecialClass.Type.T));
	  
	standardMobilityTypes.put(Mobility.Type.Foot, Mobility.newInstance("Foot", Mobility.Type.Foot));
	standardMobilityTypes.put(Mobility.Type.Mech, Mobility.newInstance("Mechanized", Mobility.Type.Mech));
	standardMobilityTypes.put(Mobility.Type.Motor, Mobility.newInstance("Motorized", Mobility.Type.Motor));
	standardMobilityTypes.put(Mobility.Type.Mtd, Mobility.newInstance("Mounted", Mobility.Type.Mtd));
	standardMobilityTypes.put(Mobility.Type.Coast, Mobility.newInstance("Coast", Mobility.Type.Coast));
	standardMobilityTypes.put(Mobility.Type.Sea, Mobility.newInstance("Sea", Mobility.Type.Sea));  
	standardMobilityTypes.put(Mobility.Type.FA, Mobility.newInstance("Fast Air", Mobility.Type.FA));
	standardMobilityTypes.put(Mobility.Type.SA, Mobility.newInstance("Slow Air", Mobility.Type.SA));
    
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
