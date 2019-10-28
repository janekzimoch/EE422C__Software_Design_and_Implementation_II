// SortTools.java 

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

public class SortTools {
	/**
	  * This method tests to see if the given array is sorted.
	  * @param x is the array
	  * @param n is the size of the input to be checked
	  * @return true if array is sorted
	  */
	public static boolean isSorted(int[] x, int n) {

		if(n == 0 || x.length == 0) {
			return false;
		}
		for(int i = 1; i < n; i++) {
			if(x[i] < x[i-1]){
				return false;
			}
		}
		return true;
	}

	/**
	 * This method returns the index of value v within array x.
	 * @param x is the array
	 * @param n is the size of the input to be checked
	 * @param v is the value to be searched for within x
	 * @return index of v if v is contained within x. -1 if v is not contained within x
	 */
	public static int find(int[] x, int n, int v) {
		
		for(int i=0; i < n; i++) {
			if( x[i] == v) {
				return i;
			}			
		}
        return -1;
    }

	/**
	 * This method returns a newly created array containing the first n elements of x, and v.
	 * @param x is the array
	 * @param n is the number of elements to be copied from x
	 * @param v is the value to be added to the new array
	 * @return a new array containing the first n elements of x, and v
	 */
	public static int[] insertGeneral(int[] x, int n, int v){

		int[] y;
		int index_v = find(x,n,v);
		boolean v_was_added = false;
		if(index_v == -1 || index_v > n) {
			y = new int[n+1];
			for(int i = 0; i < n; i++) {
				if(x[i]<v) {
					y[i]=x[i];
					if(i==n-1) {
						y[i]=x[i];
						y[i+1]=v;
					}
				}
				else {
					if(v_was_added) {
						y[i+1]=x[i];
					}
					else {
						y[i]=v;
						y[i+1]=x[i];
						v_was_added = true;
					}
				}
			}			
		}
		else{
			y = new int[n];
			for(int i = 0; i < n; i++) {
				y[i]=x[i];
			}
		}
		return y;
	}

	/**
	 * This method inserts a value into the array and ensures it's still sorted
	 * @param x is the array
	 * @param n is the number of elements in the array
	 * @param v is the value to be added
	 * @return n if v is already in x, otherwise returns n+1
	 */
	public static int insertInPlace(int[] x, int n, int v){
		
		int index_v = find(x,n,v);
		boolean contains_v = false;
		if(index_v == -1) {  // v is not contained in x -> add at some place
			
			boolean first_time = true;
			int[] copy = Arrays.copyOf(x, n);
						
			for(int i = 0; i < n; i++) {
				if(copy[i]>v && first_time) {
					x[i] = v;
					x[i+1] = copy[i];
					first_time = false;
				}
				else if(copy[i]>v && !first_time) {
					x[i+1]=copy[i];
				}
				else if(v > x[n]){  // v > x[n]
					x[n] = v;
					return n+1;
				}
			}
			return n+1;
		}
		else if(index_v > n){  // v is bigger than x[n] -> add at the end
			x[n] = v;
			return n+1; 
		
		}
		else {  // v is in first n elements
			return n;
		}
    }

	/**
	 * This method sorts a given array using insertion sort
	 * @param x is the array to be sorted
	 * @param n is the number of elements of the array to be sorted
	 */
	public static void insertSort(int[] x, int n){
		
		int[] copy = Arrays.copyOf(x, n);
		
		for(int i=1; i < n; i++) {
			boolean first_time = true;
			copy = Arrays.copyOf(x, n);
			if(x[i]<x[i-1]) { // if x[i]>x[i-1] -> skip the loop below
				for(int k=0; k < n; k++) {

					if(copy[k]>x[i] && first_time) {
						x[k] = x[i];
						x[k+1] = copy[k];
						first_time = false;
					}
					else if(copy[k]>x[i] && !first_time) {
						x[k+1]=copy[k];
					}

				}
			}
		}
    }
}
