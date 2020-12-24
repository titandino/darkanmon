package com.darkan.pkmn;

import com.darkan.pkmn.engine.GameManager;
import com.darkan.pkmn.engine.base.Resolution;
import com.darkan.pkmn.levels.MainMenu;

public class Main {

	private static Resolution WINDOW_SIZE = new Resolution(1280, 720);
	private static Resolution GAME_RESOLUTION = new Resolution(1920, 1080);

	private static GameManager manager;

	public static void main(String[] args) {
		GameManager.DEBUG = false;

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				if (manager != null)
					manager.shutdown();
			}
		});
		manager = new GameManager(new MainMenu(), WINDOW_SIZE, GAME_RESOLUTION);
		manager.init();
	}

}