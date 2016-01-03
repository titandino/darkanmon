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
	
	private Vector2f velocity;
	private float angularVelocity;
	
	public Entity(Texture texture, Vector2f position, Vector2f scale) {
		this.position = position;
		this.scale = scale;
		this.rotation = 0.0f;
		this.texture = texture;
		this.velocity = new Vector2f(0.0f, 0.0f);
		this.angularVelocity = 0.0f;
	}
	
	public void update(double delta) {
		this.rotation += angularVelocity*delta;
		this.position.x += velocity.x*delta;
		this.position.y += velocity.y*delta;
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

	public Vector2f getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2f velocity) {
		this.velocity = velocity;
	}

	public float getAngularVelocity() {
		return angularVelocity;
	}

	public void setAngularVelocity(float angularVelocity) {
		this.angularVelocity = angularVelocity;
	}
}
