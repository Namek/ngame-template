package net.namekdev.newgame.component

import com.artemis.PooledComponent

public class Renderable2() : PooledComponent() {
	var layer:Int = 0
	var z:Int = 0
	

	override fun reset() {
		layer = 0
		z = 0
	}
}