package com.htm.graphic;

public class Character {
	
	private int textureId;
	private float size;
	
	public Character(int textureId, float size) {
		this.textureId = textureId;
		this.size = size;
	}

	public int getTextureId() {
		return textureId;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}
}
