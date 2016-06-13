package com.htm.game.object;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import com.htm.graphic.texture.Texture;

public class Entity extends Body {
	
	private Vector2f scale;
	private Texture texture;
	private Vector3f color;
	
	private boolean active;
	
	public Entity(Texture texture, Vector2f position, Vector2f scale) {
		this.getTransform().setTranslation(new Vector2(position.x, position.y));
		Rectangle rect = new Rectangle(scale.x, scale.y);
		this.addFixture(rect);
		this.setMass(MassType.NORMAL);
		this.scale = scale;
		this.texture = texture;
		this.angularVelocity = 0.0f;
	}
	
	public void update(double delta) {

	}

	public Vector2f getScale() {
		return scale;
	}

	public void setScale(Vector2f scale) {
		this.scale = scale;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
