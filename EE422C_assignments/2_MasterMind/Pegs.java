/**
 * EE422C Project 2 (Mastermind) submission by
 * Replace <...> with your actual data. 
 * Jan Zimoch
 * jsz323
 * Slip days used: <0>
 * Spring 2019
 */

package assignment2;

public abstract class Pegs {
	
	private static int whites;
	private static int blacks;
	
	
	// check how many colors from user guess are contained in a secret code
	private static void calculateWhites(Board board) {
		whites = 0;
		boolean[] arr = new boolean[4];
		String codeUser = UserCode.userInput;
		String codeGame = board.getSecretCode();
		for (int i = 0; i < codeUser.length(); i++){
			String s = "" + codeUser.charAt(i);
			for(int x = 0; x < codeGame.length(); x++) {
				String c = "" + codeGame.charAt(x);
				if(c.equals(s) && !arr[x]) {
				    whites++;
				    arr[x] = true;
				    break;
				}
			}
		}
	}
	
	
	// check how many colors from user guess 
	// are contained in a secret code & are in the right location
	public static void calculateBlacks(Board board) {
		blacks = 0;
		String codeUser = UserCode.userInput;
		String codeGame = board.getSecretCode();
		for (int i = 0; i < codeUser.length(); i++){
			if(codeUser.charAt(i) == codeGame.charAt(i)){
				blacks++;
				whites--;
			}
		}
	}
	
		
	public static String producePegsOutput(Board board) {
		calculateWhites(board);
		calculateBlacks(board);
		String s = blacks + "b_" + whites + "w";
		return s;
	}
	
	
	public static int getBlacks() {
		return blacks;
	}
	
}

