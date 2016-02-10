package com.htm.game.collision;

import org.lwjgl.util.vector.Vector2f;

public class CollisionEvent {
	
	private Collider curr;
	private Collider other;
	
	public CollisionEvent(Collider curr, Collider other) {
		this.curr = curr;
		this.other = other;
	}
	
	public void handle(double delta) {
		Vector2f thisRef = curr.collides(other);
		if (thisRef != null && !curr.isImmobile()) {
			curr.getEntity().setPosition(curr.getEntity().getPosition().translate((float) (thisRef.x*delta)*1.05f, (float) (thisRef.y*delta)*1.05f));
			curr.getEntity().setVelocity(thisRef);
			other.getEntity().setPosition(other.getEntity().getPosition().translate((float) (-thisRef.x*delta)*1.05f, (float) (-thisRef.y*delta)*1.05f));
			other.getEntity().setVelocity(new Vector2f(-thisRef.x, -thisRef.y));
		}
	}

	public Collider getCurr() {
		return curr;
	}

	public Collider getOther() {
		return other;
	}

}
