package com.darkan.engine.render;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import com.darkan.engine.Scene;
import com.darkan.engine.base.Window;
import com.darkan.engine.entity.Entity;

public class EntityRenderer extends Renderer {

	public EntityRenderer(Window window) {
		super(window, "normal.vert", "normal.frag");
	}

	@Override
	public void prepare(Scene scene) {
		scene.getCamera().bindUniform(getShader());
	}
	
	public void render(Scene scene) {
		for (Entity entity : scene.getWorldEntities().getSortedEntities())
			render(entity);
		scene.renderExtraEntity(this);
	}
	
	public void render(Entity entity) {
		entity.getMesh().bind();
        entity.getTexture().bind(getShader().getUniformLocation("tex"));

        //Flip y axis of texture coordinates if it is an FBO as a texture
        glUniform1i(getShader().getUniformLocation("flip"), entity.isTexFbo() ? 1 : 0);

        glUniform4fv(getShader().getUniformLocation("color"), new float[] { entity.getColor() == null ? 2.0f : entity.getColor().getRed() / 255f, entity.getColor() == null ? 2.0f : entity.getColor().getGreen() / 255f, entity.getColor() == null ? 2.0f : entity.getColor().getBlue() / 255f, entity.getColor() == null ? 2.0f : entity.getColor().getAlpha() / 255f });

        //Pass transformation to shader
        glUniform2fv(getShader().getUniformLocation("translation"), new float[] { entity.getPosition().x, entity.getPosition().y });
        glUniform1f(getShader().getUniformLocation("rotation"), entity.getRotation());
        glUniform2fv(getShader().getUniformLocation("scale"), new float[] { entity.getScale().x, entity.getScale().y });

        //Draw the entity
        glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);
        entity.getMesh().unbind();
	}


	@Override
	public void end() {
		glDisableVertexAttribArray(0);
   	 	glDisableVertexAttribArray(1);
	}
}
