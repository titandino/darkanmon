package com.darkan.pkmn.engine.entity;

import java.awt.Color;

import org.lwjgl.util.vector.Vector2f;

import com.darkan.pkmn.engine.gfx.mesh.Mesh;
import com.darkan.pkmn.engine.gfx.texture.Texture;
import com.darkan.pkmn.engine.render.FBO;

/**
 * Created by trent on 4/6/2018.
 *
 * Represents a rectangular 2D entity that has a texture to bind to it
 * when rendered.
 */
public class Entity {
    //Transform variables
    private Vector2f position;
    private float rotation;
    private Vector2f scale;

    private Vector2f velocity;

    //Texture and texture coordinates
    private Mesh mesh;
	private Texture texture;
    private boolean texFbo;
    private Color color;

    public Entity(Vector2f position, float width, float height, Mesh mesh, Texture texture) {
        this(position, new Vector2f(0, 0), width, height, mesh, texture);
    }

    /**
     * Initialize the entity.
     *
     * @param position Position of the entity.
     * @param velocity Velocity to give at the start.
     * @param width Width of rectangle.
     * @param height Height of rectangle.
     * @param texture Texture to bind to the entity.
     */
    public Entity(Vector2f position, Vector2f velocity, float width, float height, Mesh mesh, Texture texture) {
        this.position = position;
        this.velocity = velocity;
        this.scale = new Vector2f(width, height);
        this.rotation = 0;
        this.mesh = mesh;
        this.texture = texture;
        this.texFbo = texture instanceof FBO;
    }

    /**
     * Override method for handling custom logic
     * @param delta time passed since last update
     */
    public void update(float delta) {  }

    /**
     * Update the entity based on time.
     * @param delta time passed since last update
     */
    public final void _update(float delta) {
        update(delta);
        position = Vector2f.add(position, (Vector2f) velocity.scale(delta), position);
    }

    /**
     * Check whether a point is within the rectangular area of the entity.
     * @param point Point to check
     * @return Whether the point is within the rectangle
     */
    public boolean collides(Vector2f point) {
        if (point.x > (position.x+scale.x/2))
            return false;
        if (point.x < (position.x-scale.x/2))
            return false;
        if (point.y > (position.y+scale.y/2))
            return false;
        if (point.y < (position.y-scale.y/2))
            return false;
        return true;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public Vector2f getScale() {
        return scale;
    }

    public void setScale(Vector2f scale) {
        this.scale = scale;
    }

    public Vector2f getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2f velocity) {
        this.velocity = velocity;
    }
    
    public Mesh getMesh() {
		return mesh;
	}

	public Texture getTexture() {
		return texture;
	}

	public boolean isTexFbo() {
		return texFbo;
	}

	public Color getColor() {
		return color;
	}
}
