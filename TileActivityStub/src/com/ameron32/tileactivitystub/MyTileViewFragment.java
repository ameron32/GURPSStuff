package com.ameron32.tileactivitystub;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.qozix.layouts.ZoomPanLayout;
import com.qozix.tileview.TileView;
import com.qozix.tileview.geom.PositionManager;

public class MyTileViewFragment extends TileViewFragment {
  
  public static MyTileViewFragment newInstance() {
    return new MyTileViewFragment();
  }
  
  public MyTileViewFragment() {}

  private void initTileView() {
    
    // multiple references
    TileView tileView = getTileView();

    // size of original image at 100% scale
    tileView.setSize( 4015, 4057 );

    // detail levels
    tileView.addDetailLevel( 1.000f, "tiles-old/fantasy/1000/%col%_%row%.jpg", "samples-old/middle-earth.jpg");
    tileView.addDetailLevel( 0.500f, "tiles-old/fantasy/500/%col%_%row%.jpg", "samples-old/middle-earth.jpg");
    tileView.addDetailLevel( 0.250f, "tiles-old/fantasy/250/%col%_%row%.jpg", "samples-old/middle-earth.jpg");
    tileView.addDetailLevel( 0.125f, "tiles-old/fantasy/125/%col%_%row%.jpg", "samples-old/middle-earth.jpg");

    // allow scaling past original size
    tileView.setScaleLimits( 0, 2 );

    // lets center all markers both horizontally and vertically
    tileView.setMarkerAnchorPoints( -0.5f, -0.5f );

    // individual markers
    placeMarker( R.drawable.fantasy_elves, 1616, 1353 );
    placeMarker( R.drawable.fantasy_humans, 2311, 2637 );
    placeMarker( R.drawable.fantasy_dwarves, 2104, 701 );
    placeMarker( R.drawable.fantasy_rohan, 2108, 1832 );
    placeMarker( R.drawable.fantasy_troll, 3267, 1896 );

    // frame the troll
    frameTo( 3267, 1896 );

    // scale down a little
    tileView.setScale( 0.5 );
  }
  
  private MotionEvent storedEvent;
  private void initTileViewNew() {
    
    // multiple references
    TileView tileView = getTileView();
//    tileView.setOnTouchListener(new View.OnTouchListener() {
//      
//      @Override
//      public boolean onTouch(View v, MotionEvent event) {
//        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
//          storedEvent = event;
//        } else if (event.getActionMasked() == MotionEvent.ACTION_UP 
//            && storedEvent != null) {
////        log("Enter onTouch()");
//          
//          long threeSeconds = 1000 * 3;
//          double allowance = 100.0d;
//          
//          long heldTime = event.getEventTime() - storedEvent.getEventTime();
//
//          float x = Math.abs(storedEvent.getX(0) - event.getX(0));
//          float y = Math.abs(storedEvent.getY(0) - event.getY(0));
//          
//          double distance = Math.sqrt((x * x) + (y * y));
//          
//          log("Distance: " + distance + " / HeldTime: " + heldTime);
//          
//          placeMarker( R.drawable.fantasy_humans, event.getX(), event.getY() );
//        }
//        return false;
//      }
//    });
    
    tileView.addGestureListener(new ZoomPanLayout.GestureListener() {
      
      @Override
      public void onTap(Point arg0) {
        // TODO Auto-generated method stub
        
      }
      
      @Override
      public void onScrollComplete(Point arg0) {
        // TODO Auto-generated method stub
        
      }
      
      @Override
      public void onPinchStart(Point arg0) {
        // TODO Auto-generated method stub
        
      }
      
      @Override
      public void onPinchComplete(Point arg0) {
        // TODO Auto-generated method stub
        
      }
      
      @Override
      public void onPinch(Point arg0) {
        // TODO Auto-generated method stub
        
      }
      
      @Override
      public void onFlingComplete(Point arg0) {
        // TODO Auto-generated method stub
        
      }
      
      @Override
      public void onFling(Point arg0, Point arg1) {
        // TODO Auto-generated method stub
        
      }
      
      @Override
      public void onFingerUp(Point arg0) {
        // TODO Auto-generated method stub
        
      }
      
      @Override
      public void onFingerDown(Point arg0) {
        // TODO Auto-generated method stub
        
      }
      
      @Override
      public void onDrag(Point arg0) {
        // TODO Auto-generated method stub
        
      }
      
      @Override
      public void onDoubleTap(Point point) {
        // TODO Auto-generated method stub
        placeMarker( R.drawable.fantasy_dwarves, point.x, point.y);
      }
    });
    
    // Set the minimum parameters
    tileView.setSize(5400,3469);
    
    // detail levels
    tileView.addDetailLevel(1f, "tiles/1000_%col%_%row%.png", "downsamples/map.png");
    tileView.addDetailLevel(0.5f, "tiles/500_%col%_%row%.png", "downsamples/map.png");
    tileView.addDetailLevel(0.25f, "tiles/250_%col%_%row%.png", "downsamples/map.png");
    tileView.addDetailLevel(0.125f, "tiles/125_%col%_%row%.png", "downsamples/map.png");

    // lets center all markers both horizontally and vertically
    tileView.setMarkerAnchorPoints( -0.5f, -0.5f );
    
    // use pixel coordinates to roughly center it
    // they are calculated against the "full" size of the mapView 
    // i.e., the largest zoom level as it would be rendered at a scale of 1.0f
//    tileView.moveToAndCenter( 1000, 642 );
//    tileView.slideToAndCenter( 1000, 642 );
    
    // scale down a little
//    tileView.setScale( 0.1 );
  }
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View v = super.onCreateView(inflater, container, savedInstanceState);
    // initTileView();
    initTileViewNew();
    return v;
  }

  private void placeMarker( int resId, double x, double y ) {
    log("New Marker @ " + x + "/" + y);
    ImageView imageView = new ImageView(this.getActivity());
    imageView.setImageResource( resId );

    getTileView().addMarker( imageView, x, y );
  }
  
  private void log(String s) {
    Log.e("MyTileViewFragment", s);
    Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
  }
  
  
}