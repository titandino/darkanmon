package com.darkan.pkmn.engine.entity;

import java.awt.Color;

import com.darkan.pkmn.engine.gfx.mesh.Mesh;
import com.darkan.pkmn.engine.gfx.texture.Texture;
import com.darkan.pkmn.engine.render.FBO;

import glm.Glm;
import glm.vec._2.Vec2;

/**
 * Created by trent on 4/6/2018.
 *
 * Represents a rectangular 2D entity that has a texture to bind to it
 * when rendered.
 */
public class Entity {
    //Transform variables
    private Vec2 position;
    private float rotation;
    private Vec2 scale;

    private Vec2 velocity;

    //Texture and texture coordinates
    private Mesh mesh;
	private Texture texture;
    private boolean texFbo;
    private Color color;

    public Entity(Vec2 position, float width, float height, Mesh mesh, Texture texture) {
        this(position, new Vec2(0, 0), width, height, mesh, texture);
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
    public Entity(Vec2 position, Vec2 velocity, float width, float height, Mesh mesh, Texture texture) {
        this.position = position;
        this.velocity = velocity;
        this.scale = new Vec2(width, height);
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
        position = position.add(Glm.mul(velocity, delta));
    }

    /**
     * Check whether a point is within the rectangular area of the entity.
     * @param point Point to check
     * @return Whether the point is within the rectangle
     */
    public boolean collides(Vec2 point) {
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

    public Vec2 getPosition() {
        return position;
    }

    public void setPosition(Vec2 position) {
        this.position = position;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public Vec2 getScale() {
        return scale;
    }

    public void setScale(Vec2 scale) {
        this.scale = scale;
    }

    public Vec2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vec2 velocity) {
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
