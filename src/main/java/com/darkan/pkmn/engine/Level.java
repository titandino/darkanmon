package com.darkan.pkmn.engine;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.darkan.pkmn.engine.entity.Entity;
import com.darkan.pkmn.engine.render.EntityRenderer;
import com.darkan.pkmn.engine.render.FBO;
import com.darkan.pkmn.engine.render.FontRenderer;
import com.darkan.pkmn.engine.util.Camera;

import glm.vec._2.Vec2;

/**
 * Class to handle all the logic for a level instance.
 *
 * Created by trent on 4/16/2018.
 */
public abstract class Level {
    protected ConcurrentHashMap<Integer, Entity> entities = new ConcurrentHashMap<>();
    private Camera camera = new Camera();

    /**
     * Called when the level is first initialized.
     */
    public abstract void init();
    
    public final void _init() {
    	camera.setOrigin(new Vec2(GameManager.getResolution().getWidth()/2, GameManager.getResolution().getHeight()/2));
    	init();
    }

    /**
     * Handles the extra logic for the level.
     * @param delta Time passed since last update in seconds.
     */
    public abstract void update(float delta);

    /**
     * Handles extra render features on top of the default.
     * @param shader Shader currently bound when called
     */
    public abstract void renderExtraEntity(EntityRenderer entityRenderer);
    public abstract void renderExtraFont(FontRenderer fontRenderer);

    /**
     * Method for handing post processing after the primary render
     * @param fbo FBO containing rendered frame
     */
    public FBO postProcess(FBO fbo) {
        return null;
    }

    /**
     * Method to render extra UI elements after the main view is rendered.
     * @param shader Shader currently being used.
     */
    public void renderUIEntity(EntityRenderer entityRenderer) {

    }
    
    public void renderUIFont(FontRenderer fontRenderer) {

    }
    
    public void onWindowResize() {
    	
    }

    /**
     * Immutable update method with required update functionality.
     * @param delta Time passed since last update in seconds
     */
    public final void _update(float delta) {	
        //Call the extra update features first
        update(delta);

        //Update all entities
        for (Entity ent : entities.values()) {
            if (ent != null) {
                ent._update(delta);
            }
        }
    }
    
    public final void finish() {
    	//TODO unload all textures/meshes that will not
    	//be used in the next level
    	entities.clear();
    }

    /**
     * Add an entity to the level.
     * @param entity Entity to be added
     */
    public void addEntity(Entity entity) {
        entities.put(entity.hashCode(), entity);
    }

    /**
     * Remove an entity from the level.
     * @param entity Entity to be removed
     */
    public void removeEntity(Entity entity) {
        entities.remove(entity.hashCode());
    }

	public Map<Integer, Entity> getEntities() {
		return entities;
	}

	public Camera getCamera() {
		return camera;
	}
}
