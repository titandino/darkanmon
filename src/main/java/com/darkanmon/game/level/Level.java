package com.darkanmon.game.level;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

import org.dyn4j.world.World;
import org.dyn4j.geometry.Vector2;
import com.darkanmon.game.object.Entity;
import com.darkanmon.game.object.Text;
import com.darkanmon.graphic.Renderer;
import com.darkanmon.graphic.TextRenderer;

public abstract class Level {
	
	private ArrayList<Entity> entities;
	private ArrayList<Text> texts;
	
	private World<Entity> world;
	
	public Level() {
		entities = new ArrayList<Entity>();
		texts = new ArrayList<Text>();
		this.world = new World<>();
		this.world.setGravity(new Vector2(0.0, 0.0));
	}
	
	public abstract void init();
	public abstract void update(double delta);
	public abstract void finish();
	
	public void addEntity(Entity entity) {
		addEntity(entity, false);
	}
	
	public void addEntity(Entity entity, boolean hasPhysics) {
		if (hasPhysics)
			this.world.addBody(entity);
		entities.add(entity);
		entity.setEnabled(true);
	}
	
	public void removeEntity(Entity entity) {
		world.removeBody(entity);
		removeEntity(entity);
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
		this.world.update(delta);
		for (Entity e : entities) {
			if (e != null)
				e.update(delta);
		}
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
	}
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public ArrayList<Text> getTexts() {
		return texts;
	}

	public World<Entity> getWorld() {
		return world;
	}

	public void setWorld(World<Entity> world) {
		this.world = world;
	}

}
