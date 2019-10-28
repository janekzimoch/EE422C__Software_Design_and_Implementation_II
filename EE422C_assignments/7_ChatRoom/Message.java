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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Message implements Serializable {


	private static final long serialVersionUID = 9010166346934707620L;


	String username;
	MessageType messageType;
	boolean broadcast = true;
	String textMessage;
	String[] userList;
	String[] username_password_to_server;
	HashMap<String, String> username_password_from_server;
	int chatRoomID;
	int[] listOfChatRoomIDs;
	ArrayList<String> groupChatlistOfUsers;
	boolean unicast = false;
	boolean online = true;


	/**
	 * This is a class that is a message that clients send to a server and back.
	 *
	 * @param command - type of the information contained, controled by enumeration
	 * @param information - information being sent. Can be any type defined by MessageType enum
	 * @param username - username of a client that sent the message
	 */
	@SuppressWarnings("unchecked")
	public Message(MessageType command, Object information, String username){
		this.username = username;

		switch (command) {

		case MESSAGE:
			messageType = command;
			if(information instanceof String){
				textMessage = (String) information;
			}
			else{
				textMessage = "error";
			}

		case LIST_OF_USERS:
			messageType = command;
			if(information instanceof String[]){
				userList = (String[]) information;
			}

		case USERNAME_PASSWORD_INPUT:
			messageType = command;
			if(information instanceof String[]){ //String is a [username, password] pair. an input to ServerMain username&password storage
				username_password_to_server = (String[]) information;
			}

		case USERNAME_PASSWORD_OUTPUT:
			messageType = command;
			if(information instanceof HashMap<?,?>){ //collection of all username & password data
				username_password_from_server = (HashMap<String, String>) information;
			}

		case CHAT_ROOM_ID:
			messageType = command;
			if(information instanceof Integer){ //collection of all username & password data
				chatRoomID = (int) information;
			}

		case LIST_CHAT_ROOM_ID:
			messageType = command;
			if(information instanceof Integer[]){ //collection of all username & password data
				listOfChatRoomIDs = (int[]) information;
			}

		case LIST_OF_USERS_FOR_CHAT:
			messageType = command;
			if(information instanceof ArrayList<?>){ //collection of all username & password data
				groupChatlistOfUsers = (ArrayList<String>) information;
			}

		case GO_OFFLINE:
			messageType = command;
			if(information instanceof Boolean){ //collection of all username & password data
				online = (boolean) information;
			}
		}
	}

	public void setUserList(String[] userList){
		this.userList = userList;
	}

	public void setChatRoomID(int id){
		this.chatRoomID = id;
	}
}
