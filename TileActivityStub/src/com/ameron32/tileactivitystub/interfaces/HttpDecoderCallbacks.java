package com.ameron32.tileactivitystub.interfaces;

public interface HttpDecoderCallbacks {

	public void onDecodeStart(String fileName);
	
	public void onDecodeUpdate(int progress);
	
	public void onDecodeComplete();
}
