package edu.ncsu.csc216.pack_scheduler.user;


import static org.junit.jupiter.api.Assertions.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.junit.jupiter.api.Test;
/**
 * Tests the Student class
 * 
 * @author Chloe Israel
 */
public class StudentTest {
	
	/** Test Student first name */
	private static final String FIRST_NAME = "Jane";
	
	/** Test Student last name */
	private static final String LAST_NAME = "Doe";
	
	/** Test Student id */
	private static final String ID = "jdoe";
	
	/** Test Student email */
	private static final String EMAIL = "jane.doe@ncsu.edu";
	
	/** Test Student password */
	private String hashPw;
	
	/** Test Student max credits less than the default */
	private static final int MAX_CREDITS = 12;
	
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	{
		try {
			String plaintextPW = "password";
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(plaintextPW.getBytes());
			this.hashPw = Base64.getEncoder().encodeToString(digest.digest());
		} catch (NoSuchAlgorithmException e) {
			fail("An unexpected NoSuchAlgorithmException was thrown.");
		}
	}
	
	/**
	 * Tests constructing Student with max credits not at 18
	 */
	@Test
	public void testStudentNonDefaultMaxCredits() {
		// Valid construction
		Student s = assertDoesNotThrow(
				() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPw, MAX_CREDITS),
				"Should not throw exception");
		
		// Valid input for students + for student getters
		assertEquals(FIRST_NAME, s.getFirstName(), "incorrect first name");
		assertEquals(LAST_NAME, s.getLastName(), "incorrect last name");
		assertEquals(ID, s.getId(), "incorrect id");
		assertEquals(EMAIL, s.getEmail(), "incorrect email");
		assertEquals(hashPw, s.getPassword(), "incorrect password");
		assertEquals(MAX_CREDITS, s.getMaxCredits(), "incorrect max credits");
		
		// Invalid input for first name
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student("", LAST_NAME, ID, EMAIL, hashPw, MAX_CREDITS));
		assertEquals("Invalid first name", e1.getMessage());
		
		// Invalid input for last name
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, "", ID, EMAIL, hashPw, MAX_CREDITS));
		assertEquals("Invalid last name", e2.getMessage());
		
		// Invalid input for id
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, "", EMAIL, hashPw, MAX_CREDITS));
		assertEquals("Invalid id", e3.getMessage());
		
		// Invalid input for email
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, ID, "janedoencsu.edu", hashPw, MAX_CREDITS));
		assertEquals("Invalid email", e4.getMessage());
		
		// Invalid input for password
		Exception e5 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "", MAX_CREDITS));
		assertEquals("Invalid password", e5.getMessage());
		
		// Invalid input for max credits
		Exception e6 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPw, 1));
		assertEquals("Invalid max credits", e6.getMessage());
			
	}
	
	/**
	 * Tests constructing Student with max credits set to 18
	 */
	@Test
	public void testStudentDefaultMaxCredits() {
		// Valid construction
				Student s2 = assertDoesNotThrow(
						() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPw),
						"Should not throw exception");
				
				// Valid input for students + for student getters
				assertEquals(FIRST_NAME, s2.getFirstName(), "incorrect first name");
				assertEquals(LAST_NAME, s2.getLastName(), "incorrect last name");
				assertEquals(ID, s2.getId(), "incorrect id");
				assertEquals(EMAIL, s2.getEmail(), "incorrect email");
				assertEquals(hashPw, s2.getPassword(), "incorrect password");
				assertEquals(18, s2.getMaxCredits(), "incorrect max credits");
				
				// Invalid input for first name
				Exception e1 = assertThrows(IllegalArgumentException.class,
						() -> new Student("", LAST_NAME, ID, EMAIL, hashPw));
				assertEquals("Invalid first name", e1.getMessage());
				
				// Invalid input for last name
				Exception e2 = assertThrows(IllegalArgumentException.class,
						() -> new Student(FIRST_NAME, "", ID, EMAIL, hashPw));
				assertEquals("Invalid last name", e2.getMessage());
				
				// Invalid input for id
				Exception e3 = assertThrows(IllegalArgumentException.class,
						() -> new Student(FIRST_NAME, LAST_NAME, "", EMAIL, hashPw));
				assertEquals("Invalid id", e3.getMessage());
				
				// Invalid input for email
				Exception e4 = assertThrows(IllegalArgumentException.class,
						() -> new Student(FIRST_NAME, LAST_NAME, ID, "janedoencsu.edu", hashPw));
				assertEquals("Invalid email", e4.getMessage());
				
				// Invalid input for password
				Exception e5 = assertThrows(IllegalArgumentException.class,
						() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, ""));
				assertEquals("Invalid password", e5.getMessage());
				
	}
	/**
	 * Test setFirstName with a valid input
	 */
	@Test
	public void testValidSetFirstName() {
		User s = assertDoesNotThrow(
				() -> new Student("John", LAST_NAME, ID, EMAIL, hashPw, MAX_CREDITS),
				"Should not throw exception");
		assertEquals("John", s.getFirstName(), "Failed with valid first name");
	}
	
	/**
	 * Test setFirstName with an invalid input
	 */
	@Test
	public void testInvalidSetFirstName() {
		User s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPw, MAX_CREDITS);
		
		// Test with null value
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> s.setFirstName(null));
		assertEquals("Invalid first name", e1.getMessage());
		assertEquals(FIRST_NAME, s.getFirstName());
		
		// Test with an empty string
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> s.setFirstName(""));
		assertEquals("Invalid first name", e2.getMessage());
		assertEquals(FIRST_NAME, s.getFirstName());
	}
	
	/**
	 * Test setLastName with valid input
	 */
	@Test
	public void testValidSetLastName() {
		User s = assertDoesNotThrow(
				() -> new Student(FIRST_NAME, "Deere", ID, EMAIL, hashPw, MAX_CREDITS),
				"Should not throw exception");
		assertEquals("Deere", s.getLastName(), "Failed with valid last name");
	}
	
	/**
	 * Test setLastName with invalid input
	 */
	@Test
	public void testInvalidSetLastName() {
		User s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPw, MAX_CREDITS);
		
		// Test with null value
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> s.setLastName(null));
		assertEquals("Invalid last name", e1.getMessage());
		assertEquals(LAST_NAME, s.getLastName());
		
		// Test with an empty string 
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> s.setLastName(""));
		assertEquals("Invalid last name", e2.getMessage());
		assertEquals(LAST_NAME, s.getLastName());
	}
	
	/**
	 * Test setId with valid input --> done through the constructor
	 */
	@Test
	public void testValidSetId() {
		User s = assertDoesNotThrow(
				() -> new Student(FIRST_NAME, LAST_NAME, "jndoe", EMAIL, hashPw, MAX_CREDITS),
				"Should not throw exception");
		assertEquals("jndoe", s.getId(), "Failed with valid id");
	}
	
	/**
	 * Test setId with invalid input --> done through the constructor
	 */
	@Test
	public void testInvalidSetId() {
		// Test with null id
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, null, EMAIL, hashPw, MAX_CREDITS),
				"Should throw exception");
		assertEquals("Invalid id", e1.getMessage());
		
		// Test with empty string
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, "", EMAIL, hashPw, MAX_CREDITS),
				"Should throw exception");
		assertEquals("Invalid id", e2.getMessage());
	}
	
	/**
	 * Test setEmail with valid inputs
	 */
	@Test
	public void testValidGetEmail() {
		User s = assertDoesNotThrow(
				() -> new Student(FIRST_NAME, LAST_NAME, ID, "jane_doe@gmail.com", hashPw, MAX_CREDITS),
				"Should not throw exception");
		assertEquals("jane_doe@gmail.com", s.getEmail(), "Failed with valid email");
		
		User s2 = assertDoesNotThrow(
				() -> new Student(FIRST_NAME, LAST_NAME, ID, "jane.doe.26@yahoo.com", hashPw, MAX_CREDITS),
				"Should not throw exception");
		assertEquals("jane.doe.26@yahoo.com", s2.getEmail(), "Failed with valid email");
	}
	
	/**
	 * Test setEmail with invalid inputs
	 */
	@Test
	public void testInvalidSetEmail() {
		User s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPw, MAX_CREDITS);
		
		// Test with null value
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> s.setEmail(null));
		assertEquals("Invalid email", e1.getMessage());
		assertEquals(EMAIL, s.getEmail());
		
		// Test with empty string
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> s.setEmail(""));
		assertEquals("Invalid email", e2.getMessage());
		assertEquals(EMAIL, s.getEmail());
		
		// Test with no "@" character
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> s.setEmail("jdoe22ncsu.edu"));
		assertEquals("Invalid email", e3.getMessage());
		assertEquals(EMAIL, s.getEmail());
		
		// Test with no "." character
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> s.setEmail("jndoe@gmailcom"));
		assertEquals("Invalid email", e4.getMessage());
		assertEquals(EMAIL, s.getEmail());
		
		// Test if "." is before the "@"
		Exception e5 = assertThrows(IllegalArgumentException.class,
				() -> s.setEmail("j.doe.3@yahoocom"));
		assertEquals("Invalid email", e5.getMessage());
		assertEquals(EMAIL, s.getEmail());
	}
	
	/**
	 * Set setPassword with valid inputs
	 */
	@Test
	public void testValidSetPassword() {
		User s = assertDoesNotThrow(
				() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "password", MAX_CREDITS),
				"Should not throw exception");
		assertEquals("password", s.getPassword(), "Failed with valid password");
		
		User s2 = assertDoesNotThrow(
				() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "12345", MAX_CREDITS),
				"Should not throw exception");
		assertEquals("12345", s2.getPassword(), "Failed with valid password");
		
		User s3 = assertDoesNotThrow(
				() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "Pass.word32", MAX_CREDITS),
				"Should not throw exception");
		assertEquals("Pass.word32", s3.getPassword(), "Failed with valid password");
	}

	/**
	 * Test setPassword with invalid inputs
	 */
	@Test
	public void testInvalidSetPassword() {
		User s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPw, MAX_CREDITS);
		
		// Test with null value
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> s.setPassword(null));
		assertEquals("Invalid password", e1.getMessage());
		assertEquals(hashPw, s.getPassword());
		
		// Test with empty string
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> s.setPassword(""));
		assertEquals("Invalid password", e2.getMessage());
		assertEquals(hashPw, s.getPassword());
	}
	
	/**
	 * Test setMaxCredits with valid inputs
	 */
	@Test
	public void testValidSetMaxCredits() {
		// Valid value --> lower boundary
		Student s = assertDoesNotThrow(
				() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPw, 3),
				"Should not throw exception");
		assertEquals(3, s.getMaxCredits(), "Failed with valid max credits");
		
		// Valid value --> higher boundary
		Student s2 = assertDoesNotThrow(
				() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPw, 18),
				"Should not throw exception");
		assertEquals(18, s2.getMaxCredits(), "Failed with valid max credits");
		
		// Valid value 
		Student s3 = assertDoesNotThrow(
				() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPw, 12),
				"Should not throw exception");
		assertEquals(12, s3.getMaxCredits(), "Failed with valid max credits");
	}
	
	/**
	 * Test setMaxCredits with invalid inputs
	 */
	@Test
	public void testInvalidSetMaxCredits() {
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPw, MAX_CREDITS);
		
		// Test with values less than 3
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> s.setMaxCredits(1));
		assertEquals("Invalid max credits", e1.getMessage());
		assertEquals(MAX_CREDITS, s.getMaxCredits());
		
		// Test with value greater than 18
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> s.setMaxCredits(25));
		assertEquals("Invalid max credits", e2.getMessage());
		assertEquals(MAX_CREDITS, s.getMaxCredits());
		
		// Test with value at zero
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> s.setMaxCredits(0));
		assertEquals("Invalid max credits", e3.getMessage());
		assertEquals(MAX_CREDITS, s.getMaxCredits());
		
		// Test with negative value
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> s.setMaxCredits(-12));
		assertEquals("Invalid max credits", e4.getMessage());
		assertEquals(MAX_CREDITS, s.getMaxCredits());
	}
	
	/**
	 * Test compareTo in Student
	 */
	@Test
	public void testCompareTo() {
		Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPw, MAX_CREDITS);
		Student s2 = null;
		Student s3 = new Student("Ann", LAST_NAME, ID, EMAIL, hashPw, MAX_CREDITS);
		Student s4 = new Student(FIRST_NAME, "Brown", ID, EMAIL, hashPw, MAX_CREDITS);
		Student s5 = new Student(FIRST_NAME, LAST_NAME, "adoe", EMAIL, hashPw, MAX_CREDITS);
		Student s6 = new Student("Pam", LAST_NAME, ID, EMAIL, hashPw, MAX_CREDITS);
		Student s7 = new Student(FIRST_NAME, "Kampbell", ID, EMAIL, hashPw, MAX_CREDITS);
		Student s8 = new Student(FIRST_NAME, LAST_NAME, "ydoe", EMAIL, hashPw, MAX_CREDITS);
		Student s9 = new Student(FIRST_NAME, LAST_NAME, ID, "janedoe2@gmail.com", hashPw, MAX_CREDITS);
		Student s10 = new Student(FIRST_NAME, "Dean", ID, EMAIL, hashPw, MAX_CREDITS);
		Student s11 = new Student("Jon", LAST_NAME, ID, EMAIL, hashPw, MAX_CREDITS);
		
		// Test comparing to a null Student object
		try {
			s1.compareTo(s2);
			fail();
		} catch (NullPointerException e) {
			assertEquals(null, s2);
		}
		
		// Tests students less than this Student (s1)
		
		// Less than by first name
		assertEquals(-1, s3.compareTo(s1));
		// Test if transitive
		assertEquals(1, s1.compareTo(s3)); 
		
		// Less than by last name
		assertEquals(-1, s4.compareTo(s1));
		assertEquals(1, s1.compareTo(s4));
		
		// Less than by student id
		assertEquals(-1, s5.compareTo(s1));
		assertEquals(1, s1.compareTo(s5));
		
		// Less than by a second letter
		assertEquals(-1, s10.compareTo(s1));
		assertEquals(1, s1.compareTo(s10));
		
		// Tests students greater than this Student (s1)
		
		// Greater than by first name
		assertEquals(1, s6.compareTo(s1));
		// Test if transitive
		assertEquals(-1, s1.compareTo(s6));
		
		// Greater than by last name
		assertEquals(1, s7.compareTo(s1));
		assertEquals(-1, s1.compareTo(s7));
		
		// Greater than by student id
		assertEquals(1, s8.compareTo(s1));
		assertEquals(-1, s1.compareTo(s8));
		
		// Greater than by a second letter
		assertEquals(1, s11.compareTo(s1));
		assertEquals(-1, s1.compareTo(s11));
		
		
		// Equal (same first name, last name, and id only to be equal)
		assertEquals(0, s9.compareTo(s1));
		assertEquals(0, s1.compareTo(s9));
		
		
	}
	
	/**
	 * Test that the equals method works for all Student fields
	 */
	@Test
	public void testEqualsObject() {
		// Objects with valid inputs
		User s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPw, MAX_CREDITS);
		User s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPw, MAX_CREDITS);
		User s3 = new Student("Andres", LAST_NAME, ID, EMAIL, hashPw, MAX_CREDITS);
		User s4 = new Student(FIRST_NAME, "Ayala-Lagunas", ID, EMAIL, hashPw, MAX_CREDITS);
		User s5 = new Student(FIRST_NAME, LAST_NAME, "aayalal", EMAIL, hashPw, MAX_CREDITS);
		User s6 = new Student(FIRST_NAME, LAST_NAME, ID, "aayalal@ncsu.edu", hashPw, MAX_CREDITS);
		User s7 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "Minecraft101", MAX_CREDITS);
		User s8 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPw, 15);
		User s9 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPw);
		User s10 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPw);
		
		// Test for equality in both directions
		assertEquals(s1, s2);
		assertEquals(s2, s1);
		assertEquals(s9, s10);
		assertEquals(s10, s9);
		
		// Test for each of the fields
		assertNotEquals(s1, s3);
		assertNotEquals(s1, s4);
		assertNotEquals(s1, s5);
		assertNotEquals(s1, s6);
		assertNotEquals(s1, s7);
		assertNotEquals(s1, s8);
		assertNotEquals(s1, s9);
		assertNotEquals(s1, s10);
		
		// Test Class equality
		assertEquals(s1.getClass(), s2.getClass());
		assertEquals(s1.getClass(), s3.getClass());
		assertEquals(s1.getClass(), s9.getClass());

	}
	
	/**
	 * Test toString
	 */
	@Test
	public void testToString() {
		User s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPw, MAX_CREDITS);
		
		assertEquals(FIRST_NAME + "," + LAST_NAME + "," + ID + "," + EMAIL + "," + hashPw + "," + MAX_CREDITS, s.toString());
	}
	
	/**
	 * Tests that hashCode works correctly
	 */
	@Test
	public void testHashCode() {
		User s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPw, MAX_CREDITS);
		User s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPw, MAX_CREDITS);
		User s3 = new Student("Andres", LAST_NAME, ID, EMAIL, hashPw, MAX_CREDITS);
		User s4 = new Student(FIRST_NAME, "Ayala-Lagunas", ID, EMAIL, hashPw, MAX_CREDITS);
		User s5 = new Student(FIRST_NAME, LAST_NAME, "aayalal", EMAIL, hashPw, MAX_CREDITS);
		User s6 = new Student(FIRST_NAME, LAST_NAME, ID, "aayalal@ncsu.edu", hashPw, MAX_CREDITS);
		User s7 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "Minecraft101", MAX_CREDITS);
		User s8 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPw, 15);
		
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
