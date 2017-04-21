package newengine.sprite.components;

import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class Selectable extends Component {
	public enum SelectionBoundType {
		POLYGON, IMAGE		
	}
	
	public static final ComponentType<Selectable> TYPE = new ComponentType<>(Selectable.class.getName());
	private final SelectionBoundType boundType;

	public Selectable(SelectionBoundType boundType) {
		this.boundType = boundType;
	}
	
	public SelectionBoundType boundType() {
		return boundType;
	}
	
	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}
	
	@Override
	public Selectable clone() {
		return new Selectable(boundType);
	}

}
