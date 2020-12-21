package com.darkanmon.graphic;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

import com.darkanmon.game.object.Text;
import com.darkanmon.graphic.shader.Shader;
import com.darkanmon.utils.TextureLoader;

public class TextRenderer {

	private HashMap<java.lang.Character, Character> characters = new HashMap<java.lang.Character, Character>();

	private int vaoId;
	private int vboId;
	private Shader shader;

	public void renderText(Text text) {
		if (characters.isEmpty()) {
			System.err.println("No font loaded to render from.");
			return;
		}

		shader.bind();
		shader.setUniformVec3("textColor", text.getColor());
		glActiveTexture(GL_TEXTURE0);
		glBindVertexArray(vaoId);
		
		float xOff = 0;
		
		for (char c : text.getText().toCharArray()) {
			Character ch = characters.get(c);
			if (ch == null) {
				xOff += text.getSize()/10;
				continue;
			}
			float x = text.getPosition().x + xOff;
			float y = text.getPosition().y;

			float width = text.getSize();
			float height = text.getSize();

			float vertices[][] = {
					{ x, y + height, 0.0f, 1.0f }, 
					{ x + width, y, 1.0f, 0.0f }, 
					{ x, y, 0.0f, 0.0f },

					{ x, y + height, 0.0f, 1.0f }, 
					{ x + width, y + height, 1.0f, 1.0f }, 
					{ x + width, y, 1.0f, 0.0f }
			};

			FloatBuffer buffer = BufferUtils.createFloatBuffer(6*4);
			buffer.put(vertices[0]);
			buffer.put(vertices[1]);
			buffer.put(vertices[2]);
			buffer.put(vertices[3]);
			buffer.put(vertices[4]);
			buffer.put(vertices[5]);
			buffer.flip();

			glBindTexture(GL_TEXTURE_2D, ch.getTextureId());

			glBindBuffer(GL_ARRAY_BUFFER, vboId);
			glBufferSubData(GL_ARRAY_BUFFER, 0, buffer);
			glBindBuffer(GL_ARRAY_BUFFER, 0);

			glDrawArrays(GL_TRIANGLES, 0, 6);

			xOff += ch.getSize().x+ch.getSize().x/8;
		}
		glBindVertexArray(0);
		glBindTexture(GL_TEXTURE_2D, 0);
	}

	public void loadFont(String fileName, int size) {
		try {
			File file = new File("./data/font/"+fileName);
			characters.clear();

			Font font1 = Font.createFont(Font.TRUETYPE_FONT, file);
			Font font = font1.deriveFont((float) size*8).deriveFont(Font.BOLD);
			
			glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

			for (int i = 0;i < 128;i++) {
				char c = (char) i;
				int texId = 0;
				
				IntBuffer tmp = TextureLoader.createIntBuffer(1);
				GL11.glGenTextures(tmp);
				texId = tmp.get(0);
								
				glBindTexture(GL_TEXTURE_2D, texId);
				
				Character character = new Character(texId);
				
				BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
				Graphics2D g1 = image.createGraphics();
				g1.setFont(font);
				FontRenderContext frc = g1.getFontMetrics().getFontRenderContext();
				
				TextLayout layout = new TextLayout(""+c, font, frc);
				
				Rectangle2D rect = layout.getBounds();
				
				if (rect.getHeight() == 0 || rect.getWidth() == 0)
					continue;
						
				image = new BufferedImage(128, 128, BufferedImage.TYPE_4BYTE_ABGR);
				
				Graphics2D g = image.createGraphics();
				g.setColor(Color.WHITE);
				g.setFont(font);
				g.drawString(""+c, (int) rect.getWidth(), image.getHeight()-4);
				
				ByteBuffer textureBuffer = TextureLoader.convertTextImageData(image, character);
				
				glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL_RGBA, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, textureBuffer);
				
				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
				
				character.setSize(new Vector2f((float) rect.getWidth(), (float) rect.getHeight()));
				
				characters.put(c, character);
			}
		} catch (FontFormatException e) {
			System.err.println("Error loading font.");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error loading font file.");
			e.printStackTrace();
		}
	}

	public void initialize(Shader shader) {
		this.shader = shader;

		vaoId = glGenVertexArrays();
		vboId = glGenBuffers();

		//Bind VAO to VBO
		glBindBuffer(GL_ARRAY_BUFFER, vboId);
		glBufferData(GL_ARRAY_BUFFER, 6*4*4, GL_DYNAMIC_DRAW);

		//Set VAO to be used
		glBindVertexArray(vaoId);
		//Set up the size of the data within the buffer
		glEnableVertexAttribArray(0);
		glVertexAttribPointer(0, 4, GL_FLOAT, false, 4*4, 0);
		//unbind the VAO
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}
	
	public Shader getShader() {
		return shader;
	}
}
