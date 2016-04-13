package com.phoenixkahlo.roomgame.client;

import org.newdawn.slick.SlickException;

public interface PhysicsChangeDirective {
	
	/**
	 * @return the time at which this change is supposed to happen.
	 */
	public int getTime();
	
	/**
	 * @param delta the difference between the current the time and the result of getTime() 
	 */
	public void implement(Client client, int delta) throws SlickException;
	
}
