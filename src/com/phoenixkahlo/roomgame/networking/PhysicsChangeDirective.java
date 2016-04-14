package com.phoenixkahlo.roomgame.networking;

import org.newdawn.slick.SlickException;

import com.phoenixkahlo.roomgame.client.Client;

/**
 * Directives from the server for the client to do something in the game loop in a way
 * that ensures time synchronization.
 */
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
