package com.darkan.pkmn.engine.render;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.Version;
import org.lwjgl.opengl.GL;

import com.darkan.pkmn.engine.Entity;
import com.darkan.pkmn.engine.base.GLWindow;
import com.darkan.pkmn.engine.level.Level;
import com.darkan.pkmn.engine.util.Util;
import com.darkan.pkmn.engine.util.Vector2f;

public class LevelRenderer {
	//Shader, texture manager, mesh manager, and FBO to render the level to
	private GLWindow window;
	private Shader shader;
	private FBO fbo;
	private Level level;

	//Holds the time the previous frame was processed at
	private long prevFrame = System.currentTimeMillis();

	//Device width and height
	private static int SCREEN_WIDTH = 800;
	private static int SCREEN_HEIGHT = 600;

	//Separate entity to render the FBO to on the main screen
	//downsized and rotated to best fit screen
	private Entity view;

	/**
	 * Initializes the texture manager and passes the
	 * context of the activity for easy access to resources
	 *
	 * @param context The LevelActivity's context.
	 */
	public LevelRenderer(Level level, int width, int height) {
		SCREEN_WIDTH = width;
		SCREEN_HEIGHT = height;
		this.level = level;
		level.setRenderer(this);
	}

	/**
	 * Init GL related variables and the main view entitiy
	 * that the level FBO will be rendered to
	 */
	public void initGL() {
		fbo = new FBO(level.getWidth(), level.getHeight());
		shader = new Shader("normal.vert", "normal.frag").use();

		//Set the background color to black
		glClearColor(0f, 0f, 0f, 1f);

		//Enable alpha blending for transparent textures
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		//Initialize the main view entity and rotate it 90 degrees because I don't know
		//how to rotate the board like you would a phone
		view = new Entity(new Vector2f(0, 0), 1, 1, MeshManager.defaultMesh(), fbo);
		view.setHeight(1000);
	}

	/**
	 * Render the level or FBO to the view
	 */
	public void renderView() {
		//Bind render shader
		shader.use();
		//Set orthogonal matrix/glViewport to the screen width
		//glOrtho(0, SCREEN_WIDTH, 0, SCREEN_HEIGHT, -1, 1);
		Util.glOrtho(shader, SCREEN_WIDTH, SCREEN_HEIGHT);
		glViewport(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		glClear(GL_COLOR_BUFFER_BIT);
		//Render the fbo to the view entity
		view._render(shader);
		level.renderUI(shader);
	}

	/**
	 * Loop for calling update and render functions as well as calculating
	 * the delta for the update
	 */
	public void loop() {
		float millis = System.currentTimeMillis() - prevFrame;
		float delta = millis / 1000f;
		prevFrame = System.currentTimeMillis();

		level.input();
		
		//Update and render the level to the FBO
		level._update(delta);
		level._render(shader, fbo);

		//Call the post processing method in case the level has special post processing
		FBO newFBO = level.postProcess(fbo);
		if (newFBO != null)
			view.setTexture(newFBO);
		//Render the level FBO using the view provided
		renderView();
	}

	/**
	 * Releases the shader and textures from memory
	 */
	public void unload() {
		if (shader != null)
			shader.unload();
		TextureManager.unloadTextures();
		MeshManager.unloadMeshes();
	}

	/**
	 * Initializes the texture manager, mesh manager, and level GL properties.
	 */
	public void init() {
		System.out.println("Inited LWJGL version " + Version.getVersion() + ".");
		
		window = new GLWindow("Level", SCREEN_WIDTH, SCREEN_HEIGHT);
		window.center();
		window.makeCurrent();
		window.setVsync(true);
		window.show();
		
		GL.createCapabilities();
		
		TextureManager.init();
		MeshManager.init();
		initGL();
		level.init(shader);
		resizeScreen(SCREEN_WIDTH, SCREEN_HEIGHT);
		
		window.handleInputs((id, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                window.close();
        });
		
		while(!window.isClosed()) {
			loop();
			window.swapBuffers();
			window.getMouse().clearInputs();
			window.getKeyboard().clearInputs();
			window.pollInputEvents();
		}
	}

	/**
	 * Whenever the device is rotated or the screen size changes,
	 * update the screen dimension constants and the position/size
	 * of the entity the fbo is rendered to.
	 */
	public void resizeScreen(int width, int height) {
		SCREEN_WIDTH = width;
		SCREEN_HEIGHT = height;

		float ratio = (float) SCREEN_WIDTH / (float) level.getWidth();

		int scaledWidth = (int) (ratio * level.getWidth());
		int scaledHeight = (int) (ratio * level.getHeight());

		//Calculate the best scale to fit the device's height/width
		view.setScale(new Vector2f(scaledWidth, scaledHeight));
		view.setPosition(new Vector2f(SCREEN_WIDTH/2, SCREEN_HEIGHT/2));
	}

	public FBO getFBO() {
		return fbo;
	}

	public Shader getShader() {
		return shader;
	}

	public Vector2f getScreenDimensions() {
		return new Vector2f(SCREEN_WIDTH, SCREEN_HEIGHT);
	}

	public Entity getView() {
		return view;
	}
	
	public GLWindow getWindow() {
		return window;
	}
}
