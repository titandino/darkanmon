package com.darkanmon.game.object;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import com.darkanmon.graphic.texture.Texture;

public class Entity extends Body {
	
	private Vector2f scale;
	private Texture texture;
	private Vector3f color;
		
	public Entity(Texture texture, Vector2f position, Vector2f scale) {
		this(texture, position, scale, false);
	}
	
	public Entity(Texture texture, Vector2f position, Vector2f scale, boolean circle) {
		this.getTransform().setTranslation(new Vector2((double) position.x, (double) position.y));
		this.scale = scale;
		this.texture = texture;
		if (circle) {
			Circle circ = Geometry.createCircle((double) (scale.x/2));
			circ.translate(0, 0);
			addFixture(circ);
		} else {
			Rectangle rect = Geometry.createRectangle((double) scale.x, (double) scale.y);
			rect.translate(scale.x/2, 0);
			addFixture(rect);
		}
		this.setMass(MassType.NORMAL);
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
}
