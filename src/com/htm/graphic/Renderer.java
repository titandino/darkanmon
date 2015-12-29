package com.htm.graphic;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import com.htm.game.Entity;
import com.htm.graphic.shader.Shader;

public class Renderer {
	
	private int vaoId;
	private Shader shader;
	
	public void drawEntity(Entity entity) {
		shader.bind();
		Matrix4f transform = new Matrix4f();
		transform.translate(new Vector3f(entity.getPosition().x, entity.getPosition().y, 0.0f));
		transform.translate(new Vector3f(entity.getScale().x/2, entity.getScale().y/2, 0.0f));
		transform.rotate(entity.getRotation(), new Vector3f(0, 0, 1.0f));
		transform.translate(new Vector3f(-(entity.getScale().x/2), -(entity.getScale().y/2), 0.0f));
		transform.scale(new Vector3f(entity.getScale().x, entity.getScale().y, 1.0f));
		
		shader.setUniformMatrix4("transform", transform);
		shader.setUniformVec3("texColor", new Vector3f(0, 1.0f, 0));
				
		glBindVertexArray(vaoId);
		glDrawArrays(GL_TRIANGLES, 0, 6);
		glBindVertexArray(0);
		
	}

	public void initialize(Shader shader) {
		this.shader = shader;
		float vertices[] = { 
			0.0f, 1.0f, 0.0f, 1.0f,
			1.0f, 0.0f, 1.0f, 0.0f,
			0.0f, 0.0f, 0.0f, 0.0f, 

			0.0f, 1.0f, 0.0f, 1.0f,
			1.0f, 1.0f, 1.0f, 1.0f,
			1.0f, 0.0f, 1.0f, 0.0f
		};

		FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length);
		buffer.put(vertices);
		buffer.flip();

		vaoId = glGenVertexArrays();
		int vbo = glGenBuffers();

		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);

		glBindVertexArray(vaoId);
		glEnableVertexAttribArray(0);
		glVertexAttribPointer(0, 4, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}

}
