package com.darkan.pkmn.levels;

import com.darkan.pkmn.engine.GameManager;
import com.darkan.pkmn.engine.Level;
import com.darkan.pkmn.engine.entity.Entity;
import com.darkan.pkmn.engine.gfx.mesh.MeshManager;
import com.darkan.pkmn.engine.gfx.texture.TextureManager;
import com.darkan.pkmn.engine.render.EntityRenderer;
import com.darkan.pkmn.engine.render.FontRenderer;
import com.darkan.pkmn.engine.util.Vector2f;

public class Game extends Level {

    @Override
    public void init() {
        Entity background = new Entity(new Vector2f(GameManager.getResolution().getWidth()/2, GameManager.getResolution().getHeight()/2), 384, 384, MeshManager.defaultMesh(), TextureManager.getTexture("pallet-town-test.png"));
        addEntity(background);
    }

    @Override
    public void update(float delta) {
    	
    }

	@Override
	public void renderExtraEntity(EntityRenderer entityRenderer) {
		
	}

	@Override
	public void renderExtraFont(FontRenderer fontRenderer) {
		
	}
}
