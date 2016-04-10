package com.phoenixkahlo.roomgame.networking.core;

import java.io.IOException;
import java.net.ServerSocket;

public class Waiter extends Thread {

	private ConnectionFactory connectionFactory;
	private ServerSocket serverSocket;
	
	private volatile boolean shouldContinueRunning = true;

	public Waiter(ConnectionFactory connectionFactory, int port) throws RuntimeException {
		super("Waiter thread on port " + port);
		this.connectionFactory = connectionFactory;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			throw new RuntimeException("Waiter failed to bind to port " + port);
		}
	}

	public void terminate() {
		shouldContinueRunning = false;
		interrupt();
	}
	
	@Override
	public void run() {
		while (shouldContinueRunning) {
			try {
				connectionFactory.createConnection(serverSocket.accept());
			} catch (IOException e) {
				System.err.println("Failed to accept socket");
				e.printStackTrace();
			}
		}
	}

}