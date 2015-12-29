package com.htm.graphic.shader.impl;

import com.htm.graphic.shader.Shader;

public class BasicShader extends Shader {

	public BasicShader() {
		super("norm");
		addUniform("projection");
		addUniform("transform");
		//addUniform("image");
		addUniform("texColor");
	}

}
