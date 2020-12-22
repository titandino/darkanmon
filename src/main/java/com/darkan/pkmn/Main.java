package com.darkan.pkmn;

import com.darkan.pkmn.engine.render.LevelRenderer;
import com.darkan.pkmn.levels.MainMenu;

public class Main {
	
	private static int WINDOW_WIDTH = 1280;
	private static int WINDOW_HEIGHT = 720;
	
	public static void main(String[] args) {
		new LevelRenderer(new MainMenu(), WINDOW_WIDTH, WINDOW_HEIGHT).init();
	}

}