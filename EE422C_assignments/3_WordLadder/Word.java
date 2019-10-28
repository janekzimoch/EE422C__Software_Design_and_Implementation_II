/* WORD LADDER Word.java
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

public class Word implements Comparable<Word> {
	
	private String node_name;
	private Word parent; // there should be always one parent. Once you set it. keep it
	private int color; // 0 - not visited, 1 - curently exploring, 2 - finished
	private int correct;
	public ArrayList<Word> children;
//	public ArrayList<String> path;
	
	public Word(String root) {
		node_name = root;
		parent = null;
		color = 0;
		children=new ArrayList<Word>();
	}

	
	// adds a child to an ArrayList of all children (i.e. all words that differ only by one char)
	public void addChild(Word node) {
		children.add(node);
	}
	
	
	public static void makePath(Word node, ArrayList<String> output) {
		if(node.parent == null){  // node is the initial word
			output.add(node.node_name.toLowerCase()); // terminate
		}
		else {  // node is NOT the initial word
			makePath(node.parent, output);  // keep printing parent nodes recursively until initial word
			output.add(node.node_name.toLowerCase());
		}
	}
	
	
	@Override
	public boolean equals(Object o) {
		Word compare = null;
		if(o instanceof Word) {
			compare=(Word)o;
			if(compare.node_name==node_name) {
				return true;
			}
		}
		return false;
	}
	
	
	public void printChildren() {
		System.out.println(node_name);
		System.out.println(children.size());
		System.out.println("");
		for(int x=0; x<children.size(); x++) {
			if(children.get(x)!=null) {
			System.out.println(children.get(x).node_name);
			}
		}
		System.out.println("");
	}
	
	
	public String getName() {
		return node_name;
	}
	
	
	public void setCorrect(String target) {
		correct=0;
		for(int x=0; x<SearchHashHelper.wordLength; x++) {
			if(target.charAt(x)==node_name.charAt(x)) {
				correct++;
			}
		}
	}
	
	
	public void setParent(Word parent) {
		this.parent=parent;
	}

	
	public Word getParent() {
		return parent;
	}


	@Override
	public int compareTo(Word o) {
		// TODO Auto-generated method stub
		return correct-o.correct;
	}
	
	
	public void setColor(int color) {
		this.color=color;
	}
	
	
	public int getColor() {
		return color;
	}
	
	public int different(Word parent) {  //method that returns which character is different
		for(int x=0; x<SearchHashHelper.wordLength; x++) {
			if(node_name.charAt(x)!=parent.node_name.charAt(x)) {
				return x;
			}
		}
		return SearchHashHelper.wordLength;
	}

}

