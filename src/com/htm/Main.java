package com.htm;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

public class Main {
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	public static void main(String[] args) {
		try {
			initialize();
			while (!Display.isCloseRequested()) {
				render();
			}
			finish();
		} catch (LWJGLException e) {
			System.err.println("Error initializing OpenGL.");
			System.exit(1);
		}
	}
	
	public static void render() {
		glClear(GL_COLOR_BUFFER_BIT);

		glColor3f(0, 1.0f, 0);
		glBegin(GL_QUADS);
		{
			glVertex2f(-0.1f, -0.1f);
			glVertex2f(0.1f, -0.1f);
			glVertex2f(0.1f, 0.1f);
			glVertex2f(-0.1f, 0.1f);
		}
		glEnd();
		Display.update();
	}

	public static void initialize() throws LWJGLException {
		Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
		Display.setTitle("Here To Mars");
		Display.create(new PixelFormat(0, 16, 1));
		
		//Set viewport size of game window
		glViewport(0, 0, WIDTH, HEIGHT);
		
		//Set matrix operations to apply to the projection matrix stack
		glMatrixMode(GL_PROJECTION);
		
		//Set up the camera for 2D
		float aspect = (float)WIDTH / (float)HEIGHT;
		glOrtho(-aspect, aspect, -1, 1, -1, 1);

		//Set matrix operations to apply to transformations of game objects
		glMatrixMode(GL_MODELVIEW);
		//Default the modelview matrix
		glLoadIdentity();
		
		glEnable(GL_STENCIL_TEST);
		glClearColor(0.0f, 0.0f, 0.0f, 1.0f); //background black
	}
	
	public static void finish() {
		Display.destroy();
	}

}
