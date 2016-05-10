package com.phoenixkahlo.eclipseold;

import java.io.InputStream;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * Static utils class for loading resources.
 */
public class ResourceUtils {

	private ResourceUtils() {}
	
	private static ClassLoader loader = ResourceUtils.class.getClassLoader();
	
	public static InputStream resourceStream(String name) {
		return loader.getResourceAsStream(name);
	}
	
	public static Image loadAbsImage(String name) throws SlickException {
		return new Image(resourceStream(name), name, false);
	}
	
	public static Image loadImage(String name) throws SlickException {
		return loadAbsImage("resources/images/" + name + ".png");
	}
	
	public static Sound loadAbsSound(String name) throws SlickException {
		return new Sound(resourceStream(name), name);
	}
	
	public static Sound loadSound(String name) throws SlickException {
		return loadAbsSound("resources/audio/" + name + ".wav");
	}
	
}
