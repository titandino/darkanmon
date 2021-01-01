package com.darkan.engine.entity.text;

import java.awt.Color;

import com.darkan.engine.entity.Entity;
import com.darkan.engine.render.RenderPriority;
import com.darkan.engine.util.BaseTransform;
import com.darkan.engine.util.OffsetTransform;
import com.darkan.engine.util.font.Font;
import com.darkan.engine.util.font.FontManager;

import glm.vec._2.Vec2;

public class Text extends Entity {
	private Font font;
	private float maxLineLen;
	private boolean centered;
	private int numLines;
	private String text;
	private TextEffects effects;
	
	public Text(String text, String fontName, float fontSize, float maxLineLen, Vec2 position, Color color, boolean centered) {
		super(RenderPriority.MAIN, position, fontSize, fontSize, null, null);
		this.transform = new BaseTransform(position, new Vec2(fontSize, fontSize));
		this.text = text;
		this.color = color;
		this.maxLineLen = maxLineLen;
		this.centered = centered;
		this.effects = new TextEffects();
		this.font = FontManager.getFont(fontName);
		this.texture = font.getTextureAtlas();
		this.mesh = font.create(this);
	}
	
	public Text(String text, String fontName, float fontSize, Vec2 position, Color color, boolean centered) {
		this(text, fontName, fontSize, Integer.MAX_VALUE, position, color, centered);
	}
	
	public Text(String text, String fontName, OffsetTransform transform, Color color, boolean centered) {
		this(text, fontName, transform.getScale().x, Integer.MAX_VALUE, transform.getPosition(), color, centered);
		this.setTransform(transform);
	}
	
	public Font getFont() {
		return font;
	}

	public String getText() {
		return text;
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

	public TextEffects getEffects() {
		return effects;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setEffects(TextEffects effects) {
		this.effects = effects;
	}
}
