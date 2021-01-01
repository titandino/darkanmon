package com.darkan.engine.util.font;

import java.util.HashMap;
import java.util.Map;

public class FontManager {
	
	private static Map<String, Font> FONTS = new HashMap<>();
	
	public static Font getFont(String fontName) {
		Font font = FONTS.get(fontName);
		if (font == null) {
			font = new Font(fontName);
			FONTS.put(fontName, font);
		}
		return font;
	}

}
