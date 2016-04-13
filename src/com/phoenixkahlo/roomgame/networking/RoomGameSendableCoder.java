package com.phoenixkahlo.roomgame.networking;

import com.phoenixkahlo.roomgame.networking.core.SendableCoder;

public class RoomGameSendableCoder extends SendableCoder {

	public RoomGameSendableCoder() {
		register(1, CreateBasicGlidingEntityDirective.class);
		register(2, VelocityChangeDirective.class);
	}
	
}
