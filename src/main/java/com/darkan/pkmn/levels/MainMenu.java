package com.darkan.pkmn.levels;

import com.darkan.pkmn.engine.GameManager;
import com.darkan.pkmn.engine.base.Window;
import com.darkan.pkmn.engine.base.input.Key;
import com.darkan.pkmn.engine.entity.Entity;
import com.darkan.pkmn.engine.level.Level;
import com.darkan.pkmn.engine.render.MeshManager;
import com.darkan.pkmn.engine.render.TextureManager;
import com.darkan.pkmn.engine.util.Vector2f;

public class MainMenu extends Level {

    //Button entities
    private Entity playButton;

    @Override
    public void init() {
        //Initialize the screenshot button
        playButton = new Entity(new Vector2f((Window.get().getWidth() / 2), 150), 230, 64, MeshManager.defaultMesh(), TextureManager.getTexture("playbutton.png"));
        playButton.setHeight(1000);
        
        Entity background = new Entity(new Vector2f(GameManager.getResolution().getWidth() / 2, GameManager.getResolution().getHeight() / 2), GameManager.getResolution().getWidth(), GameManager.getResolution().getHeight(), MeshManager.defaultMesh(), TextureManager.getTexture("mainmenubg.png"));
        background.setHeight(1000);
        addEntity(background);
    }

    @Override
    public void renderUI() {
    	//UI Elements will use window size coordinates while game entities rendered in the world
    	//will use the game's resolution coordinates
        playButton._render(getEntityRenderer());
    }

    @Override
    public void update(float delta) {
    	if (Window.getMouse().clicked(Key.M_LEFT))
    		System.out.println("Clicked: " + Window.getMouse().getX() + ", " + Window.getMouse().getY() + " - " + playButton.getPosition().x + ", " + playButton.getPosition().y);
    	if (Window.getMouse().colliding(playButton) && Window.getMouse().clicked(Key.M_LEFT))
    		GameManager.get().setLevel(new Game());
    }

    @Override
    public void render() {

    }
}
