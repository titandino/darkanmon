package com.htm.game.level;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;

import com.htm.game.object.Entity;
import com.htm.graphic.Renderer;
import com.htm.graphic.shader.Shader;

public abstract class Level {
	
	private ArrayList<Entity> entities;
	
	public Level() {
		entities = new ArrayList<Entity>();
	}
	
	public abstract void init();
	public abstract void update(double delta);
	public abstract void finish();
	
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		if (entities.contains(entity))
			entities.remove(entity);
	}
	
	public void _update(double delta) {
		for (Entity e : entities) {
			if (e != null)
				e.update(delta);
		}
		update(delta);
	}
	
	public void render(Shader shader, Renderer renderer) {
		glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT);
		
        for (Entity e : entities) {
        	if (e != null)
        		renderer.drawEntity(e);
        }

		Display.update();
	}
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}

}
