package com.darkanmon.game.level.impl;

import org.dyn4j.dynamics.Force;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import com.darkanmon.base.input.Key;
import com.darkanmon.game.Game;
import com.darkanmon.game.level.Level;
import com.darkanmon.game.object.Entity;
import com.darkanmon.graphic.texture.Texture;
import com.darkanmon.utils.TextureLoader;
import com.darkanmon.utils.Util;

public class Tutorial extends Level {
	
	Texture star;
	
	Texture bgTex;
	Entity bg;
	
	Entity player;
	
	Texture platTex;
	Entity floor;

	@Override
	public void init() {
		setGravity(new Vector2(0, 9.8));
		
		bgTex = TextureLoader.getTexture("tutorialbg.png");
		star = TextureLoader.getTexture("star2.png");
		platTex = TextureLoader.getTexture("debug.png");
		bg = new Entity(bgTex, new Vector2f(0.0f, 0.0f), new Vector2f(800.0f, 600.0f));
		addEntity(bg);
		
		player = new Entity(star, new Vector2f(100.0f, 450.0f), new Vector2f(50f, 50f), true);
		player.setColor(new Vector3f(0, 255, 0));
		player.getFixture(0).setFriction(20.0);
		player.getFixture(0).setDensity(1.0);
		player.getFixture(0).setRestitution(0.1);
		player.setMass(MassType.NORMAL);
		addEntity(player, true);
		
		floor = new Entity(platTex, new Vector2f(20.0f, 550.0f), new Vector2f(700.0f, 16.0f));
		floor.setMass(MassType.INFINITE);
		addEntity(floor, true);
		
		for (int i = 0;i < 10;i++) {
			int size = Util.random(20, 64);
			Entity starEnt = new Entity(star, new Vector2f(Util.random(20, 800), Util.random(110, 300)), new Vector2f(size, size), true);
			addEntity(starEnt, true);
		}
	}

	@Override
	public void update(double delta) {
		if (Game.getKeyboard().pressed(Key.K_RIGHT)) {
			player.applyTorque(50000);
		}
		if (Game.getKeyboard().pressed(Key.K_LEFT)) {
			player.applyTorque(50000);
		}
		if (Game.getKeyboard().pressed(Key.K_SPACE)) {
			player.applyForce(new Force(0.0, -5000));
		}
	}

	@Override
	public void finish() {
		
	}

}
