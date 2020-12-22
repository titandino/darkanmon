package com.darkan.pkmn.levels;

import com.darkan.pkmn.engine.GameManager;
import com.darkan.pkmn.engine.entity.Entity;
import com.darkan.pkmn.engine.level.Level;
import com.darkan.pkmn.engine.render.MeshManager;
import com.darkan.pkmn.engine.render.TextureManager;
import com.darkan.pkmn.engine.util.Vector2f;

public class Game extends Level {

    @Override
    public void init() {
        Entity background = new Entity(new Vector2f(GameManager.getResolution().getWidth()/2, GameManager.getResolution().getHeight()/2), 384, 384, MeshManager.defaultMesh(), TextureManager.getTexture("pallet-town-test.png"));
        background.setHeight(1000);
        addEntity(background);
    }

    @Override
    public void renderUI() {
    	
    }

    @Override
    public void update(float delta) {
    	
    }

    @Override
    public void render() {

    }
}
