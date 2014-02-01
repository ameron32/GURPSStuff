package com.ameron32.worldtilemap;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

import com.qozix.tileview.TileView;

public class MainActivity extends Activity
{
    
	TileView tileView;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        tileView = new TileView(this);
		setContentView(tileView);
    }
	
	@Override
	public void onPause() {
		super.onPause();
		tileView.clear();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		tileView.resume();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		tileView.destroy();
		tileView = null;
	}
	
	public TileView getTileView() {
		return tileView;
	}
	
	public void frameTo( final double x, final double y ) {
		getTileView().post( new Runnable() { @Override public void run() {
			getTileView().moveToAndCenter( x, y);
		}});
	}
}
