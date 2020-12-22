package com.darkan.pkmn.engine.base.input;

import static org.lwjgl.glfw.GLFW.*;

import java.util.HashMap;
import java.util.Map;

public enum Key {
	K_1(GLFW_KEY_1, "1"),
	K_2(GLFW_KEY_2, "2"),
	K_3(GLFW_KEY_3, "3"),
	K_4(GLFW_KEY_4, "4"),
	K_5(GLFW_KEY_5, "5"),
	K_6(GLFW_KEY_6, "6"),
	K_7(GLFW_KEY_7, "7"),
	K_8(GLFW_KEY_8, "8"),
	K_9(GLFW_KEY_9, "9"),
	K_0(GLFW_KEY_0, "0"),
	K_Q(GLFW_KEY_Q, "Q"),
	K_W(GLFW_KEY_W, "W"),
	K_E(GLFW_KEY_E, "E"),
	K_R(GLFW_KEY_R, "R"),
	K_T(GLFW_KEY_T, "T"),
	K_Y(GLFW_KEY_Y, "Y"),
	K_U(GLFW_KEY_U, "U"),
	K_I(GLFW_KEY_I, "I"),
	K_O(GLFW_KEY_O, "O"),
	K_P(GLFW_KEY_P, "P"),
	K_A(GLFW_KEY_A, "A"),
	K_S(GLFW_KEY_S, "S"),
	K_D(GLFW_KEY_D, "D"),
	K_F(GLFW_KEY_F, "F"),
	K_G(GLFW_KEY_G, "G"),
	K_H(GLFW_KEY_H, "H"),
	K_J(GLFW_KEY_J, "J"),
	K_K(GLFW_KEY_K, "K"),
	K_L(GLFW_KEY_L, "L"),
	K_Z(GLFW_KEY_Z, "Z"),
	K_X(GLFW_KEY_X, "X"),
	K_C(GLFW_KEY_C, "C"),
	K_V(GLFW_KEY_V, "V"),
	K_B(GLFW_KEY_B, "B"),
	K_N(GLFW_KEY_N, "N"),
	K_M(GLFW_KEY_M, "M"),
	K_GRAVE(GLFW_KEY_GRAVE_ACCENT, "`"),
	K_MINUS(GLFW_KEY_MINUS, "-"),
	K_EQUAL(GLFW_KEY_EQUAL, "="),
	K_SPACE(GLFW_KEY_SPACE, "SPACE"),
	K_BACKSPACE(GLFW_KEY_BACKSPACE, "<-"),
	K_APOSTRAPHE(GLFW_KEY_APOSTROPHE, "'"),
	K_SEMICOLON(GLFW_KEY_SEMICOLON, ";"),
	K_COMMA(GLFW_KEY_COMMA, ","),
	K_PERIOD(GLFW_KEY_PERIOD, "."),
	K_UP(GLFW_KEY_UP, "UP"),
	K_DOWN(GLFW_KEY_DOWN, "DOWN"),
	K_LEFT(GLFW_KEY_LEFT, "LEFT"),
	K_RIGHT(GLFW_KEY_RIGHT, "RIGHT"),
	
	M_1(GLFW_MOUSE_BUTTON_1, "MB1"),
	M_2(GLFW_MOUSE_BUTTON_2, "MB2"),
	M_3(GLFW_MOUSE_BUTTON_3, "MB3"),
	M_4(GLFW_MOUSE_BUTTON_4, "MB4"),
	M_5(GLFW_MOUSE_BUTTON_5, "MB5"),
	M_6(GLFW_MOUSE_BUTTON_6, "MB6"),
	M_7(GLFW_MOUSE_BUTTON_7, "MB7"),
	M_8(GLFW_MOUSE_BUTTON_8, "MB8"),
	M_LAST(GLFW_MOUSE_BUTTON_LAST, "MOUSE_LAST"),
	M_LEFT(GLFW_MOUSE_BUTTON_LEFT, "MOUSE_LEFT"),
	M_MIDDLE(GLFW_MOUSE_BUTTON_MIDDLE, "MOUSE_MIDDLE"),
	M_RIGHT(GLFW_MOUSE_BUTTON_RIGHT, "MOUSE_RIGHT"),
	
	;
	
	private int code;
	private String name;
	
	private static Map<Integer, Key> KEY_CODES = new HashMap<>();
	
	static {
		for (Key k : Key.values())
			KEY_CODES.put(k.code, k);
	}
	
	public static Key forCode(int keyCode) {
		return KEY_CODES.get(keyCode);
	}
	
	private Key(int code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
}
