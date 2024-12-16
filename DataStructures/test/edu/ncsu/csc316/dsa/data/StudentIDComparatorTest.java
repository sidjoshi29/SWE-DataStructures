package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * The StudentIDComparatorTest class is responsible for testing the functionality
 * of the StudentIDComparator
 * 
 * @author Siddhant Joshi
 */
public class StudentIDComparatorTest {

	private Student sOne;
	private Student sTwo;
	private Student sThree;
	private Student sFour;
	private Student sFive;

	/** Instance of the StudentIDComparator */
	private StudentIDComparator comparator;

	/**
     * This method initializes several student objects with different ID values for testing the comparator.
     */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		comparator = new StudentIDComparator();
	}

	/**
	 * Tests the compare method of StudentIDComparator.
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sOne, sTwo) < 0);
		assertFalse(comparator.compare(sTwo, sOne) < 0);
		assertTrue(comparator.compare(sTwo, sThree) < 0);
		assertTrue(comparator.compare(sThree, sFour) < 0);
		assertTrue(comparator.compare(sTwo, sFour) < 0);
		assertFalse(comparator.compare(sFive, sFour) < 0);
		assertTrue(comparator.compare(sFour, sFive) < 0);

		
	}


}
