package com.htm.graphic.shader.impl;

import com.htm.graphic.shader.Shader;

public class TextShader extends Shader {
	
	public TextShader() {
		super("text");
		addUniform("text");
		addUniform("textColor");
		addUniform("projection");
	}

}
