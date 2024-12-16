package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;

class CourseRollTest {
	
	/** Test Student first name */
	private static final String FIRST_NAME = "Jane";
	
	/** Test Student last name */
	private static final String LAST_NAME = "Doe";
	
	/** Test Student id */
	private static final String ID = "jdoe";
	
	/** Test Student email */
	private static final String EMAIL = "jane.doe@ncsu.edu";
	
	/** Test Student password */
	private String hashPw = "password";
	
	/** Test Student max credits less than the default */
	private static final int MAX_CREDITS = 12;
	
	
	@Test
	void testCourseRoll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "A");
		CourseRoll courseRoll = c.getCourseRoll();
		assertEquals(15, courseRoll.getEnrollmentCap());
		assertEquals(0, courseRoll.getNumberOnWaitlist());
	}

	@Test
	void testSetEnrollmentCap() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "A");
		CourseRoll courseRoll = c.getCourseRoll();
		assertEquals(15, courseRoll.getEnrollmentCap());
		
		courseRoll.setEnrollmentCap(20);
		assertEquals(20, courseRoll.getEnrollmentCap());
		
		//checking exceptions
		assertThrows(IllegalArgumentException.class, () -> courseRoll.setEnrollmentCap(5));
		assertThrows(IllegalArgumentException.class, () -> courseRoll.setEnrollmentCap(300));
	}

	@Test
	void testGetEnrollmentCap() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "A");
		CourseRoll courseRoll = c.getCourseRoll();
		assertEquals(15, courseRoll.getEnrollmentCap());
	}

	@Test
	void testGetOpenSeats() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "A");
		CourseRoll courseRoll = c.getCourseRoll();
		assertEquals(15, courseRoll.getEnrollmentCap());
		assertEquals(15, courseRoll.getOpenSeats());
		
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPw, MAX_CREDITS);
		courseRoll.enroll(s);
		
		assertEquals(14, courseRoll.getOpenSeats());
	}

	@Test
	void testEnroll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll courseRoll = c.getCourseRoll();
		assertEquals(10, courseRoll.getEnrollmentCap());
		assertEquals(10, courseRoll.getOpenSeats());
		
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPw, MAX_CREDITS);
		
		Student s1 = new Student("John", "Doe", "jdoe", "jdoe@ncsu.edu", hashPw, MAX_CREDITS);
		Student s2 = new Student("Alice", "Smith", "asmith", "asmith@ncsu.edu", hashPw, MAX_CREDITS);
		Student s3 = new Student("Bob", "Johnson", "bjohnson", "bjohnson@ncsu.edu", hashPw, MAX_CREDITS);
		Student s4 = new Student("Ella", "Garcia", "egarcia", "egarcia@ncsu.edu", hashPw, MAX_CREDITS);
		Student s5 = new Student("Charlie", "Brown", "cbrown", "cbrown@ncsu.edu", hashPw, MAX_CREDITS);
		Student s6 = new Student("Sophia", "Martinez", "smartinez", "smartinez@ncsu.edu", hashPw, MAX_CREDITS);
		Student s7 = new Student("Oliver", "Lopez", "olopez", "olopez@ncsu.edu", hashPw, MAX_CREDITS);
		Student s8 = new Student("Grace", "Lee", "glee", "glee@ncsu.edu", hashPw, MAX_CREDITS);
		Student s9 = new Student("Liam", "Nguyen", "lnguyen", "lnguyen@ncsu.edu", hashPw, MAX_CREDITS);
		Student s10 = new Student("Ava", "Adams", "aadams", "aadams@ncsu.edu", hashPw, MAX_CREDITS);
		Student s11 = new Student("Emma", "Rivera", "erivera", "erivera@ncsu.edu", hashPw, MAX_CREDITS);
		Student s12 = new Student("William", "Gonzalez", "wgonzalez", "wgonzalez@ncsu.edu", hashPw, MAX_CREDITS);
		Student s13 = new Student("Mia", "Collins", "mcollins", "mcollins@ncsu.edu", hashPw, MAX_CREDITS);
		Student s14 = new Student("James", "Murphy", "jmurphy", "jmurphy@ncsu.edu", hashPw, MAX_CREDITS);
		Student s15 = new Student("Isabella", "Turner", "iturner", "iturner@ncsu.edu", hashPw, MAX_CREDITS);
		Student s16 = new Student("Logan", "Baker", "lbaker", "lbaker@ncsu.edu", hashPw, MAX_CREDITS);
		Student s17 = new Student("Aiden", "Hill", "ahill", "ahill@ncsu.edu", hashPw, MAX_CREDITS);
		Student s18 = new Student("Evelyn", "Cooper", "ecooper", "ecooper@ncsu.edu", hashPw, MAX_CREDITS);
		Student s19 = new Student("Benjamin", "Ward", "bward", "bward@ncsu.edu", hashPw, MAX_CREDITS);
		Student s20 = new Student("Sophie", "Perez", "sperez", "sperez@ncsu.edu", hashPw, MAX_CREDITS);



		
		courseRoll.enroll(s);
		courseRoll.enroll(s1);
		courseRoll.enroll(s2);
		courseRoll.enroll(s3);
		courseRoll.enroll(s4);
		courseRoll.enroll(s5);
		courseRoll.enroll(s6);
		courseRoll.enroll(s7);
		courseRoll.enroll(s8);
		courseRoll.enroll(s9);
		
		assertEquals(0, courseRoll.getOpenSeats());
		
		assertEquals(0, courseRoll.getNumberOnWaitlist());
		
		courseRoll.enroll(s10);
		courseRoll.enroll(s11);
		courseRoll.enroll(s12);
		courseRoll.enroll(s13);
		
		assertEquals(4, courseRoll.getNumberOnWaitlist());
		
		courseRoll.enroll(s14);
		courseRoll.enroll(s15);
		courseRoll.enroll(s16);
		courseRoll.enroll(s17);
		courseRoll.enroll(s18);
		courseRoll.enroll(s19);
		
		assertEquals(10, courseRoll.getNumberOnWaitlist());
		
		Exception e = assertThrows(IllegalArgumentException.class, () -> courseRoll.enroll(s20));
		assertEquals("Unable to enroll the student in the course.", e.getMessage());
		assertThrows(IllegalArgumentException.class, () -> courseRoll.enroll(null));
		assertThrows(IllegalArgumentException.class, () -> courseRoll.enroll(s));
		
	}

	@Test
	void testDrop() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll courseRoll = c.getCourseRoll();
		assertEquals(10, courseRoll.getEnrollmentCap());
		assertEquals(10, courseRoll.getOpenSeats());
		
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPw, MAX_CREDITS);
		Student s1 = new Student("John", "Doe", "jdoe", "jdoe@ncsu.edu", hashPw, MAX_CREDITS);
		Student s2 = new Student("Alice", "Smith", "asmith", "asmith@ncsu.edu", hashPw, MAX_CREDITS);
		Student s3 = new Student("Bob", "Johnson", "bjohnson", "bjohnson@ncsu.edu", hashPw, MAX_CREDITS);
		Student s4 = new Student("Ella", "Garcia", "egarcia", "egarcia@ncsu.edu", hashPw, MAX_CREDITS);
		Student s5 = new Student("Charlie", "Brown", "cbrown", "cbrown@ncsu.edu", hashPw, MAX_CREDITS);
		Student s6 = new Student("Sophia", "Martinez", "smartinez", "smartinez@ncsu.edu", hashPw, MAX_CREDITS);
		Student s7 = new Student("Oliver", "Lopez", "olopez", "olopez@ncsu.edu", hashPw, MAX_CREDITS);
		Student s8 = new Student("Grace", "Lee", "glee", "glee@ncsu.edu", hashPw, MAX_CREDITS);
		Student s9 = new Student("Liam", "Nguyen", "lnguyen", "lnguyen@ncsu.edu", hashPw, MAX_CREDITS);
		Student s10 = new Student("Ava", "Adams", "aadams", "aadams@ncsu.edu", hashPw, MAX_CREDITS);
		Student s11 = new Student("Emma", "Rivera", "erivera", "erivera@ncsu.edu", hashPw, MAX_CREDITS);
		Student s12 = new Student("William", "Gonzalez", "wgonzalez", "wgonzalez@ncsu.edu", hashPw, MAX_CREDITS);
		Student s13 = new Student("Mia", "Collins", "mcollins", "mcollins@ncsu.edu", hashPw, MAX_CREDITS);
		Student s14 = new Student("James", "Murphy", "jmurphy", "jmurphy@ncsu.edu", hashPw, MAX_CREDITS);
		Student s15 = new Student("Isabella", "Turner", "iturner", "iturner@ncsu.edu", hashPw, MAX_CREDITS);
		Student s16 = new Student("Logan", "Baker", "lbaker", "lbaker@ncsu.edu", hashPw, MAX_CREDITS);
		Student s17 = new Student("Aiden", "Hill", "ahill", "ahill@ncsu.edu", hashPw, MAX_CREDITS);
		Student s18 = new Student("Evelyn", "Cooper", "ecooper", "ecooper@ncsu.edu", hashPw, MAX_CREDITS);
		Student s19 = new Student("Benjamin", "Ward", "bward", "bward@ncsu.edu", hashPw, MAX_CREDITS);
		
		courseRoll.enroll(s);
		courseRoll.enroll(s1);
		courseRoll.enroll(s2);
		courseRoll.enroll(s3);
		courseRoll.enroll(s4);
		courseRoll.enroll(s5);
		courseRoll.enroll(s6);
		courseRoll.enroll(s7);
		courseRoll.enroll(s8);
		courseRoll.enroll(s9);
		
		assertEquals(0, courseRoll.getOpenSeats());
		
		assertEquals(0, courseRoll.getNumberOnWaitlist());
		
		courseRoll.drop(s3);
		assertEquals(1, courseRoll.getOpenSeats());
		
		courseRoll.enroll(s3);
		
		courseRoll.enroll(s10);
		courseRoll.enroll(s11);
		courseRoll.enroll(s12);
		courseRoll.enroll(s13);
		
		assertEquals(4, courseRoll.getNumberOnWaitlist());
		
		courseRoll.enroll(s14);
		courseRoll.enroll(s15);
		courseRoll.enroll(s16);
		courseRoll.enroll(s17);
		courseRoll.enroll(s18);
		courseRoll.enroll(s19);
		
		assertEquals(10, courseRoll.getNumberOnWaitlist());
		
		courseRoll.drop(s14);
		
		assertEquals(9, courseRoll.getNumberOnWaitlist());
		
		assertThrows(IllegalArgumentException.class, () -> courseRoll.drop(null));
		
	}

	@Test
	void testCanEnroll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "A");
		CourseRoll courseRoll = c.getCourseRoll();
		assertEquals(15, courseRoll.getEnrollmentCap());
		assertEquals(15, courseRoll.getOpenSeats());
		
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPw, MAX_CREDITS);
		assertTrue(courseRoll.canEnroll(s));
		courseRoll.enroll(s);
		
		assertThrows(IllegalArgumentException.class, () -> courseRoll.canEnroll(null));
		assertFalse(courseRoll.canEnroll(s));
	}

}
