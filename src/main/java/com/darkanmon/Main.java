package com.darkanmon;

import com.darkanmon.base.Window;
import com.darkanmon.game.Game;
import com.darkanmon.game.level.impl.Tutorial;

public class Main {

	public static final int WIDTH = 1600;
	public static final int HEIGHT = 900;
	
	public static void main(String[] args) {
		long lastTime;
		double delta = 0;
		initialize();
		lastTime = System.nanoTime();
		while (!Game.getWindow().shouldClose()) {
			long curr = System.nanoTime();
			delta = (curr - lastTime) / 1000000000.0;
			lastTime = curr;
			input();
			update(delta);
			render();
		}
		Game.get().finish();
	}
	
	public static void initialize() {
		new Game(new Window("Darkanmon", WIDTH, HEIGHT), new Tutorial());
	}
	
	public static void input() {
		Game.get().input();
	}

	public static void update(double delta) {
		Game.get().update(delta);
	}

	public static void render() {
		Game.get().render();
	}
}
