package com.phoenixkahlo.roomgame.client;

/**
 * A directive from another thread for the game loop to undergo some physics change,
 * and keep it delta-synchronized across sides.
 */
public interface ClientPhysicsInjection {

	boolean isReady(int deltaSum);
	
	void inject(Client client, int delta);
	
}
