/**
 * EE422C Project 2 (Mastermind) submission by
 * Replace <...> with your actual data. 
 * Jan Zimoch
 * jsz323
 * Slip days used: <0>
 * Spring 2019
 */

package assignment2;

public abstract class Output {

	//Welcome to Mastermind
	public static void welcome() {
		System.out.println("Welcome to Mastermind.");
	}
	
	
	// Do you want to play a new game?
	public static void playNewGame() {
		System.out.println("Do you want to play a new game? (Y/N):");
	}
	
	
	// print Secret Code
	public static void printSecretCode(Board board) {
		System.out.println("Secret code: " + board.getSecretCode());
	}
	
	
	// print feedback on number of guesses remaining in the game
	public static void remainingGuesses(History h) {
		System.out.println();
		System.out.println("You have " + h.getNumberOfGuesses() + " guess(es) left.");
		System.out.println("Enter guess:");
	}
	
	
	// print feedback on history
	public static void printHistory(History history) {
		for(int i=0; i < (12-history.getNumberOfGuesses()); i++) {
			System.out.println(history.getHistoryList().get(i));
		}
	}
	
	
	// print feedback on accuracy of the guess
	public static void guess(Board board, History history) {
		String guessEvaluation = UserCode.userInput + " -> " + Pegs.producePegsOutput(board);
		System.out.println(guessEvaluation);
		history.addToHistoryList(guessEvaluation);
	}
	
	// invalid guess
	public static void invalidGuess() {
		System.out.println("INVALID_GUESS");
	}
	
	// you win the game
	public static void youWin() {
		System.out.println("You win!");
	}
	
	// you lose
	public static void youLose(Board board) {
		System.out.println("You lose! The pattern was " + board.getSecretCode());
	}
}
