/* 
 * This file is how you might test out your code.  Don't submit this, and don't 
 * have a main method in SortTools.java.
 */

/*
 * EE422C Project 1 submission by
 * Replace <Feb 4th> with your actual data.
 * <Jan S Zimoch>
 * <jsz323>
 * <16210>
 * Fall 2018
 * Slip days used: 0
 */

package assignment1;

import java.util.Arrays;

public class Main {
	public static void main(String [] args) {


		int[] list_test = {5,4,3,2,1,};
		//boolean test1 = SortTools.isSorted(list_test, 4);
		//System.out.println(test1);
				
		
		//int index = SortTools.find(list_test, 10, 5);
		//System.out.println(index);
		
		
		//int[] p = SortTools.insertGeneral(list_test, 5, 3);
		//System.out.println(Arrays.toString(p));
		
		
		//int d = SortTools.insertInPlace(list_test, 4, 3);
		//System.out.println(d);
		//System.out.println(Arrays.toString(list_test));
		
		SortTools.insertSort(list_test, 5);
		System.out.println(Arrays.toString(list_test));
	}
}
