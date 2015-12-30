package com.htm.graphic.texture;

import static org.lwjgl.opengl.GL11.*;

public class Texture {
	
	private int id;
	private int width;
	private int height;
	
	public Texture(int id) {
		this.id = id;
	}
	
	public void bind() {
		glBindTexture(GL_TEXTURE_2D, this.id);
	}

	public int getId() {
		return id;
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
