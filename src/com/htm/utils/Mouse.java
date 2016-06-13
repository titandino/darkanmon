package com.htm.utils;

import org.dyn4j.geometry.Vector2;
import com.htm.Main;
import com.htm.game.object.Entity;

public class Mouse {
	
	public static boolean mouseOver(Entity entity) {
		Vector2 mouse = new Vector2(org.lwjgl.input.Mouse.getX(), Main.HEIGHT-org.lwjgl.input.Mouse.getY());
		return pointToRectangle(mouse, entity.getTransform().getTranslation(), entity.getScale().x, entity.getScale().y);
	}

	public static boolean clicked(int button) {
		while (org.lwjgl.input.Mouse.next()) {
			if (org.lwjgl.input.Mouse.getEventButton() == button) {
				return !org.lwjgl.input.Mouse.getEventButtonState();
			}
		}
		return false;
	}

	public static boolean pointToRectangle(Vector2 point, Vector2 rect, double width, double height) {
		double left = rect.x; double right = rect.x+width;
		double top = rect.y; double bottom = rect.y+height;
		return !(point.x <= left || point.x >= right || point.y <= top || point.y >= bottom);
	}
}
