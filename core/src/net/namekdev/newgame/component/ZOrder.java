package net.namekdev.newgame.component;

import com.artemis.PooledComponent;

public class ZOrder extends PooledComponent {
	public int z = 0;

	@Override
	protected void reset() {
		z = 0;
	}
}
