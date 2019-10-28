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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;


/*
 * Usage: java <pkg name>.Main <input file> test input file is
 * optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */

public class Main extends Application{

    /* coordinates of a control panel */
    public static double x_of_new_stage;
    public static double y_of_new_stage;


    /* counter of how many steps were made in the game */
    public static int number_of_steps;

    static Stage window;

    /* information about location of control panel window */
    public static double x_coord;
    public static double y_coord;
    public static double width;
    public static double height;

    /*Declaration of all tabs for controller*/
    public static TabPane tabpane;
    public static Tab controls;
    public static Tab animation;
    public static Tab about_critters;
    public static Tab parameters;
    public static Tab stats;

    /* Scanner connected to keyboard input, or input file */
    static Scanner kb;

    /* Input file, used instead of keyboard input if specified */
    private static String inputFile;

    /* If test specified, holds all console output */
    static ByteArrayOutputStream testOutputString;

    /* Use it or not, as you wish! */
    private static boolean DEBUG = false;

    /* if you want to restore output to console */
    static PrintStream old = System.out;

    /* Gets the package name.  The usage assumes that Critter and its
       subclasses are all in the same package. */
    private static String myPackage; // package of Critter file.

    /* list of all critters in this version of the game */
    public static List<String> critters;

    /* Critter cannot be in default pkg. */
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	Params.initialiseParams();  // Params are not final in this program -- they have to be initialised.
    	initialiseCritterWorld();  // Initialise critterWorld engine
    	getCritterTypes();  // Load critter classes from directory

    	window = primaryStage;
    	window.setTitle("Control panel");

        number_of_steps = 0;

        tabpane = new TabPane();
        controls = new Tab("Controls");
        animation = new Tab("Animation");
        about_critters = new Tab("About critters");
        parameters = new Tab("Set Parameters");
        stats = new Tab("Stats");

        tabpane.getTabs().add(controls);
        tabpane.getTabs().add(animation);
        tabpane.getTabs().add(about_critters);
        tabpane.getTabs().add(parameters);
        tabpane.getTabs().add(stats);

        // each of these classes creates it's own tab and is responsible for handling events in them
        SetParameters prameters_class = new SetParameters(parameters);
        Animation animation_class = new Animation(animation);
        AboutCritters about_critters_class = new AboutCritters(about_critters);
        Controls controls_class = new Controls(controls);
        RunStats stats_class = new RunStats(stats);

        window.setOnCloseRequest(e -> {
        	e.consume();
        	controls_class.exitProgram();
        });

        width = 465;
        height = 515;
        Scene scene = new Scene(tabpane, width, height);
        window.setScene(scene);


        double[] list = {0,0};
        list = WorldDisplay.display();
        window.setX(list[0] - 475);
        window.setY(list[1]);
        x_coord = window.getX();
        y_coord = window.getY();
        window.show();
        RunStats.displayStats();
    }


    /* function to initialise critterWorld */
    public static void initialiseCritterWorld(){
      CritterWorld critter_world = new CritterWorld();
      //WorldVisual visualisation = new WorldVisual();
    }


    /* search through a directory to find a list of all .java Critter types */
    public static void getCritterTypes(){
    	critters = new ArrayList<String>();
    	String directory = System.getProperty("user.dir") + File.separator + "src" + File.separator + "assignment5";
    	File f = new File(directory);
    	File[] matchingFiles = f.listFiles(new FilenameFilter() {
    	    public boolean accept(File dir, String name) {  // if you want to introduce new unique critters names you have to change this method
    	        return (name.startsWith("MyCritter") ||
    	        		name.startsWith("Clover") ||
    	        		name.startsWith("Goblin") ||
    	        		name.startsWith("Critter") &&
    	        		name.endsWith("java") &&
    	        		!name.startsWith("Critter.") &&
    	        		!name.startsWith("CritterW"));
    	        }
    	});
    	for(File file : matchingFiles){
    		String str = file.getName();
    		int dotIndex = str.indexOf(".");
    		critters.add(str.substring(0 , dotIndex));
    	}
    }
}


