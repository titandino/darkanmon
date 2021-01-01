package com.darkan.pkmn.scenes;

import java.awt.Color;

import com.darkan.engine.GameManager;
import com.darkan.engine.Scene;
import com.darkan.engine.base.Window;
import com.darkan.engine.base.input.Key;
import com.darkan.engine.entity.Entity;
import com.darkan.engine.entity.text.Text;
import com.darkan.engine.gfx.mesh.MeshManager;
import com.darkan.engine.gfx.texture.TextureManager;
import com.darkan.engine.render.RenderPriority;

import glm.vec._2.Vec2;

public class LoginScreen extends Scene {

	private Entity playButton;

	@Override
	public void init() {
		playButton = new Entity(new Vec2(Window.get().getWidth() / 2, 150), 230, 64, MeshManager.defaultMesh(), TextureManager.getTexture("playbutton.png"));
		addUIEntity(playButton);

		Entity background = new Entity(RenderPriority.BACKGROUND, new Vec2(0, 0), GameManager.getResolution().getWidth(), GameManager.getResolution().getHeight(), MeshManager.defaultMesh(), TextureManager.getTexture("mainmenubg.png"));
		addEntity(background);
		
		addEntity(new Text("Meme World", "runescape", 100, 4, new Vec2(0, 0), Color.BLACK, false));
		addUIEntity(new Text("Meme UI", "runescape", 50, 4, new Vec2(100, 100), Color.RED, false));
	}

	@Override
	public void update(float delta) {
		if (Window.getMouse().colliding(playButton) && Window.getMouse().clicked(Key.M_LEFT))
			GameManager.get().setScene(new World());
	}

	@Override
	public void onWindowResize() {
		playButton.setPosition(new Vec2(Window.get().getWidth() / 2, 150));
	}
}
