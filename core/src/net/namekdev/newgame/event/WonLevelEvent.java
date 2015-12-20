package net.namekdev.newgame.event;

import net.mostlyoriginal.api.event.common.Event;
import net.namekdev.newgame.component.GameState;

public class WonLevelEvent implements Event {
	public GameState state;
	
	public WonLevelEvent(GameState state) {
		this.state = state;
	}
}
