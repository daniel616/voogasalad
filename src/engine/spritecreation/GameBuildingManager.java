package engine.spritecreation;

import java.util.List;

import bus.EventBus;
import data.AttributeData;
import utilities.XStreamHandler;

/**
 * @author tahiaemran
 *
 *Class that manages taking all data from the game authoring environment 
 * and creating the game from it 
 *
 */
public class GameBuildingManager {
	private XStreamHandler fileHandler; 
	private List<AttributeData> fileAttributes; 
	private SpriteBuildingManager spriteBuilder;
	private EventBus bus;
	
	public GameBuildingManager(EventBus bus){
		this.fileHandler = new XStreamHandler();
		this.fileAttributes = fileHandler.getScreenModelFile();
		this.spriteBuilder = new SpriteBuildingManager(bus);
		this.bus = bus;
	}
	public GameBuildingManager(XStreamHandler xSH) {
		this.fileHandler = xSH; 
	}
	
//	public void printTestFile() {
//		for (AttributeData a : fileAttributes){
//			System.out.println("attribute name is " + a.getName());
//		}
//	}
	
	
	 
	
	
}
