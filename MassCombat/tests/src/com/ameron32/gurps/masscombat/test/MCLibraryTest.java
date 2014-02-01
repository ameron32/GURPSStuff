package com.ameron32.gurps.masscombat.test;

import android.test.AndroidTestCase;
import android.util.Log;

import com.ameron32.gurps.masscombat.MCLibrary;


public class MCLibraryTest extends AndroidTestCase {
  public static final String TAG = "MCLibrary Test";
  
  MCLibrary mcLibrary;
  
  protected void setUp()
      throws Exception {
    super.setUp();
    mcLibrary = new MCLibrary();
  }
  
  protected void tearDown()
      throws Exception {
    super.tearDown();
    mcLibrary = null;
  }
  
  public void testMCLibrary() {
//    fail("Not yet implemented"); // TODO
  }
  
  public void testGetElement() {
    for (int i = 0; i < mcLibrary.getStandardElementsSize(); i++) {
      Log.d(TAG, mcLibrary.getElement(i).elementType);
      System.out.println(mcLibrary.getElement(i).toString());
    }
//    fail("Not yet implemented"); // TODO
  }
  
}
