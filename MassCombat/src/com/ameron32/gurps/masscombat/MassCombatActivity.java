package com.ameron32.gurps.masscombat;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MassCombatActivity extends Activity {
  public static final String TAG = "MassCombatActivity";
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    

  }
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }
  
  
  
  
  public static void log(int logType, String s) {
    switch (logType) {
    case Log.DEBUG:
      Log.d(TAG, s);
      break;
    case Log.ERROR:
      Log.e(TAG, s);
      break;
    case Log.INFO:
      Log.i(TAG, s);
      break;
    case Log.VERBOSE:
      Log.v(TAG, s);
      break;
    case Log.WARN:
      Log.w(TAG, s);
      break;
    default:
      Log.i(TAG, s);
    }
  }
}
