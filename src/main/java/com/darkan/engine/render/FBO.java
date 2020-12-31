package com.darkan.engine.render;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.imageio.ImageIO;

import com.darkan.engine.gfx.texture.Texture;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * Created by trent on 4/12/2018.
 *
 * Represents an FBO with intent of being a Texture. Extends Texture because an
 * FBO can be bound the same way a texture can to be rendered onto a mesh.
 */
public class FBO extends Texture {
	
	private int fboId;
	
	/**
	 * Initializes an FBO with certain width and height. Will call all necessary GL
	 * initialization functions needed upon creation.
	 *
	 * @param width
	 *            Width of the texture to render to.
	 * @param height
	 *            Height of the texture to render to.
	 */
	public FBO(int width, int height) {
		// generate the texture from parent class and bind it.
		super(glGenTextures());
		this.width = width;
		this.height = height;
		
		// Load in an empty texture with specified size.
		glBindTexture(GL_TEXTURE_2D, textureId);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, 0);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		
		this.fboId = glGenFramebuffers();
		// Generate the frame buffer, bind it, and attach the texture to it.
		glBindFramebuffer(GL_FRAMEBUFFER, fboId);
		glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, textureId, 0);
		int status = glCheckFramebufferStatus(GL_FRAMEBUFFER);
		if (status != GL_FRAMEBUFFER_COMPLETE)
			System.err.println("Error creating FBO");
		glBindFramebuffer(GL_FRAMEBUFFER, 0);
	}

	/**
	 * Saves the FBO's pixels in JPEG image format to a file.
	 * 
	 * @param name
	 *            Filename to save to.
	 */
	public void saveImage(String name) {
		try {
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File(name)));
			ByteBuffer buffer = ByteBuffer.allocateDirect(width * height * 4);
			buffer.order(ByteOrder.LITTLE_ENDIAN);

			glBindFramebuffer(GL_FRAMEBUFFER, fboId);
			glReadPixels(0, 0, width, height, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
			glBindFramebuffer(GL_FRAMEBUFFER, 0);

			buffer.rewind();
			int n = 0;
			byte[] pixels = new byte[buffer.capacity()];
			while (n < buffer.capacity()) {
				pixels[n] = buffer.get(n + 3);
				pixels[n + 1] = buffer.get(n);
				pixels[n + 2] = buffer.get(n + 1);
				pixels[n + 3] = buffer.get(n + 2);
			  n += 4;
			}
			buffer.rewind();
			BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
			img.getRaster().setDataElements(0, 0, width, height, buffer);
			ImageIO.write(img, "PNG", os);
		} catch (IOException e) {
			System.out.println("Error creating image file.");
		}
	}

	/**
	 * Binds differently from texture class but acts the same way.
	 * 
	 * @param uniform
	 *            uniform pointer
	 */
	@Override
	public void bind(int uniform) {
		glBindTexture(GL_TEXTURE_2D, fboId);
	}

	/**
	 * Bind the FBO for rendering to.
	 */
	public void bindFBO() {
		glBindFramebuffer(GL_FRAMEBUFFER, fboId);
	}

	/**
	 * Unbind the FBO after finished rendering.
	 */
	public void unbindFBO() {
		glBindFramebuffer(GL_FRAMEBUFFER, 0);
	}
}
