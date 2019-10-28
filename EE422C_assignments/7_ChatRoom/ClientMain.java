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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClientMain extends Application{

	public static Stage window;
    public static TabPane tabpane;
    public static ComboBox availableUsers;
    public static String ipAdress;


    private Client client;
	private Message loginData;
	Map<Integer, SetChat> mapOfChatRooms;

	public static void main(String[] args) {
        launch(args);
	}

	/**
	 * main method that calls all the Client methods required for the setup
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		// create a Client entity
		client = new Client();

		//ask for IP
		ProvideIP i = new ProvideIP();
		i.getIP();

		// connect Client to the server
		setUpNetworking();

		// request user name from the user
		ProvideUsername p = new ProvideUsername();
		p.getUserName();

		// create GUI
		setUpChatWindow(primaryStage);

		// setup thread for incoming messages
		setupThread();
	}


	/* creates GUI */
	private void setUpChatWindow(Stage primaryStage){
		window = primaryStage;
    	window.setTitle("User: " + client.getUsername());

    	window.setOnCloseRequest( event -> {
        	Message onCloseMessage = new Message(MessageType.GO_OFFLINE,
        			false,
        			null);
        	try {
				client.getObjectWriter().writeObject(onCloseMessage);
			} catch (IOException e) {
				e.printStackTrace();
			}
        } );


		tabpane = new TabPane();
        Tab controls = new Tab("Controls");
        Tab broadcast = new Tab("Broadcast");
        tabpane.getTabs().add(controls);
        tabpane.getTabs().add(broadcast);

        SetControls controls_class = new SetControls(controls, loginData, client);
        SetBroadcast broadcast_class = new SetBroadcast(broadcast, client);
		mapOfChatRooms = new HashMap<Integer, SetChat>();

        double width = 465;
        double height = 515;
        Scene scene = new Scene(tabpane, width, height);
        window.setScene(scene);
        window.show();
	}


	/* setup connection to a server */
	private void setUpNetworking() throws Exception {
		@SuppressWarnings("resource")
		Socket sock = new Socket(ipAdress, 4242);
		client.setObjectWriter(new ObjectOutputStream(sock.getOutputStream()));
		client.setObjectReader(new ObjectInputStream(sock.getInputStream()));
		loginData = (Message) client.getObjectReader().readObject();

		System.out.println("networking established");
	}

	public void setupThread(){
		Thread objectReaderThread = new Thread(new IncomingObjectReader());
		objectReaderThread.start();
	}
	class IncomingObjectReader implements Runnable {

		@SuppressWarnings("unchecked")
		public void run(){
			Message message;
			try {
				while((message = (Message) client.getObjectReader().readObject()) != null){
					if (message.messageType == MessageType.MESSAGE && message.broadcast == true){
						SetBroadcast.textOutput.appendText(message.username + " : " + message.textMessage +"\n");
						String musicFile = "music/broadcast_notification.mp3";
						playMusic(musicFile);
					}

					else if (message.messageType == MessageType.CHAT_ROOM_ID){
						Message messagee = message;
						Platform.runLater(() -> {
							int id = messagee.chatRoomID;
							boolean unicast = messagee.unicast;
							String tabName = "";
							if(unicast){
								messagee.groupChatlistOfUsers.remove(client.getUsername());
								String otherUserName = messagee.groupChatlistOfUsers.get(0);
								tabName = otherUserName;
							}
							// creates a tab for a new chatroom
							else{
								tabName = "Group" + id;
							}
			                Tab directChat = new Tab(tabName);
			                tabpane.getTabs().add(directChat);
			                SetChat chatroom = new SetChat(directChat, client, id);
				            mapOfChatRooms.put(id, chatroom);
						});
					}
					else if (message.messageType == MessageType.MESSAGE && message.broadcast == false){
						SetChat chatroom = mapOfChatRooms.get(message.chatRoomID);
						chatroom.updateWithMessage(message);
						String musicFile = "music/chatRoom_notification.mp3";
						playMusic(musicFile);
					}
					else if (message.messageType == MessageType.LIST_OF_USERS){
						String[] userList = message.userList;
						SetControls.updateGUI(userList);
					}
					else{
						System.out.println("uppss.... ERROR");
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private void playMusic(String musicFile) {
			Media sound = new Media(new File(musicFile).toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.play();
		}
	}

private class ProvideIP {

		public void getIP() {
			// setup stage
			Stage window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Provide Username");
			window.setMinWidth(400);

			// setup label
			Label informationLabel = new Label("Enter in the IP Adress of the server host");
			informationLabel.setWrapText(true);
			informationLabel.setAlignment(Pos.CENTER);
			// setting up sub-container
			HBox hbox_text = new HBox();
			hbox_text.setSpacing(10);
			hbox_text.getChildren().addAll(informationLabel);
			hbox_text.setAlignment(Pos.CENTER);
			hbox_text.setPadding(new Insets(5, 12, 5, 12));

	        TextField ipInput = new TextField();
	        ipInput.setAlignment(Pos.CENTER);
	        ipInput.setPromptText("Enter IP Adress of Server");

			Button submit = new Button("submit");
			submit.setMinWidth(130);

			submit.setOnAction(e -> {
				ClientMain.ipAdress = ipInput.getText();

				/* what heppens when user doesn't provide input */
	    		if(ipAdress.equals("") || ipAdress == null){
    				new PopupNotification("Input IP", "please, input server IP to start client");
	    		}
	    		else{
	    			/* what happens when user provides VALID and UNUSED user name */
						window.close();
	    			}

			});

			// setting up main container
			VBox container = new VBox();
			container.setPadding(new Insets(15, 12, 15, 12));
			container.setStyle("-fx-background-color: #fffac8;");

			// setting up sub-container
			HBox hbox = new HBox();
		    hbox.setSpacing(30);
		    hbox.getChildren().addAll(submit);
		    hbox.setAlignment(Pos.CENTER);
		    hbox.setPadding(new Insets(15, 12, 15, 12));

			container.getChildren().addAll(hbox_text, ipInput, hbox);
			container.setAlignment(Pos.CENTER);

			// displaying
			Scene scene = new Scene(container, 150, 220);
			window.setScene(scene);
			window.showAndWait();
		}
	}

	private class ProvideUsername {

		/* displays a box to confirm quitting from a game */
		public void getUserName() {
			// setup stage
			Stage window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Provide Username");
			window.setMinWidth(400);

			// setup label
			Label informationLabel = new Label("NOTE: To create new profile simply enter a new combination of username and password.");
			informationLabel.setWrapText(true);
			informationLabel.setAlignment(Pos.CENTER);
			// setting up sub-container
			HBox hbox_text = new HBox();
			hbox_text.setSpacing(10);
			hbox_text.getChildren().addAll(informationLabel);
			hbox_text.setAlignment(Pos.CENTER);
			hbox_text.setPadding(new Insets(5, 12, 5, 12));

			// create text fields
	        TextField usernameInput = new TextField();
	        usernameInput.setAlignment(Pos.CENTER);
	        usernameInput.setPromptText("Please provide your User Name");
	        TextField passwordInput = new TextField();
	        passwordInput.setAlignment(Pos.CENTER);
	        passwordInput.setPromptText("Please enter your password");

			Button submit = new Button("submit");
			submit.setMinWidth(130);


			// setup event handlers
			submit.setOnAction(e -> {
				String username = usernameInput.getText().trim();
				String password = passwordInput.getText().trim();

				/* what heppens when user doesn't provide input */
	    		if(username.equals("") || username == null){
    				new PopupNotification("Input username", "please, input username to open chat");
	    		}
	    		else if(password.equals("") || password == null){
    				new PopupNotification("Input password", "please, input password to login to chat");
	    		}


	    		else{
	    			/* what happens when user provides VALID and UNUSED user name */
	    			if(!loginData.username_password_from_server.containsKey(username)){
	    				String[] username_password = {username, password};
		    			client.setUsername(username);
		    			try {
							client.getObjectWriter().writeObject(new Message(MessageType.USERNAME_PASSWORD_INPUT,
									username_password,
									client.getUsername()));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						window.close();
	    			}

	    			/* USERNAME has been used in the past */
	    			else{
	    				/* logged back in correctly */
	    				if(loginData.username_password_from_server.get(username).equals(password)){
			    			client.setUsername(username);
			    			try {
								client.getObjectWriter().writeObject(new Message(MessageType.USERNAME_PASSWORD_INPUT,
										null,
										client.getUsername()));
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							window.close();
	    				}
	    				/* wrong password */
	    				else{
		    				new PopupNotification("Username is already in use OR wrong password", "Incorect password for this username");
	    				}
	    			}
	    		}
			});


			// setting up main container
			VBox container = new VBox();
			container.setPadding(new Insets(15, 12, 15, 12));
			container.setStyle("-fx-background-color: #fffac8;");

			// setting up sub-container
			HBox hbox = new HBox();
		    hbox.setSpacing(30);
		    hbox.getChildren().addAll(submit);
		    hbox.setAlignment(Pos.CENTER);
		    hbox.setPadding(new Insets(15, 12, 15, 12));

			container.getChildren().addAll(hbox_text, usernameInput, passwordInput, hbox);
			container.setAlignment(Pos.CENTER);

			// displaying
			Scene scene = new Scene(container, 150, 220);
			window.setScene(scene);
			window.showAndWait();
		}
	}
}
