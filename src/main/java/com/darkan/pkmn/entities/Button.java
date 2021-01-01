package com.darkan.pkmn.entities;

import java.awt.Color;

import com.darkan.engine.entity.Entity;
import com.darkan.engine.entity.text.Text;
import com.darkan.engine.gfx.mesh.MeshManager;
import com.darkan.engine.gfx.texture.TextureManager;
import com.darkan.engine.render.RenderPriority;
import com.darkan.engine.util.BaseTransform;
import com.darkan.engine.util.OffsetTransform;

import glm.vec._2.Vec2;

public class Button extends Entity {
	
	private Text text;

	public Button(RenderPriority priority, String text, Vec2 position, float size) {
		super(priority, position, size * 230f, size * 64f, MeshManager.defaultMesh(), TextureManager.getTexture("blankbutton.png"));
		this.text = new Text(text, "candara", new OffsetTransform(this.getTransform(), new BaseTransform(new Vec2(0.0f, 0.0f), new Vec2((size*40f)/230f, (size*40f)/64f))), Color.BLACK, false);
		addChild(this.text);
	}
	
	public Button(String text, Vec2 position, float size) {
		this(RenderPriority.MAIN, text, position, size);
	}

	public Text getText() {
		return text;
	}

}
