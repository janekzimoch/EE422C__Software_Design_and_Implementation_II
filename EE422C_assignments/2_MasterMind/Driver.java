/**
 * EE422C Project 2 (Mastermind) submission by
 * Replace <...> with your actual data. 
 * Jan Zimoch
 * jsz323
 * Slip days used: <0>
 * Spring 2019
 */

package assignment2;

import java.util.*;

public class Driver {

	public static void main(String[] args) {
		boolean isInTestMode = false;
		if (args.length > 0) {
			isInTestMode = args[0].equals("1");
		}
		
		Scanner console = new Scanner(System.in);
		Game newGame = new Game(console, isInTestMode);

		newGame.runGame();
	}
}
