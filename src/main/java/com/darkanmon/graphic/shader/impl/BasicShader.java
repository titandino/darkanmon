package com.darkanmon.graphic.shader.impl;

import com.darkanmon.graphic.shader.Shader;

public class BasicShader extends Shader {

	public BasicShader() {
		super("norm");
		addUniform("projection");
		addUniform("transform");
		//addUniform("image");
		addUniform("texColor");
	}

}
