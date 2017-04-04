package engine.app;

import engine.gameloop.GameLoop;
import engine.model.BasicModel;
import engine.model.Model;
import engine.sprite.Image;
import engine.sprite.Sprite;
import engine.view.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	
	@Override
	public void start(Stage stage) throws Exception {
		GameFactory gameFactory = new GameFactory();
		
		Model model = new BasicModel();
		View view = gameFactory.createView();
		model.setView(view);
		Sprite sprite1 = new Sprite();
		sprite1.setImage(new Image("images/characters/bahamut_left.png"));
		model.addSprite(sprite1);
		
//		Scene scene = gameFactory.createGameScene();
		Scene scene = view.getScene();
		
		GameLoop gameLoop = gameFactory.createGameLoop();
		gameLoop.addLoopComponent(model.getLoopComponent());
		gameLoop.start(); // nothing added in the loop yet.
		
		gameFactory.createSoundManager(); // Automatically linked by the event bus.
		gameFactory.createInputManager(model); 
		
        stage.setTitle("My JavaFX Application");
        stage.setScene(scene);
        stage.show();		
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
	
}
