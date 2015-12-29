package com.htm;

import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;

import com.htm.game.Entity;
import com.htm.graphic.Renderer;
import com.htm.graphic.shader.Shader;
import com.htm.graphic.shader.impl.BasicShader;
import com.htm.graphic.texture.Texture;
import com.htm.utils.TextureLoader;
import com.htm.utils.Util;

public class Main {
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	private static Shader shader;
	private static Renderer renderer;
	private static TextureLoader texLoader = new TextureLoader();
	
	public static Texture testTexture;
	public static Entity testEntity;
	
	public static void main(String[] args) {
		try {
			long lastTime = System.currentTimeMillis();
            double delta = 0;
			initialize();
			while (!Display.isCloseRequested()) {
				long curr = System.currentTimeMillis();
                delta = (curr - lastTime)/1000;
                lastTime = curr;
                
				update(delta);
				render();
			}
			finish();
		} catch (LWJGLException e) {
			System.err.println("Error initializing OpenGL.");
			System.exit(1);
		}
	}
	
	public static void update(double delta) {
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			testEntity.setRotation(testEntity.getRotation()+0.001f);
		}
	}
	
	public static void render() {
		glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT);
		
		renderer.drawEntity(testEntity);
		
		//Refresh display
		Display.update();
	}

	public static void initialize() throws LWJGLException {
		Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
		Display.setTitle("Here To Mars");
		Display.create();
		
		try {
			testTexture = texLoader.getTexture("test.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		testEntity = new Entity(testTexture, new Vector2f(200.0f, 200.0f), new Vector2f(250.0f, 250.0f));
		
		glViewport(0, 0, WIDTH, HEIGHT);
		glEnable(GL_CULL_FACE);
	    glEnable(GL_BLEND);
	    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		/*
		 * Init rendering
		 */
		Matrix4f projection = new Matrix4f();
		Util.mtxOrtho(projection, 0, WIDTH, HEIGHT, 0, -1, 1);
		
		renderer = new Renderer();
		shader = new BasicShader();
		shader.bind();
		shader.setUniformMatrix4("projection", projection);
		renderer.initialize(shader);
	}
	
	public static void finish() {
		Display.destroy();
	}

}
