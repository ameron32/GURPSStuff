package com.ameron32.tileactivitystub;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ameron32.tileactivitystub.interfaces.HttpDecoderCallbacks;
import com.ameron32.tileactivitystub.interfaces.TileViewFragmentListener;
import com.ameron32.tileactivitystub.tiled.TileMap;
import com.ameron32.tileactivitystub.tiled.TiledXMLParser;
import com.ameron32.tileactivitystub.tiled.Tileset;
import com.qozix.tileview.TileView;
import com.qozix.tileview.graphics.BitmapDecoderAssets;
import com.qozix.tileview.markers.MarkerEventListener;

public class SmartTileViewFragment extends TileViewFragment implements HttpDecoderCallbacks {

	// CONSTANTS
	private static final boolean DEBUG = true;
	private static final String TILE_MAP = "TILE_MAP";
	private static final String HTTP_PREFIX = "https://dl.dropboxusercontent.com/u/949753/ANDROID/TileView/indoor/";
    private static final String TILES_PREFIX = "tileset";
    private static final String EXTENSION_SUFFIX = "png";
    
    
    // ACTIVITY
    private MainActivity activity = null;
    
    
    // TILEVIEW
    private TileView mTileView = null;
    private String mTileMap = null;

    
    // VIEWS
	private RequestView mRequestView = null;
	private ProgressBar mProgressBar = null;
	private TextView mFileName = null;
	
	
	public static SmartTileViewFragment newInstance(String tileMap) {
		SmartTileViewFragment fragment = new SmartTileViewFragment();
		Bundle bundle = new Bundle();
		bundle.putString(TILE_MAP, tileMap);
		fragment.setArguments(bundle);
		return fragment;
	}

	public SmartTileViewFragment() {}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = super.onCreateView(inflater, container, savedInstanceState);
		mRequestView = (RequestView) rootView.findViewById(R.id.request_view);
		mProgressBar  = (ProgressBar) rootView.findViewById(R.id.progressBar1);
		mFileName = (TextView) rootView.findViewById(R.id.tvHello);
		return rootView;
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity instanceof TileViewFragmentListener) {
			this.activity = (MainActivity) activity;
		} else {
			throw new ClassCastException("activity: failed implement TileViewFragmentListener");
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		Bundle bundle = getArguments();
		if (bundle != null) {
			reload(bundle);
		} else {
			startNew();
		}
	}
	
	private void reload(Bundle bundle) {
		mTileMap = bundle.getString(TILE_MAP);
		
		init();
		initTileMap(mTileView);
		initTileListeners(mTileView);
		initMarkers(mTileView);
	}
	
	private void startNew() {
		// TODO: no bundle available
	}
	
	private void init() {
		mTileView = getTileView();
		log("mTileView", mTileView.toString());
	}

	private void initTileMap(TileView tileView) {
		TileMap tileMap = TiledXMLParser.parseTMX(activity, mTileMap + ".tmx");
		log("tileMap", tileMap.toString());
		
		// TODO: where should mapImage string be retrieved from?
		tileMap.mapImage = mTileMap +"."+ EXTENSION_SUFFIX;
		log("tileMap.mapImage", tileMap.mapImage);
		
		float zoomScale = 0.2f;
		
	    int width = tileMap.getTileWidth() * tileMap.getMapTileColumns();
	    int height = tileMap.getTileHeight() * tileMap.getMapTileRows();
	    tileView.setSize(width, height);
	    
	    tileView.setDownsampleDecoder(new BitmapDecoderAssets());
	    tileView.setTileDecoder(new MyFOWTileBitmapDecoder(tileView, tileMap, this));
	    
	    float[] sizes = new float[] { 0.5f, 0.25f, 0.125f };
	    String downsampleImage = tileMap.mapImage;
	    initDetailLevels(sizes, tileMap, tileView, downsampleImage);
	
	    tileView.setScale(zoomScale);
	    tileView.setScaleLimits(0.2d, 2.25d);
	}
	
	private void initTileListeners(TileView tileView) {
		tileView
				.addTileViewEventListener(new TileView.TileViewEventListener() {

					@Override
					public void onDetailLevelChanged() {
					}

					@Override
					public void onDoubleTap(int x, int y) {
					}

					@Override
					public void onDrag(int x, int y) {
					}

					@Override
					public void onFingerDown(int x, int y) {
					}

					@Override
					public void onFingerUp(int x, int y) {
					}

					@Override
					public void onFling(int sx, int sy, int dx, int dy) {
					}

					@Override
					public void onFlingComplete(int x, int y) {
					}

					@Override
					public void onPinch(int x, int y) {
					}

					@Override
					public void onPinchComplete(int x, int y) {
					}

					@Override
					public void onPinchStart(int x, int y) {
					}

					@Override
					public void onRenderComplete() {
					}

					@Override
					public void onRenderStart() {
					}

					@Override
					public void onScaleChanged(double scale) {

						log("SCALE_CHANGED", "scale: " + scale);
						for (ImageView iv : lIV) {
							iv.setScaleX((float) scale);
							iv.setScaleY((float) scale);
							iv.invalidate();
						}
					}

					@Override
					public void onScrollChanged(int x, int y) {
					}

					@Override
					public void onTap(final int x, final int y) {

						mRequestView.setMessage("Create a New Marker @ " + x
								+ "/" + y + " ?");
						mRequestView
								.setPositiveListener(new View.OnClickListener() {

									@Override
									public void onClick(View v) {
										double scale = getTileView().getScale();
										log("SCALE", "scale: " + scale);
										placeMarker(R.drawable.fantasy_dwarves,
												x / scale, y / scale);
										mRequestView.hide();
									}
								});
						mRequestView
								.setNegativeListener(new View.OnClickListener() {

									@Override
									public void onClick(View v) {
										mRequestView.hide();
									}
								});
						mRequestView.show();
					}

					@Override
					public void onZoomComplete(double scale) {
					}

					@Override
					public void onZoomStart(double scale) {
					}
				});

		tileView.addMarkerEventListener(new MarkerEventListener() {

			@Override
			public void onMarkerTap(View v, int x, int y) {
				String message = "Marker Tapped @: " + x + "/" + y;
				TextView textView = ((TextView) getView().findViewById(
						R.id.tvHello));
				textView.setText(message);
				log("MARKER_TAP", message);
				Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT)
						.show();
			}
		});
	}

	private void initDetailLevels(float[] sizes, 
			TileMap tileMap, TileView tileView, 
			String downsampleImage) {
		
		String tilesetPrefix = HTTP_PREFIX + TILES_PREFIX;
		int width = tileMap.getTileWidth();
		int height = tileMap.getTileHeight();
		
		// TODO convert to imbalanced tiles
		int tileSize = width;
		
	    Tileset.ComponentHandler handler 
	      = new Tileset.ComponentHandler( 
	          tileMap, tilesetPrefix, tileSize, EXTENSION_SUFFIX);
		
		for (float size : sizes) {
			int detailSize = ((int) (size * tileSize)); 
			tileView.addDetailLevel(size, handler.getDetailLevelUrl(detailSize),
					downsampleImage, detailSize, detailSize);
		}
	}
	
	private void initMarkers(TileView tileView) {
		// lets center all markers both horizontally and vertically
	    tileView.setMarkerAnchorPoints(-0.5f, -0.5f);
	    
	    // use pixel coordinates to roughly center it
	    // they are calculated against the "full" size of the mapView
	    // i.e., the largest zoom level as it would be rendered at a scale of 1.0f
	    tileView.moveToAndCenter(0, 0);
	}
	
	private final List<ImageView> lIV = new ArrayList<ImageView>();

	private void placeMarker(int resId, double x, double y) {

		log("New Marker @ ", x + "/" + y);
		ImageView imageView = new ImageView(this.getActivity());
		imageView.setImageResource(resId);
		imageView.setScaleX((float) mTileView.getScale());
		imageView.setScaleY((float) mTileView.getScale());
		lIV.add(imageView);

		mTileView.addMarker(imageView, x, y);
	}

	private void log(String tag, String message) {
		
		if (DEBUG) {
			Log.i("SmartTileViewFragment", tag + ": " + message);
			/*
			 * Toast.makeText(getActivity(), message,
			 * Toast.LENGTH_SHORT).show();
			 */
		}
	}


	// HTTPDECODERCALLBACKS
	@Override
	public void onDecodeStart(String filename) {
		pushProgress(0);
		setFilename(filename);
		changeVisibility(true);
	}

	@Override
	public void onDecodeUpdate(int progress) {
		pushProgress(progress);
	}

	@Override
	public void onDecodeComplete() {
		changeVisibility(false);
		pushProgress(0);
	}
	
	private void pushProgress(final int progress) {
		mProgressBar.post(new Runnable() {
			
			@Override
			public void run() {
				mProgressBar.setProgress(progress);
			}
		});
	}
	
	private void changeVisibility(final boolean visible) {
		mProgressBar.post(new Runnable() {
			
			@Override
			public void run() {
				if (visible) {
					mProgressBar.setVisibility(View.VISIBLE);
				} else {
					mProgressBar.setVisibility(View.INVISIBLE);
				}
			}
		});
	}
	
	private void setFilename(final String filename) {
		mFileName.post(new Runnable() {
			
			@Override
			public void run() {
				mFileName.setText(filename);
			}
		});
	}
}
