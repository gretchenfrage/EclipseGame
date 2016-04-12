package com.phoenixkahlo.roomgame.networking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.phoenixkahlo.roomgame.networking.core.BadDataException;
import com.phoenixkahlo.roomgame.networking.core.Sendable;
import com.phoenixkahlo.roomgame.networking.core.SendableCoder;
import com.phoenixkahlo.roomgame.server.Server;

/**
 * A server's connection to the client
 */
public class ServerConnection extends Thread {

	private Socket socket;
	private Server server;
	private SendableCoder coder;
	
	public ServerConnection(Socket socket, Server server, SendableCoder coder) {
		this.socket = socket;
		this.server = server;
		this.coder = coder;
		Thread.currentThread().setUncaughtExceptionHandler((t, e) -> disconnect());
	}

	/*
	 * Warning: may result in removal of this connection from the server
	 */
	public void send(Sendable sendable) {
		try {
			OutputStream out = socket.getOutputStream();
			coder.write(out, sendable);
		} catch (IOException e) {
			System.out.println("Disconnecting " + socket + " in write because " + e);
			disconnect();
		}
	}
	
	@Override
	public void run() {
		InputStream in = null;
		try {
			in = socket.getInputStream();
			while (true) {
				coder.read(in).effectServer(this);
			}
		} catch (IOException | BadDataException e) {
			System.out.println("Disconnecting " + socket + " in read because " + e);
			disconnect();
		}
	}
	
	public void end() {
		interrupt();
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		server.disconnect(this);
	}
	
}
