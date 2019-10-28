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

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RunStats {

	private static Stage window;
	private static BorderPane container;
	private static ArrayList<Label> display_stats;  // TODO: every time step all the labels included must be refreshed
	private static CheckBox[] list_of_boxes;
	private static boolean windowCreated = false;

    /* constructor */
	public RunStats(Tab stats){
		container = display();
		stats.setContent(container);
	}

	public static BorderPane display(){
		BorderPane bPane = new BorderPane(); 
		HBox hbox = new HBox();
		HBox hbox2 = new HBox();
		VBox vbox = new VBox();

		Label info = new Label("Display stats for following critters:");
		info.setFont(new Font(20));
		hbox.getChildren().add(info);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(20, 12, 5, 12));
		
		Button display_stats = new Button("Display stats");
		display_stats.setOnAction(e -> {
			if(window != null){
				window.close();
			}
			displayStats();
		});
		hbox2.getChildren().add(display_stats);
		hbox2.setAlignment(Pos.CENTER);
		hbox2.setPadding(new Insets(0, 12, 70, 12));
		
		list_of_boxes = new CheckBox[Main.critters.size()];
		int i = 0;
		for(String critter : Main.critters){
			list_of_boxes[i] = new CheckBox(critter);
			vbox.getChildren().add(list_of_boxes[i]);
			i++;
		}
		bPane.setTop(hbox);
		bPane.setCenter(vbox);
		bPane.setBottom(hbox2);
        vbox.setAlignment(Pos.BASELINE_LEFT);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(15, 12, 15, 12));
		return bPane;
	}
	
	public static void displayStats(){
		if(windowCreated == false) {
			window = new Stage();
			window.setTitle("Run Stats Display");
			window.setMinWidth(Main.width);
			display_stats = new ArrayList<Label>();
			window.setX(Main.x_coord);
			window.setY(Main.y_coord + Main.height + 50);
			windowCreated = true;
		}
		
		VBox content = createField();
		Scene scene = new Scene(content, Main.width, 205);
		window.setScene(scene);
		window.show();
	}


    private static VBox createField() {
        VBox container = new VBox();
        
        for(CheckBox box : list_of_boxes){
        	if(box.isSelected()){
        		String critter_name = box.toString();
        		int index1 = critter_name.indexOf("'");
        		String str = critter_name.substring(index1);
        		int index2 = str.indexOf("'");
        		String critter = str.substring(1, str.length()-1);
    			Critter critter_object;
				try {
					critter_object = getCritterFromString(Critter.getMyPackage() + "." + critter);
	    			Method m;
					m = critter_object.getClass().getMethod("runStats", List.class);
					String output = (String) m.invoke(critter_object, critter_object.getInstances(critter));  //String str2 = (String)
	        		Label label = new Label(output);
	        		container.getChildren().add(label);
				} catch (InvalidCritterException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				} 
        	}
        }
        container.setPadding(new Insets(15, 12, 15, 12));

        return container;
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
