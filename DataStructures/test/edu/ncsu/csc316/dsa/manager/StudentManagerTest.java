package edu.ncsu.csc316.dsa.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.sorter.InsertionSorter;
import edu.ncsu.csc316.dsa.sorter.Sorter;

/**
 * The StudentManagerTest class provides test cases for the StudentManager class.
 * 
 * @author Siddhant Joshi
 */
public class StudentManagerTest {

	/** Instance of the student manager sm */
	private StudentManager sm;
	
	/** Intsance for student sorter */
	private Sorter<Student> std;
	
	/**
	 * This method initializes the StudentManager for the tests.
	 */
	@Before
	public void setUp() {
		sm = new StudentManager("input/student_ascendingID.csv");
		std = new InsertionSorter<Student>(new StudentIDComparator());
	}
	
	/**
	 * Tests the sorting functionality of the StudentManager.
	 */
	@Test
	public void testSort() {
		Student[] sorted = sm.sort();
		assertEquals("Tanner", sorted[0].getFirst());
		assertEquals("Roxann", sorted[1].getFirst());
		assertEquals("Shanti", sorted[2].getFirst());
		assertEquals("Dante", sorted[3].getFirst());
		assertEquals("Cristine", sorted[4].getFirst());
		assertEquals("Ara", sorted[5].getFirst());
		assertEquals("Lewis", sorted[6].getFirst());
		assertEquals("Charlene", sorted[7].getFirst());
		assertEquals("Amber", sorted[8].getFirst());
		assertEquals("Lacie", sorted[9].getFirst());
		assertEquals("Idalia", sorted[10].getFirst());
		assertEquals("Tyree", sorted[11].getFirst());
		assertEquals("Evelin", sorted[12].getFirst());
		assertEquals("Alicia", sorted[13].getFirst());
		assertEquals("Loise", sorted[14].getFirst());
		assertEquals("Nichole", sorted[15].getFirst());
	}
	
	/**
	 * Tests the sorting of students by their IDs. Using Insertion Sort
	 */
	@Test
	public void testStudentID() {
		StudentManager randomSm = new StudentManager("input/student_randomOrder.csv", std);
		
		Student[] studentIdSort = randomSm.sort();

		assertEquals("Amber", studentIdSort[0].getFirst());
		assertEquals("Ara", studentIdSort[1].getFirst());
		assertEquals("Lacie", studentIdSort[2].getFirst());
		assertEquals("Idalia", studentIdSort[3].getFirst());
		assertEquals("Evelin", studentIdSort[4].getFirst());
		assertEquals("Lewis", studentIdSort[5].getFirst());
		assertEquals("Alicia", studentIdSort[6].getFirst());
		assertEquals("Tyree", studentIdSort[7].getFirst());
		assertEquals("Loise", studentIdSort[8].getFirst());
		assertEquals("Roxann", studentIdSort[9].getFirst());
		assertEquals("Nichole", studentIdSort[10].getFirst());
		assertEquals("Charlene", studentIdSort[11].getFirst());
		assertEquals("Shanti", studentIdSort[12].getFirst());
		assertEquals("Cristine", studentIdSort[13].getFirst());
		assertEquals("Tanner", studentIdSort[14].getFirst());
		assertEquals("Dante", studentIdSort[15].getFirst());
	}

}
