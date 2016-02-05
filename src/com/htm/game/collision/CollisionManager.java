package com.htm.game.collision;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

public class CollisionManager {
	
	public static final int COLLISION_LEFT = 	0x00000001;
	public static final int COLLISION_RIGHT = 	0x00000002;
	public static final int COLLISION_TOP = 	0x00000004;
	public static final int COLLISION_BOTTOM = 	0x00000008;
	
	private static ArrayList<Collider> colliders = new ArrayList<Collider>();
	
	public static void addCollider(Collider collider) {
		if (!colliders.contains(collider))
			colliders.add(collider);
	}
	
	public static void removeCollider(Collider collider) {
		if (collider.getEntity() != null)
			collider.getEntity().setCollider(null);
		colliders.remove(collider);
	}
	
	public static void clearColliders() {
		colliders.clear();
	}
	
	public static void processCollisions(double delta) {
		for (Collider curr : colliders) {
			for (Collider other : colliders) {
				if (curr == other)
					continue;
				Vector2f thisRef = curr.collides(other);
				Vector2f otherRef = other.collides(curr);
				if (thisRef != null && !curr.isImmobile()) {
					curr.getEntity().setPosition(curr.getEntity().getPosition().translate((float) (thisRef.x*delta)*1.05f, (float) (thisRef.y*delta)*1.05f));
					curr.getEntity().setVelocity(thisRef);
				}
				if (otherRef != null && !other.isImmobile()) {
					other.getEntity().setPosition(other.getEntity().getPosition().translate((float) (otherRef.x*delta)*1.05f, (float) (otherRef.y*delta)*1.05f));
					other.getEntity().setVelocity(otherRef);
				}
			}
		}
	}

	public static int rectangleToRectangle(Vector2f rect1, float width1, float height1, Vector2f rect2, float width2, float height2) {
		int flag = 0;
		
		Vector2f point = new Vector2f();
		
		point.x = rect1.x; point.y = rect1.y+(height1/8);
		if (pointToRectangle(point, rect2, width2, height2)) {
			flag |= COLLISION_LEFT;
			System.out.println("Colliding left1.");
		}
		
		point.x = rect1.x; point.y = rect1.y+(height1/4);
		if (pointToRectangle(point, rect2, width2, height2)) {
			flag |= COLLISION_LEFT;
			System.out.println("Colliding left2.");
		}
		
		point.x = rect1.x+width1; point.y = rect1.y+(height1/8);
		if (pointToRectangle(point, rect2, width2, height2)) {
			flag |= COLLISION_LEFT;
			System.out.println("Colliding right1.");
		}
		
		point.x = rect1.x+width1; point.y = rect1.y+(height1/4);
		if (pointToRectangle(point, rect2, width2, height2)) {
			flag |= COLLISION_LEFT;
			System.out.println("Colliding right2.");
		}
		
		point.x = rect1.x+width1/4; point.y = rect1.y;
		if (pointToRectangle(point, rect2, width2, height2)) {
			flag |= COLLISION_TOP;
			System.out.println("Colliding top1.");
		}
		
		point.x = rect1.x+width1/8; point.y = rect1.y;
		if (pointToRectangle(point, rect2, width2, height2)) {
			flag |= COLLISION_TOP;
			System.out.println("Colliding top2.");
		}
		
		point.x = rect1.x+width1/4; point.y = rect1.y+height1;
		if (pointToRectangle(point, rect2, width2, height2)) {
			flag |= COLLISION_BOTTOM;
			System.out.println("Colliding bottom1.");
		}
		
		point.x = rect1.x+width1/8; point.y = rect1.y+height1;
		if (pointToRectangle(point, rect2, width2, height2)) {
			flag |= COLLISION_BOTTOM;
			System.out.println("Colliding bottom2.");
		}



		return flag;
	}
	
	public static boolean pointToRectangle(Vector2f point, Vector2f rect, float width, float height) {
		float left = rect.x; float right = rect.x+width;
		float top = rect.y; float bottom = rect.y+height;
		return !(point.x < left || point.x > right || point.y < top || point.y > bottom);
	}

	public static boolean circleToRectangle(Vector2f circle, float diameter, Vector2f rect, float width, float height) {
		double left = rect.x; double right = rect.x+width;
		double top = rect.y+height; double bottom = rect.y;
		return !(circle.x+diameter < left || circle.x > right || circle.y < top || circle.y+diameter > bottom);
	}
	
}
