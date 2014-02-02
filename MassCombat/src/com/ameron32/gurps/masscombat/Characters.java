package com.ameron32.gurps.masscombat;

import java.util.*;

public class Characters {
	
	public static class Character {
		
	  public String name;
		
		protected Skill strategy = new Skill("Strategy");
		protected Skill leadership = new Skill("Leadership");
		protected Skill intelligenceAnalysis = new Skill("Intelligence Analysis");
		protected Skill administration = new Skill("Administration");
		
		private Character() {}
	}
	
	public static class Leader extends Character {
		
	  Type type;
		Force ofForce;
		
		/** Random Leader factory */
		public static Leader generateLeader() {
			Leader leader = new Leader();
			Random r = new java.util.Random();
			final int minimumRandomSkill = 7;
			final int skillSpread = 13;
			
			// generate skills
			leader.strategy.level = r.nextInt(skillSpread) + minimumRandomSkill;
			leader.leadership.level = r.nextInt(skillSpread) + minimumRandomSkill;
			leader.intelligenceAnalysis.level = r.nextInt(skillSpread) + minimumRandomSkill;
			leader.administration.level = r.nextInt(skillSpread) + minimumRandomSkill;
			
			// generate AWESOME name!
			leader.name = "General " + r.nextInt(100);
			
			return leader;
		}
		
		private Leader() {}
		
		public void appointLeader(Leader.Type type, Force ofForce) {
			
		  this.type = type;
			this.ofForce = ofForce;
		} 
		
		public enum Type {
			Commander, IntelligenceChief, Quartermaster
		}
	}
	
	public static class Hero extends Character {
		
	}
	
  public static class Skill {
    
    /** simple constructor */
    Skill(String name) {this.name = name;}
    public String name;
    public int level;
  }
}
