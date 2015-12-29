package com.htm.utils;

import org.lwjgl.util.vector.Matrix4f;

public class Util {
	
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
