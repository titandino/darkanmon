package com.htm.game.level.impl;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import com.htm.Main;
import com.htm.game.level.Level;
import com.htm.game.object.Entity;
import com.htm.game.object.Text;
import com.htm.graphic.texture.Texture;
import com.htm.utils.Mouse;
import com.htm.utils.TextureLoader;
import com.htm.utils.Util;

public class MainMenu extends Level {
	
	public Texture bgTex;
	public Texture playTex;
	public Entity bg;
	public Entity play;
	
	public Texture star;
	
	public Text test;
	public Text test2;
	
	public ArrayList<Entity> stars = new ArrayList<Entity>();
	
	float r = 0.0f;
	float g = 0.0f;
	float b = 0.0f;

	@Override
	public void init() {
		playTex = TextureLoader.getTexture("play.png");
		bgTex = TextureLoader.getTexture("mainmenubg.png");
		star = TextureLoader.getTexture("star.png");
		test = new Text("Here To Mars", new Vector2f(150.0f, 25.0f), 50, new Vector3f(0.0f, 1.0f, 0.0f));
		test2 = new Text("Here To Mars", new Vector2f(150.0f, 25.0f), 100, new Vector3f(0.0f, 1.0f, 0.0f));
		bg = new Entity(bgTex, new Vector2f(0.0f, 0.0f), new Vector2f(800.0f, 600.0f));
		play = new Entity(playTex, new Vector2f(150.0f, 200.0f), new Vector2f(128.0f, 60.0f));
		addEntity(bg);
		for (int i = 0;i < 10;i++) {
			int size = Util.random(15, 40);
			Entity starEnt = new Entity(star, new Vector2f(Util.random(20, 800), Util.random(110, 300)), new Vector2f(size, size));
			starEnt.setAngularVelocity(Util.random(1, 4));
			addEntity(starEnt);
		}
		addText(test);
		addText(test2);
		addEntity(play);
	}

	@Override
	public void update(double delta) {
		if (Mouse.mouseOver(play) && Mouse.clicked(0)) {
			Main.game.setLevel(new Tutorial());
		}
		r = 1/Util.random(1, 10);
		g = 1/Util.random(1, 10);
		b = 1/Util.random(1, 10);
		test2.setColor(new Vector3f(r, g, b));
	}

	@Override
	public void finish() {
		
	}

}
