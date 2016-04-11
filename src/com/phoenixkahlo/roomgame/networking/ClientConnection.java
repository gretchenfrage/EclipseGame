package com.phoenixkahlo.roomgame.networking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.phoenixkahlo.roomgame.client.Client;
import com.phoenixkahlo.roomgame.networking.core.BadDataException;
import com.phoenixkahlo.roomgame.networking.core.Sendable;
import com.phoenixkahlo.roomgame.networking.core.SendableCoder;

public class ClientConnection extends Thread {

	private Socket socket;
	private Client client;
	private SendableCoder coder;
	
	public ClientConnection(Socket socket, Client client, SendableCoder coder) {
		this.socket = socket;
		this.client = client;
		this.coder = coder;
	}

	/*
	 * Warning: may result in removal of this connection from the client
	 */
	public void send(Sendable sendable) {
		try {
			OutputStream out = socket.getOutputStream();
			coder.write(out, sendable);
		} catch (IOException e) {
			System.out.println("Disconnecting " + socket + " in write because " + e);
			client.disconnected();
		}
	}
	
	@Override
	public void run() {
		InputStream in = null;
		try {
			in = socket.getInputStream();
			while (true) {
				coder.read(in).effectClient(client);
			}
		} catch (IOException | BadDataException e) {
			System.out.println("Disconnecting " + socket + " in read because " + e);
			client.disconnected();
		}
	}
	
}
