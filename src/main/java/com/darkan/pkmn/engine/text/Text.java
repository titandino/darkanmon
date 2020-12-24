package com.darkan.pkmn.engine.text;

import java.awt.Color;

import com.darkan.pkmn.engine.gfx.mesh.Mesh;
import com.darkan.pkmn.engine.text.font.Font;
import com.darkan.pkmn.engine.text.font.FontManager;
import com.darkan.pkmn.engine.util.Vector2f;

public class Text {
	private Font font;
	private float maxLineLen;
	private boolean centered;
	private int numLines;
	private String text;
	private Vector2f position;
    private float rotation;
    private Vector2f scale;
	private Color color;
	private Mesh mesh;
	private TextEffects effects;
	
	public Text(String text, String fontName, float fontSize, float maxLineLen, Vector2f position, Color color, boolean centered) {
		this.font = FontManager.getFont(fontName);
		this.text = text;
		this.position = position;
		this.color = color;
		this.maxLineLen = maxLineLen;
		this.centered = centered;
		this.scale = new Vector2f(fontSize * 1.5f, fontSize);
		this.mesh = font.create(this);
		this.effects = new TextEffects();
	}
	
	public Font getFont() {
		return font;
	}

	public String getText() {
		return text;
	}

	public Vector2f getPosition() {
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

	public Vector2f getScale() {
		return scale;
	}

	public TextEffects getEffects() {
		return effects;
	}
	
	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public void setScale(Vector2f scale) {
		this.scale = scale;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setEffects(TextEffects effects) {
		this.effects = effects;
	}
}
