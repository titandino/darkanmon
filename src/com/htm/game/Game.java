package com.htm.game;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Matrix4f;

import com.htm.game.level.Level;
import com.htm.graphic.Renderer;
import com.htm.graphic.TextRenderer;
import com.htm.graphic.shader.Shader;
import com.htm.graphic.shader.impl.BasicShader;
import com.htm.graphic.shader.impl.TextShader;
import com.htm.utils.Util;

public class Game {
	
	private Shader shader;
	private Shader textShader;
	private Renderer renderer;
	private TextRenderer textRenderer;
	
	private Level level;
	
	private int width;
	private int height;
	private String windowName;
	
	public Game(int width, int height, String windowName, Level level) {
		try {
			this.width = width;
			this.height = height;
			this.windowName = windowName;
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(windowName);
			Display.create();
			setLevel(level);
			init();
		} catch(LWJGLException e) {
			
		}
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
	
	public void update(double delta) {
		if (level != null)
			level._update(delta);
	}
	
	public void render() {
		if (level != null)
			level.render(renderer, textRenderer);
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		if (this.level != null)
			this.level.finish();
		this.level = level;
		this.level.init();
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

	public String getWindowName() {
		return windowName;
	}

	public void setWindowName(String windowName) {
		Display.setTitle(windowName);
		this.windowName = windowName;
	}

	public TextRenderer getTextRenderer() {
		return textRenderer;
	}

	public Shader getTextShader() {
		return textShader;
	}
}
