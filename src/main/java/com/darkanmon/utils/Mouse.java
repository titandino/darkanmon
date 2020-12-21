package com.darkanmon.utils;

import org.lwjgl.util.vector.Vector2f;

import com.darkanmon.game.object.Entity;

public class Mouse {
	
	public static boolean mouseOver(Entity entity) {
//		Vector2f mouse = new Vector2f(org.lwjgl.input.Mouse.getX(), Main.HEIGHT-org.lwjgl.input.Mouse.getY());
//		return pointToRectangle(mouse, entity.getTransform().getTranslation(), entity.getScale().x, entity.getScale().y);
		return false;
	}

	public static boolean clicked(int button) {
//		while (org.lwjgl.input.Mouse.next()) {
//			if (org.lwjgl.input.Mouse.getEventButton() == button) {
//				return !org.lwjgl.input.Mouse.getEventButtonState();
//			}
//		}
		return false;
	}

	public static boolean pointToRectangle(Vector2f point, Vector2f rect, double width, double height) {
		double left = rect.getX(); double right = rect.getX()+width;
		double top = rect.getY(); double bottom = rect.getY()+height;
		return !(point.getX() <= left || point.getX() >= right || point.getY() <= top || point.getY() >= bottom);
	}
}
