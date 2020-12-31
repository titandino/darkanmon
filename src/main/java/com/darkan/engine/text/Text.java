package com.darkan.engine.text;

import java.awt.Color;

import com.darkan.engine.gfx.mesh.Mesh;
import com.darkan.engine.text.font.Font;
import com.darkan.engine.text.font.FontManager;
import com.darkan.engine.util.BaseTransform;
import com.darkan.engine.util.Transform;

import glm.vec._2.Vec2;

public class Text {
	private Transform transform;
	private Font font;
	private float maxLineLen;
	private boolean centered;
	private int numLines;
	private String text;
	private Color color;
	private Mesh mesh;
	private TextEffects effects;
	
	public Text(String text, String fontName, float fontSize, float maxLineLen, Vec2 position, Color color, boolean centered) {
		this.transform = new BaseTransform(position, new Vec2(fontSize * 1.5f, fontSize));
		this.font = FontManager.getFont(fontName);
		this.text = text;
		this.color = color;
		this.maxLineLen = maxLineLen;
		this.centered = centered;
		this.mesh = font.create(this);
		this.effects = new TextEffects();
	}
	
	public Font getFont() {
		return font;
	}

	public String getText() {
		return text;
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

    public Vec2 getPosition() {
        return transform.getPosition();
    }

    public void setPosition(Vec2 position) {
        transform.setPosition(position);
    }

    public float getRotation() {
        return transform.getRotation();
    }

    public void setRotation(float rotation) {
        transform.setRotation(rotation);
    }

    public Vec2 getScale() {
        return transform.getScale();
    }

    public void setScale(Vec2 scale) {
        transform.setScale(scale);
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

	public Transform getTransform() {
		return transform;
	}

	public void setTransform(BaseTransform transform) {
		this.transform = transform;
	}
}
