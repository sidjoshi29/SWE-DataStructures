package edu.ncsu.csc216.pack_scheduler.users;


import static org.junit.jupiter.api.Assertions.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;

/**
 * Tests the Student object.
 * @author SarahHeckman
 */
public class StudentTest {
	 
	/** Test Student's first name. */
	private String firstName = "first";
	/** Test Student's last name */
	private String lastName = "last";
	/** Test Student's id */
	private String id = "flast";
	/** Test Student's email */
	private String email = "first_last@ncsu.edu";
	/** Test Student's hashed password */
	private String hashPW;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	//This is a block of code that is executed when the StudentTest object is
	//created by JUnit.  Since we only need to generate the hashed version
	//of the plaintext password once, we want to create it as the StudentTest object is
	//constructed.  By automating the hash of the plaintext password, we are
	//not tied to a specific hash implementation.  We can change the algorithm
	//easily.
	{
		try {
			String plaintextPW = "password";
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(plaintextPW.getBytes());
			this.hashPW = Base64.getEncoder().encodeToString(digest.digest());
		} catch (NoSuchAlgorithmException e) {
			fail("An unexpected NoSuchAlgorithmException was thrown.");
		}
	}
	
	/**
	 * Test toString() method.
	 */
	@Test
	public void testToString() {
		User s1 = new Student(firstName, lastName, id, email, hashPW);
		assertEquals("first,last,flast,first_last@ncsu.edu," + hashPW + ",18", s1.toString());
	}
	
	/**
	 * test for canAdd
	 */
	@Test
	public void testCanAdd() {
	    
	    Student student = new Student(firstName, lastName, id, email, hashPW, 18);
	    
	    // Create some courses to add
	    Course course1 = new Course("CSC216", "Programming Concepts", "001", 3, "Instructor", 10, "MW", 1230, 1415);
	    //Course course2 = new Course("CSC226", "Discrete Mathematics", "001", 3, "Instructor", 10, "TH", 1230, 1415);
	    
	    assertTrue(student.canAdd(course1)); 
	    student.getSchedule().addCourseToSchedule(course1);
	    assertFalse(student.canAdd(course1)); 
	    
	    
	   
	    Course course2 = null;
	    assertFalse(student.canAdd(course2));

	    Course course3 = new Course("CSC116", "Intro to Computing - Java", "002", 3, "Instructor", 10, "MW", 1240, 1435);
	    assertFalse(student.canAdd(course3));
	}

}
