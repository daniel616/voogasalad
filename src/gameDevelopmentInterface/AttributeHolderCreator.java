package gameDevelopmentInterface;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import data.AttributeData;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * 
 * @author Jake, Daniel Takes in data concerning interface data from the
 *         interface popup box and puts it in a xStream-readable format, to
 *         produce the data for a single object type. Gives this data to a XML
 *         file producer when needed.
 */
public class AttributeHolderCreator extends BorderPane {
	// Put in attribute data from an attribute
	private Node attributePane;
	private AttributeData attributeHolder;
	private final String userCreatedAttributesFile = "data/attributeSkeletons/userCreatedAttributes";

	public AttributeHolderCreator() {
		Button saveButton = new Button("Save attribute to file");
		saveButton.setOnAction((c)->createClassData());
		attributeHolder=new AttributeData("dummy");
		attributePane = new AttributeSelectorPane(attributeHolder);
		this.setRight(attributePane);
		this.setCenter(new AttributeCustomizerPane(attributeHolder));
		this.setBottom(saveButton);
	}
	// Produce XML file for this class' data.
	public void createClassData() {
		XStream xstream = new XStream(new DomDriver());
		try {
            FileOutputStream fs = new FileOutputStream(userCreatedAttributesFile+"/"+attributeHolder.getName()+".xml");
            xstream.toXML(attributeHolder, fs);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
	}
}
