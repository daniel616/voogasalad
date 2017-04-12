package engine.sprite.range;

import bus.EventBus;
import engine.sprite.Sprite;
import engine.sprite.attack.AttackEvent;
import engine.sprite.attack.Attacker;

/**
 * Manage actions to do when one sprite is in the detection range of another sprite.
 * @author Yilin Gao
 *
 */
public class InRangeManager {

	private EventBus bus;
	
	public InRangeManager(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}
	
	private void initHandlers() {
		bus.on(InRangeEvent.ANY, e -> {
			Sprite detector = e.getDetector();
			Sprite detectee = e.getDetectee();
			// TODO actions to do when one sprite gets into the range of another sprite
			System.out.println("Sprite " + detector.toString() + " detects Sprite " + detectee.toString() 
				+ " is in its range.");
			// if detector is an attacker, launch weapon with target = detectee
			if (detector.getAttacker().isPresent()){
				System.out.println("emit AttackEvent");
				bus.emit(new AttackEvent(AttackEvent.ANY, detector, detectee));
				
			}
		});
	}
}
