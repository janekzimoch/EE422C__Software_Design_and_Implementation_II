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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class SetControls implements EventHandler<ActionEvent> {

	private GridPane container;

	private Button groupChat;
	private Button directChat;
    private static ComboBox<String> available_GC;
	private static ComboBox<String> available_DC;
	private TextArea membersSelected;
	private Button choose;

	private Message loginData;
	private static Client client;

	private List<String> membersSelectedList;
	private static TextArea clientsOnline;

    /* constructor */
	public SetControls(Tab controls, Message loginData, Client client){
		membersSelectedList = new ArrayList<String>();
		this.client = client;
		this.loginData = loginData;
		container = display();
		controls.setContent(container);
	}

	/* create a AboutCritters tab */
	public GridPane display(){

		GridPane grid = new GridPane();

		/* create buttons */
        directChat = new Button("send private message");
        directChat.setMinWidth(120);
        directChat.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        directChat.setStyle("-fx-text-fill: #00b32c");


        available_DC = new ComboBox<String>();
        available_DC.setPromptText("available");
        Set<String> setOFClients = loginData.username_password_from_server.keySet();
        setOFClients.remove(client.getUsername());
        available_DC.getItems().addAll(setOFClients.toArray(new String[setOFClients.size()]));
        available_DC.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		VBox vbox = new VBox();
		vbox.getChildren().addAll(directChat, available_DC);
		vbox.setAlignment(Pos.BASELINE_CENTER);
        vbox.setSpacing(10);


        groupChat = new Button("create a group chat");
        groupChat.setMinWidth(200);
        groupChat.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        groupChat.setStyle("-fx-text-fill: #00b32c");


        choose = new Button("add");
        choose.setMinWidth(120);
        choose.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);


        /* create drop down menu */
        available_GC = new ComboBox<String>();
        available_GC.getItems().addAll(setOFClients.toArray(new String[setOFClients.size()]));
        available_GC.setPromptText("available");
        available_GC.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);


        /* Text area to display people added to a group chat */
        membersSelected = new TextArea();
        membersSelected.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        clientsOnline = new TextArea();
        updateClientsOnline(setOFClients.toArray(new String[setOFClients.size()]));
        clientsOnline.setMaxWidth(150);

        grid.add(vbox, 0, 0, 2, 1);
        grid.add(clientsOnline, 2, 0, 1, 6);

        grid.add(groupChat, 0, 3, 2, 1);
        grid.add(available_GC, 0, 4, 1, 1);
        grid.add(choose, 1, 4, 1, 1);
        grid.add(membersSelected, 0, 5, 2, 1);

        groupChat.setOnAction(this);
        directChat.setOnAction(this);
        choose.setOnAction(this);

        // configure grid (GridPane)
        grid.setHgap(15);
        grid.setVgap(25);
        grid.setPadding(new Insets(15, 10, 0, 10));
        return grid;
	}

	public void handle(ActionEvent event) {
        if(event.getSource() == choose){
        	if(available_GC.getValue() == null){  // no user was selected
        		new PopupNotification("No Client selected to add", "Please provide who you want to add to the chat group");
        	}
        	else{
        		String member = available_GC.getValue();
        		if(!membersSelectedList.contains(member)){
            		membersSelectedList.add(member);
        		}
        		else{
            		new PopupNotification(member + " in the list", member + " is already included in the list");
        		}
        		String setText = "";
        		for(String user : membersSelectedList){
        			setText += user + " \n";
        		}
        		membersSelected.setText(setText);
        	}
        	available_GC.getSelectionModel().clearSelection();
        }

		if(event.getSource() == groupChat){
        	/*
        	 * send a message to a server such that it can create an appropriate observable
        	 * you have to include a list of user names that are in this group chat so ServerMain can create appropriate observable
        	 */
        	if(membersSelectedList.size() == 0){  // no user was selected
        		new PopupNotification("No Clients provided", "Please provide who you wnat to chat with.");
        	}
        	else{
        		membersSelectedList.add(client.getUsername());
        		createChatRoom(membersSelectedList);
        	}
        	membersSelectedList.clear();
        	membersSelected.setText("");
        }

        if(event.getSource() == directChat){
        	if(available_DC.getValue() == null){  // no user was selected
        		new PopupNotification("No Client provided", "Please provide who you want to chat with.");
        	}
        	else{
        		membersSelectedList.clear();
        		String member = available_DC.getValue();
//        		System.out.println(member);
        		membersSelectedList.add(member);
        		membersSelectedList.add(client.getUsername());
//        		System.out.println(membersSelectedList);
        		createChatRoom(membersSelectedList);
        	}
        	membersSelectedList.clear();
        	available_DC.getSelectionModel().clearSelection();
        }
	}

	private void createChatRoom(List<String> membersSelectedList){
		try {
			client.getObjectWriter().writeObject(new Message(MessageType.LIST_OF_USERS_FOR_CHAT,
					membersSelectedList,
					client.getUsername()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/* method responsible for updating GUI. it is called every time there is some change introduced to the Map of all the
	 * users */
	public static void updateGUI(String[] availableUsers) {
		Set<String> temp_set = new HashSet<String>(Arrays.asList(availableUsers));
		temp_set.remove(client.getUsername());
		String[] availUsers = temp_set.toArray(new String[temp_set.size()]);


		// update 3 javaFX fields that display list of Clients currently available for direct/group chatting
		updateClientsOnline(availUsers);
		updateAvailable_DC(availUsers);
		updateAvailable_GC(availUsers);
	}

	private static void updateAvailable_GC(String[] availableUsers) {
		available_GC.getItems().clear();
		available_GC.getItems().addAll(availableUsers);
	}

	private static void updateAvailable_DC(String[] availableUsers) {
		available_DC.getItems().clear();
		available_DC.getItems().addAll(availableUsers);
	}

	private static void updateClientsOnline(String[] availableUsers) {
		String setText = "";
		for(String user : availableUsers){
			setText += user + " \n";
		}
		clientsOnline.setText(setText);
	}
}