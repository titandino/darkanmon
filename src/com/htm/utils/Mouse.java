package com.htm.utils;

import org.lwjgl.util.vector.Vector2f;

import com.htm.Main;
import com.htm.game.object.Entity;

public class Mouse {
	
	public static boolean mouseOver(Entity entity) {
		Vector2f mouse = new Vector2f(org.lwjgl.input.Mouse.getX(), Main.HEIGHT-org.lwjgl.input.Mouse.getY());
		return pointToRectangle(mouse, entity.getPosition(), entity.getScale().x, entity.getScale().y);
	}

	public static boolean clicked(int button) {
		while (org.lwjgl.input.Mouse.next()) {
			if (org.lwjgl.input.Mouse.getEventButton() == button) {
				return !org.lwjgl.input.Mouse.getEventButtonState();
			}
		}
		return false;
	}

	public static boolean pointToRectangle(Vector2f point, Vector2f rect, float width, float height) {
		float left = rect.x; float right = rect.x+width;
		float top = rect.y; float bottom = rect.y+height;
		return !(point.x <= left || point.x >= right || point.y <= top || point.y >= bottom);
	}
}
