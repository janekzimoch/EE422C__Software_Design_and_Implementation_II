/* CHAT ROOM <ChatRoom.java>
 *  EE422C Project 7 submission by
 *  <Janek Zimoch>
 *  <jsz323>
 *  <16190>
 *  <Ryed Ahmed>
 *  <ra35335>
 *  <16190>
 *  Slip days used: <1>
 *  Spring 2019
 */
package assignment7;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

public class ServerMain extends Observable {

	public static Map<String, ObjectOutputStream> clientObjectStream;
	private static HashMap<String, String> username_password_map;
	static Map<Integer, ChatRoom> chatRoomObservablesMap;
	static Map<String, ClientObserver> allObservers;

	public static void main(String[] args) {
		try {
			ServerMain server = new ServerMain();
			server.setUpNetworking(server);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setUpNetworking(ServerMain server) throws Exception {
		@SuppressWarnings("resource")
		ServerSocket serverSock = new ServerSocket(4242);
		clientObjectStream = new HashMap<String, ObjectOutputStream>();  // not sure whether we need it anymore - check this later
		username_password_map = new HashMap<String, String>(); // needed
		chatRoomObservablesMap = new HashMap<Integer, ChatRoom>(); // needed
		allObservers = new HashMap<String, ClientObserver>(); // needed

		while (true) {
			Socket clientSocket = serverSock.accept();
			ObjectOutputStream objectWriter = new ObjectOutputStream(clientSocket.getOutputStream());

			// WRITE to CLIENT - map of username & passwords
			objectWriter.writeObject(new Message(MessageType.USERNAME_PASSWORD_OUTPUT,
					username_password_map,
					null));

			ObjectInputStream objectReader = new ObjectInputStream(clientSocket.getInputStream());

			// READ from CLIENT - new password | username
			Message message = (Message) objectReader.readObject();
			if(message.username_password_to_server != null){
				username_password_map.put(message.username_password_to_server[0], message.username_password_to_server[1]);
			}

			ClientObserver client = new ClientObserver();
			client.setObjectWriter(objectWriter);
			if(!allObservers.containsKey(message.username)){  //message.username - indicates which username (Client) wrote this message
				allObservers.put(message.username, client);
			}
			this.addObserver(client);

			Message updateUserList = new Message(MessageType.LIST_OF_USERS,
					allObservers.keySet().toArray(new String[allObservers.size()]),
					null);
			serverNotifyClients(updateUserList);

			// CLIENT IS INITIALISED
			// now all READING and WRITING will be done in ClientHandler
			Thread t = new ClientHandler(objectReader, objectWriter, server);
			t.start();

			System.out.println("got a connection");
		}
	}

	public void serverNotifyClients(Message message) {
		setChanged();
		notifyObservers(message);
	}
}
