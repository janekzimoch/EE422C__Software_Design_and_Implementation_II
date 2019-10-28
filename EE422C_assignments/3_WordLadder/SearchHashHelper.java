/* WORD LADDER SearchHashHelper.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * Yash Lad
 * yrl88
 * 16190
 * Jan Zimoch
 * jsz323
 * 16190
 * Slip days used: <0>
 * Git URL: https://github.com/EE422C/project-3-wordladder-pair-42
 * Spring 2019
 */






package assignment3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class SearchHashHelper {

	
	public static char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	public static int wordLength;
	public static HashMap<String, Word> tokens;

	
	// function responsible for creating HashMap
	// each bucket will contain words that differ only by one character in a specific psoition
	// for example bucket "*AR"
	// will contain both words "BAR" and "CAR" and all other similar contained in 'dict'
	// then when creating a graph
	// node "CAR" would have as children all the nodes that fall into buckets "*AR", "C*R", "CA*"
	// 
	// Interesting improvement #1: Given that a final word is "TAN" which has shares the same
	// 2nd letter as "CAR", the letter "A" we can block words in bucket "C*R" from becoming children of "CAR"
	// this will ensure that we don't wonder around and once the letter at some position is correct it won't be changed
	// Potential Limitations: we close some of the routs. by blocking out elements in bucket "C*R" we eleimnate
	// 1/3 of potential routs to the final word. So we might end up not finding the Word Ladder 
	// even though one existed thrugh the rout we decided not to consider
	//
	// Interesting improvement #2: We could prioritise those words whose blank letter would make the
	// new node resemble final word by one more letter
	// i.e. if the initial word is "CAT" and final word is "BAR"
	// we should prioritise going to "CAR" rather than going to "SAT"
	// Implementation: i haven't thought it out yet
	public static void createHashMap(Set<String> dict) {
		
		// accessing string length
		Iterator<String> iter_temp = dict.iterator();
		String word_temp = iter_temp.next();
		wordLength = word_temp.length();
		
		tokens = new HashMap<>();
		
		// iterate over set and create hashMap
		for(String word_str:dict)
		{
			Word temp=new Word(word_str);
			tokens.put(word_str,temp);
		}
	}
	public static void makeGraph(Set<String> dict) {
		// this code would access a bucket i.e. "*EHAB" and
		// print/return all the element contained in this bucket - all words that share the same last letters EHAB
		for(String word_temp:dict) {
			for(int x=0; x<wordLength; x++) {
				for(int y=0; y<alphabet.length; y++) {
					char[] charArray = word_temp.toCharArray();  //changes word
					charArray[x]=alphabet[y];
					String compare = new String(charArray);
					if(!word_temp.equals(compare)) {
						if(tokens.containsKey(compare)) {
							Word w=tokens.get(word_temp);
							tokens.get(word_temp).addChild(tokens.get(compare));  //add child
						}
					}
				}
			}
		}
	}
}
