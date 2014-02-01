package com.ameron32.gurps.masscombat;

import java.util.Map;
import java.util.TreeMap;

import android.util.Log;

import com.ameron32.gurps.masscombat.Element.Mobility;
import com.ameron32.gurps.masscombat.Element.SpecialClass;

public class MCLibrary {
public static final String TAG = "MCLibrary";
public static final boolean DEBUG = false;
  
  
  Map<SpecialClass.Type, SpecialClass> standardSpecialClassTypes = new TreeMap<SpecialClass.Type, SpecialClass>();
  Map<Mobility.Type, Mobility> standardMobilityTypes = new TreeMap<Mobility.Type, Mobility>();
  
  Map<Integer, Element> standardElements = new TreeMap<Integer, Element>();
  
  // create standard Elements
  public MCLibrary() {

    standardSpecialClassTypes.put(SpecialClass.Type.Air, 
        SpecialClass.newInstance("Air Combat", SpecialClass.Type.Air));
    standardSpecialClassTypes.put(SpecialClass.Type.Arm, 
        SpecialClass.newInstance("Armor", SpecialClass.Type.Arm));
    standardSpecialClassTypes.put(SpecialClass.Type.Art, 
        SpecialClass.newInstance("Artillery", SpecialClass.Type.Art));
    standardSpecialClassTypes.put(SpecialClass.Type.Cv, 
        SpecialClass.newInstance("Cavalry", SpecialClass.Type.Cv));
    standardSpecialClassTypes.put(SpecialClass.Type.C3I, 
        SpecialClass.newInstance("Command, Control, Communications, and Intelligence", SpecialClass.Type.C3I));
    standardSpecialClassTypes.put(SpecialClass.Type.Eng, 
        SpecialClass.newInstance("Engineering", SpecialClass.Type.Eng));
    standardSpecialClassTypes.put(SpecialClass.Type.F, 
        SpecialClass.newInstance("Fire", SpecialClass.Type.F));
    standardSpecialClassTypes.put(SpecialClass.Type.Nav, 
        SpecialClass.newInstance("Naval", SpecialClass.Type.Nav));
    standardSpecialClassTypes.put(SpecialClass.Type.Rec, 
        SpecialClass.newInstance("Recon", SpecialClass.Type.Rec));
    standardSpecialClassTypes.put(SpecialClass.Type.T, 
        SpecialClass.newInstance("Transport", SpecialClass.Type.T));
    standardSpecialClassTypes.put(SpecialClass.Type.None, 
        SpecialClass.newInstance("None", SpecialClass.Type.None));
  	  
    standardMobilityTypes.put(Mobility.Type.None, 
        Mobility.newInstance("Must be transported", Mobility.Type.None));
  	standardMobilityTypes.put(Mobility.Type.Foot, 
  	    Mobility.newInstance("Foot", Mobility.Type.Foot));
  	standardMobilityTypes.put(Mobility.Type.Mech, 
  	    Mobility.newInstance("Mechanized", Mobility.Type.Mech));
  	standardMobilityTypes.put(Mobility.Type.Motor, 
  	    Mobility.newInstance("Motorized", Mobility.Type.Motor));
  	standardMobilityTypes.put(Mobility.Type.Mtd, 
  	    Mobility.newInstance("Mounted", Mobility.Type.Mtd));
  	standardMobilityTypes.put(Mobility.Type.Coast, 
  	    Mobility.newInstance("Coast", Mobility.Type.Coast));
  	standardMobilityTypes.put(Mobility.Type.Sea, 
  	    Mobility.newInstance("Sea", Mobility.Type.Sea));  
  	standardMobilityTypes.put(Mobility.Type.FA, 
  	    Mobility.newInstance("Fast Air", Mobility.Type.FA));
  	standardMobilityTypes.put(Mobility.Type.SA, 
  	    Mobility.newInstance("Slow Air", Mobility.Type.SA));
      

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
    store("Light Artillery; (1); Art; 1; Foot; 40K; 8K; 2");
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
    // store("Pikemen; 4; (Cv); 1; Foot; 60K; 12K; 2");
    // needs (Cv) resolved
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
  
  /**
   * Hand it a human-readable string and it builds and hands back the corresponding Element.
   * Example "Bowmen; 2; F; 1; Foot; 40K; 8K; 2"
   * 
   * @param elementCode
   * @return
   */
  Element lib(String elementCode) {
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
    
    
    // DEBUG
    if (DEBUG) {
      StringBuilder sb = new StringBuilder();
      for (String piece: pieces) { sb.append(piece + "\n"); }
      Log.d(TAG, sb.toString());
    }

    
    // ELEMENT
    mElement = pieces[element];
    
    
    // TS & SUPPORTTS
    // decode ts & supportTS
    mTS = 0;
    mSupportTS = 0;
    if (pieces[ts].contains("+")) {
      String[] tsFormula = pieces[ts].split("+");
      for (String s : tsFormula) {
        s = s.trim();
        boolean stop = false;
        
        // test for standard TS
        if (!stop) {
          try {
            mTS = Float.parseFloat(s);
            stop = true;
          }
          catch (NumberFormatException e) {}
        }
        
        // test for support TS
        if (!stop) {
          if (s.contains("(") && s.contains(")")) {
            s = s.substring(1, s.length() - 1);
            mSupportTS = Float.parseFloat(s);
            stop = true;
          }
        }
        
        // log failure
        if (!stop) {
          Log.d(TAG, "decode TS found an irregular number");
        }
      }
    }


    // SPECIALCLASSES
    // split specialClasses if more than 1 separated by comma
    String[] sSpecialClasses;
    SpecialClass.Type[] scSpecialClasses;
    sSpecialClasses = pieces[specialClasses].split(",");
    scSpecialClasses = new SpecialClass.Type[sSpecialClasses.length];
    mCarryingCapacity = 0;
    for (int k = 0; k < sSpecialClasses.length; k++) {
      sSpecialClasses[k] = sSpecialClasses[k].trim();
      if (sSpecialClasses[k].startsWith("T")) {
        scSpecialClasses[k] = SpecialClass.Type.valueOf("T");
        mCarryingCapacity = Integer.decode(sSpecialClasses[k].substring(1));
      } 
      else if (sSpecialClasses[k].equalsIgnoreCase("–")) {
        scSpecialClasses[k] = SpecialClass.Type.None;
      } 
      else {
        scSpecialClasses[k] = SpecialClass.Type.valueOf(sSpecialClasses[k]);
      }
    }
    mSpecialClasses = getSpecialClasses(scSpecialClasses);
    
    
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
    mMob = getMobility(mobilities);
    
    
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
  
  SpecialClass[] getSpecialClasses(SpecialClass.Type... types) {
    SpecialClass[] classes = new SpecialClass[types.length];
    for (int i = 0; i < types.length; i++) {
      classes[i] = standardSpecialClassTypes.get(types[i]);
    }
    return classes;
  }
  
  Mobility[] getMobility(Mobility.Type... types) {
    Mobility[] mobilities = new Mobility[types.length];
    for (int i = 0; i < types.length; i++) {
      mobilities[i] = standardMobilityTypes.get(types[i]);
    }
    return mobilities;
  }
  
  int key = 0;
  void store(String elementCode) {
    standardElements.put(key, lib(elementCode));
    key++;
  }
  
  public Element getElement(int key) {
    return standardElements.get(key);
  }
  
  public int getStandardElementsSize() {
    return standardElements.size();
  }

  
  
  
  
  
  
  /**
   * Helper method.
   * @param intToConvert String containing a number with trailing K or M 
   * to convert to 1,000 or 1,000,000. Or no K, that's fine too.
   * @return cropped Integer multiplied by 1,000 or 1,000,000
   */
  int convertIfNeeded(String intToConvert) {
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
}
