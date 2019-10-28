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

import java.util.Observable;

public class ChatRoom extends Observable {

	private int numberOfChatRoom;

	public ChatRoom(int numberOfChatRoom){
		this.numberOfChatRoom = numberOfChatRoom;
	}

	public void serverNotifyClients(Message message) {
		setChanged();
		notifyObservers(message);
	}
}
