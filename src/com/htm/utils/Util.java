package com.htm.utils;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;

import com.htm.Main;
import com.htm.game.object.Entity;

public class Util {

	public static boolean collidesPointToRect(Vector2f point, Vector2f rect, float width, float height) {
		float left = rect.x; float right = rect.x+width;
		float top = rect.y+height; float bottom = rect.y;
		return !(point.x < left || point.x > right || point.y > top || point.y < bottom);
	}
	
	public static boolean mouseOver(Entity entity) {
		Vector2f mouse = new Vector2f(Mouse.getX(), Main.HEIGHT-Mouse.getY());
		return collidesPointToRect(mouse, entity.getPosition(), entity.getScale().x, entity.getScale().y);
	}
	
	public static void mtxOrtho(Matrix4f m, float left, float right, float bottom, float top,
			float near, float far) {
		if (m == null)
			return;
		float xOrth = 2 / (right - left);
		float yOrth = 2 / (top - bottom);
		float zOrth = -2 / (far - near);

		float tx = -(right + left) / (right - left);
		float ty = -(top + bottom) / (top - bottom);
		float tz = -(far + near) / (far - near);
		
		m.setIdentity();
		
		m.m00 = xOrth;
		m.m11 = yOrth;
		m.m22 = zOrth;
		m.m30 = tx;
		m.m31 = ty;
		m.m32 = tz;
	}

}
