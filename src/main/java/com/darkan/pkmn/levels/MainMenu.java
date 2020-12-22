package com.darkan.pkmn.levels;

import com.darkan.pkmn.engine.Entity;
import com.darkan.pkmn.engine.level.Level;
import com.darkan.pkmn.engine.render.MeshManager;
import com.darkan.pkmn.engine.render.Shader;
import com.darkan.pkmn.engine.render.TextureManager;
import com.darkan.pkmn.engine.util.Vector2f;

public class MainMenu extends Level {
    //Dimensions of the level texture
    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;

    //Button entities
    private Entity playButton;

    public MainMenu() {
        super(WIDTH, HEIGHT);
    }

    @Override
    public void init(Shader shader) {
        //Initialize the screenshot button
        playButton = new Entity(new Vector2f(-1, -1), 230, 64, MeshManager.defaultMesh(), TextureManager.getTexture("playbutton.png"));
        playButton.setHeight(1000);
        
        Entity background = new Entity(new Vector2f(WIDTH/2, HEIGHT/2), WIDTH, HEIGHT, MeshManager.defaultMesh(), TextureManager.getTexture("mainmenubg.png"));
        background.setHeight(1000);
        addEntity(background);
    }

    @Override
    public void renderUI(Shader shader) {
        //render the screenshot button
        playButton._render(shader);
    }

    @Override
    public void update(float delta) {
        //Keep the save screenshot button in the top left of the screen regardless of orientation
        playButton.setPosition(new Vector2f((getRenderer().getScreenDimensions().x/2.0f), 150));
    }

    @Override
    public void render(Shader shader) {

    }
}
