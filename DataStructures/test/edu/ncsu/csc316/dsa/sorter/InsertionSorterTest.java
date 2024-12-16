package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;

/**
 * This class tests for the implementation of the Insertion Sort Algorithm.
 * @author Siddhant Joshi
 */
public class InsertionSorterTest {

	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };
	
	private Student sOne;
	private Student sTwo;
	private Student sThree;
	private Student sFour;
	private Student sFive;

	/** Instance of InsertionSorter */
	private InsertionSorter<Integer> integerSorter;

	/**
	 * Set up method that initializes the InsertionSorter instance before each test.
	 */
	@Before
	public void setUp() {
		integerSorter = new InsertionSorter<Integer>();
		
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
	}

	/**
	 * Sorts integers using insertion sort
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
	 * sorts students with insertion sort based on GPA
	 * 
	 */
	@Test
	public void testSortStudent() {

	    Student[] students = { sThree, sOne, sFive, sTwo, sFour };

	    // Sort the students
	    Sorter<Student> studentSorter = new InsertionSorter<Student>(new StudentGPAComparator());
	    studentSorter.sort(students);
	    
	    assertEquals(sFive, students[0]);
	    assertEquals(sFour, students[1]);
	    assertEquals(sThree, students[2]);
	    assertEquals(sTwo, students[3]);
	    assertEquals(sOne, students[4]);
	    
	    
	}
}
