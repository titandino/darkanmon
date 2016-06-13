package com.htm.graphic;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import com.htm.game.object.Entity;
import com.htm.graphic.shader.Shader;

public class Renderer {
	
	private int vaoId;
	private Shader shader;
	
	public void drawEntity(Entity entity) {
		//Bind shader to use
		shader.bind();
		
		//Initialize transformation matrix
		Matrix4f transform = new Matrix4f();
		//translate
		transform.translate(new Vector3f((float) entity.getTransform().getTranslation().x, (float) entity.getTransform().getTranslation().y, 0.0f));
		//rotate from the center of the entity
		transform.translate(new Vector3f(entity.getScale().x/2, entity.getScale().y/2, 0.0f));
		transform.rotate((float) entity.getTransform().getRotation(), new Vector3f(0, 0, 1.0f));
		//move the entity back to normal position
		transform.translate(new Vector3f(-(entity.getScale().x/2), -(entity.getScale().y/2), 0.0f));
		//scale
		transform.scale(new Vector3f(entity.getScale().x, entity.getScale().y, 1.0f));
		
		//update shader with transformations and texture.
		shader.setUniformMatrix4("transform", transform);
		
		if (entity.getColor() != null)
			shader.setUniformVec3("texColor", entity.getColor());
		else
			shader.setUniformVec3("texColor", new Vector3f(1.0f, 1.0f, 1.0f));
		
		//Bind and use texture
		if (entity.getTexture() != null) {
			shader.setUniformInteger("image", 0);
			glActiveTexture(GL_TEXTURE0);
			entity.getTexture().bind();
		}
		
		//Draw quad from VAO
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

		//Convert vertices to floatbuffer for storage in VBO
		FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length);
		buffer.put(vertices);
		buffer.flip();

		vaoId = glGenVertexArrays();
		int vbo = glGenBuffers();

		//Bind VAO to VBO
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);

		//Set VAO to be used
		glBindVertexArray(vaoId);
		//Set up the size of the data within the buffer
		glEnableVertexAttribArray(0);
		glVertexAttribPointer(0, 4, GL_FLOAT, false, 0, 0);
		//unbind the VAO
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}

}
