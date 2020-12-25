package com.darkan.pkmn.engine.render;

import static org.lwjgl.opengl.GL11.*;

import com.darkan.pkmn.engine.Level;
import com.darkan.pkmn.engine.base.Window;
import com.darkan.pkmn.engine.gfx.mesh.MeshManager;
import com.darkan.pkmn.engine.gfx.texture.TextureManager;

public abstract class Renderer {
	
	private Window window;
	private Shader shader;
	
	public Renderer(Window window, String vShaderFile, String fShaderFile) {
		this.window = window;
		shader = new Shader(vShaderFile, fShaderFile);
	}
	
	public abstract void prepare(Level level);
	
	public void _prepare(Level level) {
		shader.use();
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		prepare(level);
	}
	
	public abstract void render(Level level);
	
	public final void _render(Level level) {
		_prepare(level);
		render(level);
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
