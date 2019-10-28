package assignment5;

/*
* CRITTERS Main.java
* EE422C Project 5 submission by
* Replace <...> with your actual data.
* <Ryed Ahmed>
* <ra35335>
* <16190>
* <Janek Z>
* <jsz323>
* <16190>
* Slip days used: <0>
* Spring 2019
*/

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopUpWindow {

	/* displays a box to confirm quitting from a game */
	public static void display(String title, String messege){
		// setup stage
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(400);

		
		// create elements
		Label prompt = new Label();
		prompt.setText(messege);

		Button ok = new Button("yes");
		ok.setMinWidth(100);

		
		// setup event handlers
		ok.setOnAction(e -> {
			window.close();
		});


		// setting up main container
		VBox container = new VBox();
		container.setPadding(new Insets(15, 12, 15, 12));
		container.setStyle("-fx-background-color: #fffac8;");

		// setting up sub-container
		HBox hbox = new HBox();
	    hbox.setSpacing(30);
	    hbox.getChildren().addAll(ok);
	    hbox.setAlignment(Pos.CENTER);
	    hbox.setPadding(new Insets(15, 12, 15, 12));

		container.getChildren().addAll(prompt, hbox);
		container.setAlignment(Pos.CENTER);

		// displaying
		Scene scene = new Scene(container, 150, 150);
		window.setScene(scene);
		window.showAndWait();
	}
}
