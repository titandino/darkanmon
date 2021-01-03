package com.darkan.engine.util.font;

import java.util.ArrayList;
import java.util.List;

import com.darkan.engine.entity.text.Text;
import com.darkan.engine.gfx.mesh.Mesh;

public class FontMeshLoader {

	protected static final double LINE_HEIGHT = 0.03f;
	protected static final int SPACE_ASCII = 32;
	protected static final int FONT_SIZE = 16;

	private MetaFile metaData;

	protected FontMeshLoader(String fontFile) {
		metaData = new MetaFile(fontFile);
	}

	protected Mesh create(Text text) {
		List<Line> lines = createStructure(text);
		double height = LINE_HEIGHT * FONT_SIZE * lines.size();
		double maxWidth = text.getMaxLineLen();
		if (text.isCentered()) {
			maxWidth = 0;
			for (Line line : lines) {
				double lineWidth = 0;
				for (Word word : line.getWords()) {
					for (Character letter : word.getCharacters()) {
						lineWidth += letter.getxAdvance() * FONT_SIZE;
					}
					lineWidth += metaData.getSpaceWidth() * FONT_SIZE;
				}
				if (lineWidth > maxWidth)
					maxWidth = lineWidth;
			}
		}
		Mesh data = createQuadVertices(text, lines, text.isCentered() ? (-maxWidth/2.0) : 0, height/2.0);
		return data;
	}

	private List<Line> createStructure(Text text) {
		char[] chars = text.getText().toCharArray();
		List<Line> lines = new ArrayList<Line>();
		Line currentLine = new Line(metaData.getSpaceWidth(), FONT_SIZE, text.getMaxLineLen());
		Word currentWord = new Word(FONT_SIZE);
		for (char c : chars) {
			int ascii = (int) c;
			if (ascii == SPACE_ASCII) {
				boolean added = currentLine.attemptToAddWord(currentWord);
				if (!added) {
					lines.add(currentLine);
					currentLine = new Line(metaData.getSpaceWidth(), FONT_SIZE, text.getMaxLineLen());
					currentLine.attemptToAddWord(currentWord);
				}
				currentWord = new Word(FONT_SIZE);
				continue;
			}
			Character character = metaData.getCharacter(ascii);
			currentWord.addCharacter(character);
		}
		completeStructure(lines, currentLine, currentWord, text);
		return lines;
	}

	private void completeStructure(List<Line> lines, Line currentLine, Word currentWord, Text text) {
		boolean added = currentLine.attemptToAddWord(currentWord);
		if (!added) {
			lines.add(currentLine);
			currentLine = new Line(metaData.getSpaceWidth(), FONT_SIZE, text.getMaxLineLen());
			currentLine.attemptToAddWord(currentWord);
		}
		lines.add(currentLine);
	}

	private Mesh createQuadVertices(Text text, List<Line> lines, double startX, double startY) {
		text.setNumLines(lines.size());
		double curserX = startX;
		double curserY = startY;
		List<Float> vertices = new ArrayList<Float>();
		List<Float> textureCoords = new ArrayList<Float>();
		for (Line line : lines) {
			for (Word word : line.getWords()) {
				for (Character letter : word.getCharacters()) {
					addVerticesForCharacter(curserX, curserY, letter, FONT_SIZE, vertices);
					addTexCoords(textureCoords, letter.getXTexCoord(), letter.getYTexCoord(), letter.getXMaxTexCoord(), letter.getYMaxTexCoord());
					curserX += letter.getxAdvance() * FONT_SIZE;
				}
				curserX += metaData.getSpaceWidth() * FONT_SIZE;
			}
			curserX = startX;
			curserY += LINE_HEIGHT * FONT_SIZE;
		}
		return new Mesh(listToArray(vertices), listToArray(textureCoords));
	}

	private void addVerticesForCharacter(double curserX, double curserY, Character character, double fontSize, List<Float> vertices) {
		double x = curserX + (character.getXOffset() * fontSize);
		double y = curserY + (character.getYOffset() * fontSize);
		double maxX = x + (character.getSizeX() * fontSize);
		double maxY = y + (character.getSizeY() * fontSize);
		addVertices(vertices, 2*x, -2*y+1, 2*maxX, -2*maxY+1);
	}

	private static void addVertices(List<Float> vertices, double x, double y, double maxX, double maxY) {
		vertices.add((float) x);
		vertices.add((float) y);
		
		vertices.add((float) x);
		vertices.add((float) maxY);
		
		vertices.add((float) maxX);
		vertices.add((float) maxY);
		
		vertices.add((float) maxX);
		vertices.add((float) maxY);
		
		vertices.add((float) maxX);
		vertices.add((float) y);
		
		vertices.add((float) x);
		vertices.add((float) y);
	}

	private static void addTexCoords(List<Float> texCoords, double x, double y, double maxX, double maxY) {
		texCoords.add((float) x);
		texCoords.add((float) y);
		texCoords.add((float) x);
		texCoords.add((float) maxY);
		texCoords.add((float) maxX);
		texCoords.add((float) maxY);
		texCoords.add((float) maxX);
		texCoords.add((float) maxY);
		texCoords.add((float) maxX);
		texCoords.add((float) y);
		texCoords.add((float) x);
		texCoords.add((float) y);
	}

	private static float[] listToArray(List<Float> listOfFloats) {
		float[] array = new float[listOfFloats.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = listOfFloats.get(i);
		}
		return array;
	}

}
