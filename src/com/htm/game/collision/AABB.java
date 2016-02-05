package com.htm.game.collision;

import org.lwjgl.util.vector.Vector2f;

import com.htm.game.object.Entity;

public class AABB extends Collider {

	public AABB(Entity entity) {
		super(entity);
	}

	@Override
	public Vector2f collides(Collider other) {
		if (other instanceof AABB) {
			int collisionData = CollisionManager.rectangleToRectangle(this.getEntity().getPosition(), this.getEntity().getScale().x, this.getEntity().getScale().y, other.getEntity().getPosition(), other.getEntity().getScale().x, other.getEntity().getScale().y);
			if (collisionData != 0) {
				Vector2f ref = new Vector2f();
				if ((collisionData & CollisionManager.COLLISION_LEFT) != 0) {
					ref.x = -getEntity().getVelocity().x;
					ref.y = getEntity().getVelocity().y;
				}
				if ((collisionData & CollisionManager.COLLISION_RIGHT) != 0) {
					ref.x = -getEntity().getVelocity().x;
					ref.y = getEntity().getVelocity().y;
				}
				if ((collisionData & CollisionManager.COLLISION_TOP) != 0) {
					ref.x = getEntity().getVelocity().x;
					ref.y = -getEntity().getVelocity().y;			
				}
				if ((collisionData & CollisionManager.COLLISION_BOTTOM) != 0) {
					ref.x = getEntity().getVelocity().x;
					ref.y = -getEntity().getVelocity().y;
				}
				if (ref != null) {
					System.out.println("Collision: Data: "+collisionData+", "+ref);
				}
				return ref;
			}
			return null;
		} else
			return null;
	}

}
