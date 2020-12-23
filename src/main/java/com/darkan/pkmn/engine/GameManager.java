package com.darkan.pkmn.engine;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.Version;
import org.lwjgl.opengl.GL;

import com.darkan.pkmn.engine.base.Resolution;
import com.darkan.pkmn.engine.base.Window;
import com.darkan.pkmn.engine.entity.Entity;
import com.darkan.pkmn.engine.gfx.mesh.MeshManager;
import com.darkan.pkmn.engine.gfx.texture.TextureManager;
import com.darkan.pkmn.engine.render.EntityRenderer;
import com.darkan.pkmn.engine.render.FBO;
import com.darkan.pkmn.engine.render.FontRenderer;
import com.darkan.pkmn.engine.util.Util;
import com.darkan.pkmn.engine.util.Vector2f;

public class GameManager {
	
	public static boolean DEBUG = false;
	private static GameManager singleton;
	
	private Resolution windowSize;
	private Resolution resolution;
	
	private Window window;
	private EntityRenderer entityRenderer;
	private FontRenderer fontRenderer;
	private Level currentLevel;
	
	private FBO fbo;
	private Entity view;
	
	private long prevFrame = System.currentTimeMillis();
			
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
		TextureManager.init();
		MeshManager.init();
		
		fbo = new FBO(GameManager.getResolution().getWidth(), GameManager.getResolution().getHeight());
		view = new Entity(new Vector2f(0, 0), 1, 1, MeshManager.defaultMesh(), fbo);
		
		entityRenderer = new EntityRenderer(window);
		fontRenderer = new FontRenderer(window);
		
		glClearColor(0f, 0f, 0f, 1f);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		setLevel(currentLevel);
		
		while(!window.isClosed()) {
			loop();
			window.swapBuffers();
			Window.getMouse().clearInputs();
			Window.getKeyboard().clearInputs();
			window.pollInputEvents();
		}
	}
	
	public void renderView() {
		//Bind render shader
		entityRenderer._prepare();
		//Set orthogonal matrix/glViewport to the screen width
		//glOrtho(0, SCREEN_WIDTH, 0, SCREEN_HEIGHT, -1, 1);
		Util.glOrtho(entityRenderer.getShader(), window.getWidth(), window.getHeight());
		glViewport(0, 0, window.getWidth(), window.getHeight());
		glClear(GL_COLOR_BUFFER_BIT);
		//Render the fbo to the view entity
		entityRenderer.render(view);
		currentLevel.renderUIEntity(entityRenderer);
		entityRenderer._end();
		fontRenderer._prepare();
		currentLevel.renderUIFont(fontRenderer);
		fontRenderer._end();
	}

	private final void loop() {
		float millis = System.currentTimeMillis() - prevFrame;
		float delta = millis / 1000f;
		
		if (DEBUG)
			System.out.println("Frame time: " + delta);
		
		prevFrame = System.currentTimeMillis();

		window.updateInputs();
		
		currentLevel._update(delta);
		
		/*
		 * BIND GAME VIEW FBO
		 * All rendering after this will be rendered to the game world!
		 */
		entityRenderer._prepare();
		fbo.bindFBO();
		
		Util.glOrtho(entityRenderer.getShader(), GameManager.getResolution().getWidth(),  GameManager.getResolution().getHeight());
        //TODO possibly need to orthogonal the font renderer here as well
		glViewport(0, 0, GameManager.getResolution().getWidth(),  GameManager.getResolution().getHeight());

        //Clear the previous frame and render all entities
        glClear(GL_COLOR_BUFFER_BIT);
        
		// ALL BASE GAME BUFFER RENDERING PROCEDURES
		entityRenderer._render(currentLevel);
		fontRenderer._render(currentLevel);
		
		/*
		 * END GAME VIEW FBO
		 * All rendering after this will not be rendered to game world!
		 */
		fbo.unbindFBO();
		
		// Call the post processing method in case the level has special post processing
		FBO newFBO = currentLevel.postProcess(fbo);
		if (newFBO != null)
			view.setTexture(newFBO);
		// Render the level FBO using the view provided
		renderView();
	}
	
	public void setLevel(Level level) {
		if (currentLevel != null)
			currentLevel.finish();
		currentLevel = level;
		
		currentLevel.init();
		resizeScreen(currentLevel);
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
	
	public void resizeScreen(Level level) {
		float ratio = (float) window.getWidth() / (float) GameManager.getResolution().getWidth();

		int scaledWidth = (int) (ratio * GameManager.getResolution().getWidth());
		int scaledHeight = (int) (ratio * GameManager.getResolution().getHeight());

		//Calculate the best scale to fit the device's height/width
		view.setScale(new Vector2f(scaledWidth, scaledHeight));
		view.setPosition(new Vector2f(window.getWidth()/2, window.getHeight()/2));
	}

	public void notifyWindowResize() {
		if (entityRenderer != null && currentLevel != null) {
			resizeScreen(currentLevel);
			currentLevel.onWindowResize();
		}
	}
}
