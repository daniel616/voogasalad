package newengine.control;

import engine.skill.Target;
import newengine.attribute.PositionAttribute;
import newengine.attribute.SpeedAttribute;
import newengine.event.MoveEvent;
import newengine.sprite.Sprite;

public class MoveControl extends Control {
	
	public static final ControlType<MoveControl> TYPE = new ControlType<>();
	
	private Sprite sprite;
	private PositionAttribute positionAttribute;
	private SpeedAttribute speedAttribute;

	@Override
	public void onAdded(Sprite sprite) {
		this.sprite = sprite;
		// now there is no attribute in Sprite
//		positionAttribute = sprite.getAttribute(PositionAttribute.TYPE);
//		speedAttribute = sprite.getAttribute(SpeedAttribute.TYPE);
		initHandlers();
	}

	private void initHandlers() {
		sprite.on(MoveEvent.SPECIFIC, e -> {
			moveTo(e.getTarget());
		});
	}

	@Override
	public void onUpdated(double dt) {
		// TODO update the position of the attached Sprite
		
	}

	@Override
	public void onRemoved() {
		// TODO Auto-generated method stub
		
	}

	private void moveTo(Target target) {
		System.out.print("Sprite at (" + positionAttribute.getPos().x() + ", " + positionAttribute.getPos().y() + ")" );
		if (target.getLocation().isPresent()) {
			positionAttribute.setPos(target.getLocation().get());			
		}
		else {
			
		}
		System.out.println(" has been moved to (" + positionAttribute.getPos().x() + ", " + positionAttribute.getPos().y() + ")");
	}
	
	@Override
	public ControlType<? extends Control> getType() {
		return TYPE;
	}

}