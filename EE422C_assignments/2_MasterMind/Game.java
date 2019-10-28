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

public class Game {
	
	private boolean isInTestMode;
	private Scanner console;
	private boolean keepPlaying;
	
	public Game(Scanner console, boolean isInTestMode) {
		this.isInTestMode = isInTestMode;
		this.console = console;
	}
	
	
	//  high level method responsible for running the game
	public void runGame() {
		
		Output.welcome();
		keepPlaying = true;
		
		while(keepPlaying) {
			
			// Ask and then read user's input on whether to play a new game.
			Output.playNewGame();
			UserCode.requestUserInput(console);
			keepPlaying = UserCode.playNext();
			if(!keepPlaying) {
				break;  // if user answered 'N' break the loop, otherwise continue
			}
			
			// a new game has been started
			History history = new History();
			Board board = new Board(SecretCodeGenerator.getInstance().getNewSecretCode());
			if(isInTestMode) {
				Output.printSecretCode(board);
			}
			while(keepPlaying) {
				
				// ask user for a guess
				Output.remainingGuesses(history);
				UserCode.requestUserInput(console);
				
				if(UserCode.requestedHistory()) {
					Output.printHistory(history);
				}
				else {
					if(UserCode.isGuessValid()) {
						Output.guess(board, history);
						history.decrementNumGuesses();
					}
					else {
						Output.invalidGuess();
					}
					if(Pegs.getBlacks() == GameConfiguration.pegNumber) { // Game is won
						Output.youWin();
						break;
					}
					else if(history.getNumberOfGuesses() == 0) {  // Game is lost
						Output.youLose(board);
						break;
					}
				}
			}
			System.out.println();
		}
	}
}


	
			
		

