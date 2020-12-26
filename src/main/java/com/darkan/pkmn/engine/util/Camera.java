package com.darkan.pkmn.engine.util;

import static org.lwjgl.opengl.GL20.*;

import com.darkan.pkmn.engine.render.Shader;

import glm.mat._4.Mat4;
import glm.vec._2.Vec2;
import glm.vec._3.Vec3;

public class Camera {

	private Vec2 origin;
	private Vec2 position;
	private float rotation;
	private float zoom;
	
	public Camera(Vec2 origin, Vec2 position, float rotation, float zoom) {
		this.origin = origin;
		this.position = position;
		this.rotation = rotation;
		this.zoom = zoom;
	}
	
	public Camera() {
		this(new Vec2(0.0f, 0.0f), new Vec2(0.0f, 0.0f), 0.0f, 1.0f);
	}
	
	public Mat4 getTransform() {
		Mat4 trans = new Mat4().identity();
		
		trans.mul(new Mat4().translate(new Vec3(origin.x, origin.y, 0f)));
		trans.mul(new Mat4().scale(zoom, zoom, 1.0f));
		trans.mul(new Mat4().rotate(rotation, new Vec3(0f, 0f, 1f)));
		trans.mul(new Mat4().translate(new Vec3(position.x, position.y, 0f)));
		
		return trans;
	}
	
	public void bindUniform(Shader shader) {
		glUniformMatrix4fv(shader.getUniformLocation("camMtx"), false, getTransform().toDfb_());
	}
	
	public Vec2 getOrigin() {
		return origin;
	}

	public void setOrigin(Vec2 origin) {
		this.origin = origin;
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

	public float getZoom() {
		return zoom;
	}

	public void setZoom(float zoom) {
		this.zoom = zoom;
	}
}
