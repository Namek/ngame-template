package net.namekdev.newgame.factory;

import net.mostlyoriginal.api.system.core.PassiveSystem;
import net.namekdev.newgame.Assets;
import net.namekdev.newgame.component.Collider;
import net.namekdev.newgame.component.Colored;
import net.namekdev.newgame.component.Pos;
import net.namekdev.newgame.component.Renderable;
import net.namekdev.newgame.component.Rotation;
import net.namekdev.newgame.component.Scale;
import net.namekdev.newgame.component.ZOrder;
import net.namekdev.newgame.enums.C;
import net.namekdev.newgame.enums.Tags;

import com.artemis.Entity;
import com.artemis.EntityEdit;
import com.artemis.managers.TagManager;

public class EntityFactory extends PassiveSystem {
	private TagManager tags;

	public Assets assets;

	
	@Override
	protected void initialize() {
		
	}
	
	public Entity createPlayer(float x, float y) {
		Entity player = world.createEntity();
		EntityEdit e = player.edit();
		tags.register(Tags.Player, player);
		
		e.create(Pos.class).xy(x, y);
		e.create(Collider.class).wh(C.Player.ColliderBottomWidth, C.Player.ColliderBottomHeight);
		e.create(Renderable.class).setToSprite(assets.playerTex);
		e.create(Scale.class);
		e.create(Rotation.class);
		e.create(ZOrder.class);
		e.create(Colored.class);
		
		return player;
	}
}
