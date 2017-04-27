package gameDevelopmentInterface.spriteCreator;

import exception.UnsupportedTypeException;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * 
 * @author Daniel
 * An interface designed to produce an arbitrary variable.
 * @param <T>
 */
public abstract class VariableSetter<T> extends HBox{
	private double PREF_LABEL_WIDTH=200;
	private double PREF_LABEL_HEIGHT=50;
	
	public abstract T getValue() throws UnsupportedTypeException;

	protected Label produceLabel(String name){
		Label label=new Label(name);
		label.setPrefSize(PREF_LABEL_WIDTH, PREF_LABEL_HEIGHT);
		return label;
	}
}