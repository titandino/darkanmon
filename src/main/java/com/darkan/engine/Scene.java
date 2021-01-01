package com.darkan.engine;

import com.darkan.engine.entity.Entity;
import com.darkan.engine.render.EntityRenderer;
import com.darkan.engine.render.FBO;
import com.darkan.engine.render.FontRenderer;
import com.darkan.engine.render.EntityMap;
import com.darkan.engine.util.Camera;

import glm.vec._2.Vec2;

/**
 * Class to handle all the logic for a scene instance.
 *
 * Created by trent on 4/16/2018.
 */
public abstract class Scene {
	private EntityMap worldEntities = new EntityMap();
	private EntityMap uiEntities = new EntityMap();
	private Camera camera = new Camera();

	/**
	 * Called when the scene is first initialized.
	 */
	public abstract void init();

	public final void _init() {
		camera.setOrigin(new Vec2(GameManager.getResolution().getWidth() / 2, GameManager.getResolution().getHeight() / 2));
		init();
	}

	/**
	 * Handles the extra logic for the scene.
	 * 
	 * @param delta Time passed since last update in seconds.
	 */
	public abstract void update(float delta);

	/**
	 * Handles extra render features on top of the default.
	 * 
	 * @param shader Shader currently bound when called
	 */
	public void renderExtraEntity(EntityRenderer entityRenderer) {

	}

	public void renderExtraFont(FontRenderer fontRenderer) {

	}

	/**
	 * Method to render extra UI elements after the main view is rendered.
	 * 
	 * @param shader Shader currently being used.
	 */
	public void renderExtraUIEntity(EntityRenderer entityRenderer) {

	}

	public void renderExtraUIFont(FontRenderer fontRenderer) {

	}

	/**
	 * Method for handing post processing after the primary render
	 * 
	 * @param fbo FBO containing rendered frame
	 */
	public FBO postProcess(FBO fbo) {
		return null;
	}

	public void onWindowResize() {

	}

	/**
	 * Immutable update method with required update functionality.
	 * 
	 * @param delta Time passed since last update in seconds
	 */
	public final void _update(float delta) {
		// Call the extra update features first
		update(delta);

		// update all entities
		worldEntities.update(delta);
		uiEntities.update(delta);
	}

	public final void finish() {
		// TODO unload all textures/meshes that will not
		// be used in the next scene
	}

	public void addEntity(Entity entity) {
		worldEntities.add(entity);
	}

	public void addUIEntity(Entity entity) {
		uiEntities.add(entity);
	}

	public EntityMap getWorldEntities() {
		return worldEntities;
	}
	
	public EntityMap getUIEntities() {
		return uiEntities;
	}

	public Camera getCamera() {
		return camera;
	}
}
