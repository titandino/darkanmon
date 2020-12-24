package com.darkan.pkmn.levels;

import java.awt.Color;

import com.darkan.pkmn.engine.GameManager;
import com.darkan.pkmn.engine.Level;
import com.darkan.pkmn.engine.base.Window;
import com.darkan.pkmn.engine.base.input.Key;
import com.darkan.pkmn.engine.entity.Entity;
import com.darkan.pkmn.engine.gfx.mesh.MeshManager;
import com.darkan.pkmn.engine.gfx.texture.TextureManager;
import com.darkan.pkmn.engine.render.EntityRenderer;
import com.darkan.pkmn.engine.render.FontRenderer;
import com.darkan.pkmn.engine.text.Text;
import com.darkan.pkmn.engine.util.Vector2f;

public class MainMenu extends Level {

	private Entity playButton;
	private Text testText;

	@Override
    public void init() {
        playButton = new Entity(new Vector2f((Window.get().getWidth() / 2), 150), 230, 64, MeshManager.defaultMesh(), TextureManager.getTexture("playbutton.png"));
        
        Entity background = new Entity(new Vector2f(GameManager.getResolution().getWidth() / 2, GameManager.getResolution().getHeight() / 2), GameManager.getResolution().getWidth(), GameManager.getResolution().getHeight(), MeshManager.defaultMesh(), TextureManager.getTexture("mainmenubg.png"));
        addEntity(background);
    	
    	testText = new Text("A q p                          W", "runescape", 100, 3.5f, new Vector2f(GameManager.getResolution().getWidth() / 2, GameManager.getResolution().getHeight() / 2), Color.BLACK, false);
    }

	@Override
	public void renderExtraEntity(EntityRenderer entityRenderer) {
		
	}

	@Override
	public void renderExtraFont(FontRenderer fontRenderer) {
		fontRenderer.render(testText);
	}

	@Override
	public void renderUIEntity(EntityRenderer entityRenderer) {
		entityRenderer.render(playButton);
	}

	@Override
	public void renderUIFont(FontRenderer fontRenderer) {
		
	}

	@Override
	public void update(float delta) {
		if (Window.getMouse().colliding(playButton) && Window.getMouse().clicked(Key.M_LEFT))
			GameManager.get().setLevel(new Game());
	}

	@Override
	public void onWindowResize() {
		playButton.setPosition(new Vector2f((Window.get().getWidth() / 2), 150));
	}
}
