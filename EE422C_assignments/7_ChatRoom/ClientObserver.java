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
import java.io.ObjectOutputStream;
import java.util.Observable;
import java.util.Observer;

public class ClientObserver implements Observer {

	private ObjectOutputStream objectWriter;

	@Override
	public void update(Observable o, Object arg) {
		try {
			objectWriter.writeObject((Message) arg);
			objectWriter.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public ObjectOutputStream getObjectWriter() {
		return objectWriter;
	}
	public void setObjectWriter(ObjectOutputStream objectWriter) {
		this.objectWriter = objectWriter;
	}
}
