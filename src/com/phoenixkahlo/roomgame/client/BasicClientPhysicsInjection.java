package com.phoenixkahlo.roomgame.client;

public abstract class BasicClientPhysicsInjection implements ClientPhysicsInjection {

	/**
	 * The delta sum at which this event was/will occur
	 */
	private int deltaOccur;
	
	public BasicClientPhysicsInjection(int deltaOccur) {
		this.deltaOccur = deltaOccur;
	}
	
	@Override
	public boolean isReady(int deltaSum) {
		return deltaSum >= deltaOccur;
	}

	@Override
	public void inject(Client client, int delta) {
		toUpdate().
	}
	
	protected abstract Entity toUpdate();

}
