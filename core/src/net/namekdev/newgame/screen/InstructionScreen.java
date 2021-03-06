package net.namekdev.newgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class InstructionScreen extends BaseScreen<InstructionScreen> {
	Runnable exitCallback;
	
	public InstructionScreen(Runnable exitCallback) {
		this.exitCallback = exitCallback;
	}
	
	@Override
	public void render(float delta) {
		darkenBackground();

		batch.begin();
		float x = (sw() - assets.instruction.getRegionWidth()) / 2;
		float y = (sh() - assets.instruction.getRegionHeight()) / 2;
		batch.draw(assets.instruction, x, y);
		batch.end();

		if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			popScreen();
			
			if (exitCallback != null) {
				exitCallback.run();
			}
		}
	}
}
