package com.ameron32.gurps.masscombat;


public class BattleManager implements TurnBased {

	boolean inBattle = false;
	boolean canceled = false;
	
	public void onPrepare() {
		initiativeCommand();
	}
	
	public void onTurnBegin() {
		inBattle = true;
		beforeBattle();
		while(inBattle) {
			battleRound();
			
			// postBattleRound/preBattleRound
			if (totalCasualties) {	
				// end the Battle
				inBattle = false;
			}
			else {
				// prepare for next round
				recalcBasicStrategyModifier();
				reinitIntelligence();
			}
		}
		if (!canceled) afterBattle();		
	}
	
	private void beforeBattle() {
		calcBasicStrategyModifier();
		calcDefensiveBonus(); 
		initIntelligence();
		
	}
	
	private void battleRound() {
		chooseRisk();
		chooseBattleStrategy();
		calcStrategyEffectiveness();
		calcMisfortuneOfWar();
		
	}
	
	private void afterBattle() {
		regroupCasualties();
		calcLogisticForceCasualty();
		dispenseLoot();
		determineCaptives();
	}
	
	public void onFinish() {
		inBattle = false;
	}
	
	public void onCancel() {
		inBattle = false;
		canceled = true;
	}
	

	public void onTurnEnd(){};
	
	public void onOpponentTurnBegin(){};

	public void onOpponentTurnEnd(){};

	public void onAllyTurnBegin(){};

	public void onAllyTurnEnd(){};

	

}
