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
import com.darkan.pkmn.engine.util.Camera;
import com.darkan.pkmn.engine.util.Util;

import glm.vec._2.Vec2;

public class GameManager {
	
	private static GameManager singleton;
	
	private Resolution resolution;
	
	private Window window;
	private EntityRenderer entityRenderer;
	private FontRenderer fontRenderer;
	private Scene scene;
	
	private FBO fbo;
	private Camera viewCam;
	private Entity view;
	
	private long prevFrame = System.currentTimeMillis();
			
	private GameManager(Scene scene, Resolution resolution) {
		this.scene = scene;
		this.resolution = resolution;
	}

	public static final GameManager create(Scene scene, Resolution windowSize, Resolution resolution) {
		if (singleton != null)
			throw new IllegalArgumentException("Game manager has already been instantiated.");
		singleton = new GameManager(scene, resolution);
		singleton.init(windowSize);
		return singleton;
	}
	
	public final void init(Resolution windowSize) {
		System.out.println("Inited LWJGL version " + Version.getVersion() + ".");
		
		window = new Window("Darkanmon", windowSize);
		window.center();
		window.makeCurrent();
		window.setVsync(true);
		window.show();
		
		GL.createCapabilities();
		TextureManager.init();
		MeshManager.init();
		
		fbo = new FBO(GameManager.getResolution().getWidth(), GameManager.getResolution().getHeight());
		view = new Entity(new Vec2(0, 0), 1, 1, MeshManager.defaultMesh(), fbo);
		viewCam = new Camera();
		
		entityRenderer = new EntityRenderer(window);
		fontRenderer = new FontRenderer(window);
		
		glClearColor(0f, 0f, 0f, 1f);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		setScene(scene);
		
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
		entityRenderer._prepare(scene);
		//Setup orthogonal projection and camera
		Util.glOrtho(entityRenderer.getShader(), window.getWidth(), window.getHeight());
		glViewport(0, 0, window.getWidth(), window.getHeight());
		//viewCam.setOrigin(new Vec2(window.getWidth() / 2, window.getHeight() / 2));
		glClear(GL_COLOR_BUFFER_BIT);
		//Render the fbo to the view entity
		viewCam.bindUniform(entityRenderer.getShader());
		entityRenderer.render(view);
		scene.renderUIEntity(entityRenderer);
		entityRenderer._end();
		fontRenderer._prepare(scene);
		viewCam.bindUniform(fontRenderer.getShader());
		scene.renderUIFont(fontRenderer);
		fontRenderer._end();
	}

	private final void loop() {
		float millis = (float) (System.currentTimeMillis() - prevFrame);
		float delta = millis / 1000f;

		prevFrame = System.currentTimeMillis();

		window.updateInputs();
		
		scene._update(delta);
		
		/*
		 * BIND GAME VIEW FBO
		 * All rendering after this will be rendered to the game world!
		 */
		entityRenderer._prepare(scene);
		fbo.bindFBO();
		
		Util.glOrtho(entityRenderer.getShader(), GameManager.getResolution().getWidth(),  GameManager.getResolution().getHeight());
        //TODO possibly need to orthogonal the font renderer here as well
		glViewport(0, 0, GameManager.getResolution().getWidth(),  GameManager.getResolution().getHeight());

        //Clear the previous frame and render all entities
        glClear(GL_COLOR_BUFFER_BIT);
        
		// ALL BASE GAME BUFFER RENDERING PROCEDURES
		entityRenderer._render(scene);
		fontRenderer._render(scene);
		
		/*
		 * END GAME VIEW FBO
		 * All rendering after this will not be rendered to game world!
		 */
		fbo.unbindFBO();
		
		// Call the post processing method in case the level has special post processing
		FBO newFBO = scene.postProcess(fbo);
		if (newFBO != null)
			view.setTexture(newFBO);
		// Render the level FBO using the view provided
		renderView();
	}
	
	public void setScene(Scene newScene) {
		if (scene != null)
			scene.finish();
		scene = newScene;
		
		scene._init();
		resizeScreen();
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
	
	public void resizeScreen() {
		float ratio = (float) window.getWidth() / (float) GameManager.getResolution().getWidth();

		int scaledWidth = (int) (ratio * GameManager.getResolution().getWidth());
		int scaledHeight = (int) (ratio * GameManager.getResolution().getHeight());

		//Calculate the best scale to fit the device's height/width
		view.setScale(new Vec2(scaledWidth, scaledHeight));
		view.setPosition(new Vec2(window.getWidth()/2f, window.getHeight()/2f));
	}

	public void notifyWindowResize() {
		if (entityRenderer != null && scene != null) {
			resizeScreen();
			scene.onWindowResize();
		}
	}
}
