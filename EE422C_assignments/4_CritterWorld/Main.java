/*
 * CRITTERS Main.java
 * EE422C Project 4 submission by
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

package assignment4;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Scanner;

/*
 * Usage: java <pkg name>.Main <input file> test input file is
 * optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */

public class Main {

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
    private static String[] critters = {"Clover", "Goblin", "Critter1", "Critter2", "Critter3", "Critter4"};

    /* Critter cannot be in default pkg. */
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     *
     * @param args args can be empty.  If not empty, provide two
     *             parameters -- the first is a file name, and the
     *             second is test (for test output, where all output
     *             to be directed to a String), or nothing.
     */
    public static void main(String[] args) {
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java <pkg name>.Main OR java <pkg name>.Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java <pkg name>.Main OR java <pkg name>.Main <input file> <test output>");
            }
            if (args.length >= 2) {
                /* If the word "test" is the second argument to java */
                if (args[1].equals("test")) {
                    /* Create a stream to hold the output */
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    /* Save the old System.out. */
                    old = System.out;
                    /* Tell Java to use the special stream; all
                     * console output will be redirected here from
                     * now */
                    System.setOut(ps);
                }
            }
        } else { // If no arguments to main
            kb = new Scanner(System.in); // Use keyboard and console
        }
        
        // initialise CreaterWorld
        CritterWorld critter_world = new CritterWorld();
        
        commandInterpreter(kb);

        System.out.flush();
    }

    /* Do not alter the code above for your submission. */

    private static void commandInterpreter(Scanner kb) {
        boolean exit = false;
    	while(!exit) {
        	String input = kb.nextLine();
        	String[] arr = input.split(" ");
        	switch(arr[0]) {
        	
        	case "show":
        		if(arr.length == 1) {
        			Critter.displayWorld();
        		}
        		else{
    				System.out.println("error processing: " + input);
        		}
        		break;
        		
        	case "step":
        		// i.e. step
        		if(arr.length == 1) {
        			Critter.worldTimeStep();
        		}
        		
        		// i.e step 1000
        		else if(arr.length == 2 && isInteger(arr[1])){
	        		for(int i = 0; i < Integer.parseInt(arr[1]);i++) {
	        			Critter.worldTimeStep();
        			}
        		}
        		else{
    				System.out.println("error processing: " + input);
        		}
        		break;
        		
        	case "quit":
        		if(arr.length == 1) {
            		exit = true;
        		}
        		else {
    				System.out.println("error processing: " + input);
        		}
        		break;
        		
        	case "clear":
        		// i.e. clear
        		if(arr.length == 1) {
            		Critter.clearWorld();
        		}
        		else {
    				System.out.println("error processing: " + input);
        		}
        		break;
        		
        	case "seed":
        		// i.e. seed 200
        		if(isInteger(arr[1])){
        			if(arr.length == 2) {
        				Critter.setSeed(Integer.parseInt(arr[1]));
        			}
        			else {
        				System.out.println("error processing: " + input);
        			}
        		}
    			else {
    				System.out.println("error processing: " + input);
    			}
        		break;
        		
        	case "create":
        		// i.e. create Goblin
        		if (isCritter(arr[1])) {
        			if(arr.length == 2) {
	    				try {
							Critter.createCritter(arr[1]);
						} catch (InvalidCritterException e) {
							System.out.println("error processing: " + input);
						}
        			}
            		// i.e. create Goblins 200
        			else if(isInteger(arr[2]) && arr.length == 3) {
        				for(int i = 0; i < Integer.parseInt(arr[2]);i++) {
	        				try {
								Critter.createCritter(arr[1]);
							} catch (InvalidCritterException e) {
								System.out.println("error processing: " + input);
							}
	        			}
        			}
        			else {
        				System.out.println("error processing: " + input);
        			}
        		}
    			else {
    				System.out.println("error processing: " + input);
    			}
        		break;
        		
        	case "stats":
        		// i.e. stats Goblin
        		if (arr.length == 2 && arr[0].equals("stats") && isCritter(arr[1])) {
        			Critter critter;
        			try {
						critter = Critter.getCritterFromString(Critter.getMyPackage() + "." + arr[1]);
						Method m = critter.getClass().getMethod("runStats", List.class);
						m.invoke(critter, critter.getInstances(arr[1]));
					} catch (InvalidCritterException e) {
        				System.out.println("error processing: " + input);
					} catch (Exception e) {
	        			System.out.println("error processing: " + input);
					}
				}
        		else {
	    			System.out.println("error processing: " + input);
	    		}
        		break;
        		
        	default:
            	System.out.println("invalid command: " + input);
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
    
    /* check whether input contains a valid critter name */
    public static boolean isCritter(String input) {
        for (String critter : critters) {
        	if(input.contentEquals(critter)) {
        		return true;
        	}
        }
        return false;
    }
}


