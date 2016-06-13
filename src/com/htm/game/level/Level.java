package com.htm.game.level;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Vector2;
import org.lwjgl.opengl.Display;

import com.htm.game.object.Entity;
import com.htm.game.object.Text;
import com.htm.graphic.Renderer;
import com.htm.graphic.TextRenderer;

public abstract class Level {
	
	private ArrayList<Entity> entities;
	private ArrayList<Text> texts;
	
	private World world;
	
	public Level() {
		entities = new ArrayList<Entity>();
		texts = new ArrayList<Text>();
		this.world = new World();
		world.setGravity(new Vector2(0.0, 0.0));
	}
	
	public abstract void init();
	public abstract void update(double delta);
	public abstract void finish();
	
	public void addEntity(Entity entity) {
		addEntity(entity, false);
	}
	
	public void addEntity(Entity entity, boolean hasPhysics) {
		entities.add(entity);
		if (hasPhysics)
			world.addBody(entity);
		entity.setActive(true);
	}
	
	public void removeEntity(Entity entity) {
		removeEntity(entity);
		world.removeBody(entity);
	}
	
	public void addText(Text text) {
		texts.add(text);
	}
	
	public void removeText(Text text) {
		if (texts.contains(text))
			texts.remove(text);
	}
	
	public void setGravity(Vector2 gravity) {
		this.world.setGravity(gravity);
	}
	
	public final void _finish() {
		finish();
	}
	
	public final void _update(double delta) {
		for (Entity e : entities) {
			if (e != null)
				e.update(delta);
		}
		this.world.update(delta);
		update(delta);
	}
	
	public void render(Renderer renderer, TextRenderer textRenderer) {
		glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT);
		
        for (Entity e : entities) {
        	if (e != null)
        		renderer.drawEntity(e);
        }
        
        for (Text t : texts) {
        	if (t != null)
        		textRenderer.renderText(t);
        }

		Display.update();
	}
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public ArrayList<Text> getTexts() {
		return texts;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

}
