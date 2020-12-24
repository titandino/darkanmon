package com.darkan.pkmn;

import com.darkan.pkmn.engine.GameManager;
import com.darkan.pkmn.engine.base.Resolution;
import com.darkan.pkmn.levels.MainMenu;

public class Main {

	private static Resolution windowSize = new Resolution(1280, 720);
	private static Resolution gameResolution = new Resolution(1920, 1080);

	private static GameManager manager;

	public static void main(String[] args) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				if (manager != null)
					manager.shutdown();
			}
		});
		manager = new GameManager(new MainMenu(), windowSize, gameResolution);
		manager.init();
	}

}