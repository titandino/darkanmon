package com.darkan.pkmn.engine.text;

import java.awt.Color;

import com.darkan.pkmn.engine.util.Vector2f;

public class TextEffects {
	
	private float smoothingWidth;
	private float smoothingEdge;
	private float borderWidth;
	private float borderEdge;
	private Color borderColor;
	private Vector2f borderOffset;
	
	public TextEffects() {
		none();
	}
	
	public TextEffects none() {
		smoothingWidth = 0.5f;
		smoothingEdge = 0.1f;
		borderWidth = 0.0f;
		borderEdge = 0.35f;
		borderColor = Color.BLACK;
		borderOffset = new Vector2f(0.0f, 0.0f);
		return this;
	}
	
	public TextEffects addBorder(Color color) {
		borderColor = color;
		borderWidth = 0.7f;
		borderEdge = 0.1f;
		return this;
	}
	
	public TextEffects addDropShadow(Color color) {
		borderColor = color;
		borderWidth = 0.7f;
		borderEdge = 0.1f;
		borderOffset = new Vector2f(0.006f, 0.006f);
		return this;
	}
	
	public TextEffects addDropShadow() {
		return addDropShadow(Color.BLACK);
	}
	
	public float getSmoothingWidth() {
		return smoothingWidth;
	}

	public void setSmoothingWidth(float smoothingWidth) {
		this.smoothingWidth = smoothingWidth;
	}

	public float getSmoothingEdge() {
		return smoothingEdge;
	}

	public void setSmoothingEdge(float smoothingEdge) {
		this.smoothingEdge = smoothingEdge;
	}

	public float getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(float borderWidth) {
		this.borderWidth = borderWidth;
	}

	public float getBorderEdge() {
		return borderEdge;
	}

	public void setBorderEdge(float borderEdge) {
		this.borderEdge = borderEdge;
	}

	public Vector2f getBorderOffset() {
		return borderOffset;
	}

	public void setBorderOffset(Vector2f borderOffset) {
		this.borderOffset = borderOffset;
	}
	
	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
}
