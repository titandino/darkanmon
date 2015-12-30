package com.htm.game.object;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import com.htm.graphic.texture.Texture;

public class Entity {
	
	private Vector2f position;
	private float rotation;
	private Vector2f scale;
	private Texture texture;
	private Vector3f color;
	
	public Entity(Texture texture, Vector2f position, Vector2f scale) {
		this.position = position;
		this.scale = scale;
		this.rotation = 0.0f;
		this.texture = texture;
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

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Vector3f getColor() {
		return color;
	}

	public void setColor(Vector3f color) {
		this.color = color;
	}
}
