package com.darkan.pkmn.levels;

import com.darkan.pkmn.engine.GameManager;
import com.darkan.pkmn.engine.Level;
import com.darkan.pkmn.engine.base.Window;
import com.darkan.pkmn.engine.base.input.Key;
import com.darkan.pkmn.engine.entity.Entity;
import com.darkan.pkmn.engine.gfx.mesh.MeshManager;
import com.darkan.pkmn.engine.gfx.texture.TextureManager;
import com.darkan.pkmn.engine.render.EntityRenderer;
import com.darkan.pkmn.engine.render.FontRenderer;

import glm.vec._2.Vec2;

public class Game extends Level {
	
	private static float P_SPEED = 10.0f;
	
	private Entity player;

    @Override
    public void init() {
    	Entity background = new Entity(new Vec2(GameManager.getResolution().getWidth()/2, GameManager.getResolution().getHeight()/2), 384, 384, MeshManager.defaultMesh(), TextureManager.getTexture("pallet-town-test.png"));
        addEntity(background);
    	player = new Entity(new Vec2(GameManager.getResolution().getWidth()/2, GameManager.getResolution().getHeight()/2), 32, 32, MeshManager.defaultMesh(), TextureManager.getTexture("player.png"));
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
    	if (Window.getMouse().clicked(Key.M_LEFT)) {
    		getCamera().setZoom(getCamera().getZoom()+0.1f);
    	} else if (Window.getMouse().clicked(Key.M_RIGHT)) {
    		getCamera().setZoom(getCamera().getZoom()-0.1f);
    	}
    	//getCamera().setPosition(player.getPosition());
    }

	@Override
	public void renderExtraEntity(EntityRenderer entityRenderer) {
		
	}

	@Override
	public void renderExtraFont(FontRenderer fontRenderer) {
		
	}
}
