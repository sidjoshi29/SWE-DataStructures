package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FacultyTest {
	
	/** Test Student first name */
	private static final String FIRST_NAME = "Jane";
	
	/** Test Student last name */
	private static final String LAST_NAME = "Doe";
	
	/** Test Student id */
	private static final String ID = "jdoe";
	
	/** Test Student email */
	private static final String EMAIL = "jane.doe@ncsu.edu";
	
	/** Test Student password */
	private String hashPw = "123";
	
	/** Test Student max credits less than the default */
	private static final int MAX_COURSES = 3;
	
	@Test
	void testFaculty() {
		assertDoesNotThrow(() -> new Faculty("Arnav", "Ganguly", "asgangul", "asgangul@ncsu.edu", "123", 2));
		Faculty faculty = new Faculty("Arnav", "Ganguly", "asgangul", "asgangul@ncsu.edu", "123", 2);
		assertEquals("Arnav", faculty.getFirstName());
		assertEquals("Ganguly", faculty.getLastName());
		assertEquals("asgangul@ncsu.edu", faculty.getEmail());
	}
	
	@Test
	void testSetMaxCourses() {
		assertThrows(IllegalArgumentException.class, () -> new Faculty("Arnav", "Ganguly", "asgangul", "asgangul@ncsu.edu", "123", -1));
		Faculty faculty = new Faculty("Arnav", "Ganguly", "asgangul", "asgangul@ncsu.edu", "123", 2);
		assertEquals(2, faculty.getMaxCourses());
	}
	
	@Test
	void testgetMaxCourses() {
		Faculty faculty = new Faculty("Arnav", "Ganguly", "asgangul", "asgangul@ncsu.edu", "123", 3);
		assertEquals(3, faculty.getMaxCourses());
		
		Faculty faculty2 = new Faculty("Arnav", "Ganguly", "asgangul", "asgangul@ncsu.edu", "123", 1);
		assertEquals(1, faculty2.getMaxCourses());
	}
	
	@Test 
	void testEquals() {
		Faculty s1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPw, MAX_COURSES);
		Faculty s2 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPw, MAX_COURSES);
		Faculty s3 = new Faculty("Andres", LAST_NAME, ID, EMAIL, hashPw, MAX_COURSES);
		Faculty s4 = new Faculty(FIRST_NAME, "Ayala-Lagunas", ID, EMAIL, hashPw, MAX_COURSES);
		Faculty s5 = new Faculty(FIRST_NAME, LAST_NAME, "aayalal", EMAIL, hashPw, MAX_COURSES);
		Faculty s6 = new Faculty(FIRST_NAME, LAST_NAME, ID, "aayalal@ncsu.edu", hashPw, MAX_COURSES);
		Faculty s7 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "Minecraft101", MAX_COURSES);
		Faculty s8 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "Minecraft101", 1);
		
		
		// Test for equality in both directions
		assertTrue(s1.equals(s2));
		assertTrue(s2.equals(s1));
		
		// Test for each of the fields
		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));

		
		// Test Class equality
		assertEquals(s1.getClass(), s2.getClass());
		assertEquals(s1.getClass(), s3.getClass());
	}
	
	@Test
	public void testToString() {
		Faculty s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPw, MAX_COURSES);
		
		assertEquals(FIRST_NAME + "," + LAST_NAME + "," + ID + "," + EMAIL + "," + hashPw + "," + MAX_COURSES, s.toString());
	}
	
	@Test
	void testHashCode() {
		
		Faculty s1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPw, MAX_COURSES);
		Faculty s2 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPw, MAX_COURSES);
		Faculty s3 = new Faculty("Andres", LAST_NAME, ID, EMAIL, hashPw, MAX_COURSES);
		Faculty s4 = new Faculty(FIRST_NAME, "Ayala-Lagunas", ID, EMAIL, hashPw, MAX_COURSES);
		Faculty s5 = new Faculty(FIRST_NAME, LAST_NAME, "aayalal", EMAIL, hashPw, MAX_COURSES);
		Faculty s6 = new Faculty(FIRST_NAME, LAST_NAME, ID, "aayalal@ncsu.edu", hashPw, MAX_COURSES);
		Faculty s7 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "Minecraft101", MAX_COURSES);
		Faculty s8 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "Minecraft101", 1);
		
		// Test for the same hash code for the same values
		assertEquals(s1.hashCode(), s2.hashCode());
				
		// Test for each of fields
		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
		assertNotEquals(s1.hashCode(), s8.hashCode());
	}

}
