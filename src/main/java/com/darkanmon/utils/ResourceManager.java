package com.darkanmon.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.darkanmon.graphic.texture.Texture;

public class ResourceManager {
	
	public static HashMap<String, Texture> textures = new HashMap<String, Texture>();
	
	public static String loadShader(String fileName) {
		StringBuilder shader = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("./data/shad/"+fileName));
			String line;
			while((line = reader.readLine()) != null) {
				shader.append(line).append("\n");
			}
			reader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return shader.toString();
	}

}
