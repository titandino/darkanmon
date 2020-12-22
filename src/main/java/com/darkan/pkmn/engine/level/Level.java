package com.darkan.pkmn.engine.level;

import java.util.concurrent.ConcurrentHashMap;

import com.darkan.pkmn.engine.Entity;
import com.darkan.pkmn.engine.render.FBO;
import com.darkan.pkmn.engine.render.LevelRenderer;
import com.darkan.pkmn.engine.render.Shader;
import com.darkan.pkmn.engine.util.Util;

import static org.lwjgl.opengl.GL11.*;

/**
 * Class to handle all the logic for a level instance.
 *
 * Created by trent on 4/16/2018.
 */
public abstract class Level {
    //Renderer variables
    private LevelRenderer renderer;
    protected ConcurrentHashMap<Integer, Entity> entities = new ConcurrentHashMap<>();

    //Resolution for the FBO
    private int width;
    private int height;

    /**
     * Creates a new level with the width and height provided.
     * @param width Width of the level world
     * @param height Height of the level world
     */
    public Level(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Called when the level is first initialized.
     */
    public abstract void init(Shader shader);

    /**
     * Handles the extra logic for the level.
     * @param delta Time passed since last update in seconds.
     */
    public abstract void update(float delta);

    /**
     * Handles extra render features on top of the default.
     * @param shader Shader currently bound when called
     */
    public abstract void render(Shader shader);

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
    public void renderUI(Shader shader) {

    }
    
    public final void input() {
    	renderer.getWindow().updateInputs();
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

    /**
     * Immutable render method with required render functionality and GL calls.
     * @param shader Shader to use
     * @param fbo FBO to draw the level onto
     */
    public final void _render(Shader shader, FBO fbo) {
        //Bind the render shader
        shader.use();
        //Bind the FBO for drawing to
        fbo.bindFBO();

        //Set the viewport and orthogonal matrix to full texture resolution
        //glOrtho(0, width, 0, height, -1, 1);
        Util.glOrtho(shader, width,  height);
        glViewport(0, 0, width,  height);

        //Clear the previous frame and render all entities
        glClear(GL_COLOR_BUFFER_BIT);
        for (Entity ent : entities.values()) {
            if (ent != null) {
                ent._render(shader);
            }
        }
        //Render extra functionality
        render(shader);
        //Finish rendering to FBO by unbinding it
        fbo.unbindFBO();
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public LevelRenderer getRenderer() {
        return renderer;
    }
    
    public void setRenderer(LevelRenderer renderer) {
    	this.renderer = renderer;
    }
}
