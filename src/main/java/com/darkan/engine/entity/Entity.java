package com.darkan.engine.entity;

import java.awt.Color;

import com.darkan.engine.gfx.mesh.Mesh;
import com.darkan.engine.gfx.texture.Texture;
import com.darkan.engine.render.FBO;
import com.darkan.engine.render.RenderPriority;
import com.darkan.engine.util.BaseTransform;
import com.darkan.engine.util.Transform;

import glm.vec._2.Vec2;

/**
 * Created by trent on 4/6/2018.
 *
 * Represents a rectangular 2D entity that has a texture to bind to it
 * when rendered.
 */
public class Entity {
	
	protected Transform transform;
	protected Vec2 velocity;

    //Texture and texture coordinates
	protected Mesh mesh;
	protected Texture texture;
	protected boolean texFbo;
    protected Color color;
    protected boolean markedForDeletion = false;

	protected RenderPriority priority;
    
    public Entity(Vec2 position, float width, float height, Mesh mesh, Texture texture) {
        this(RenderPriority.MAIN, position, new Vec2(0, 0), width, height, mesh, texture);
    }

    public Entity(RenderPriority priority, Vec2 position, float width, float height, Mesh mesh, Texture texture) {
        this(priority, position, new Vec2(0, 0), width, height, mesh, texture);
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
    public Entity(RenderPriority priority, Vec2 position, Vec2 velocity, float width, float height, Mesh mesh, Texture texture) {
    	this.transform = new BaseTransform(position, 0.0f, new Vec2(width, height));
        this.velocity = velocity;
        this.mesh = mesh;
        this.texture = texture;
        this.texFbo = texture instanceof FBO;
        this.priority = priority;
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
        transform.getPosition().add(new Vec2(velocity).mul(delta));
    }

    /**
     * Check whether a point is within the rectangular area of the entity.
     * @param point Point to check
     * @return Whether the point is within the rectangle
     */
    public boolean collides(Vec2 point) {
        if (point.x > (getPosition().x+getScale().x/2))
            return false;
        if (point.x < (getPosition().x-getScale().x/2))
            return false;
        if (point.y > (getPosition().y+getScale().y/2))
            return false;
        if (point.y < (getPosition().y-getScale().y/2))
            return false;
        return true;
    }
    
    public boolean isMarkedForDeletion() {
		return markedForDeletion;
	}

	public void delete() {
		this.markedForDeletion = true;
	}

    public void setColor(Color color) {
        this.color = color;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Vec2 getPosition() {
        return transform.getPosition();
    }

    public void setPosition(Vec2 position) {
        transform.setPosition(position);
    }

    public float getRotation() {
        return transform.getRotation();
    }

    public void setRotation(float rotation) {
        transform.setRotation(rotation);
    }

    public Vec2 getScale() {
        return transform.getScale();
    }

    public void setScale(Vec2 scale) {
        transform.setScale(scale);
    }

    public Vec2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vec2 velocity) {
        this.velocity = velocity;
    }
    
    public Transform getTransform() {
    	return transform;
    }
    
    public void setTransform(BaseTransform transform) {
    	this.transform = transform;
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

	public RenderPriority getPriority() {
		return priority;
	}
}
