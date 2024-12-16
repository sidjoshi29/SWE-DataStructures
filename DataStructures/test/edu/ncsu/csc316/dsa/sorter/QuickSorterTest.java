package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
import edu.ncsu.csc316.dsa.sorter.QuickSorter.FirstElementSelector;
import edu.ncsu.csc316.dsa.sorter.QuickSorter.LastElementSelector;
import edu.ncsu.csc316.dsa.sorter.QuickSorter.MiddleElementSelector;

public class QuickSorterTest {

	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };

	/** Instance of bubbleSorter to sort integers */
	private QuickSorter<Integer> integerSorter;
	
	/** student 1 */
	private Student sOne;
	/** student 2 */
	private Student sTwo;
	/** student 3 */
	private Student sThree;
	/** student 4 */
	private Student sFour;
	/** student 5 */
	private Student sFive;
	
	/** student sorter */
	private QuickSorter<Student> studentSort;
	
	/** student sorter */
	private QuickSorter<Student> firstPivot;
	
	/** student sorter */
	private QuickSorter<Student> lastPivot;
	
	/** student sorter */
	private QuickSorter<Student> middlePivot;

	/**
     * Set up method that initializes the BubbleSorter instance before each test.
     */
	@Before
	public void setUp() {
		integerSorter = new QuickSorter<Integer>();
		
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		
		
		studentSort = new QuickSorter<Student>(new StudentGPAComparator());
		
		firstPivot = new QuickSorter<Student>(new StudentGPAComparator(), new FirstElementSelector());
		
		lastPivot = new QuickSorter<Student>(new StudentGPAComparator(), new LastElementSelector());
		
		middlePivot = new QuickSorter<Student>(new StudentGPAComparator(), new MiddleElementSelector());
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
	
	/**
	 * Sort students based on quicksort
	 */
	@Test
	public void testSortStudents() {
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		studentSort.sort(original);
		assertEquals(sFive, original[0]);
		assertEquals(sFour, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sTwo, original[3]);
		assertEquals(sOne, original[4]);
		
		Student[] firstPivotSortList = { sTwo, sOne, sFour, sThree, sFive };
		firstPivot.sort(firstPivotSortList);
		assertEquals(sFive, firstPivotSortList[0]);
		assertEquals(sFour, firstPivotSortList[1]);
		assertEquals(sThree, firstPivotSortList[2]);
		assertEquals(sTwo, firstPivotSortList[3]);
		assertEquals(sOne, firstPivotSortList[4]);
		
		Student[] lastPivotSortList = { sTwo, sOne, sFour, sThree, sFive };
		lastPivot.sort(lastPivotSortList);
		assertEquals(sFive, lastPivotSortList[0]);
		assertEquals(sFour, lastPivotSortList[1]);
		assertEquals(sThree, lastPivotSortList[2]);
		assertEquals(sTwo, lastPivotSortList[3]);
		assertEquals(sOne, lastPivotSortList[4]);
		
		
		Student[] middlePivotSortList = { sTwo, sOne, sFour, sThree, sFive };
		middlePivot.sort(middlePivotSortList);
		assertEquals(sFive, middlePivotSortList[0]);
		assertEquals(sFour, middlePivotSortList[1]);
		assertEquals(sThree, middlePivotSortList[2]);
		assertEquals(sTwo, middlePivotSortList[3]);
		assertEquals(sOne, middlePivotSortList[4]);
	}
	
}
