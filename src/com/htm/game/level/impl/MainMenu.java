package com.htm.game.level.impl;

import org.lwjgl.util.vector.Vector2f;

import com.htm.Main;
import com.htm.game.level.Level;
import com.htm.game.object.Entity;
import com.htm.graphic.texture.Texture;
import com.htm.utils.Mouse;
import com.htm.utils.TextureLoader;

public class MainMenu extends Level {
	
	public Texture bgTex;
	public Texture playTex;
	public Entity bg;
	public Entity play;

	@Override
	public void init() {
		playTex = TextureLoader.getTexture("play.png");
		bgTex = TextureLoader.getTexture("mainmenubg.png");
		bg = new Entity(bgTex, new Vector2f(0.0f, 0.0f), new Vector2f(800.0f, 600.0f));
		play = new Entity(playTex, new Vector2f(150.0f, 200.0f), new Vector2f(128.0f, 60.0f));
		addEntity(bg);
		addEntity(play);
	}

	@Override
	public void update(double delta) {
		if (Mouse.mouseOver(play) && Mouse.clicked(0)) {
			Main.game.setLevel(new Tutorial());
		}
	}

	@Override
	public void finish() {
		
	}

}
