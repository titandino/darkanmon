package com.darkan.engine.util;

import glm.vec._2.Vec2;

public class BaseTransform implements Transform {
	
    private Vec2 position;
	private float rotation;
    private Vec2 scale;
    
    public BaseTransform(Vec2 position, float rotation, Vec2 scale) {
    	this.position = position;
    	this.rotation = rotation;
    	this.scale = scale;
    }
    
    public BaseTransform(Vec2 position, Vec2 scale) {
    	this(position, 0.0f, scale);
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
	
	@Override
	public String toString() {
		return "["+position+", " + rotation + ", " + scale + "]";
	}
}
