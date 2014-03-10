package com.ameron32.tileactivitystub;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.qozix.tileview.TileView;

public class TileViewFragment extends Fragment {
  
  private TileView mTileView;
  
  public TileViewFragment() {}
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mTileView = new TileView(getActivity());
    mTileView.setLayoutParams(
        new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
    RelativeLayout rootView = 
        (RelativeLayout) inflater.inflate(R.layout.fragment_main, container, false);
    rootView.addView(mTileView, 0);
    return rootView;
  }
  

  @Override
  public void onPause() {
    super.onPause();
    mTileView.clear();
  }

  @Override
  public void onResume() {
    super.onResume();
    mTileView.resume();
  }

  @Override
  public void onDestroyView() {
    super.onDestroy();
    mTileView.destroy();
    mTileView = null;
  }

  public TileView getTileView(){
    return mTileView;
  }

  /**
   * This is a convenience method to moveToAndCenter after layout (which won't happen if called directly in onCreate
   * see https://github.com/moagrius/TileView/wiki/FAQ
   */
  public void frameTo( final double x, final double y ) {
    getTileView().post( new Runnable() {
      @Override
      public void run() {
        getTileView().moveToAndCenter( x, y );
      }     
    });   
  }
}