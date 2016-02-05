package com.htm.game.collision;

import org.lwjgl.util.vector.Vector2f;

import com.htm.game.object.Entity;

public class LineSegment extends Collider {

	public LineSegment(Entity entity) {
		super(entity);
	}

	@Override
	public Vector2f collides(Collider other) {
		return null;
	}

}
