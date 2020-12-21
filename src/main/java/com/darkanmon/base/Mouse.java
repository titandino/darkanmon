package com.darkanmon.base;

import static org.lwjgl.glfw.GLFW.*;

import java.nio.DoubleBuffer;
import java.util.HashMap;
import java.util.Map;

import org.dyn4j.geometry.Vector2;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import com.darkanmon.game.object.Entity;
import com.darkanmon.utils.Util;

public class Mouse {

	private int x, y;

	private GLFWMouseButtonCallback mouseCallback;
	private DoubleBuffer mouseXBuffer;
	private DoubleBuffer mouseYBuffer;
	
	private Map<Integer, KeyState> buttons = new HashMap<>();

	public Mouse(Window window) {
		mouseXBuffer = BufferUtils.createDoubleBuffer(1);
		mouseYBuffer = BufferUtils.createDoubleBuffer(1);
		glfwSetMouseButtonCallback(window.getId(), mouseCallback = GLFWMouseButtonCallback.create((windowId, button, action, mods) -> {
			
		}));
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean colliding(Entity entity) {
		return Util.pointToRectangle(new Vector2(x, y), entity.getTransform().getTranslation(), entity.getScale().x, entity.getScale().y);
	}

	public boolean clicked(Button button) {

	}

	public void update(Window window) {
		glfwSetInputMode(window.getId(), GLFW_CURSOR, GLFW_CURSOR_HIDDEN);

		glfwGetCursorPos(window.getId(), mouseXBuffer, mouseYBuffer);

		x = (int) mouseXBuffer.get(0);
		y = (int) mouseYBuffer.get(0);
	}

}
