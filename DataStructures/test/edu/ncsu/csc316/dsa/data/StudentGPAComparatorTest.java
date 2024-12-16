package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * The StudentGPAComparatorTest class is responsible for testing the functionality
 * of the StudentGPAComparator
 * 
 * @author Siddhant Joshi
 */
public class StudentGPAComparatorTest {

	private Student sOne;
	private Student sTwo;
	private Student sThree;
	private Student sFour;
	private Student sFive;

	/** Instance of the Student GPA comparator */
	private StudentGPAComparator comparator;

	/**
     * This method initializes several student objects with different GPA values for testing the comparator.
     */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		comparator = new StudentGPAComparator();
	}

	/**
	 * Tests the compare method of StudentGPAComparator.
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sTwo, sOne) < 0);
		assertFalse(comparator.compare(sOne, sTwo) < 0);
		assertTrue(comparator.compare(sThree, sTwo) < 0);
		assertTrue(comparator.compare(sFour, sTwo) < 0);
		assertTrue(comparator.compare(sFive, sThree) < 0);
		
		assertTrue(comparator.compare(sThree, sOne) < 0);
		assertTrue(comparator.compare(sThree, sThree) == 0);
		

	}

}
