package com.darkan.engine.util;

import glm.vec._2.Vec2;

public interface Transform {
	Vec2 getPosition();

	void setPosition(Vec2 position);

	float getRotation();

	void setRotation(float rotation);

	Vec2 getScale();

	void setScale(Vec2 scale);
}
