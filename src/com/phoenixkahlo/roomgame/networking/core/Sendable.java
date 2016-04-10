package com.phoenixkahlo.roomgame.networking.core;

import java.io.IOException;
import java.io.OutputStream;

import com.phoenixkahlo.roomgame.networking.ClientConnection;
import com.phoenixkahlo.roomgame.networking.ServerConnection;

public interface Sendable {
	
	void write(OutputStream out) throws IOException;
	
	void effectClient(ClientConnection connection);
	
	void effectServer(ServerConnection connection);
	
}