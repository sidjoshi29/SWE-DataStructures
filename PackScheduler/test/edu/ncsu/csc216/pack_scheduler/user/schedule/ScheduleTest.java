/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Schedule Class Test
 * 
 * @author Siddhant Joshi
 * @author Arnav Gnaguly
 * @author Shaurya Deepak
 */
class ScheduleTest {

	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Software Development Fundamentals";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 3;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course enrollment cap */
	private static final int ENROLLMENT_CAP = 15;
	/** Course meeting days */
	private static final String MEETING_DAYS = "MW";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;

	/**
	 * test for constructor
	 */
	@Test
	public void testSchedule() {
		Schedule s = new Schedule();
		assertEquals("My Schedule", s.getTitle());
		assertEquals(0, s.getScheduledCourses().length);
	}

	/**
	 * Test setTitle() method
	 */
	@Test
	public void testSetTitle() {
		Schedule schedule = new Schedule();

		// Set Title and check that changed
		schedule.setTitle("New Title");
		assertEquals("New Title", schedule.getTitle());

		// Check that exception is thrown if null title and no
		// change to title already there.
		try {
			schedule.setTitle(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("New Title", schedule.getTitle());
		}
	}

	/**
	 * Test addToSchedule method
	 */
	@Test
	public void testAddCourseToSchedule() {

		// ADDED ONE COURSE
		Schedule schedule = new Schedule();
		Course course = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS,
				START_TIME, END_TIME);
		schedule.addCourseToSchedule(course);
		assertEquals(NAME, schedule.getScheduledCourses()[0][0]);
		assertEquals(SECTION, schedule.getScheduledCourses()[0][1]);
		assertEquals(TITLE, schedule.getScheduledCourses()[0][2]);

		// ADDED ANOTHER COURSE
		Course course1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS,
				START_TIME, END_TIME);
		assertThrows(IllegalArgumentException.class, () -> schedule.addCourseToSchedule(course1));

		// ADDED CONFLICTING COURSE
		Course course2 = new Course("CSC226", "Discrete Mathematics", SECTION, 2, INSTRUCTOR_ID, ENROLLMENT_CAP, "MW",
				1430, 1500);
		assertThrows(IllegalArgumentException.class, () -> schedule.addCourseToSchedule(course2));

		// ADDED NULL COURSE
		assertThrows(NullPointerException.class, () -> schedule.addCourseToSchedule(null));

	}

	/**
	 * Tests removeCourseFromSchedule
	 */
	@Test
	public void testRemoveCourseFromSchedule() {
		Schedule schedule = new Schedule();
		Course course = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS,
				START_TIME, END_TIME);
		schedule.addCourseToSchedule(course);
		assertEquals(1, schedule.getScheduledCourses().length);
		assertEquals(NAME, schedule.getScheduledCourses()[0][0]);
		assertEquals(SECTION, schedule.getScheduledCourses()[0][1]);
		assertEquals(TITLE, schedule.getScheduledCourses()[0][2]);

		schedule.removeCourseFromSchedule(course);
		assertEquals(0, schedule.getScheduledCourses().length);

		assertFalse(schedule.removeCourseFromSchedule(course));
		assertFalse(schedule.removeCourseFromSchedule(null));
	}

	/**
	 * Tests resetSchedule
	 */
	@Test
	public void testResetSchedule() {
		Schedule schedule = new Schedule();
		Course course = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS,
				START_TIME, END_TIME);
		schedule.addCourseToSchedule(course);
		assertEquals(1, schedule.getScheduledCourses().length);
		assertEquals(NAME, schedule.getScheduledCourses()[0][0]);
		assertEquals(SECTION, schedule.getScheduledCourses()[0][1]);
		assertEquals(TITLE, schedule.getScheduledCourses()[0][2]);
		schedule.setTitle("My updated schedule");

		schedule.resetSchedule();
		assertEquals(0, schedule.getScheduledCourses().length);
		assertEquals("My Schedule", schedule.getTitle());
	}

	/**
	 * Tests getScheduleCourses
	 */
	@Test
	public void testGetScheduleCourses() {
		Schedule schedule = new Schedule();
		Course course = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS,
				START_TIME, END_TIME);
		schedule.addCourseToSchedule(course);
		assertEquals(1, schedule.getScheduledCourses().length);
		assertEquals(NAME, schedule.getScheduledCourses()[0][0]);
		assertEquals(SECTION, schedule.getScheduledCourses()[0][1]);
		assertEquals(TITLE, schedule.getScheduledCourses()[0][2]);

		Course course2 = new Course("CSC226", "Discrete Mathematics", SECTION, 2, INSTRUCTOR_ID, ENROLLMENT_CAP, "MW",
				1600, 1800);
		schedule.addCourseToSchedule(course2);
		assertEquals(2, schedule.getScheduledCourses().length);
		assertEquals("CSC226", schedule.getScheduledCourses()[1][0]);
		assertEquals(SECTION, schedule.getScheduledCourses()[1][1]);
		assertEquals("Discrete Mathematics", schedule.getScheduledCourses()[1][2]);

	}
	
	@Test
	public void testGetScheduleCredits() {
	    Schedule schedule = new Schedule();
	    assertEquals(0, schedule.getScheduleCredits());

	    Course course1 = new Course("CSC216", "Java", "001", 3, "Instructor", 10, "MW", 1230, 1415);
	    schedule.addCourseToSchedule(course1);
	    assertEquals(3, schedule.getScheduleCredits());
	    Course course2 = new Course("CSC226", "Discrete Mathematics", "001", 3, "Instructor", 10, "TH", 1230, 1415);
	    schedule.addCourseToSchedule(course2);
	    assertEquals(6, schedule.getScheduleCredits());
	}
	
	@Test
	public void testCanAdd() {
	    Schedule schedule = new Schedule();
	    Course course1 = new Course("CSC216", "Java", "001", 3, "Instructor", 10, "MW", 1230, 1415);
	    assertTrue(schedule.canAdd(course1)); 

	    schedule.addCourseToSchedule(course1);
	    assertFalse(schedule.canAdd(course1)); 

	    Course nullCourse = null;
	    assertFalse(schedule.canAdd(nullCourse)); 
	    Course courseWithConflict = new Course("CSC217", "Java Lab", "001", 1, "Instructor", 10, "MW", 1300, 1415);
	    assertFalse(schedule.canAdd(courseWithConflict));
	}



}
