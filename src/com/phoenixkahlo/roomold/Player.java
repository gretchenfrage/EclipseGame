package com.phoenixkahlo.roomold;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends BasicEntity {
	
	private static final float SPEED = 0.3f;
	private static final float SPRINT_SPEED = 0.8f;
	
	private Room game;
	private Image[] itemBar;
	private Item[] items;
	private int selectedItem = 0;
	
	public Player(Room game) {
		this.game = game;
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {
		injectFields(ResourceUtils.loadImage("resources/images/minecraft/wheat.png"), container.getWidth() / 2, container.getHeight() / 2, 50, 50);
		setZeroAngle(-45);
		
		itemBar = new Image[4];
		for (int i = 0; i < itemBar.length; i++) {
			itemBar[i] = ResourceUtils.loadImage("resources/images/gui/item_bar" + (i + 1) + ".png");
		}
		
		items = new Item[4];
		items[0] = new Bow();
		items[1] = new Sword();
		items[2] = new NoneItem();
		items[3] = new NoneItem();
		for (int i = 0; i < items.length; i++) {
			items[i].init(container);
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		super.render(container, g);
		final float ITEM_BAR_HEIGHT = 250;
		g.drawImage(itemBar[selectedItem],
				container.getWidth() - (ITEM_BAR_HEIGHT / itemBar[selectedItem].getHeight() * itemBar[selectedItem].getWidth()),
				container.getHeight() / 2 - ITEM_BAR_HEIGHT / 2,
				container.getWidth(),
				container.getHeight() / 2 + ITEM_BAR_HEIGHT / 2,
				0, 0, itemBar[selectedItem].getWidth(), itemBar[selectedItem].getHeight()
				);
		for (int i = 0; i < items.length; i++) {
			items[i].render(g,
					container.getWidth() - (ITEM_BAR_HEIGHT / itemBar[selectedItem].getHeight() * itemBar[selectedItem].getWidth()) + 10,
					container.getHeight() / 2 + ITEM_BAR_HEIGHT / 2 - ITEM_BAR_HEIGHT / 4 * (i + 1) + 10,
					container.getWidth() - 10,
					container.getHeight() / 2 + ITEM_BAR_HEIGHT / 2 - ITEM_BAR_HEIGHT / 4 * i - 10
					);
		}
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		float speed = container.getInput().isKeyDown(Input.KEY_LSHIFT) ? SPRINT_SPEED : SPEED;
		float newX = getX();
		float newY = getY();
		if (container.getInput().isKeyDown(Input.KEY_W))
			newY -= speed * delta;
		if (container.getInput().isKeyDown(Input.KEY_S))
			newY += speed * delta;
		if (container.getInput().isKeyDown(Input.KEY_A))
			newX -= speed * delta;
		if (container.getInput().isKeyDown(Input.KEY_D))
			newX += speed * delta;
		if (!(newX < 25 || newX > container.getWidth() - 25)) setX(newX);
		if (!(newY < 25 || newY > container.getHeight() - 25)) setY(newY);
		
		pointToMouse(container.getInput());
		
		if (container.getInput().isKeyPressed(Input.KEY_1)) selectedItem = 0;
		if (container.getInput().isKeyPressed(Input.KEY_2)) selectedItem = 1;
		if (container.getInput().isKeyPressed(Input.KEY_3)) selectedItem = 2;
		if (container.getInput().isKeyPressed(Input.KEY_4)) selectedItem = 3;
		
		if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) items[selectedItem].activate(
				container.getInput().getMouseX(), container.getInput().getMouseY(), this, game);
	}

}
