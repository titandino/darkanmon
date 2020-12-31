package com.darkan.engine.base.input;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWKeyCallback;

import com.darkan.engine.base.Window;

public class Keyboard extends InputHandler {
		
	public Keyboard(Window window) {
		glfwSetKeyCallback(window.getId(), GLFWKeyCallback.create((windowId, keyCode, scancode, action, mods) -> {
			Key key = Key.forCode(keyCode);
			if (key == null) {
				System.out.println("Unmapped keyboard key: " + keyCode);
				return;
			}
			setState(key, action == 0 ? KeyState.RELEASED : KeyState.PRESSED);
		}));
	}

}
