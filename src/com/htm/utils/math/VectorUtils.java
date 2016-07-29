package com.htm.utils.math;

import org.lwjgl.util.vector.Vector2f;

public class VectorUtils {
	
	public static Vector2f reflect(Vector2f vec, Vector2f line) {
		Vector2f reflected = new Vector2f();
		Vector2f scal2 = new Vector2f(vec);
		float scalar = 2*(Vector2f.dot(vec, line)/Vector2f.dot(line, line));
		scal2 = (Vector2f) line.scale(scalar);
		Vector2f.sub(scal2, vec, reflected);
		return reflected;
	}
	
}
