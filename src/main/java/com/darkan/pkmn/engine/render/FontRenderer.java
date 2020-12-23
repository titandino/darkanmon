package com.darkan.pkmn.engine.render;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import com.darkan.pkmn.engine.GameManager;
import com.darkan.pkmn.engine.Level;
import com.darkan.pkmn.engine.base.Window;
import com.darkan.pkmn.engine.text.Text;
import com.darkan.pkmn.engine.util.Util;

public class FontRenderer extends Renderer {

	public FontRenderer(Window window) {
		super(window, "font.vert", "font.frag");
	}

	@Override
	public void prepare() {
		Util.glOrtho(getShader(), GameManager.getResolution().getWidth(),  GameManager.getResolution().getHeight());
	}

	@Override
	public void render(Level level) {
		//TODO loop over level text and render using renderText()
		
		level.renderExtraFont(this);
	}
	
	public void render(Text text) {
		text.getMesh().bind();
		text.getFont().getTextureAtlas().bind(getShader().getUniformLocation("fontAtlas"));

        //Flip y axis of texture coordinates if it is an FBO as a texture
        glUniform4fv(getShader().getUniformLocation("color"), new float[] { text.getColor() == null ? 2.0f : text.getColor().getRed() / 255f, text.getColor() == null ? 2.0f : text.getColor().getGreen() / 255f, text.getColor() == null ? 2.0f : text.getColor().getBlue() / 255f, text.getColor() == null ? 2.0f : text.getColor().getAlpha() / 255f });
		
        //Pass transformation to shader
        glUniform2fv(getShader().getUniformLocation("translation"), new float[] { text.getPosition().x, text.getPosition().y });
        glUniform1f(getShader().getUniformLocation("rotation"), text.getRotation());
        glUniform2fv(getShader().getUniformLocation("scale"), new float[] { text.getScale().x, text.getScale().y });
        
        //Draw the entity
        glDrawArrays(GL_TRIANGLES, 0, text.getMesh().getVertexCount());
	}

	@Override
	public void end() {
		
	}

}
