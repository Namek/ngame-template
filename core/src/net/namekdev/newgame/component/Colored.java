package net.namekdev.newgame.component;

import com.artemis.PooledComponent;
import com.badlogic.gdx.graphics.Color;

public class Colored extends PooledComponent {
	public Color color = new Color(Color.WHITE);

	@Override
	protected void reset() {
		color = new Color(Color.WHITE);
	}
}
