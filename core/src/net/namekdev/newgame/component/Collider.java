package net.namekdev.newgame.component;

import com.artemis.PooledComponent;

public class Collider extends PooledComponent {
	public float width;
	public float height;


	@Override
	protected void reset() {
		width = height = 0;
	}

	public void wh(float width, float height) {
		this.width = width;
		this.height = height;
	}

}
