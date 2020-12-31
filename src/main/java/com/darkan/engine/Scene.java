package com.darkan.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.darkan.engine.entity.Entity;
import com.darkan.engine.render.EntityRenderer;
import com.darkan.engine.render.FBO;
import com.darkan.engine.render.FontRenderer;
import com.darkan.engine.render.RenderPriority;
import com.darkan.engine.text.Text;
import com.darkan.engine.util.Camera;

import glm.vec._2.Vec2;

/**
 * Class to handle all the logic for a scene instance.
 *
 * Created by trent on 4/16/2018.
 */
public abstract class Scene {
    private Map<RenderPriority, List<Entity>> entities = new HashMap<>();
    private Map<RenderPriority, List<Entity>> uiEntities = new HashMap<>();
    private Map<RenderPriority, List<Text>> texts = new HashMap<>();
    private Map<RenderPriority, List<Text>> uiTexts = new HashMap<>();
    private Camera camera = new Camera();

    /**
     * Called when the scene is first initialized.
     */
    public abstract void init();
    
    public final void _init() {
    	camera.setOrigin(new Vec2(GameManager.getResolution().getWidth()/2, GameManager.getResolution().getHeight()/2));
    	init();
    }

    /**
     * Handles the extra logic for the scene.
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
        for (List<Entity> list : entities.values()) {
	        for (Entity ent : list) {
	            if (ent != null) {
	                ent._update(delta);
	            }
	        }
        }
    }
    
    public final void finish() {
    	//TODO unload all textures/meshes that will not
    	//be used in the next scene
    	entities.clear();
    }

    /**
     * Add an entity to the scene.
     * @param entity Entity to be added
     */
    public void addEntity(Entity entity) {
    	List<Entity> list = entities.get(entity.getPriority());
    	if (list == null)
    		list = new ArrayList<>();
    	list.add(entity);
        entities.put(entity.getPriority(), list);
    }

    /**
     * Remove an entity from the scene.
     * @param entity Entity to be removed
     */
    public void removeEntity(Entity entity) {
    	List<Entity> list = entities.get(entity.getPriority());
    	if (list == null)
    		return;
    	list.remove(entity);
    	if (list.size() <= 0)
    		entities.remove(entity.getPriority(), list);
    }

	public Map<RenderPriority, List<Entity>> getEntities() {
		return entities;
	}

	public Camera getCamera() {
		return camera;
	}
}
