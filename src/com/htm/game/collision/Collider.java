package com.htm.game.collision;

import org.lwjgl.util.vector.Vector2f;

import com.htm.game.object.Entity;

public abstract class Collider {
	
	private Entity entity;
	private boolean immobile;
	
	public Collider(Entity entity, boolean immobile) {
		this.entity = entity;
		this.immobile = immobile;
		entity.setCollider(this);
		CollisionManager.addCollider(this);
	}
	
	public Collider(Entity entity) {
		this(entity, false);
	}
	
	public abstract Vector2f collides(Collider other);

	public Entity getEntity() {
		return entity;
	}

	public boolean isImmobile() {
		return immobile;
	}

	public void setImmobile(boolean immobile) {
		this.immobile = immobile;
	}
}
