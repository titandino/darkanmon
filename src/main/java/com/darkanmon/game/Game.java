package com.darkanmon.game;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.vector.Matrix4f;

import com.darkanmon.base.Mouse;
import com.darkanmon.base.Window;
import com.darkanmon.game.level.Level;
import com.darkanmon.graphic.Renderer;
import com.darkanmon.graphic.TextRenderer;
import com.darkanmon.graphic.shader.Shader;
import com.darkanmon.graphic.shader.impl.BasicShader;
import com.darkanmon.graphic.shader.impl.TextShader;
import com.darkanmon.utils.Util;

public class Game {

	private Shader shader;
	private Shader textShader;
	private Renderer renderer;
	private TextRenderer textRenderer;
	
	private Level level;
	
	private Window window;
	private int width;
	private int height;
	
	private static Game singleton;
	
	public Game(Window window, Level level) {
		if (singleton != null)
			throw new Error("Game instance has already been initialized.");
		this.window = window;
		this.width = window.getWidth();
		this.height = window.getHeight();
		setLevel(level);
		init();
		singleton = this;
	}
	
	public void init() {
		glViewport(0, 0, width, height);
		glEnable(GL_CULL_FACE);
	    glEnable(GL_BLEND); //enable alpha
	    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		Matrix4f projection = new Matrix4f();
		Util.mtxOrtho(projection, 0, width, height, 0, -1, 1);
		
		renderer = new Renderer();
		shader = new BasicShader();
		shader.bind();
		shader.setUniformMatrix4("projection", projection);
		shader.setUniformInteger("image", 0);
		renderer.initialize(shader);
		
		textRenderer = new TextRenderer();
		textShader = new TextShader();
		textShader.bind();
		textShader.setUniformMatrix4("projection", projection);
		textShader.setUniformInteger("text", 0);
		textRenderer.initialize(textShader);
		textRenderer.loadFont("sans.ttf", 20);
	}
	
	public final void input() {
		window.updateInputs();
	}
	
	public final void update(double delta) {
		if (level != null)
			level._update(delta);
	}
	
	public final void render() {
		if (level != null)
			level.render(renderer, textRenderer);
		window.postRender();
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		if (this.level != null) {
			this.level._finish();
		}
		this.level = level;
		this.level.init();
	}
	
	public void finish() {
		window.close();
	}

	public Renderer getRenderer() {
		return renderer;
	}

	public Shader getShader() {
		return shader;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public TextRenderer getTextRenderer() {
		return textRenderer;
	}

	public Shader getTextShader() {
		return textShader;
	}
	
	public static Game get() {
		return singleton;
	}
	
	public static Mouse getMouse() {
		return singleton.window.getMouse();
	}

	public static Window getWindow() {
		return singleton.window;
	}
}
