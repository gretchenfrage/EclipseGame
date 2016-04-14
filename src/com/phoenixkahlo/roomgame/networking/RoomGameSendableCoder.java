package com.phoenixkahlo.roomgame.networking;

import com.phoenixkahlo.roomgame.networking.core.SendableCoder;

public class RoomGameSendableCoder extends SendableCoder {

	public RoomGameSendableCoder() {
		register(1, CreateThinClientEntity.class);
		register(2, ChangeEntityGlide.class);
	}
	
}
