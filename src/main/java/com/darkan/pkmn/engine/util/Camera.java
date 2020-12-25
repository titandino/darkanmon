package com.darkan.pkmn.engine.util;

import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import com.darkan.pkmn.engine.render.Shader;

public class Camera {

	private Vector2f origin;
	private Vector2f position;
	private float rotation;
	private float zoom;
	
	public Camera(Vector2f origin, Vector2f position, float rotation, float zoom) {
		this.origin = origin;
		this.position = position;
		this.rotation = rotation;
		this.zoom = zoom;
	}
	
	public Camera() {
		this(new Vector2f(0.0f, 0.0f), new Vector2f(0.0f, 0.0f), 0.0f, 1.0f);
	}
	
	public Matrix4f getTransform() {
		Matrix4f transform = new Matrix4f();
		transform.translate(origin);
		Matrix4f.mul(transform, new Matrix4f().rotate(rotation, new Vector3f(0f, 0f, 1f)), transform);
		Matrix4f.mul(transform, new Matrix4f().scale(new Vector3f(zoom, zoom, 1f)), transform);
		Matrix4f.mul(transform, new Matrix4f().translate(position), transform);
		return transform;
	}
	
	public void bindUniform(Shader shader) {
		FloatBuffer transform = FloatBuffer.allocate(16);
		getTransform().store(transform);
		glUniformMatrix4fv(shader.getUniformLocation("camMtx"), false, transform);
	}
	
	public Vector2f getOrigin() {
		return origin;
	}

	public void setOrigin(Vector2f origin) {
		this.origin = origin;
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

	public float getZoom() {
		return zoom;
	}

	public void setZoom(float zoom) {
		this.zoom = zoom;
	}
}
