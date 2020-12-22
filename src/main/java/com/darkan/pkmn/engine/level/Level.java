package com.darkan.pkmn.engine.level;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.darkan.pkmn.engine.entity.Entity;
import com.darkan.pkmn.engine.render.FBO;
import com.darkan.pkmn.engine.render.EntityRenderer;

/**
 * Class to handle all the logic for a level instance.
 *
 * Created by trent on 4/16/2018.
 */
public abstract class Level {
    //Renderer variables
    private EntityRenderer entityRenderer;
    protected ConcurrentHashMap<Integer, Entity> entities = new ConcurrentHashMap<>();

    /**
     * Called when the level is first initialized.
     */
    public abstract void init();

    /**
     * Handles the extra logic for the level.
     * @param delta Time passed since last update in seconds.
     */
    public abstract void update(float delta);

    /**
     * Handles extra render features on top of the default.
     * @param shader Shader currently bound when called
     */
    public abstract void render();

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
    public void renderUI() {

    }
    
    public void onWindowResize() {
    	
    }
    
    public final void input() {
    	entityRenderer.getWindow().updateInputs();
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

    public final void _render() {    	
        entityRenderer.render(this);
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

    public EntityRenderer getEntityRenderer() {
        return entityRenderer;
    }
    
    public void setEntityRenderer(EntityRenderer renderer) {
    	this.entityRenderer = renderer;
    }

	public Map<Integer, Entity> getEntities() {
		return entities;
	}
}
