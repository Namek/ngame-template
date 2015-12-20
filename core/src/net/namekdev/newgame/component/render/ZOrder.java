package net.namekdev.newgame.component.render;

import com.artemis.PooledComponent;

/**
 * Optional z axis for sorting. Sortes inside one {@link Layer}.
 *
 * @author Namek
 */
public class ZOrder extends PooledComponent {
	public int z = 0;

	@Override
	protected void reset() {
		z = 0;
	}

}
