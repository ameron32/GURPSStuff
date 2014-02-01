package com.ameron32.gurps.masscombat;

public interface TurnBased {
	
	public void onPrepare();
	
	public void onTurnBegin();
	
	public void onTurnEnd();
	
	public void onOpponentTurnBegin();
	
	public void onOpponentTurnEnd();
	
	public void onAllyTurnBegin();
	
	public void onAllyTurnEnd();
	
	public void onFinish();
	
	public void onCancel();
}
