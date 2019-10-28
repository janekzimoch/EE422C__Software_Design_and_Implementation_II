/* WORD LADDER Main.java
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
import java.util.*;
import java.io.*;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
	
	// static variables and constants only here.
	private static boolean quit = false;
	private static SearchHashHelper helper;

	
	
	public static void main(String[] args) throws Exception {
		
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file, for student testing and grading only
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default input from Stdin
			ps = System.out;			// default output to Stdout
		}
		initialize();
		
		ArrayList<String> answer = parse(kb);
		if(!quit) {
			printLadder(answer);
		}
	}
	
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
		Set<String> dict = makeDictionary();
		SearchHashHelper.createHashMap(dict);
		SearchHashHelper.makeGraph(dict);
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		// read inputs from console
		String init_word = keyboard.next().toLowerCase();
		
		if(init_word.equals("/quit")) {
			quit = true;
		}
		
		/*
		if(fin_word.equals("/quit")) {
			quit = true;
		}
		*/
		
		if(!quit) {
			String fin_word = keyboard.next().toLowerCase();
			ArrayList<String> answer = getWordLadderDFS(init_word, fin_word);
			//ArrayList<String> answer = getWordLadderBFS(init_word, fin_word);
			return answer;
		}
		System.out.println("finished execution");
		return null;
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		start = start.toUpperCase();
		end = end.toUpperCase();
		ArrayList<String> output = new ArrayList<String>();
		
		boolean found=dfs(SearchHashHelper.tokens.get(start),end,output, SearchHashHelper.wordLength);
		
		Collections.reverse(output);
		if(!found) {
			output.add(start.toLowerCase());
			output.add(end.toLowerCase());
		}
		
		return output;
	}

	
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		ArrayList<String> output=new ArrayList<String>();
		LinkedList<Word> queue=new LinkedList<Word>();
		
		start = start.toUpperCase();
		end = end.toUpperCase();
		
		SearchHashHelper.tokens.get(start).setColor(1);
		queue.add(SearchHashHelper.tokens.get(start));
		
		Word destination = null;
		boolean found=false;
		while(!queue.isEmpty()&&!found) {
			Word current=queue.poll();  //gets next on queue
			if(current.getName().equals(end)) {
				found=true;
				destination=current;
			}
			else {
				
				for(int x=0; x<current.children.size(); x++) {
					Word child = current.children.get(x);
					child.setCorrect(end);
				}
				
				Collections.sort(current.children);  //sorts children by most correct letters
				for(int x=current.children.size()-1; x>=0; x--) {
					Word child = current.children.get(x);
					if(child.getColor()==0) {   //puts children in queue
						child.setColor(1);
						child.setParent(current);
						queue.add(child);
					}
				}
			}
		}
		if(destination!=null) {     //makes path
			Word.makePath(destination, output);
		}
		else {
			output.add(start.toLowerCase());
			output.add(end.toLowerCase());
		}
		
		return output; // replace this line later with real return
	}
    
	
	public static void printLadder(ArrayList<String> ladder) {
		
		int helperSize = (ladder.size() - 2);  // ladder size excluding FIRST and LAST words
		if(helperSize > 0) {  // ladder exists if NOT null
			System.out.println(String.format("a %d-rung word ladder exists between %s and %s.", helperSize, ladder.get(0), ladder.get(ladder.size()-1)));
			for(String str: ladder) {
				System.out.println(str);
			}
		}
		else {  // ladder is of size 2 because word ladder does not exist
			System.out.println(String.format("no word ladder can be found between %s and %s", ladder.get(0), ladder.get(1))+".");
		}
	}
	
	// Other private static methods here


	/* Do not modify makeDictionary */
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
	
	private static boolean dfs(Word start, String end, ArrayList<String> output, int different) {
		
		
		start.setColor(1);		//set to visited
		if(start.getName().equals(end)) {
			output.add(start.getName().toLowerCase());
			return true;
		}
		else {
			for(int x=0; x<start.children.size(); x++) {
				Word child = start.children.get(x);
				child.setCorrect(end);
			}
			
			Collections.sort(start.children);  //sort by most correct
			boolean found=false;
			for(int x=start.children.size()-1; x>=0 && !found; x--) {
				Word child = start.children.get(x);
				if(child.getColor()==0 && (start.children.get(x).different(start)!=different)) {  //child may not be visited or change same letter repeatedly
				
					found = dfs(child, end,output, child.different(start));
					if(found) {
						output.add(start.getName().toLowerCase());
						return found;
					}
				}
			}
			
		}
		
		return false;      //bad branch
		
	}
	
	private static void printChildren(Set<String> dict) {      //testing purposes
		for(String word: dict){
			Word current = SearchHashHelper.tokens.get(word);
			current.printChildren();
		}
	}
}
