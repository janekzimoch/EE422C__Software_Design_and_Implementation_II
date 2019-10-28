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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class AboutCritters extends LookCritters{

	private BorderPane container;

	/* all elements */
	private ComboBox<String> critterList;
	private Button show;
	private Text characteristics;
	private Tab learn;
	private String label_content;
	private ScrollPane scrollPane;

    /* constructor */
	public AboutCritters(Tab learn){
		this.learn = learn;
		label_content = "";
		container = display();
		learn.setContent(container);
	}

	/* create a AboutCritters tab */
	public BorderPane display(){
		BorderPane bPane = new BorderPane();
		VBox vbox = new VBox();
		HBox hbox = new HBox();

		Label info = new Label("Select Critter to display their characteristics:");
		info.setFont(new Font(20));
		critterList = new ComboBox<String>();
        critterList.getItems().addAll(getCrittersList());
        critterList.setPromptText("select critter to be created");
        show = new Button("show");
        show.setMinWidth(200);

		vbox.getChildren().addAll(info, critterList, show);
		vbox.setAlignment(Pos.BASELINE_CENTER);
        vbox.setSpacing(10);
		vbox.setPadding(new Insets(20, 12, 40, 12));

        characteristics = new Text(10, 50, label_content);
        characteristics.setFont(new Font(16));
        characteristics.setTextAlignment(TextAlignment.CENTER);
        hbox.getChildren().add(characteristics);
        hbox.setAlignment(Pos.BASELINE_CENTER);
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(45, 12, 15, 12));
	    scrollPane = new ScrollPane(hbox);
	    scrollPane.setFitToHeight(true);	    

		bPane.setTop(vbox);
		bPane.setCenter(scrollPane);

        show.setOnAction(e -> {
        	label_content = accessCritterCharacter();
        	container = display();
        	learn.setContent(container);
        });

		return bPane;
	}

	public String accessCritterCharacter(){
		if(critterList.getValue() == null){  // no critter type was selected
    		PopUpWindow.display("No Critter Selected","To create a critter you must specify the type first");
    	}
    	else{  // critter type was selected
    		Critter critter_object;
			try {
				critter_object = getCritterFromString(Critter.getMyPackage() + "." + critterList.getValue());
				String str = critter_object.displayCharacter();
				return str;
			} catch (InvalidCritterException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
    	}
		return "";
	}

    public static Critter getCritterFromString (String critter_class_name) throws InvalidCritterException {
		Class<?> myCritter = null;
		Constructor<?> constructor = null;
		Object instanceOfMyCritter = null;

		try {
			myCritter = Class.forName(critter_class_name); 	// Class object of specified name
		} catch (ClassNotFoundException e) {
			throw new InvalidCritterException(critter_class_name);
		}
		try {
			constructor = myCritter.getConstructor();		// No-parameter constructor object
			instanceOfMyCritter = constructor.newInstance();	// Create new object using constructor
		} catch (Exception e) {
			e.printStackTrace();
		}
		Critter me = (Critter)instanceOfMyCritter;		// Cast to Critter
		return me;
	}

}
