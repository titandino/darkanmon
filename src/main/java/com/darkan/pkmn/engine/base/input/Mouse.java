package com.darkan.pkmn.engine.base.input;

import static org.lwjgl.glfw.GLFW.*;

import java.nio.DoubleBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import com.darkan.pkmn.engine.base.Window;
import com.darkan.pkmn.engine.entity.Entity;
import com.darkan.pkmn.engine.util.Util;
import com.darkan.pkmn.engine.util.Vector2f;

public class Mouse extends InputHandler {

	private int x, y;

	private DoubleBuffer mouseXBuffer;
	private DoubleBuffer mouseYBuffer;
	
	public Mouse(Window window) {
		mouseXBuffer = BufferUtils.createDoubleBuffer(1);
		mouseYBuffer = BufferUtils.createDoubleBuffer(1);
		glfwSetMouseButtonCallback(window.getId(), GLFWMouseButtonCallback.create((windowId, button, action, mods) -> {
			Key key = Key.forCode(button);
			if (key == null) {
				System.out.println("Unmapped mouse key: " + button);
				return;
			}
			setState(key, action == 0 ? KeyState.RELEASED : KeyState.PRESSED);
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
		return Util.pointToRectangle(new Vector2f(x, y), entity.getPosition(), entity.getScale().x, entity.getScale().y);
	}

	public void update(Window window) {
		//glfwSetInputMode(window.getId(), GLFW_CURSOR, GLFW_CURSOR_HIDDEN);

		glfwGetCursorPos(window.getId(), mouseXBuffer, mouseYBuffer);

		x = (int) mouseXBuffer.get(0);
		y = Window.get().getHeight() - (int) mouseYBuffer.get(0);
	}

}
