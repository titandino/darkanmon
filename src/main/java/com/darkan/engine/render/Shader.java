package com.darkan.engine.render;

import java.nio.IntBuffer;
import java.util.HashMap;

import org.lwjgl.BufferUtils;

import com.darkan.engine.util.Util;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

/**
 * Created by trent on 4/6/2018.
 *
 * Represents the shader for rendering process.
 */
public class Shader {
	protected HashMap<String, Integer> uniforms = new HashMap<>();
	private int[] shaders;
	private int shaderProgram;

	/**
	 * Creates a vertex, fragment, and program id for the shader.
	 */
	public Shader(String... sources) {
		shaders = new int[sources.length];
		for (int i = 0; i < shaders.length; i++) {
			if (!sources[i].contains(".frag") && !sources[i].contains(".vert")) {
				System.err.println("Invalid shader extension: " + sources[i]);
				return;
			}
			shaders[i] = loadShader(sources[i].contains(".frag") ? GL_FRAGMENT_SHADER : GL_VERTEX_SHADER, Util.readTextAsset("./res/shaders/"+sources[i]));
		}
		shaderProgram = createProgram(shaders);
		fetchUniforms();
	}

	/**
	 * Loads a shader from the source text.
	 *
	 * @param shaderType
	 *            The type of shader. Either GL_VERTEX_SHADER or GL_FRAGMENT_SHADER.
	 * @param shaderSource
	 *            Source text for the shader.
	 * @return The handle to the shader.
	 */
	private int loadShader(int shaderType, String shaderSource) {
		int handle = glCreateShader(shaderType);

		if (handle == GL_FALSE)
			throw new RuntimeException("Error creating shader!");

		glShaderSource(handle, shaderSource);
		glCompileShader(handle);

		if (glGetShaderi(handle, GL_COMPILE_STATUS) == 0) {
			String error = glGetShaderInfoLog(handle);
			glDeleteShader(handle);
			System.err.println("Error compiling shader: " + shaderType);
			System.err.println(error);
			return -1;
		} else
			return handle;
	}

	/**
	 * Creates a render shader program from a vertex and fragment shader.
	 *
	 * @param shaders
	 *            Array of shader handles to be attached
	 * @return The handle to the shader program.
	 */
	private int createProgram(int... shaders) {
		int handle = glCreateProgram();

		if (handle == GL_FALSE)
			throw new RuntimeException("Error creating program!");

		for (int shader : shaders) {
			glAttachShader(handle, shader);
		}
		glLinkProgram(handle);

		if (glGetProgrami(handle, GL_LINK_STATUS) == 0) {
			String error = glGetProgramInfoLog(handle);
			glDeleteProgram(handle);
			System.err.println(error);
			throw new RuntimeException("Error in program linking: " + error);
		} else
			return handle;
	}

	/**
	 * Maps all uniforms from the shader for much more efficient retrieval and
	 * seeing.
	 */
	private void fetchUniforms() {
		int len = glGetProgrami(shaderProgram, GL_ACTIVE_UNIFORMS);
		int strLen = glGetProgrami(shaderProgram, GL_ACTIVE_UNIFORM_MAX_LENGTH);
		IntBuffer sizeBuffer = BufferUtils.createIntBuffer(1);
		IntBuffer typeBuffer = BufferUtils.createIntBuffer(1);
		for (int i = 0; i < len; i++) {
			String name = glGetActiveUniform(shaderProgram, i, strLen, sizeBuffer, typeBuffer);
			int id = glGetUniformLocation(shaderProgram, name);
			uniforms.put(name, id);
		}
	}

	/**
	 * Gets the handle to a uniform on the shader.
	 * 
	 * @param name
	 *            Name of the uniform
	 * @return The uniform handle
	 */
	public int getUniformLocation(String name) {
		Integer location = uniforms.get(name);
		if (location == null) {
			location = glGetUniformLocation(shaderProgram, name);
			uniforms.put(name, location);
		}
		return location.intValue();
	}

	/**
	 * Bind the shader.
	 * 
	 * @return this shader for chaining.
	 */
	public Shader use() {
		glUseProgram(shaderProgram);
		return this;
	}
	
	public Shader stop() {
		glUseProgram(0);
		return this;
	}

	/**
	 * Unload the shader.
	 */
	public void unload() {
		glDeleteProgram(shaderProgram);
		for (int shader : shaders)
			glDeleteShader(shader);
	}
}
