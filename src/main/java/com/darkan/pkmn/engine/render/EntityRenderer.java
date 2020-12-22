package com.darkan.pkmn.engine.render;

import static org.lwjgl.opengl.GL11.*;

import com.darkan.pkmn.engine.GameManager;
import com.darkan.pkmn.engine.base.Window;
import com.darkan.pkmn.engine.entity.Entity;
import com.darkan.pkmn.engine.level.Level;
import com.darkan.pkmn.engine.util.Util;
import com.darkan.pkmn.engine.util.Vector2f;

public class EntityRenderer {
	//Shader, texture manager, mesh manager, and FBO to render the level to
	private Window window;
	private Shader entityShader;
	private FBO fbo;

	//Separate entity to render the FBO to on the main screen
	//downsized and rotated to best fit screen
	private Entity view;

	/**
	 * Initializes the texture manager and passes the
	 * context of the activity for easy access to resources
	 *
	 * @param context The LevelActivity's context.
	 */
	public EntityRenderer(Window window) {
		this.window = window;
	}

	/**
	 * Init GL related variables and the main view entity
	 * that the level FBO will be rendered to
	 */
	public void initGL() {
		fbo = new FBO(GameManager.getResolution().getWidth(), GameManager.getResolution().getHeight());
		entityShader = new Shader("normal.vert", "normal.frag").use();

		//Set the background color to black
		glClearColor(0f, 0f, 0f, 1f);

		//Enable alpha blending for transparent textures
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		//Initialize the main view entity and rotate it 90 degrees
		view = new Entity(new Vector2f(0, 0), 1, 1, MeshManager.defaultMesh(), fbo);
		view.setHeight(1000);
	}

	/**
	 * Render the level or FBO to the view
	 */
	public void renderView(Level level) {
		//Bind render shader
		entityShader.use();
		//Set orthogonal matrix/glViewport to the screen width
		//glOrtho(0, SCREEN_WIDTH, 0, SCREEN_HEIGHT, -1, 1);
		Util.glOrtho(entityShader, window.getWidth(), window.getHeight());
		glViewport(0, 0, window.getWidth(), window.getHeight());
		glClear(GL_COLOR_BUFFER_BIT);
		//Render the fbo to the view entity
		view._render(this);
		level.renderUI();
	}

	/**
	 * Loop for calling update and render functions as well as calculating
	 * the delta for the update
	 */
	public void render(Level level) {
		//Bind the render shader
		entityShader.use();
        //Bind the FBO for drawing to
        fbo.bindFBO();

        //Set the viewport and orthogonal matrix to full texture resolution
        //glOrtho(0, width, 0, height, -1, 1);
        Util.glOrtho(entityShader, GameManager.getResolution().getWidth(),  GameManager.getResolution().getHeight());
        glViewport(0, 0, GameManager.getResolution().getWidth(),  GameManager.getResolution().getHeight());

        //Clear the previous frame and render all entities
        glClear(GL_COLOR_BUFFER_BIT);
        for (Entity ent : level.getEntities().values()) {
            if (ent != null) {
                ent._render(this);
            }
        }
        //Render extra functionality
        level.render();
        //Finish rendering to FBO by unbinding it
        fbo.unbindFBO();
        
		//Call the post processing method in case the level has special post processing
		FBO newFBO = level.postProcess(fbo);
		if (newFBO != null)
			view.setTexture(newFBO);
		//Render the level FBO using the view provided
		renderView(level);
	}

	/**
	 * Releases the shader and textures from memory
	 */
	public void unload() {
		if (entityShader != null)
			entityShader.unload();
		TextureManager.unloadTextures();
		MeshManager.unloadMeshes();
	}

	/**
	 * Whenever the device is rotated or the screen size changes,
	 * update the screen dimension constants and the position/size
	 * of the entity the fbo is rendered to.
	 */
	public void resizeScreen(Level level) {
		float ratio = (float) window.getWidth() / (float) GameManager.getResolution().getWidth();

		int scaledWidth = (int) (ratio * GameManager.getResolution().getWidth());
		int scaledHeight = (int) (ratio * GameManager.getResolution().getHeight());

		//Calculate the best scale to fit the device's height/width
		view.setScale(new Vector2f(scaledWidth, scaledHeight));
		view.setPosition(new Vector2f(window.getWidth()/2, window.getHeight()/2));
	}

	public FBO getFBO() {
		return fbo;
	}

	public Shader getShader() {
		return entityShader;
	}

	public Entity getView() {
		return view;
	}
	
	public Window getWindow() {
		return window;
	}
}
