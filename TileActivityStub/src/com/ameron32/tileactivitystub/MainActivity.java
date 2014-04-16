package com.ameron32.tileactivitystub;

import com.ameron32.tileactivitystub.interfaces.TileViewFragmentListener;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

public class MainActivity extends Activity implements TileViewFragmentListener {
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    if (savedInstanceState == null) {
      getFragmentManager().beginTransaction().add(R.id.container, SmartTileViewFragment.newInstance("cavesNew")).commit();
    }
  }
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) { return true; }
    return super.onOptionsItemSelected(item);
  }
  
  @Override
  public void onResume() {
    super.onResume();
//    test();
  }
  
  private void test() {
    ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar1);
    String[] params = { 
        Environment.getExternalStorageDirectory().getPath() 
            + "/Download/wallpaper-1186854.jpg",
        Environment.getExternalStorageDirectory().getPath() 
            + "/Download/convert-1186854.jpg"
    };
    new ImageConverterTask(this, progressBar).execute(params);
  }
  
  @Override
  public void onPause() {
    super.onPause();
//    finish();
  }
  
  /**
   * A placeholder fragment containing a simple view.
   */
  public static class PlaceholderFragment extends Fragment {
    
    public PlaceholderFragment() {}
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.fragment_main, container, false);
      return rootView;
    }
  }
  
}
