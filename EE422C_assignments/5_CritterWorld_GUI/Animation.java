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

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;

public class Animation implements EventHandler<ActionEvent>{

	private VBox container;

	/* all elements */
	private Slider slider;
	private Button pause;
	private Button run;
	private int delayTime;
	private boolean animStarted = false;
	
    /* constructor */
	public Animation(Tab animation){
		container = display();
		animation.setContent(container);
	}

	/* create a Animation tab */
	public VBox display(){
		//BorderPane bPane = new BorderPane();
		HBox hbox = new HBox();
		HBox hbox2 = new HBox();
		HBox hbox3 = new HBox();
		HBox hbox4 = new HBox();
		VBox vbox = new VBox();


		Label prompt = new Label("Select the speed of animation:");
		prompt.setFont(new Font(20));
		hbox.getChildren().add(prompt);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(20, 12, 5, 12));
	
        slider = new Slider(1, 10, 1);
        slider.setBlockIncrement(1);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setShowTickLabels(true);
        slider.setSnapToTicks(true);
        slider.setMinWidth(250);
		hbox3.getChildren().add(slider);
		hbox3.setAlignment(Pos.CENTER);
        hbox3.setSpacing(10);
		hbox3.setPadding(new Insets(20, 12, 5, 12));
		
        Label display_value = new Label();
        display_value.textProperty().bind(
                Bindings.format(
                    "# Frames per step: %.0f",
                    slider.valueProperty())
            );
		hbox2.getChildren().add(display_value);
		hbox2.setAlignment(Pos.CENTER);
        hbox2.setSpacing(10);
		hbox2.setPadding(new Insets(20, 12, 5, 12));

        run = new Button("run");
        run.setOnAction(this);
        run.setMinWidth(70);
        run.setStyle("-fx-text-fill: #00b32c");
        pause = new Button("pause");
        pause.setOnAction(this);
        pause.setMinWidth(70);
        pause.setStyle("-fx-text-fill: #b3000c");
		hbox4.getChildren().addAll(run, pause);
		hbox4.setAlignment(Pos.CENTER);
		hbox4.setSpacing(30);
		hbox4.setPadding(new Insets(20, 12, 5, 12));

        vbox.getChildren().addAll(hbox, hbox2, hbox3, hbox4);


		return vbox;
	}
	
	Thread animThread = new Thread(new Runnable() {
        @Override
        public void run() {
            Runnable updater = new Runnable() {
                @Override
                public void run() {
                	delayTime = (int) (1000 / slider.valueProperty().doubleValue());
                    Critter.worldTimeStep();
                	Critter.displayWorld(WorldDisplay.worldWindow);
                	RunStats.displayStats();
                }
            };
            while (true) {
                try {
                    Thread.sleep(delayTime);
                } catch (InterruptedException ex) {
                }
                // UI update is run on the Application thread
                Platform.runLater(updater);
            }
        }

    });


	public void handle(ActionEvent event) {
        if(event.getSource() == pause){
        	Main.controls.setDisable(false);                                                                                                                                           
    		Main.about_critters.setDisable(false);
    		Main.parameters.setDisable(false);
    		Main.stats.setDisable(false);
    		run.setDisable(false);
        	animThread.suspend();
        	//animThread.setDaemon(false);
        }
        
        if(event.getSource() == run){
        	if(animStarted == false) {
        		Main.controls.setDisable(true);
        		Main.about_critters.setDisable(true);
        		Main.parameters.setDisable(true);
        		Main.stats.setDisable(true);
        		run.setDisable(true);
        		animStarted = true;
        		animThread.setDaemon(true);
        		animThread.start();
        	}
        	else {
        		Main.controls.setDisable(true);
        		Main.about_critters.setDisable(true);
        		Main.parameters.setDisable(true);
        		Main.stats.setDisable(true);
        		run.setDisable(true);
        		animThread.resume();
        	}
       
        }	
	        	
        	
        

	}

}

