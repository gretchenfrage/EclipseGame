package com.phoenixkahlo.eclipse.testing;

import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.phoenixkahlo.eclipse.EclipseGraphics;
import com.phoenixkahlo.eclipse.ResourceUtils;

public class EclipseGraphicsTester extends BasicGame {

	public static void main(String[] args) throws SlickException {
		AppGameContainer container = new AppGameContainer(new EclipseGraphicsTester(), 700, 700, false);
		container.setTargetFrameRate(60);
		container.start();
	}
	
	private Image image;
	
	private float theta = 0;
	
	public EclipseGraphicsTester() {
		super("");
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		EclipseGraphics eg = new EclipseGraphics(container, g);
		eg.setScale(2);
		eg.drawImage(image, new Vector2(0, 0), new Vector2(100, 100), theta);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		image = ResourceUtils.loadImage("minecraft/wheat");
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		theta += delta / 1000d;
	}

}
