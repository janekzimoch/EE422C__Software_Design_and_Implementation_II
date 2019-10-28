package assignment1;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;

import org.junit.Test;


public class SampleTest {
	
	@Test(timeout = 2000)
	public void testFindFoundFull(){
		int[] x = new int[]{-2, -1, 0, 1, 2, 3};
		assertEquals(3, SortTools.find(x, 6, 1));
	}
	
	@Test(timeout = 2000)
	public void testInsertGeneralPartialEnd(){
		int[] x = new int[]{10, 20, 30, 40, 50};
		int[] expected = new int[]{10, 20, 30, 35};
		assertArrayEquals(expected, SortTools.insertGeneral(x, 3, 35));
	}
	
	//Test isSorted() method
	
	@Test
	public void testIsSorted_true(){
		int[] x = new int[]{4, 5, 6, 2, 1};
		assertTrue(SortTools.isSorted(x, 3));
	}
	
	@Test
	public void testIsSorted_false(){
		int[] x = new int[]{5, 4, 6, 2, 1};
		assertFalse(SortTools.isSorted(x, 3));
	}
	
	@Test
	public void testIsSorted_sameValues(){
		int[] x = new int[]{1, 1, 1, 1, 1};
		assertTrue(SortTools.isSorted(x, 4));
	}
	
	//Test find() method
	@Test
	public void testFind_notContained(){
		int expected = -1;
		int value = 9;
		int[] x = new int[]{4, 5, 6, 2, 1};
		assertEquals(expected, SortTools.find(x, 3, value));
	}
	
	@Test
	public void testFind_contained(){
		int expected = 1;
		int value = 9;
		int[] x = new int[]{4, 9, 6, 2, 1};
		assertEquals(expected, SortTools.find(x, 3, value));
	}
	
	@Test
	public void testFind_containedTwoSame(){
		int expected = 0;
		int value = 9;
		int[] x = new int[]{9, 9, 6, 2, 1};
		assertEquals(expected, SortTools.find(x, 3, value));
	}
	
	//Test insertGeneral() method
	@Test
	public void testInsertGeneral_atTheEnd(){
		int[] x = new int[]{1, 2, 3, 4, 5};
		int v = 10;
		int[] expected = new int[]{1, 2, 3, 10};
		assertArrayEquals(expected, SortTools.insertGeneral(x, 3, v));
	}
	
	
	@Test
	public void testInsertGeneral_inside(){
		int[] x = new int[]{1, 3, 3, 4, 5};
		int v = 2;
		int[] expected = new int[]{1, 2, 3, 3};
		assertArrayEquals(expected, SortTools.insertGeneral(x, 3, v));
	}
	
	@Test
	public void testInsertGeneral_contains(){
		int[] x = new int[]{1, 2, 3, 4, 5};
		int v = 2;
		int[] expected = new int[]{1, 2, 3};
		assertArrayEquals(expected, SortTools.insertGeneral(x, 3, v));
	}
	
	//test insertInPlace()
	@Test
	public void testInsertInPlace_contains_array(){
		int[] x = new int[]{1, 2, 3, 4, 5};
		int v = 3;
		int[] expected = new int[]{1,2,3,4,5};
		int ans = SortTools.insertInPlace(x, 4, v);
		System.out.println(Arrays.toString(x));
		assertArrayEquals(expected, x);
	}
	

	
	@Test
	public void testInsertInPlace_contains_returnValue(){
		int[] x = new int[]{1, 2, 3, 4, 5};
		int v = 3;
		int expected = 3;
		assertEquals(expected, SortTools.insertInPlace(x, 3, v));
	}
	
	@Test
	public void testInsertInPlace_notContains_returnValue(){
		int[] x = new int[]{1, 2, 3, 4, 5};
		int v = 4;
		int expected = 4;
		assertEquals(expected, SortTools.insertInPlace(x, 3, v));
	}
	
	//test insertSort()
	@Test
	public void testInsertSort_1(){
		int[] x = new int[]{4, 4, 6, 1000, -5};
		int[] expected = new int[]{-5, 4, 4, 6, 1000};
		SortTools.insertSort(x, 5);
		assertArrayEquals(expected, x);
	}

	@Test
	public void testInsertSort_2(){
		int[] x = new int[]{4, 5, 6, 2, 1};
		int[] expected = new int[]{4, 5, 6, 2, 1};
		SortTools.insertSort(x, 3);
		assertArrayEquals(expected, x);
	}
	
	@Test
	public void testInsertSort_3(){
		int[] x = new int[]{1,1,1,1,1};
		int[] expected = new int[]{1,1,1,1,1};
		SortTools.insertSort(x, 5);
		assertArrayEquals(expected, x);
	}
	
	@Test
	public void testInsertSort_4(){
		int[] x = new int[]{6, 5, 4, 2, 1};
		int[] expected = new int[]{4, 5, 6, 2, 1};
		SortTools.insertSort(x, 3);
		assertArrayEquals(expected, x);
	}
	
}
