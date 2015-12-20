package net.namekdev.newgame.component;

import com.artemis.PooledComponent;

public class Rotation extends PooledComponent {
	public float rotation;

	@Override
	protected void reset() {
		rotation = 0;
	}
}
