package com.darkanmon.graphic.shader.impl;

import com.darkanmon.graphic.shader.Shader;

public class TextShader extends Shader {
	
	public TextShader() {
		super("text");
		addUniform("text");
		addUniform("textColor");
		addUniform("projection");
	}

}
