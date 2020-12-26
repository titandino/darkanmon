package com.darkan.pkmn.engine.text;

import java.awt.Color;

import com.darkan.pkmn.engine.gfx.mesh.Mesh;
import com.darkan.pkmn.engine.text.font.Font;
import com.darkan.pkmn.engine.text.font.FontManager;

import glm.vec._2.Vec2;

public class Text {
	private Font font;
	private float maxLineLen;
	private boolean centered;
	private int numLines;
	private String text;
	private Vec2 position;
    private float rotation;
    private Vec2 scale;
	private Color color;
	private Mesh mesh;
	private TextEffects effects;
	
	public Text(String text, String fontName, float fontSize, float maxLineLen, Vec2 position, Color color, boolean centered) {
		this.font = FontManager.getFont(fontName);
		this.text = text;
		this.position = position;
		this.color = color;
		this.maxLineLen = maxLineLen;
		this.centered = centered;
		this.scale = new Vec2(fontSize * 1.5f, fontSize);
		this.mesh = font.create(this);
		this.effects = new TextEffects();
	}
	
	public Font getFont() {
		return font;
	}

	public String getText() {
		return text;
	}

	public Vec2 getPosition() {
		return position;
	}

	public Color getColor() {
		return color;
	}

	public Mesh getMesh() {
		return mesh;
	}

	public float getMaxLineLen() {
		return maxLineLen;
	}

	public boolean isCentered() {
		return centered;
	}

	public int getNumLines() {
		return numLines;
	}

	public void setNumLines(int numLines) {
		this.numLines = numLines;
	}

	public float getRotation() {
		return rotation;
	}

	public Vec2 getScale() {
		return scale;
	}

	public TextEffects getEffects() {
		return effects;
	}
	
	public void setPosition(Vec2 position) {
		this.position = position;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public void setScale(Vec2 scale) {
		this.scale = scale;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setEffects(TextEffects effects) {
		this.effects = effects;
	}
}
