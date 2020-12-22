package com.darkan.pkmn.engine.util;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.darkan.pkmn.engine.render.Shader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Random;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static org.lwjgl.opengl.GL20.*;

/**
 * Created by trent on 4/6/2018.
 *
 * Utility functions
 */
public class Util {
    private static final Random RANDOM = new Random();

    public static int random(int max) {
        return RANDOM.nextInt(max);
    }

    public static int random(int min, int max) {
        return min + RANDOM.nextInt(max-min);
    }

    /**
     * Parse an XML formatted file.
     * @param fileName Name of the file to read
     * @return Document of the parsed XML format
     */
    public static Document parseXML(String fileName) {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("./res/" + fileName));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Read a text asset from the assets folder.
     *
     * @param fileName Name of the file located in the assets folder.
     * @return The text within the file.
     */
    public static String readTextAsset(String fileName) {
        try {
            InputStream stream = new FileInputStream(new File(fileName));

            byte[] buffer = new byte[stream.available()];
            stream.read(buffer);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(buffer);
            outputStream.close();
            stream.close();

            return outputStream.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * My implementation of glOrtho since OpenGL ES doesn't
     * support it by default..
     *
     * @param width Width of the view
     * @param height Height of the view
     */
    public static void glOrtho(Shader shader, int width, int height) {
        float[] ortho = {
                2f / width, 0f, 0f, -1f,
                0f, 2f / height, 0f, -1f,
                0f, 0f, 1f, 0f,
                0f, 0f, 0f, 1f
        };

        FloatBuffer b = ByteBuffer.allocateDirect(ortho.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        b.put(ortho).position(0);
        glUniformMatrix4fv(shader.getUniformLocation("ortho"), true, b);
    }
    
    public static boolean pointToRectangle(Vector2f point, Vector2f rect, double width, double height) {
		double left = rect.x-width/2; double right = rect.x+width/2;
		double top = rect.y-height/2; double bottom = rect.y+height/2;
		return !(point.x <= left || point.x >= right || point.y <= top || point.y >= bottom);
	}
}
