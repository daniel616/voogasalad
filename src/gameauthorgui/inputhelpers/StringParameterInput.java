package gameauthorgui.inputhelpers;

import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class StringParameterInput extends HBox implements ParameterInput<String>{
	public static final String TYPE = "String";
	private String varName;
	private TextField input;
	
	public StringParameterInput(String varName){
		super();
		this.varName = varName;
		createBox();
	}
	
	private void createBox(){
		input = new TextField();
		this.getChildren().addAll(new Text(varName), input);
	}
	
	public String getValue(){
		return input.getText();
	}
	
	public StringProperty getTextProperty(){
		return input.textProperty();
	}

	@Override
	public String getType() {
		return TYPE;
	}
	
	
}
