/**
 * EE422C Project 2 (Mastermind) submission by
 * Replace <...> with your actual data. 
 * Jan Zimoch
 * jsz323
 * Slip days used: <0>
 * Spring 2019
 */

package assignment2;

import java.util.ArrayList;

public class History {
	
	private int numberOfGuesses;
	private ArrayList<String> historyList;
	

	public History() {
		numberOfGuesses = GameConfiguration.guessNumber;
		historyList = new ArrayList<String>();
	}
	
	public ArrayList<String> getHistoryList() {
		return historyList;
	}

	public void addToHistoryList(String guess) {
		this.historyList.add(guess);
	}
	
	public void decrementNumGuesses() {
		numberOfGuesses--;
	}

	public int getNumberOfGuesses() {
		return numberOfGuesses;
	}

	public void setNumberOfGuesses(int numberOfGuesses) {
		this.numberOfGuesses = numberOfGuesses;
	}

}
