package com.htm.graphic;

import org.lwjgl.util.vector.Vector2f;

public class Character {
	
	private int textureId;
	private Vector2f size;
	
	public Character(int textureId) {
		this.textureId = textureId;
	}

	public int getTextureId() {
		return textureId;
	}

	public Vector2f getSize() {
		return size;
	}

	public void setSize(Vector2f size) {
		this.size = size;
	}
}
