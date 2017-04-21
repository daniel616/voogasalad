package newengine.sprite.components;

import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.VariableName;
import newengine.events.player.MainPlayerEvent;
import newengine.player.Player;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class Owner extends Component {

	public static final ComponentType<Owner> TYPE = new ComponentType<>(Owner.class.getName());
	private Player owner;
	private Player mainPlayer;
	
	public Owner(Player player) {
		this.owner = player;
	}
	
	@ConstructorForDeveloper
	public Owner(@VariableName(name = "player name") String playerName){
		this(new Player(playerName));
	}
	
	@Override
	public void afterAdded() {
		sprite.getComponent(GameBus.TYPE).ifPresent((bus) -> {
			System.out.println(bus.getGameBus());
			bus.getGameBus().on(MainPlayerEvent.ANY, (e) -> {
				mainPlayer = e.getPlayer();
			});
		});
	}
	
	public Player player() {
		return owner;
	}
	
	public boolean canControl() {
		return owner == mainPlayer;
	}
	
	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}

	@Override
	public Owner clone() {
		return new Owner(owner);
	}
	
}
