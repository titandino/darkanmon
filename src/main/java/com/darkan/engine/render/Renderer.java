package com.darkan.engine.render;

import static org.lwjgl.opengl.GL11.*;

import com.darkan.engine.Scene;
import com.darkan.engine.base.Window;
import com.darkan.engine.gfx.mesh.MeshManager;
import com.darkan.engine.gfx.texture.TextureManager;

public abstract class Renderer {
	
	private Window window;
	private Shader shader;
	
	public Renderer(Window window, String vShaderFile, String fShaderFile) {
		this.window = window;
		shader = new Shader(vShaderFile, fShaderFile);
	}
	
	public abstract void prepare(Scene scene);
	
	public void _prepare(Scene scene) {
		shader.use();
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		prepare(scene);
	}
	
	public abstract void render(Scene scene);
	
	public final void _render(Scene scene) {
		_prepare(scene);
		render(scene);
		_end();
	}
	
	public final void _end() {
		shader.stop();
		end();
	}

	public abstract void end();
	
	public void unload() {
		if (shader != null)
			shader.unload();
		TextureManager.unloadTextures();
		MeshManager.unloadMeshes();
	}
	
	public Shader getShader() {
		return shader;
	}

	public Window getWindow() {
		return window;
	}
}
