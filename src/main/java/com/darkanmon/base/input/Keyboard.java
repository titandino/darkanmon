package com.darkanmon.base.input;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWKeyCallback;

import com.darkanmon.base.Window;

public class Keyboard extends InputHandler {
	
	private GLFWKeyCallback keyCallback;
	
	public Keyboard(Window window) {
		glfwSetKeyCallback(window.getId(), keyCallback = GLFWKeyCallback.create((windowId, key, scancode, action, mods) -> {
			
		}));
	}

}
