package gamedata;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import bus.EventBus;
import data.DeveloperData;
import data.SerializableDeveloperData;
import data.SpriteMakerModel;
import newengine.app.Game;
import newengine.events.GameInitializationEvent;
import newengine.events.SpriteModelEvent;
import newengine.events.conditions.SetEndConditionEvent;
import newengine.events.levels.InitILevelsEvent;
import newengine.events.player.MainPlayerEvent;
import newengine.events.sound.SoundEvent;
import newengine.events.stats.ChangeLivesEvent;
import newengine.events.stats.ChangeWealthEvent;
import newengine.managers.conditions.GoldMinimumCondition;
import newengine.managers.conditions.NoLivesCondition;
import newengine.model.PlayerStatsModel.WealthType;
import newengine.player.Player;
import newengine.skill.Skill;
import newengine.skill.SkillType;
import newengine.skill.skills.BuildSkill;
import newengine.skill.skills.BuildSkillFactory;
import newengine.sprite.Sprite;
import newengine.sprite.SpriteID;
import newengine.sprite.components.GameBus;
import newengine.sprite.components.SkillSet;
import player.helpers.GameLoadException;

/**
 * Class that creates a game from an XML file of translated GameData
 *  
 * @author tahiaemran, Keping
 */
public class GameCreator {

	private XStream xstream;

	public GameCreator() {
		xstream = new XStream(new DomDriver());
	}

	private Sprite createTowerBuilder(Player player, List<SpriteMakerModel> availableTowers) {
		Sprite towerBuilder = new Sprite(SpriteID.TOWER_BUILDER_ID);
		towerBuilder.addComponent(new GameBus());
		Map<SkillType<? extends Skill>, Skill> skillMap = new HashMap<>();
		for (SpriteMakerModel availableTower : availableTowers) {
			BuildSkill buildSkill = BuildSkillFactory.createBuildSkill(availableTower);
			skillMap.put(buildSkill.getType(), buildSkill);
		}
		towerBuilder.addComponent(new SkillSet(skillMap));
		// currently no image
		return towerBuilder;
	}
	
	public Game createGame(String gameFilePath) throws GameLoadException {
		return createGame(new File(gameFilePath));
	}
	public Game createGame(File gameFile) throws GameLoadException {
		try {
			Game game = new Game();

			SerializableDeveloperData myData = (SerializableDeveloperData) xstream.fromXML(gameFile); 
			// serializableDeveloperData?
			
			// sync the players authoring and game.
			// player 1: the user, towers
			// enemy: the monsters
			Player userPlayer = myData.getUserPlayer();
			
			List<Sprite> sprites = myData.getLevels().get(0).getSpawners().stream().map(
					(spriteMakerModel) -> (new AuthDataTranslator(spriteMakerModel)).getSprite()
			).collect(Collectors.toList());
			sprites.add(createTowerBuilder(myData.getUserPlayer(), myData.getSprites()));
			

			EventBus bus = game.getBus();
			bus.on(GameInitializationEvent.ANY, (e) -> {
				bus.emit(new InitILevelsEvent(myData.getLevels()));
				bus.emit(new SoundEvent(SoundEvent.BACKGROUND_MUSIC, "data/sounds/01-dark-covenant.mp3"));
				bus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, sprites));
				
				bus.emit(new MainPlayerEvent(userPlayer));
				bus.emit(new ChangeLivesEvent(ChangeLivesEvent.SET, userPlayer, Integer.parseInt(myData.getGameData().get(DeveloperData.NUMBER_OF_LIVES))));
				bus.emit(new ChangeWealthEvent(ChangeWealthEvent.CHANGE, userPlayer, WealthType.GOLD, Integer.parseInt(myData.getGameData().get(DeveloperData.NUMBER_OF_STARTING_GOLD))));
				
				//TODO condition factory
				bus.emit(new SetEndConditionEvent(SetEndConditionEvent.SETWIN, new GoldMinimumCondition(1000)));
				bus.emit(new SetEndConditionEvent(SetEndConditionEvent.SETLOSE, new NoLivesCondition()));
			});

			return game;
		} catch (Exception e) {
			throw new GameLoadException("Fail to load game: " + gameFile);
		}
	}

}
