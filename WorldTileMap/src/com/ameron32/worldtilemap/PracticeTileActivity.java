package com.ameron32.worldtilemap;

import android.os.*;
import android.widget.*;
import com.qozix.tileview.*;

public class PracticeTileActivity extends MainActivity {
	
	int maxX, maxY;
	// need size
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		TileView tileView = getTileView();
		
		tileView.setSize( maxX, maxY );
		
		tileView.setScaleLimits(0, 2);
		
		// lets center all markers both horizontally and vertically
		tileView.setMarkerAnchorPoints( -0.5f, -0.5f );
		
		int[] p = new int[] { maxX / 2, maxY / 2 };
		placeMarker( R.drawable.ic_launcher, p[0], p[1]);
		
		frameTo( p[0], p[1] );
		
		tileView.setScale( 0.5 );
		
		
	}
	
	
	private void placeMarker( int resId, double x, double y ) {
		ImageView imageView = new ImageView(this);
		imageView.setImageResource(resId);
		getTileView().addMarker(imageView, x, y);
	}
}
