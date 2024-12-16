package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests for the implementation of the Bubble Sort Algorithm.
 * @author Siddhant Joshi
 */
public class BubbleSorterTest {

	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };

	/** Instance of bubbleSorter to sort integers */
	private BubbleSorter<Integer> integerSorter;

	/**
     * Set up method that initializes the BubbleSorter instance before each test.
     */
	@Before
	public void setUp() {
		integerSorter = new BubbleSorter<Integer>();
	}

	
	/**
	 * Test method for sorting integers.
     * It tests the sorting on already sorted (ascending order), 
     * Descending order and random order
	 */
	@Test
	public void testSortIntegers() {
		
		 integerSorter.sort(dataAscending);
	     assertEquals(Integer.valueOf(1), dataAscending[0]);
	     assertEquals(Integer.valueOf(2), dataAscending[1]);
         assertEquals(Integer.valueOf(3), dataAscending[2]);
         assertEquals(Integer.valueOf(4), dataAscending[3]);
	     assertEquals(Integer.valueOf(5), dataAscending[4]);
	     
	     integerSorter.sort(dataDescending);
	     assertEquals(Integer.valueOf(1), dataDescending[0]);
	     assertEquals(Integer.valueOf(2), dataDescending[1]);
	     assertEquals(Integer.valueOf(3), dataDescending[2]);
	     assertEquals(Integer.valueOf(4), dataDescending[3]);
	     assertEquals(Integer.valueOf(5), dataDescending[4]);

	     integerSorter.sort(dataRandom);
	     assertEquals(Integer.valueOf(1), dataRandom[0]);
	     assertEquals(Integer.valueOf(2), dataRandom[1]);
	     assertEquals(Integer.valueOf(3), dataRandom[2]);
	     assertEquals(Integer.valueOf(4), dataRandom[3]);
	     assertEquals(Integer.valueOf(5), dataRandom[4]);
	}

}
