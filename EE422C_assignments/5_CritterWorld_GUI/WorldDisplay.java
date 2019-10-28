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

import assignment5.Grid.Cell;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WorldDisplay {

	private static final Pos TOP_LEFT = null;
	private static Grid grid;
	public static Stage worldWindow = new Stage();
	public static HBox hbox2;
	public static Scene scene;
	static VBox container = new VBox();
	public static boolean initialDisp = false;
	
	public static void display(double x, double y){
		worldWindow.setX(x);
		worldWindow.setY(y);
		worldWindow.setTitle(String.format("world display - step %d", Main.number_of_steps));
		worldWindow.setMinWidth(500);
		
		scene = new Scene(createGrid());
		scene.setFill(Color.YELLOW);
		
		worldWindow.setScene(scene);
		worldWindow.show();
	}

	public static double[] display(){
		worldWindow = new Stage();
		worldWindow.setTitle(String.format("world display - step %d", Main.number_of_steps));
		worldWindow.setMinWidth(500);

		scene = new Scene(createGrid());
		worldWindow.setScene(scene);
		worldWindow.show();
		double x = worldWindow.getX();
		worldWindow.setX(x+250);
		double[] list = {worldWindow.getX(), worldWindow.getY()};
		return list;
	}


    public static Parent createGrid() {
    	Grid.setSize();
        int width = Grid.cellSize*Params.WORLD_WIDTH;
        int height = Grid.cellSize*Params.WORLD_HEIGHT+100;
        container.setPrefSize(width, height);


        grid = new Grid();
        hbox2 = new HBox();
        hbox2.getChildren().add(grid);
        hbox2.setAlignment(TOP_LEFT);
        hbox2.setPadding(new Insets(0,10,0,10));
        container.getChildren().add(hbox2);
        return container;
    }
    
}
