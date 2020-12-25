package com.darkan.pkmn;

import com.darkan.pkmn.engine.GameManager;
import com.darkan.pkmn.engine.base.Resolution;
import com.darkan.pkmn.levels.Game;

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
		manager = GameManager.create(new Game(), windowSize, gameResolution);
	}

}