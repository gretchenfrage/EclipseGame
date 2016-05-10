package com.phoenixkahlo.eclipseold;

import java.util.EnumMap;
import java.util.Map;

import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.Input;

import static org.newdawn.slick.Input.*;

public class SlickInputContext implements InputContext {

	private static Map<InputKey, Integer> map = new EnumMap<InputKey, Integer>(InputKey.class);
	static {
		map.put(InputKey.ShipAlign, KEY_T);
		map.put(InputKey.Sprint, KEY_LSHIFT);
		map.put(InputKey.TurnLeft, KEY_Q);
		map.put(InputKey.TurnRight, KEY_E);
		map.put(InputKey.Use, KEY_SPACE);
		map.put(InputKey.WalkDown, KEY_S);
		map.put(InputKey.WalkLeft, KEY_A);
		map.put(InputKey.WalkRight, KEY_D);
		map.put(InputKey.WalkUp, KEY_W);
		map.put(InputKey.ZoomIn, KEY_R);
		map.put(InputKey.ZoomOut, KEY_F);
	}
	
	private Input input;
	
	public SlickInputContext(Input input) {
		this.input = input;
	}

	@Override
	public boolean isKeyDown(InputKey key) {
		return input.isKeyDown(map.get(key));
	}

	@Override
	public Vector2 getMousePosition() {
		return new Vector2(input.getMouseX(), input.getMouseY());
	}
	
}
