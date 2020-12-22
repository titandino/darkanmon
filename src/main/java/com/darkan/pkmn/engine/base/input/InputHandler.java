package com.darkan.pkmn.engine.base.input;

import java.util.HashMap;
import java.util.Map;

public abstract class InputHandler {
	
	private static final long CLICK_TIME_THRESHHOLD = 1000L;
	
	private Map<Key, KeyState> inputs = new HashMap<>();
	private Map<Key, Long> timePressed = new HashMap<>();
	
	public void setState(Key key, KeyState state) {
		if (state == KeyState.PRESSED)
			timePressed.put(key, System.currentTimeMillis());
		else if (state == KeyState.RELEASED) {
			long startTime = timePressed.get(key) != null ? timePressed.get(key) : System.currentTimeMillis();
			long duration = System.currentTimeMillis() - startTime;
			if (duration < CLICK_TIME_THRESHHOLD)
				state = KeyState.CLICKED;
			timePressed.remove(key);
		}
		inputs.put(key, state);
	}
	
	public KeyState getState(Key key) {
		return inputs.get(key);
	}
	
	public void clearInputs() {
		inputs.entrySet().removeIf(entry -> entry.getValue() == KeyState.RELEASED || entry.getValue() == KeyState.CLICKED);
	}

	public boolean pressed(Key key) {
		return inputs.get(key) == KeyState.PRESSED;
	}
	
	public boolean released(Key key) {
		return inputs.get(key) == KeyState.RELEASED;
	}
	
	public boolean clicked(Key key) {
		return inputs.get(key) == KeyState.CLICKED;
	}
	
	public Map<Key, KeyState> getInputMap() {
		return inputs;
	}
}
