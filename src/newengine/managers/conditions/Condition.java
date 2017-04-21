package newengine.managers.conditions;

import newengine.model.PlayerStatsModel;
import newengine.model.SpriteModel; 

public abstract class Condition implements ICondition {
	private SpriteModel spriteModel;
	private PlayerStatsModel playerStatsModel;
	
	/**
	 * This blank constructor is built in to allow users to initialize a Condition without having to worry about the models
	 */
	public Condition(){
		
	}
	
	public Condition(SpriteModel spriteModel, PlayerStatsModel playerStatsModel){
		this.spriteModel = spriteModel;
		this.playerStatsModel = playerStatsModel;
	}
	
	public void setSpriteModel(SpriteModel model){
		this.spriteModel = model;
	}
	
	public void setPlayerStatsModel(PlayerStatsModel model){
		this.playerStatsModel = model;
	}
	
	public PlayerStatsModel getPlayerStatsModel(){
		return playerStatsModel;
	}
	
	public SpriteModel getSpriteModel(){
		return spriteModel;
	}

}