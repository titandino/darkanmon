package com.htm;

import org.lwjgl.opengl.Display;
import com.htm.game.Game;
import com.htm.game.level.impl.Tutorial;

public class Main {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	public static Game game;

	public static void main(String[] args) {
		long lastTime;
		double delta = 0;
		initialize();
		lastTime = System.nanoTime();
		while (!Display.isCloseRequested()) {
			long curr = System.nanoTime();
			delta = (curr - lastTime) / 1000000000.0;
			lastTime = curr;
			update(delta);
			Display.setTitle("Here To Mars (" + Double.toString(delta) + ")");
			render();
		}
		finish();
	}
	
	public static void initialize() {
		game = new Game(WIDTH, HEIGHT, "Here To Mars", new Tutorial());
	}

	public static void update(double delta) {
		game.update(delta);
	}

	public static void render() {
		game.render();
	}

	public static void finish() {
		Display.destroy();
	}

}
