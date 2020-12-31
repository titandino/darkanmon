package com.darkan.engine.gfx.texture;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.color.ColorSpace;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by trent on 4/9/2018.
 *
 * Class to map used textures so they aren't loaded more than once be able to
 * easily unload them after they are done being used.
 */
public class TextureManager {

	// Map to map textures by name
	private static HashMap<String, Texture> textures = new HashMap<>();

	private static ColorModel glAlphaColorModel;
	private static ColorModel glColorModel;

	/**
	 * Initialize the texture manager for new instance.
	 * 
	 * @param currentCtx
	 */
	public static void init() {
		glAlphaColorModel = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB), new int[] { 8, 8, 8, 8 }, true, false, ComponentColorModel.TRANSLUCENT, DataBuffer.TYPE_BYTE);
		glColorModel = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB), new int[] { 8, 8, 8, 0 }, false, false, ComponentColorModel.OPAQUE, DataBuffer.TYPE_BYTE);
		unloadTextures();
	}

	/**
	 * Maps a texture for later use.
	 *
	 * @param resourceId
	 *            ID of the resource in the resources folder
	 */
	private static Texture mapTexture(String file) {
		try {
			Texture texture = loadTexture(file, GL_TEXTURE_2D, GL_RGBA, GL_LINEAR, GL_LINEAR);
			textures.put(file, texture);
			return texture;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets a texture for use from the texture cache.
	 * 
	 * @param name
	 *            Name of the texture to retrieve
	 * @return The texture ready to be bound
	 */
	public static Texture getTexture(String fileName) {
		Texture texture = textures.get(fileName);
		if (texture == null) {
			texture = mapTexture(fileName);
		}
		if (texture == null)
			throw new IllegalStateException("Texture: \"" + fileName + "\" not found.");
		return texture;
	}

    /**
     * Load a texture into OpenGL from a image reference on
     * disk.
     *
     * @param resourceName The location of the resource to load
     * @param target The GL target to load the texture against
     * @param dstPixelFormat The pixel format of the screen
     * @param minFilter The minimising filter
     * @param magFilter The magnification filter
     * @return The loaded texture
     * @throws IOException Indicates a failure to access the resource
     */
	private static Texture loadTexture(String fileName, int target, int dstPixelFormat, int minFilter, int magFilter) throws IOException {
		int srcPixelFormat = 0;

		Texture texture = new Texture(glGenTextures());
		texture.bind();

		BufferedImage bufferedImage = loadImage(fileName);
		texture.setWidth(bufferedImage.getWidth());
		texture.setHeight(bufferedImage.getHeight());

		if (bufferedImage.getColorModel().hasAlpha()) {
			srcPixelFormat = GL_RGBA;
		} else {
			srcPixelFormat = GL_RGB;
		}

		ByteBuffer textureBuffer = convertImageData(bufferedImage, texture);
		if (target == GL_TEXTURE_2D) {
			glTexParameteri(target, GL_TEXTURE_MIN_FILTER, minFilter);
			glTexParameteri(target, GL_TEXTURE_MAG_FILTER, magFilter);
		}

		glTexImage2D(target, 0, dstPixelFormat, bufferedImage.getWidth(), bufferedImage.getHeight(), 0, srcPixelFormat, GL_UNSIGNED_BYTE, textureBuffer);
		
		return texture;
	}

	/**
	 * Convert the buffered image to a texture
	 *
	 * @param bufferedImage
	 *            The image to convert to a texture
	 * @param texture
	 *            The texture to store the data into
	 * @return A buffer containing the data
	 */
	@SuppressWarnings("rawtypes")
	private static ByteBuffer convertImageData(BufferedImage bufferedImage, Texture texture) {
		ByteBuffer imageBuffer = null;
		WritableRaster raster;
		BufferedImage texImage;

		if (bufferedImage.getColorModel().hasAlpha()) {
			raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, texture.getWidth(), texture.getHeight(), 4, null);
			texImage = new BufferedImage(glAlphaColorModel, raster, false, new Hashtable());
		} else {
			raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, texture.getWidth(), texture.getHeight(), 3, null);
			texImage = new BufferedImage(glColorModel, raster, false, new Hashtable());
		}

		Graphics g = texImage.getGraphics();
		g.setColor(new Color(0f, 0f, 0f, 0f));
		g.fillRect(0, 0, texture.getWidth(), texture.getHeight());
		g.drawImage(bufferedImage, 0, 0, null);

		byte[] data = ((DataBufferByte) texImage.getRaster().getDataBuffer()).getData();
		imageBuffer = ByteBuffer.allocateDirect(data.length);
		imageBuffer.order(ByteOrder.nativeOrder());
		imageBuffer.put(data, 0, data.length);
		imageBuffer.flip();

		return imageBuffer;
	}

	/**
	 * Load a given resource as a buffered image
	 * 
	 * @param ref
	 *            The location of the resource to load
	 * @return The loaded buffered image
	 * @throws IOException
	 *             Indicates a failure to find a resource
	 */
	private static BufferedImage loadImage(String ref) throws IOException {
		File file = new File("./res/textures/" + ref);
		if (!file.exists())
			throw new FileNotFoundException("Texture file not found.");
		return ImageIO.read(new BufferedInputStream(new FileInputStream(file)));
	}

	/**
	 * Unload all the textures and create a new map.
	 */
	public static void unloadTextures() {
		for (Texture t : textures.values()) {
			if (t != null)
				t.release();
		}
		textures = new HashMap<>();
	}
}
