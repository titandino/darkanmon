package com.darkan.pkmn.engine;

import org.lwjgl.Version;
import org.lwjgl.opengl.GL;

import com.darkan.pkmn.engine.base.Resolution;
import com.darkan.pkmn.engine.base.Window;
import com.darkan.pkmn.engine.level.Level;
import com.darkan.pkmn.engine.render.EntityRenderer;
import com.darkan.pkmn.engine.render.MeshManager;
import com.darkan.pkmn.engine.render.TextureManager;

public class GameManager {
	
	public static boolean DEBUG = false;
	
	private Resolution windowSize;
	private Resolution resolution;
	
	private Window window;
	private EntityRenderer entityRenderer;
	private Level currentLevel;
	
	private long prevFrame = System.currentTimeMillis();
	
	private static GameManager singleton;
		
	public GameManager(Level startLevel, Resolution windowSize, Resolution resolution) {
		if (singleton != null)
			throw new Error("Game manager has already been instantiated.");
		this.currentLevel = startLevel;
		this.windowSize = windowSize;
		this.resolution = resolution;
		singleton = this;
	}
	
	public final void init() {
		System.out.println("Inited LWJGL version " + Version.getVersion() + ".");
		
		window = new Window("Level", windowSize);
		window.center();
		window.makeCurrent();
		window.setVsync(true);
		window.show();
		
		GL.createCapabilities();
		
		entityRenderer = new EntityRenderer(window);
		
		TextureManager.init();
		MeshManager.init();
		entityRenderer.initGL();
		setLevel(currentLevel);
		
		while(!window.isClosed()) {
			loop();
			window.swapBuffers();
			Window.getMouse().clearInputs();
			Window.getKeyboard().clearInputs();
			window.pollInputEvents();
		}
	}

	private final void loop() {
		float millis = System.currentTimeMillis() - prevFrame;
		float delta = millis / 1000f;
		
		if (DEBUG)
			System.out.println("Frame time: " + delta);
		
		prevFrame = System.currentTimeMillis();

		currentLevel.input();
		
		//Update and render the level to the FBO
		currentLevel._update(delta);
		currentLevel._render();
	}
	
	public void setLevel(Level level) {
		if (currentLevel != null)
			currentLevel.finish();
		currentLevel = level;
		
		currentLevel.setEntityRenderer(entityRenderer);
		currentLevel.init();
		entityRenderer.resizeScreen(currentLevel);
	}

	public void shutdown() {
		entityRenderer.unload();
	}
	
	public static GameManager get() {
		return singleton;
	}

	public static Resolution getResolution() {
		return singleton.resolution;
	}
}
