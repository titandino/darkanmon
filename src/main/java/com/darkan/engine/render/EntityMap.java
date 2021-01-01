package com.darkan.engine.render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.darkan.engine.entity.Entity;
import com.darkan.engine.entity.text.Text;

public class EntityMap {
	private Set<Entity> masterSet = new HashSet<>();
	private Map<RenderPriority, List<Entity>> entities = new HashMap<>();
	private Map<RenderPriority, List<Text>> text = new HashMap<>();
	
	public void update(float delta) {
		// Update all entities
		List<Entity> toRemove = new ArrayList<>();
		for (Entity ent : masterSet) {
			if (ent != null) {
				ent._update(delta);
				if (ent.isMarkedForDeletion())
					toRemove.add(ent);
			}
		}
		for (Entity ent : toRemove)
			remove(ent);
	}
	
	public void add(Entity entity) {
		if (masterSet.add(entity)) {
			if (entity instanceof Text) {
    			List<Text> list = text.get(entity.getPriority());
    			if (list == null)
    				list = new ArrayList<>();
    			list.add((Text) entity);
    			text.put(entity.getPriority(), list);
    		} else {
    			List<Entity> list = entities.get(entity.getPriority());
    			if (list == null)
    				list = new ArrayList<>();
    			list.add(entity);
    			entities.put(entity.getPriority(), list);
    		}
		}
	}
	
    private void remove(Entity entity) {
    	if (masterSet.remove(entity)) {
    		if (entity instanceof Text) {
    			List<Text> list = text.get(entity.getPriority());
    			list.remove(entity);
    			if (list.size() <= 0)
    				text.remove(entity.getPriority());
    		} else {
    			List<Entity> list = entities.get(entity.getPriority());
    			list.remove(entity);
    			if (list.size() <= 0)
    				entities.remove(entity.getPriority());
    		}
    	}
    }
    
	public List<Entity> getSortedEntities() {
		List<Entity> sorted = new ArrayList<>();
		for (RenderPriority prio : RenderPriority.values())
			if (entities.get(prio) != null)
				sorted.addAll(entities.get(prio));
		return sorted;
	}

	public List<Text> getSortedText() {
		List<Text> sorted = new ArrayList<>();
		for (RenderPriority prio : RenderPriority.values())
			if (text.get(prio) != null)
				sorted.addAll(text.get(prio));
		return sorted;
	}

	public Map<RenderPriority, List<Entity>> getEntities() {
		return entities;
	}

	public Map<RenderPriority, List<Text>> getTexts() {
		return text;
	}

	public Set<Entity> getAll() {
		return masterSet;
	}
}
