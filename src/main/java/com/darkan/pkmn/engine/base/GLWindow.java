package com.darkan.pkmn.engine.base;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;

import com.darkan.pkmn.engine.base.input.Keyboard;
import com.darkan.pkmn.engine.base.input.Mouse;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.IntBuffer;

public class GLWindow {
	private long id;
	private int width;
	private int height;

	private Mouse mouse;
	private Keyboard keyboard;

	public GLWindow(String name, int width, int height) {
		this.width = width;
		this.height = height;
		
		GLFWErrorCallback.createPrint(System.err).set();
		
		if (!glfwInit())
			throw new IllegalStateException("Unable to initialize GLFW");
		
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

		id = glfwCreateWindow(width, height, name, NULL, NULL);
		if (id == NULL)
			throw new RuntimeException("Failed to create the GLFW window");
		mouse = new Mouse(this);
		keyboard = new Keyboard(this);
	}
	
	public void updateInputs() {
		mouse.update(this);
	}
	
	public void center() {
		try (MemoryStack stack = stackPush()) {
			IntBuffer pWidth = stack.mallocInt(1);
			IntBuffer pHeight = stack.mallocInt(1);

			glfwGetWindowSize(id, pWidth, pHeight);

			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			glfwSetWindowPos(id, (vidmode.width() - pWidth.get(0)) / 2, (vidmode.height() - pHeight.get(0)) / 2);
		}
	}
	
	public boolean isClosed() {
		return glfwWindowShouldClose(id);
	}
	
	public void makeCurrent() {
		glfwMakeContextCurrent(id);
	}
	
	public void pollInputEvents() {
		glfwPollEvents();
	}
	
	public void swapBuffers() {
		glfwSwapBuffers(id);
	}
	
	public void setVsync(boolean vsync) {
		glfwSwapInterval(vsync ? 1 : 0);
	}
	
	public void show() {
		glfwShowWindow(id);
	}

	public void hide() {
		glfwHideWindow(id);
	}
	
	public void handleInputs(GLFWKeyCallbackI func) {
		glfwSetKeyCallback(id, func);
	}
	
	public void close() {
		glfwSetWindowShouldClose(id, true);
	}

	public void destroy() {
		glfwFreeCallbacks(id);
		glfwDestroyWindow(id);

		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	public long getId() {
		return id;
	}
	
	public Mouse getMouse() {
		return mouse;
	}

	public Keyboard getKeyboard() {
		return keyboard;
	}
}
