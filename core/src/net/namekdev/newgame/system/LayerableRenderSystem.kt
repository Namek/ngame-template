package net.namekdev.newgame.system

import com.artemis.Entity
import com.artemis.Aspect;
import com.artemis.EntitySystem;
import com.artemis.utils.Bag

import net.namekdev.newgame.component.Renderable2;
import net.mostlyoriginal.api.plugin.extendedcomponentmapper.M

class LayerableRenderSystem : EntitySystem {
	var mRenderable: M<Renderable2>? = null
	
	constructor() : super(Aspect.all(Renderable2::class.java)) {
	}
	
	override fun processSystem() {
		val entities = getEntities()
		val array = entities.getData()
		
		for (e in array.indices) {
			process(e as Entity)
		}
	}
}