package com.darkan.pkmn.engine.text;

import java.awt.Color;

import com.darkan.pkmn.engine.gfx.mesh.Mesh;
import com.darkan.pkmn.engine.text.font.Font;
import com.darkan.pkmn.engine.text.font.FontManager;
import com.darkan.pkmn.engine.util.Vector2f;

public class Text {
	
	private Font font;
	private float fontSize;
	private float maxLineLen;
	private boolean centered;
	private int numLines;
	private String text;
	private Vector2f position;
	private Color color;
	private Mesh mesh;
	
	public Text(String text, String fontName, float fontSize, float maxLineLen, Vector2f position, Color color, boolean centered) {
		this.font = FontManager.getFont(fontName);
		this.text = text;
		this.position = position;
		this.color = color;
		this.fontSize = fontSize;
		this.maxLineLen = maxLineLen;
		this.centered = centered;
		this.mesh = font.create(this);
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
	
	public float getFontSize() {
		return fontSize;
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
}
