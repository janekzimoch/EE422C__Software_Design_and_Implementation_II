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
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;

public class Controls extends LookCritters implements EventHandler<ActionEvent>  {

	private GridPane container;

	/* all elements */
	private Button show;
	private Button step;
	private Button quit;
	private Button clear;
	private Button seed;
	private Button create;
	private Button stats;
    private ComboBox<String> critterList;
    private ComboBox<String> critterStats;
    private TextField numberOfSteps;
    private TextField numberOfCritters;
    private TextField numberOfSeeds;
    private Label animationSpeed;
    private Slider slider;
    public String numStepsText = "# of Steps.";

    /* constructor */
	public Controls(Tab controls){
		container = display();
		controls.setContent(container);
	}

	/* create a Controls tab */
    public GridPane display(){

		GridPane grid = new GridPane();

		/* create buttons */
        show = new Button("refresh display");
        show.setMinWidth(120);
        show.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        step = new Button("run step");
        step.setMinWidth(120);
        step.setStyle("-fx-text-fill: #00b32c");

        quit = new Button("quit game");
        quit.setMinWidth(120);
        quit.setStyle("-fx-text-fill: #b3000c");
        quit.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);


        clear = new Button("clear progress");
        clear.setMinWidth(120);

        seed = new Button("set seed");
        seed.setMinWidth(120);

        create = new Button("create critter");
        create.setMinWidth(120);
        create.setStyle("-fx-text-fill: #00b32c");

        /* create drop down menu */
        critterList = new ComboBox<String>();
        critterList.getItems().addAll(getCrittersList());
        critterList.setPromptText("select critter to be created");

        /* create input text fields */
        numberOfCritters = new TextField();
        numberOfCritters.setPromptText("# of critters.");
        numberOfSeeds = new TextField();
        numberOfSeeds.setPromptText("Seed number.");
        numberOfSteps = new TextField();
        numberOfSteps.setPromptText("# of Steps.");

        // add elements to a grid
        grid.add(show, 0, 0, 3, 1);
        grid.add(step, 0, 2, 1, 1);
        grid.add(numberOfSteps, 1, 2, 1, 1);
        grid.add(create, 0, 3, 1, 1);
        grid.add(critterList, 2, 3, 1, 1);
        grid.add(numberOfCritters, 1, 3, 1, 1);
        grid.add(seed, 0, 8, 1, 1);
        grid.add(numberOfSeeds, 1, 8, 1, 1);
        grid.add(clear, 0, 7, 1, 1);
        grid.add(quit, 0, 9, 3, 1);


        show.setOnAction(this);
        step.setOnAction(this);
        quit.setOnAction(this);
        clear.setOnAction(this);
        seed.setOnAction(this);
        create.setOnAction(this);


        // configure grid (GridPane)
        grid.setHgap(15);
        grid.setVgap(25);
        grid.setPadding(new Insets(15, 10, 0, 10));
        return grid;
	}

	public void handle(ActionEvent event) {
        if(event.getSource() == show){
        	Critter.displayWorld(WorldDisplay.worldWindow);
        	RunStats.displayStats();
        }

        if(event.getSource() == step){
        	/* displays world representation in a new window;
        	 * blocks all controls until a display world representation is closed */
        	int num;
        	if(!(numberOfSteps.getText().equals(null)) ){
	        	if(isInteger(numberOfSteps.getText())){
	        		num = Integer.parseInt(numberOfSteps.getText());
	        		for(int i = 0; i < num; i++) {
	        			Critter.worldTimeStep();
	        			Main.number_of_steps++;
	        		}
	            	Critter.displayWorld(WorldDisplay.worldWindow);
                	RunStats.displayStats();
	        	}
	        	else if(numberOfSteps.getText().matches(".*[a-zA-Z].*")){
	        		PopUpWindow.display("Invalid Input","Input has to be an integer");
	        	}
	        	else{
		        	Critter.worldTimeStep();
		        	Critter.displayWorld(WorldDisplay.worldWindow);
                	RunStats.displayStats();
		        }
        	}
        	numberOfSteps.clear();
        }

        if(event.getSource() == quit){
        	exitProgram();
        }

        if(event.getSource() == clear){
        	Critter.clearWorld();
        }
        if(event.getSource() == seed){
        	if(numberOfSeeds.getText() != null && isInteger(numberOfSeeds.getText())){
        		Critter.setSeed(Integer.parseInt(numberOfSeeds.getText()));
        	}
        	else{
        		PopUpWindow.display("Invalid Input","Input has to be an integer");
        	}
        	numberOfSeeds.clear();
        }

        if(event.getSource() == create){
        	if(critterList.getValue() == null){  // no critter type was selected
        		PopUpWindow.display("No Critter Selected","To create a critter you must specify the type first");
        	}
        	else if(numberOfCritters.getText() != null && isInteger(numberOfCritters.getText())){  // critter type was selected
        		int num = Integer.parseInt(numberOfCritters.getText());
        		for(int i = 0; i < num; i++) {
        			try {
						Critter.createCritter(critterList.getValue());
					} catch (InvalidCritterException e) {
						PopUpWindow.display("error", "error processing... sorry.");
					}
        		}
        	}
        	RunStats.displayStats();
        	numberOfCritters.clear();
        }
    }


    /* ran upon request to exit program. Calls a prompt to confirm intention of closing.*/
    public void exitProgram(){
    	boolean quit = ConfirmQuit.display("Quit Game", "Are you sure you want to quit the game?");
    	if(quit){
    		System.exit(0);
    	}
    }


    /* check whether input is a number */
    public static boolean isInteger(String input) {
        try {
            int i = Integer.parseInt(input);
        } catch (NumberFormatException | NullPointerException error) {
            return false;
        }
        return true;
    }


    /* check whether input contains a valid critter name */
    public static boolean isCritter(String input) {
        for (String critter : Main.critters) {
        	if(input.contentEquals(critter)) {
        		return true;
        	}
        }
        return false;
    }
}
