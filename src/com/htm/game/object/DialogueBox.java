package com.htm.game.object;

import org.lwjgl.util.vector.Vector2f;

import com.htm.graphic.texture.Texture;

public class DialogueBox extends Entity {
	
	private String[] dialogues;
	private int stage;

	public DialogueBox(Texture texture, Vector2f position, Vector2f scale, String... dialogues) {
		super(texture, position, scale);
		this.dialogues = dialogues;
	}
	
	public void handleClick() {
		stage++;
		if (stage == dialogues.length)
			stage = -1;
	}

	public int getStage() {
		return stage;
	}
}
