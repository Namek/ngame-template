package net.namekdev.newgame.event;

import net.mostlyoriginal.api.event.common.Event;
import net.namekdev.newgame.component.GameState;

public class LostGameEvent implements Event {
	public GameState state;
	
	public LostGameEvent(GameState state) {
		this.state = state;
	}
}
