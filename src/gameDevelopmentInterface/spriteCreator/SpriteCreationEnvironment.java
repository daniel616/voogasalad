package gameDevelopmentInterface.spriteCreator;

import java.util.ResourceBundle;

import data.DeveloperData;
import gameDevelopmentInterface.presetHandling.ExampleTower;
import gameDevelopmentInterface.presetHandling.SpriteEditorLoader;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class SpriteCreationEnvironment extends BorderPane{
	private TabPane creationScreens;
	private DeveloperData developerData;
	private final double MAX_WIDTH=1000;
	private final double MAX_HEIGHT=500;
	
	public SpriteCreationEnvironment(DeveloperData data){
		developerData=data;
		instantiateTabs();
		this.setBottom(new TabAdder());
		this.setMaxSize(MAX_WIDTH,MAX_HEIGHT);
	}
	
	private void instantiateTabs() {
		creationScreens = new TabPane();
		Tab generalSpriteCreation = new Tab("Sprite creation",new SpriteCreationScreen(developerData));
		
		ObservableList<Tab> myTabs = creationScreens.getTabs();
		myTabs.addAll(generalSpriteCreation);
		this.setCenter(creationScreens);
	}
	
	private class TabAdder extends HBox {
		private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
		private static final String RESOURCE_FILE_NAME = "gameAuthoringEnvironment";
		private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);
		private static final String CREATE_SPRITE = "CREATE_SPRITE";
		private static final String CREATE_NEW_SCREEN = "CREATE_NEW_SCREEN";
		private static final String CREATE_NEW_SPRITE = "CREATE_NEW_SPRITE";
		private static final String CREATE_NEW_ATTRIBUTE = "CREATE_NEW_ATTRIBUTE";
		private TabAdder() {
			instantiate();
		}
		
		private void instantiate() {
			SpriteEditorLoader loader=new SpriteEditorLoader();
			Button spriteButton = new Button(myResources.getString(CREATE_NEW_SPRITE));
			spriteButton.setOnAction((clicked) -> {
				Tab spriteTab = new Tab(myResources.getString(CREATE_NEW_SPRITE),
						new SpriteCreationScreen(developerData));
				creationScreens.getTabs().add(spriteTab);
				creationScreens.getSelectionModel().select(spriteTab);
			});
			Button presetTowerButton=new Button("Load Tower Preset");
			presetTowerButton.setOnAction((clicked)->{
				SpriteCreationScreen screen=loader.loadModelPreset(new ExampleTower(), developerData);
				Tab towerTab=new Tab("Loaded Tower", screen);
				creationScreens.getTabs().add(towerTab);
			});
			Button presetMonsterButton=new Button("Load Monster Preset");
			presetMonsterButton.setOnAction((event)->{
				
			});
			Button presetSpawnerButton=new Button("Load Spawner Preset");
			Button loadPresetSprite=new Button("Load Preset");
			Button loadSavedSprite=new Button("Load Saved Sprite");
			loadSavedSprite.setOnAction((event)->{

			});
			
			
			this.getChildren().addAll(spriteButton, presetTowerButton,  presetMonsterButton, presetSpawnerButton, loadSavedSprite);
		}
	}
}

