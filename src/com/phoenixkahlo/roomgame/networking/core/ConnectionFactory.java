package com.phoenixkahlo.roomgame.networking.core;

import java.net.Socket;

public interface ConnectionFactory {

	void createConnection(Socket socket);

}
