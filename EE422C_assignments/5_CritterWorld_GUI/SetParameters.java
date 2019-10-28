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


import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.*;


public class SetParameters implements EventHandler<ActionEvent>{

	private GridPane container;

	/* all elements */
	private TextField sizeOf_world_width;
	private TextField sizeOf_world_height;
	private TextField sizeOf_walk_energy_cost;
	private TextField sizeOf_run_energy_cost;
	private TextField sizeOf_rest_energy_cost;
	private TextField sizeOf_min_reproduce_energy;
	private TextField sizeOf_refresh_clover_count;
	private TextField sizeOf_photosynthesis_energy_amount;
	private TextField sizeOf_start_energy;
	private Button world_width;
	private Button world_height;
	private Button walk_energy_cost;
	private Button run_energy_cost;
	private Button rest_energy_cost;
	private Button min_reproduce_energy;
	private Button refresh_clover_count;
	private Button photosynthesis_energy_amount;
	private Button start_energy;



    /* constructor */
	public SetParameters(Tab parameters){
		container = display();
		parameters.setContent(container);
	}

	/* create a AboutCritters tab */
	public GridPane display(){
		GridPane grid = new GridPane();

	    sizeOf_world_width = new TextField();
	    sizeOf_world_width.setPromptText("width of the World");

	    sizeOf_world_height = new TextField();
	    sizeOf_world_height.setPromptText("height of the World");

	    sizeOf_walk_energy_cost = new TextField();
	    sizeOf_walk_energy_cost.setPromptText("walk energy cost");

	    sizeOf_run_energy_cost = new TextField();
	    sizeOf_run_energy_cost.setPromptText("run energy cost");

	    sizeOf_rest_energy_cost = new TextField();
	    sizeOf_rest_energy_cost.setPromptText("rest energy cost");

	    sizeOf_min_reproduce_energy = new TextField();
	    sizeOf_min_reproduce_energy.setPromptText("minimum reporduce energy");

	    sizeOf_refresh_clover_count = new TextField();
	    sizeOf_refresh_clover_count.setPromptText("refresh clover count");

	    sizeOf_photosynthesis_energy_amount = new TextField();
	    sizeOf_photosynthesis_energy_amount.setPromptText("photosynthesis energy");

	    sizeOf_start_energy = new TextField();
	    sizeOf_start_energy.setPromptText("start energy");


		/* create buttons */
        world_width = new Button("set");
        world_width.setMinWidth(100);

        world_height = new Button("set");
        world_height.setMinWidth(100);

        walk_energy_cost = new Button("set");
        walk_energy_cost.setMinWidth(100);

        run_energy_cost = new Button("set");
        run_energy_cost.setMinWidth(100);

        rest_energy_cost = new Button("set");
        rest_energy_cost.setMinWidth(100);

        min_reproduce_energy = new Button("set");
        min_reproduce_energy.setMinWidth(100);

        refresh_clover_count = new Button("set");
        refresh_clover_count.setMinWidth(100);

        photosynthesis_energy_amount = new Button("set");
        photosynthesis_energy_amount.setMinWidth(100);

        start_energy = new Button("set");
        start_energy.setMinWidth(100);


        /* add elements to the gridPane */
        grid.add(world_width, 1, 1, 1, 1);
        grid.add(world_height, 1, 2, 1, 1);
        grid.add(walk_energy_cost, 1, 3, 1, 1);
        grid.add(run_energy_cost, 1, 4, 1, 1);
        grid.add(rest_energy_cost, 1, 5, 1, 1);
        grid.add(min_reproduce_energy, 1, 6, 1, 1);
        grid.add(refresh_clover_count, 1, 7, 1, 1);
        grid.add(photosynthesis_energy_amount, 1, 8, 1, 1);
        grid.add(start_energy, 1, 9, 1, 1);
        grid.add(sizeOf_world_width, 0, 1, 1, 1);
        grid.add(sizeOf_world_height, 0, 2, 1, 1);
        grid.add(sizeOf_walk_energy_cost, 0, 3, 1, 1);
        grid.add(sizeOf_run_energy_cost, 0, 4, 1, 1);
        grid.add(sizeOf_rest_energy_cost, 0, 5, 1, 1);
        grid.add(sizeOf_min_reproduce_energy, 0, 6, 1, 1);
        grid.add(sizeOf_refresh_clover_count, 0, 7, 1, 1);
        grid.add(sizeOf_photosynthesis_energy_amount, 0, 8, 1, 1);
        grid.add(sizeOf_start_energy, 0, 9, 1, 1);


        // set on action
        world_width.setOnAction(this);
        world_height.setOnAction(this);
        walk_energy_cost.setOnAction(this);
        run_energy_cost.setOnAction(this);
        rest_energy_cost.setOnAction(this);
        min_reproduce_energy.setOnAction(this);
        refresh_clover_count.setOnAction(this);
        photosynthesis_energy_amount.setOnAction(this);
        start_energy.setOnAction(this);

        // configure grid (GridPane)
        grid.setHgap(10);
        grid.setVgap(15);
        grid.setPadding(new Insets(15, 10, 0, 10));
		return grid;
	}


	public void handle(ActionEvent event) {
        if(event.getSource() == world_width){
        	if(isInteger(sizeOf_world_width.getText())){
        		int num = Integer.parseInt(sizeOf_world_width.getText());
        		Params.WORLD_WIDTH = num;
        		sizeOf_world_width.clear();
        		Main.initialiseCritterWorld();
        		PopUpWindow.display("Parameter Update","Parameter updated Succesfully!");
        	}
        	else{
        		PopUpWindow.display("Invalid Input","Input has to be an integer");
        	}
        }

        if(event.getSource() == world_height){
        	if(isInteger(sizeOf_world_height.getText())){
        		int num = Integer.parseInt(sizeOf_world_height.getText());
        		Params.WORLD_HEIGHT = num;
        		sizeOf_world_height.clear();
        		Main.initialiseCritterWorld();
        		PopUpWindow.display("Parameter Update","Parameter updated Succesfully!");
        	}
        	else{
        		PopUpWindow.display("Invalid Input","Input has to be an integer");
        	}
        }

        if(event.getSource() == walk_energy_cost){
        	if(isInteger(sizeOf_walk_energy_cost.getText())){
        		int num = Integer.parseInt(sizeOf_walk_energy_cost.getText());
        		Params.WALK_ENERGY_COST = num;
        		sizeOf_walk_energy_cost.clear();
        		PopUpWindow.display("Parameter Update","Parameter updated Succesfully!");
        	}
        	else{
        		PopUpWindow.display("Invalid Input","Input has to be an integer");
        	}
        }

        if(event.getSource() == run_energy_cost){
        	if(isInteger(sizeOf_run_energy_cost.getText())){
        		int num = Integer.parseInt(sizeOf_run_energy_cost.getText());
        		Params.RUN_ENERGY_COST = num;
        		sizeOf_run_energy_cost.clear();
        		PopUpWindow.display("Parameter Update","Parameter updated Succesfully!");
        	}
        	else{
        		PopUpWindow.display("Invalid Input","Input has to be an integer");
        	}
        }

        if(event.getSource() == rest_energy_cost){
        	if(isInteger(sizeOf_rest_energy_cost.getText())){
        		int num = Integer.parseInt(sizeOf_rest_energy_cost.getText());
        		Params.REST_ENERGY_COST = num;
        		sizeOf_rest_energy_cost.clear();
        		PopUpWindow.display("Parameter Update","Parameter updated Succesfully!");
        	}
        	else{
        		PopUpWindow.display("Invalid Input","Input has to be an integer");
        	}
        }

        if(event.getSource() == min_reproduce_energy){
        	if(isInteger(sizeOf_min_reproduce_energy.getText())){
        		int num = Integer.parseInt(sizeOf_min_reproduce_energy.getText());
        		Params.MIN_REPRODUCE_ENERGY = num;
        		sizeOf_min_reproduce_energy.clear();
        		PopUpWindow.display("Parameter Update","Parameter updated Succesfully!");
        	}
        	else{
        		PopUpWindow.display("Invalid Input","Input has to be an integer");
        	}
        }

        if(event.getSource() == photosynthesis_energy_amount){
        	if(isInteger(sizeOf_photosynthesis_energy_amount.getText())){
        		int num = Integer.parseInt(sizeOf_photosynthesis_energy_amount.getText());
        		Params.PHOTOSYNTHESIS_ENERGY_AMOUNT = num;
        		sizeOf_photosynthesis_energy_amount.clear();
        		PopUpWindow.display("Parameter Update","Parameter updated Succesfully!");
        	}
        	else{
        		PopUpWindow.display("Invalid Input","Input has to be an integer");
        	}
        }

        if(event.getSource() == start_energy){
        	if(isInteger(sizeOf_start_energy.getText())){
        		int num = Integer.parseInt(sizeOf_start_energy.getText());
        		Params.START_ENERGY = num;
        		sizeOf_start_energy.clear();
        		PopUpWindow.display("Parameter Update","Parameter updated Succesfully!");
        	}
        	else{
        		PopUpWindow.display("Invalid Input","Input has to be an integer");
        	}
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

}
