package com.darkan.engine.base;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;

import com.darkan.engine.GameManager;
import com.darkan.engine.base.input.Keyboard;
import com.darkan.engine.base.input.Mouse;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.IntBuffer;

public class Window {
	private long id;
	private Resolution size;

	private Mouse mouse;
	private Keyboard keyboard;
	
	private static Window singleton;

	public Window(String name, Resolution size) {
		if (singleton != null)
			throw new Error("A game window has already been created.");
		this.size = size;
		
		GLFWErrorCallback.createPrint(System.err).set();
		
		if (!glfwInit())
			throw new IllegalStateException("Unable to initialize GLFW");
		
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

		id = glfwCreateWindow(size.getWidth(), size.getHeight(), name, NULL, NULL);
		if (id == NULL)
			throw new RuntimeException("Failed to create the GLFW window");
		
		glfwSetWindowSizeCallback(id, (long window, int width, int height) -> {
			this.size = new Resolution(width, height);
			if (GameManager.get() != null)
				GameManager.get().notifyWindowResize();
		});
		
		mouse = new Mouse(this);
		keyboard = new Keyboard(this);
		singleton = this;
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

	public Resolution getSize() {
		return size;
	}

	public long getId() {
		return id;
	}
	
	public static Window get() {
		return singleton;
	}
	
	public static Mouse getMouse() {
		return singleton.mouse;
	}

	public static Keyboard getKeyboard() {
		return singleton.keyboard;
	}

	public int getWidth() {
		return size.getWidth();
	}
	
	public int getHeight() {
		return size.getHeight();
	}
}
