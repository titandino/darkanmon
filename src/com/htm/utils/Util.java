package com.htm.utils;

import java.util.Random;

import org.lwjgl.util.vector.Matrix4f;

public class Util {
	
	private static Random random = new Random();
	
	public static int random(int max) {
		return random.nextInt(max);
	}
	
	public static final int getRandom(int maxValue) {
		return (int) (Math.random() * (maxValue + 1));
	}

	public static final int random(int min, int max) {
		final int i = Math.abs(max - min);
		return Math.min(min, max) + (i == 0 ? 0 : random(i));
	}

	public static final double random(double min, double max) {
		final double i = Math.abs(max - min);
		return Math.min(min, max) + (i == 0 ? 0 : random((int) i));
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
