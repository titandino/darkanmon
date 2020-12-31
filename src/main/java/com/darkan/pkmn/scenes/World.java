package com.darkan.pkmn.scenes;

import com.darkan.engine.Scene;
import com.darkan.engine.base.Window;
import com.darkan.engine.base.input.Key;
import com.darkan.engine.entity.Entity;
import com.darkan.engine.gfx.mesh.MeshManager;
import com.darkan.engine.gfx.texture.TextureManager;
import com.darkan.engine.render.EntityRenderer;
import com.darkan.engine.render.FontRenderer;
import com.darkan.engine.render.RenderPriority;

import glm.vec._2.Vec2;

public class World extends Scene {
	
	private static float P_SPEED = 50.0f;
	
	private Entity player;

    @Override
    public void init() {
    	Entity background = new Entity(RenderPriority.BACKGROUND, new Vec2(0, 0), 384, 384, MeshManager.defaultMesh(), TextureManager.getTexture("pallet-town-test.png"));
        addEntity(background);
    	player = new Entity(new Vec2(0, 0), 32, 32, MeshManager.defaultMesh(), TextureManager.getTexture("player.png"));
    	addEntity(player);
    }

	@Override
    public void update(float delta) {
    	if (Window.getKeyboard().pressed(Key.K_W)) {
    		player.setVelocity(new Vec2(0f, P_SPEED));
    	} else if (Window.getKeyboard().pressed(Key.K_S)) {
    		player.setVelocity(new Vec2(0f, -P_SPEED));
    	} else if (Window.getKeyboard().pressed(Key.K_A)) {
    		player.setVelocity(new Vec2(-P_SPEED, 0f));
    	} else if (Window.getKeyboard().pressed(Key.K_D)) {
    		player.setVelocity(new Vec2(P_SPEED, 0f));
    	} else {
    		player.setVelocity(new Vec2(0f, 0f));
    	}
    	if (Window.getMouse().pressed(Key.M_LEFT)) {
    		getCamera().setZoom(getCamera().getZoom()+0.005f);
    	} else if (Window.getMouse().pressed(Key.M_RIGHT)) {
    		getCamera().setZoom(getCamera().getZoom()-0.005f);
    	}
    	getCamera().setPosition(new Vec2(player.getPosition()).mul(-1f));
    }

	@Override
	public void renderExtraEntity(EntityRenderer entityRenderer) {
		
	}

	@Override
	public void renderExtraFont(FontRenderer fontRenderer) {
		
	}
}
