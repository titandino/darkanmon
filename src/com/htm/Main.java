package com.htm;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import com.htm.game.Game;
import com.htm.game.level.impl.MainMenu;

public class Main {
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	public static Game game;
	
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
		game.update(delta);
	}
	
	public static void render() {
		game.render();
	}

	public static void initialize() throws LWJGLException {
		game = new Game(WIDTH, HEIGHT, "Here To Mars", new MainMenu());
	}
	
	public static void finish() {
		Display.destroy();
	}

}
