package old.com.phoenixkahlo.roomgame.server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import old.com.phoenixkahlo.roomgame.client.entities.ClientEntity;
import old.com.phoenixkahlo.roomgame.networking.RoomGameSendableCoder;
import old.com.phoenixkahlo.roomgame.networking.ServerConnection;
import old.com.phoenixkahlo.roomgame.networking.core.SendableCoder;
import old.com.phoenixkahlo.roomgame.networking.core.Waiter;

public class Server {

	/*
	 * args = {} or {port}
	 */
	public static void main(String[] args) {
		new Server(args.length == 0 ? 39450 : Integer.parseInt(args[0])).start();
	}
	
	private SendableCoder coder;
	private List<ServerConnection> connections;
	private Waiter waiter;
	
	private List<ClientEntity> entity = new ArrayList<ClientEntity>();
	
	public Server(int port) {
		coder = new RoomGameSendableCoder();
		connections = new ArrayList<ServerConnection>();
		waiter = new Waiter(this::connect, port);
	}
	
	public void start() {
		waiter.start();
		System.out.println("Started");
	}
	
	public void connect(Socket socket) {
		ServerConnection connection = new ServerConnection(socket, this, coder);
		connections.add(connection);
		connection.start();
	}
	
	public void addEntity(ClientEntity entity) {
		
	}
	
	public void disconnect(ServerConnection connection) {
		connection.end();
		connections.remove(connection);
	}
	
}
