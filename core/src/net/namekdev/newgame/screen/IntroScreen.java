package net.namekdev.newgame.screen;

import net.namekdev.newgame.MyNGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class IntroScreen extends BaseScreen<IntroScreen> {
	protected Runnable exitCallback;

	public IntroScreen(Runnable exitCallback) {
		this.exitCallback = exitCallback;
	}

	@Override
	public IntroScreen init(MyNGame game) {
		super.init(game);

		// TODO some initialization using BaseScreen protected stuff

		return this;
	}

	private void goToNextScreen() {
		popScreen();

		if (exitCallback != null) {
			exitCallback.run();
		}
	}

	@Override
	public void render(float delta) {
		darkenBackground();

		batch.begin();
		float x = (sw() - assets.intro.getRegionWidth()) / 2;
		float y = (sh() - assets.intro.getRegionHeight()) / 2;
		batch.draw(assets.intro, x, y);
		batch.end();

		if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			goToNextScreen();
		}
	}
}
