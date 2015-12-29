package com.htm.graphic.shader;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL11.*;

import java.nio.FloatBuffer;
import java.util.HashMap;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import com.htm.utils.ResourceManager;

public abstract class Shader {
	
	private int program;
	private HashMap<String, Integer> uniforms;

	public Shader(String file) {
		program = glCreateProgram();
		uniforms = new HashMap<String, Integer>();

		if (program == 0) {
			System.err.println("Error creating shader. Could not find valid memory location.");
		}
		addVertexShader(ResourceManager.loadShader(file+".vs"));
	    addFragmentShader(ResourceManager.loadShader(file+".fs"));
		compile();
	}

	public void addVertexShader(String text) {
		addProgram(text, GL_VERTEX_SHADER);
	}

	public void addFragmentShader(String text) {
		addProgram(text, GL_FRAGMENT_SHADER);
	}
	
	public void setUniformFloat(String uniform, float value) {
		
		glUniform1f(glGetUniformLocation(program, uniform), value);
	}
	
	public void setUniformInteger(String uniform, int value) {
		glUniform1i(glGetUniformLocation(program, uniform), value);
	}
	
	public void setUniformVec2(String uniform, Vector2f value) {
		glUniform2f(glGetUniformLocation(program, uniform), value.x, value.y);
	}
	
	public void setUniformVec3(String uniform, Vector3f value) {
		glUniform3f(glGetUniformLocation(program, uniform), value.x, value.y, value.z);
	}
	
	public void setUniformVec4(String uniform, Vector4f value) {
		glUniform4f(glGetUniformLocation(program, uniform), value.x, value.y, value.z, value.w);
	}
	
	public void setUniformMatrix4(String uniform, Matrix4f value) {
		FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
		value.store(matrixBuffer);
		matrixBuffer.flip();
		glUniformMatrix4(glGetUniformLocation(program, uniform), false, matrixBuffer);
	}
	
	protected void addUniform(String uniform) {
		int uniformLocation = glGetUniformLocation(program, uniform);
		
		if(uniformLocation == 0xFFFFFFFF)
		{
			System.err.println("Error adding uniform: " + uniform);
			System.exit(1);
		}
		
		uniforms.put(uniform, uniformLocation);
	}

	public void addProgram(String shaderText, int type) {
		int shader = glCreateShader(type);

		if (shader == 0) {
			System.err.println("Error creating shader.");
		}

		glShaderSource(shader, shaderText);
		glCompileShader(shader);
		if (glGetShaderi(shader, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println(glGetShaderInfoLog(shader, 2048));
		}

		glAttachShader(program, shader);

	}

	public void compile() {
		glLinkProgram(program);

		if (glGetShaderi(program, GL_LINK_STATUS) == GL_FALSE) {
			System.err.println(glGetProgramInfoLog(program, 2048));
		}

		glValidateProgram(program);

		if (glGetShaderi(program, GL_VALIDATE_STATUS) == GL_FALSE) {
			System.err.println(glGetProgramInfoLog(program, 2048));
		}
	}

	public void bind() {
		glUseProgram(program);
	}
}