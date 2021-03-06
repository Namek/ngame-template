package net.namekdev.newgame.screen;

import net.mostlyoriginal.api.event.common.EventSystem;
import net.mostlyoriginal.api.event.common.Subscribe;
import net.mostlyoriginal.api.plugin.extendedcomponentmapper.ExtendedComponentMapperPlugin;
//import net.namekdev.entity_tracker.EntityTracker;
//import net.namekdev.entity_tracker.network.EntityTrackerServer;
import net.namekdev.newgame.MyNGame;
import net.namekdev.newgame.component.GameState;
import net.namekdev.newgame.enums.C;
import net.namekdev.newgame.event.LostGameEvent;
import net.namekdev.newgame.event.WonLevelEvent;
import net.namekdev.newgame.factory.EntityFactory;
import net.namekdev.newgame.manager.AspectHelpers;
import net.namekdev.newgame.manager.WorldInitManager;
import net.namekdev.newgame.system.CameraSystem;
import net.namekdev.newgame.system.CollisionDebugSystem;
import net.namekdev.newgame.system.CollisionSystem;
import net.namekdev.newgame.system.GameStateSystem;
import net.namekdev.newgame.system.PlayerStateSystem;
import net.namekdev.newgame.system.RenderSystem;
import net.namekdev.newgame.system.SchedulerSystem;
import net.namekdev.newgame.system.TweenSystem;

import com.artemis.BaseSystem;
import com.artemis.SystemInvocationStrategy;
import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.artemis.managers.TagManager;
import com.artemis.utils.Bag;

public class GameScreen extends BaseScreen<GameScreen> {
	World world;
	public boolean isPaused;

	public GameScreen init(MyNGame game) {
		super.init(game);

		EntityFactory entityFactory = new EntityFactory();
		entityFactory.assets = game.getAssets();

//		EntityTrackerServer entityTrackerServer = new EntityTrackerServer();
//		entityTrackerServer.start();

		WorldConfiguration cfg = new WorldConfigurationBuilder()
//			.with(new EntityTracker(entityTrackerServer))
//			.with(new EntityTracker(new EntityTrackerMainWindow()))
			.with(new ExtendedComponentMapperPlugin())
			.with(new AspectHelpers())
			.with(entityFactory)
			.with(new WorldInitManager())
			.with(new TagManager())

			// loop systems
			.with(new TweenSystem())
			.with(new GameStateSystem())
			.with(new PlayerStateSystem())
			.with(new CollisionSystem())
			// TODO your stuff here
			.with(new CameraSystem())
			.with(new RenderSystem())
//			.with(new CollisionDebugSystem())
			.with(new SchedulerSystem())
			.with(new EventSystem())
			.build();

		cfg.setInvocationStrategy(new SystemInvocationStrategy() {
			@Override
			protected void process(Bag<BaseSystem> systems) {
				Object[] systemsData = systems.getData();
				for (int i = 0, s = systems.size(); s > i; i++) {
					BaseSystem system = (BaseSystem) systemsData[i];
					if (!isPaused || system instanceof RenderSystem) {
						system.process();
					}
					updateEntityStates();
				}
			}
		});

		world = new World(cfg);
		world.getSystem(EventSystem.class).registerEvents(this);

		return this;
	}

	@Override
	public void render(float delta) {
		world.setDelta(!isPaused ? delta : 0);
		world.process();
	}

	@Subscribe
	private void onLostGame(LostGameEvent evt) {
		final GameState state = evt.state;

		game.pushScreen(new InstructionScreen(setFirstLevel).init(game));
	}

	@Subscribe
	private void onWonLevel(WonLevelEvent evt) {
		final GameState state = evt.state;
		int nextLevelIndex = state.levelIndex + 1;

		// Is there next level?
		if (nextLevelIndex < C.Levels.LevelCount) {
			setNextLevel.run();
		}

		// No more levels - You won whole game
		else {
//			game.pushScreen(new CongratsScreen(talk, showWinScreen).init(game));
		}

		// TODO
		throw new RuntimeException("you won the game!");
	}

	private Runnable setNextLevel = new Runnable() {
		public void run() {
			world.getSystem(GameStateSystem.class).setNextLevel();
		}
	};

	private Runnable setFirstLevel = new Runnable() {
		public void run() {
			world.getSystem(GameStateSystem.class).setFirstLevel();
			game.pushScreen(new InstructionScreen(null).init(game));
		}
	};

	private Runnable showWinScreen = new Runnable() {
		public void run() {
			game.pushScreen(new WonGameScreen(setFirstLevel).init(game));
		}
	};
}
