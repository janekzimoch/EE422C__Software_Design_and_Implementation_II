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

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class SetBroadcast implements EventHandler<ActionEvent> {

	private VBox container;

	private Button send;
	private TextField textInput;
	static TextArea textOutput;
	private Label inputLabel;
	private Label outputLabel;

	private Client client;
//	static int chatRoomID = -1; // tells ClientMain that this tab is not a direct or group chat

    /* constructor */
	public SetBroadcast(Tab broadcast, Client client){
		this.client = client;
		container = display();
		broadcast.setContent(container);
	}

	/* create a AboutCritters tab */
	public VBox display(){

		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);

		/* create buttons */
        send = new Button("send message to everyone");
        send.setMinWidth(120);

        inputLabel = new Label("type in your input");
        outputLabel = new Label("Output window");

        textInput = new TextField();
		textInput.setAlignment(Pos.CENTER);

		textOutput = new TextArea();


        vbox.getChildren().addAll(send, inputLabel, textInput, outputLabel, textOutput);

        send.setOnAction(this);

        // configure vbox
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(15, 10, 0, 10));
        return vbox;
	}


	public void handle(ActionEvent event) {
        if(event.getSource() == send){
			String noMessage;

			String input = textInput.getText().trim();
    		if(input.equals("") || input == null){
    			noMessage = "Please, type text to be sent.";
        		textOutput.appendText(noMessage + "\n");
    		}
    		else{
    			Message message = new Message(MessageType.MESSAGE, input, client.getUsername());
    			try {
    				client.getObjectWriter().writeObject(message);
    				client.getObjectWriter().flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
    			textInput.setText("");
    			textInput.requestFocus();
    		}
        }
	}
}
