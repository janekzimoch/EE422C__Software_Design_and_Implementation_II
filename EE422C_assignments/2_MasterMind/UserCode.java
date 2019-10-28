/**
 * EE422C Project 2 (Mastermind) submission by
 * Replace <...> with your actual data. 
 * Jan Zimoch
 * jsz323
 * Slip days used: <0>
 * Spring 2019
 */

package assignment2;

import java.util.Arrays;
import java.util.Scanner;

public abstract class UserCode {
	
	public static String userInput;
	
	// requests user for input - stalls the program until input is input
	// assumption: console accepts only Strings, thus userInput has to eb a string
	public static void requestUserInput(Scanner console) {
		userInput = console.next(); 
	}
	
	
	// Check whether to play next game - i.e is input Y or N
	// Assumption: user will only provide Y/N inputs
	public static boolean playNext() {
		if(userInput.equals("Y")) {
			return true;
		}
		else if(userInput.contentEquals("N")) {
			return false;
		}
		else {
			throw new IllegalArgumentException();
		}
		
	}
	
	
	// check whether input is all uppercase
	private static boolean allAreUpperCase() {
		for(int i=0; i < userInput.length(); i++) {
			char ch = userInput.charAt(i);
			if(Character.isLowerCase(ch)) {
				return false;				
			}
		}
		return true;		
	}
	
	
	// check whether input is valid
	// (is 4 characters long and is contained in GameConfiguration.colors)
	public static boolean isGuessValid() {
		if (!allAreUpperCase()) {
			return false;
		}
		else if (userInput.length() != GameConfiguration.pegNumber) {
			return false;
		}
		else {
			for(int i = 0; i < GameConfiguration.pegNumber; i++) {
				String ch = "" + userInput.charAt(i);
				if(!(Arrays.asList(GameConfiguration.colors).contains(ch))) {
					return false;
				}
			}
			return true;
		}
	}
	
	
	// is user asking for HISTORY?
	public static boolean requestedHistory() {
		if(userInput.equals("HISTORY")) {
			return true;
		}
		return false;
	}
	
	
}
