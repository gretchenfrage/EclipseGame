package com.phoenixkahlo.roomold;

import java.io.InputStream;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class ResourceUtils {

	private ResourceUtils() {}
	
	public static InputStream loadStream(String name) {
		return ResourceUtils.class.getClassLoader().getResourceAsStream(name);
	}
	
	public static Image loadImage(String name) throws SlickException {
		return new Image(loadStream(name), name, false);
	}
	
	public static Sound loadSound(String name) throws SlickException {
		return new Sound(loadStream(name), name);
	}
	
}
