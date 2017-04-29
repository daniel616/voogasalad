package gameauthorgui.tower;

import data.DeveloperData;
import gamecreation.level.LevelEditorHolder;
import javafx.scene.layout.BorderPane;

public class SpawnerCreationScreen extends BorderPane {
	private DeveloperData modelData;
	
	public SpawnerCreationScreen(DeveloperData modelData){
		super();
		this.modelData = modelData;
		this.setRight(new LevelEditorHolder(modelData.getLevelData()));
		//this.setCenter(WHATEVER JAKE IS USING);
	}

}