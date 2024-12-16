package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Student functionality.
 * 
 * @author Siddhant Joshi
 */
public class StudentTest {

	private Student sOne;
	private Student sTwo;
	private Student sThree;
	private Student sSeven;	
	private Student sSix;
	private Student sFour;
	private Student sFive;

	

	/**
     * This method initializes several student objects.
     */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
	    sSix = new Student("SixFirst", "SixLast", 6, 6, 6.0, "sixUnityID");
	    sSeven = new Student("SevenFirst", "SevenLast", 7, 7, 7.0, "sevenUnityID");
	    sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
	}
	
	/** 
	 * Tests Student.setFirst() functionality.
	 */
	@Test
	public void testSetFirst() {
		sOne.setFirst("newOne");
		assertEquals("newOne", sOne.getFirst());
	}

	/**
	 * Tests Student.setLast() functionality.
	 */
	@Test
	public void testSetLast() {
		sOne.setLast("newOne");
		assertEquals("newOne", sOne.getLast());
	}

	/**
	 * Tests Student.setID() functionality.
	 */
	@Test
	public void testSetId() {
		sOne.setId(100);
		assertEquals(100, sOne.getId());
	}


	/**
	 * Tests Student.setGPAt() functionality.
	 */
	@Test
	public void testSetGpa() {
		sOne.setGpa(3.51);
		assertEquals(3.51, sOne.getGpa(), 0.001);
	}
	
	/**
	 * Tests Student.setUnityID() functionality.
	 */
	@Test
	public void testSetUnityID() {
		sOne.setUnityID("oneUnity");
		assertEquals("oneUnity", sOne.getUnityID());
		sTwo.setUnityID("twoUnity");
		assertEquals("twoUnity", sTwo.getUnityID());
	}
	
	/**
	 * Tests Student.setCreditHours() functionality.
	 */
	@Test
	public void testSetCreditHours() {
		sOne.setCreditHours(1);
		assertEquals(1, sOne.getCreditHours());
		sTwo.setCreditHours(2);
		assertEquals(2, sTwo.getCreditHours());
	}

	/**
	 * Tests Student.compareTo() functionality.
	 */
	@Test
	public void testCompareTo() {
		assertTrue(sOne.compareTo(sTwo) < 0);
		assertTrue(sTwo.compareTo(sOne) > 0);
		assertTrue(sOne.compareTo(sOne) == 0);
		assertTrue(sTwo.compareTo(sTwo) == 0);
		assertTrue(sSix.compareTo(sFive) > 0);
		assertTrue(sSeven.compareTo(sFour) > 0);
	}
	
	/**
	 * Tests Student.equals() functionality.
	 */
	@Test
	public void testEquals() {
	    Student anotherOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
	    assertTrue(sOne.equals(anotherOne)); 
	    assertFalse(sOne.equals(sTwo));
	    assertFalse(sOne == null);
	    assertFalse(sOne.equals(sSix));
	    assertTrue(sOne.equals(sOne)); 
	    assertFalse(sOne.equals(sThree));
	    assertFalse(sTwo == null);
	}

	/**
	 * Tests Student.toString() functionality.
	 */
	@Test
	public void testToString() {
	    String expected = "Student [first=OneFirst, last=OneLast, id=1, creditHours=1, gpa=1.0, unityID=oneUnityID]";
	    assertEquals(expected, sOne.toString()); 
	    
	    String expected2 = "Student [first=TwoFirst, last=TwoLast, id=2, creditHours=2, gpa=2.0, unityID=twoUnityID]";
	    assertEquals(expected2, sTwo.toString()); 
	}
	
	
	/**
	 * Tests Student.hashCode() functionality.
	 */
	@Test
    public void testHashCode() {
        assertEquals(sOne.hashCode(), sOne.hashCode());
        assertNotEquals(sOne.hashCode(), sTwo.hashCode());
    }
}
