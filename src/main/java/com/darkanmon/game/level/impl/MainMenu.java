package com.darkanmon.game.level.impl;

import org.dyn4j.geometry.MassType;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import com.darkanmon.Main;
import com.darkanmon.game.level.Level;
import com.darkanmon.game.object.Entity;
import com.darkanmon.game.object.Text;
import com.darkanmon.graphic.texture.Texture;
import com.darkanmon.utils.Mouse;
import com.darkanmon.utils.TextureLoader;
import com.darkanmon.utils.Util;

public class MainMenu extends Level {
	
	public Texture bgTex;
	public Texture playTex;
	
	public Entity bg;
	public Entity play;
	
	public Texture star;
	public Texture aabb;
	
	public Text hereToMars;
				
	float r = 0.0f;
	float g = 0.0f;
	float b = 0.0f;

	@Override
	public void init() {
		playTex = TextureLoader.getTexture("play.png");
		bgTex = TextureLoader.getTexture("mainmenubg.png");
		star = TextureLoader.getTexture("star.png");
		aabb = TextureLoader.getTexture("aabbtest.png");
		hereToMars = new Text("Here To Mars", new Vector2f(100.0f, 40.0f), 100, new Vector3f(0.0f, 1.0f, 0.0f));
		bg = new Entity(bgTex, new Vector2f(0.0f, 0.0f), new Vector2f(800.0f, 600.0f));
		play = new Entity(playTex, new Vector2f(150.0f, 200.0f), new Vector2f(128.0f, 60.0f));
		addEntity(bg);
		for (int i = 0;i < 10;i++) {
			int size = Util.random(15, 40);
			Entity starEnt = new Entity(star, new Vector2f(Util.random(20, 800), Util.random(110, 300)), new Vector2f(size, size));
			starEnt.setMassType(MassType.INFINITE);
			starEnt.setAngularVelocity(Util.random(1, 4));
			addEntity(starEnt, true);
		}

		addText(hereToMars);
		addEntity(play);
	}

	@Override
	public void update(double delta) {
		if (Mouse.mouseOver(play) && Mouse.clicked(0)) {
			Main.game.setLevel(new Tutorial());
		}
		
		r += (float) (0.05*delta);
		g += (float) (0.10*delta);
		b += (float) (0.05*delta);
		
		if (r > 1.0f)
			r = 0;
		if (g > 1.0f)
			g = 0;
		if (b > 1.0f)
			b = 0;
		
		hereToMars.setColor(new Vector3f(r, g, b));
	}
	
	@Override
	public void finish() {
		
	}

}
