package com.darkan.engine.util;

import glm.vec._2.Vec2;

public class OffsetTransform implements Transform {
	
    private Transform origin;
    private Transform offset;
    
    public OffsetTransform(Transform origin, Transform offset) {
    	this.origin = origin;
    	this.offset = offset;
    }

    public Vec2 getPosition() {
		return new Vec2(origin.getPosition()).add(offset.getPosition());
	}

	public void setPosition(Vec2 position) {
		offset.setPosition(position);
	}

	public float getRotation() {
		return origin.getRotation() + offset.getRotation();
	}

	public void setRotation(float rotation) {
		offset.setRotation(rotation);
	}

	public Vec2 getScale() {
		return new Vec2(origin.getScale()).add(offset.getScale());
	}

	public void setScale(Vec2 scale) {
		offset.setScale(scale);
	}

	public Transform getOrigin() {
		return origin;
	}

	public void setOrigin(Transform origin) {
		this.origin = origin;
	}

	public Transform getOffset() {
		return offset;
	}

	public void setOffset(Transform offset) {
		this.offset = offset;
	}
}
