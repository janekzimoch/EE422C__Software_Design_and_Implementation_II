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

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Observable;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class ClientHandler extends Thread {

	private static int numberOfChatRoom;

	private ObjectOutputStream objectWriter;
	private ObjectInputStream objectReader;
	private boolean first_message;
	private ServerMain server;

	public ClientHandler(ObjectInputStream objectReader, ObjectOutputStream objectWriter, ServerMain server) {
		this.server = server;
		this.objectReader = objectReader;
		this.objectWriter = objectWriter;
		first_message = true;
	}

	public void run() {
			try {
				readInput();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
	}

	private void readInput() throws IOException, ClassNotFoundException {
		Message message;
		while ((message = (Message) objectReader.readObject()) != null) {
			processMessage(message);
		}
	}

	private void processMessage(Message message) {
		if(first_message){ // TODO: check whether this is needed !!!!!
			ServerMain.clientObjectStream.put(message.username, objectWriter);
			first_message = false;
		}
		if (message.messageType == MessageType.MESSAGE && message.broadcast == true){
			message.setUserList((String[]) ServerMain.clientObjectStream.keySet().toArray((new String[ServerMain.clientObjectStream.size()])));
			notifyClients(server, message);
		}

		else if(message.messageType == MessageType.LIST_OF_USERS_FOR_CHAT){
			numberOfChatRoom++;
			ChatRoom chatroom = new ChatRoom(numberOfChatRoom);
			ServerMain.chatRoomObservablesMap.put(numberOfChatRoom, chatroom);
			for(String user : message.groupChatlistOfUsers){
				chatroom.addObserver(ServerMain.allObservers.get(user));
			}
			Message sendChatRoomID = new Message(MessageType.CHAT_ROOM_ID,
					numberOfChatRoom,
					message.username);
			if(message.groupChatlistOfUsers.size() == 2){
				sendChatRoomID.unicast = true;
				sendChatRoomID.groupChatlistOfUsers = message.groupChatlistOfUsers;
			}
			notifyClients(chatroom, sendChatRoomID);
		}

		else if(message.messageType == MessageType.MESSAGE && message.broadcast == false){
			Observable observable = ServerMain.chatRoomObservablesMap.get(message.chatRoomID);
			notifyClients(observable, message);
		}

		else if(message.messageType == MessageType.GO_OFFLINE){

		}

		else{
			System.out.println("uppss.... ERROR");
		}
	}

	private void notifyClients(Observable observable, Message message) {
		if(observable instanceof ServerMain){
			((ServerMain) observable).serverNotifyClients(message);
		}
		else{
			((ChatRoom) observable).serverNotifyClients(message);
		}
	}
}
