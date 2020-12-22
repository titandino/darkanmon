package com.darkan.pkmn.engine.render;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL20.*;

/**
 * Created by trent on 4/6/2018.
 *
 * Class to represent a texture, bind/unbind it, and unload it.
 */
public class Texture {

    protected int textureId;
    protected int width;
	protected int height;

    /**
     * Generate a texture from a bitmap.
     * @param bmp The bitmap to load.
     */
    public Texture(int textureId) {
    	this.textureId = textureId;
    }
    
    /**
     * Bind the texture for rendering.
     */
    public void bind() {
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, textureId);
    }

    /**
     * Bind the texture for rendering.
     * @param uniform uniform pointer
     */
    public void bind(int uniform) {
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, textureId);
        glUniform1i(uniform, 0);
    }

    /**
     * Releases the texture from memory.
     */
    public void release() {
        glDeleteTextures(textureId);
    }

    /**
     * Gets the handle to the texture.
     * @return handle to the texture
     */
    public int getHandle() {
        return textureId;
    }
    
    public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
