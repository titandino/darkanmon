package com.htm.game;

import org.lwjgl.util.vector.Vector2f;

public class Entity {
	
	private Vector2f position;
	private float rotation;
	private Vector2f scale;
	
	public Entity(Vector2f position, Vector2f scale) {
		this.position = position;
		this.scale = scale;
		this.rotation = 0.0f;
	}
	
	public Vector2f getPosition() {
		return position;
	}
	
	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public Vector2f getScale() {
		return scale;
	}

	public void setScale(Vector2f scale) {
		this.scale = scale;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
}
