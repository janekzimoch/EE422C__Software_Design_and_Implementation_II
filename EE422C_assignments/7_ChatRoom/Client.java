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
import java.util.Observable;
import java.util.Observer;

public class Client implements Observer{

	private ObjectInputStream objectReader;
	private ObjectOutputStream objectWriter;
	private String username;
	private boolean online = true;
	// private boolean online; // I WILL DO IT LATER


	public Client(){

	}


    public ObjectInputStream getObjectReader() {
		return objectReader;
	}

	public void setObjectReader(ObjectInputStream objectReader) {
		this.objectReader = objectReader;
	}

	public ObjectOutputStream getObjectWriter() {
		return objectWriter;
	}

	public void setObjectWriter(ObjectOutputStream objectWriter) {
		this.objectWriter = objectWriter;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}
}

