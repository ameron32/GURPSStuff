package com.ameron32.gurps.masscombat;

public class BattleManager {

	boolean inBattle = false;
	boolean canceled = false;
	
	
	void prepare() {
		initiativeCommand();
	}
	
	void begin() {
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
	
	void conclude() {
		inBattle = false;
	}
	
	void cancel() {
		inBattle = false;
		canceled = true;
	}
	
	
}
