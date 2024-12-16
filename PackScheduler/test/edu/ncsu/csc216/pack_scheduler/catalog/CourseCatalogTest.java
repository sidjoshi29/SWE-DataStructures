package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
/**
 * Tests CourseCatalog.
 * 
 * @author Chloe Israel
 * @author Dolly Jani
 * @author Siddhant Joshi
 */
public class CourseCatalogTest {

	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_course_records.txt";
	
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
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	

	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws Exception any possible exception for setup
	 */
	@Before
    public void setUp() throws Exception {
        // Reset student_records.txt so that it's fine for other needed tests
        Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_student_records.txt");
        Path destinationPath = FileSystems.getDefault().getPath("test-files", "student_records.txt");
        try {
            Files.deleteIfExists(destinationPath);
            Files.copy(sourcePath, destinationPath);
        } catch (IOException e) {
            fail("Unable to reset files");
        }
    }
	
	/**
	 * Tests CourseCatalog().
	 */
	@Test
	public void testCourseCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		catalog.loadCoursesFromFile(validTestFile);
		assertEquals(13, catalog.getCourseCatalog().length);	
		
		catalog.loadCoursesFromFile(invalidTestFile);
		assertEquals(0, catalog.getCourseCatalog().length);	
	}

	/**
	 * Test getCourseFromCatalog()
	*/
	@Test
	public void testGetCourseFromCatalog() {
		CourseCatalog ws = new CourseCatalog();
		assertNull(ws.getCourseFromCatalog("CSC 492", "001"));
		
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(null, ws.getCourseFromCatalog("CSC216", "001"));

		
		// get an existing course
		ws.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(c, ws.getCourseFromCatalog(NAME, SECTION));

	}
	
	
	/**
	 * Test addCourseToCatalog()
	 */
	@Test
	public void testAddCourseToCatalog() {
		CourseCatalog ws = new CourseCatalog();
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);

		ws.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);

		
		//check if its adding same course. assert false
		assertFalse(ws.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));

		//getting a course thats there
		assertEquals(c, ws.getCourseFromCatalog(NAME, SECTION));

	}
	
	/**
	 * Test removeCourseFromCatalog()
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		CourseCatalog ws = new CourseCatalog();
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);

		ws.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);

		
		// checking for removing something that doesnt exist
		assertFalse(ws.removeCourseFromCatalog("CSC999", SECTION));

		
		//get existing course
		assertEquals(c, ws.getCourseFromCatalog(NAME, SECTION));
		
		//removing it
		assertTrue(ws.removeCourseFromCatalog(NAME, SECTION));
		
		// testing getting a course that is not there  - (null
		assertEquals(null, ws.getCourseFromCatalog(NAME, SECTION));

	}
	
	/**
	 * Tests resetSchedule()
	 */
	@Test
	public void testResetSchedule() {
		CourseCatalog ws = new CourseCatalog();
		ws.loadCoursesFromFile(validTestFile);
		assertEquals(13, ws.getCourseCatalog().length);
		ws.newCourseCatalog();
		assertEquals(0, ws.getCourseCatalog().length);
	}
	
	/**
	 * Tests getCourseCatalog().
	 */
	@Test
	public void testGetCourseCatalog() {
		CourseCatalog ws = new CourseCatalog();
		ws.loadCoursesFromFile(validTestFile);
		//Get the catalog and make sure contents are correct
		//Name, section, title
		String [][] catalog = ws.getCourseCatalog();
		//Row 0
		assertEquals("CSC116", catalog[0][0]);
		assertEquals("001", catalog[0][1]);
		assertEquals("Intro to Programming - Java", catalog[0][2]);
		assertEquals("MW 9:10AM-11:00AM", catalog[0][3]);
		assertEquals("10", catalog[0][4]);
		//Row 1
		assertEquals("CSC116", catalog[1][0]);
		assertEquals("002", catalog[1][1]);
		assertEquals("Intro to Programming - Java", catalog[1][2]);
		assertEquals("MW 11:20AM-1:10PM", catalog[1][3]);
		assertEquals("10", catalog[1][4]);
		//Row 2
		assertEquals("CSC116", catalog[2][0]);
		assertEquals("003", catalog[2][1]);
		assertEquals("Intro to Programming - Java", catalog[2][2]);
		assertEquals("TH 11:20AM-1:10PM", catalog[2][3]);
		assertEquals("10", catalog[2][4]);
		//Row 3
		assertEquals("CSC216", catalog[3][0]);
		assertEquals("001", catalog[3][1]);
		assertEquals("Software Development Fundamentals", catalog[3][2]);
		assertEquals("TH 1:30PM-2:45PM", catalog[3][3]);
		assertEquals("10", catalog[3][4]);
		//Row 4
		assertEquals("CSC216", catalog[4][0]);
		assertEquals("002", catalog[4][1]);
		assertEquals("Software Development Fundamentals", catalog[4][2]);
		assertEquals("MW 1:30PM-2:45PM", catalog[4][3]);
		assertEquals("10", catalog[4][4]);
		//Row 5
		assertEquals("CSC216", catalog[5][0]);
		assertEquals("601", catalog[5][1]);
		assertEquals("Software Development Fundamentals", catalog[5][2]);
		assertEquals("Arranged", catalog[5][3]);
		assertEquals("10", catalog[5][4]);
		//Row 6
		assertEquals("CSC217", catalog[6][0]);
		assertEquals("202", catalog[6][1]);
		assertEquals("Software Development Fundamentals Lab", catalog[6][2]);
		assertEquals("M 10:40AM-12:30PM", catalog[6][3]);
		assertEquals("10", catalog[6][4]);
		//Row 6
		assertEquals("CSC217", catalog[7][0]);
		assertEquals("211", catalog[7][1]);
		assertEquals("Software Development Fundamentals Lab", catalog[7][2]);
		assertEquals("T 8:30AM-10:20AM", catalog[7][3]);
		assertEquals("10", catalog[7][4]);
	}
	
	/**
	 * Tests newCourseCatalog()
	 */
	@Test
	public void testNewCourseCatalog() {
		CourseCatalog ws = new CourseCatalog();
		
		ws.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		
		ws.newCourseCatalog();
		
		// get the course we added, expecting null
		assertEquals(null, ws.getCourseFromCatalog(NAME, SECTION));


	}
	
	/**
	 * Test PackScheduler.saveCourseCatalog().
	 */
	@Test
	public void saveCourseCatalog() {
		//Add courses and test that exports correctly
		CourseCatalog ws1 = new CourseCatalog();
		ws1.addCourseToCatalog("CSC316", "Data Structures and Algorithims", "002", 4, "sjoshi19", 15, "MTF", 900, 1050);
		assertEquals(1, ws1.getCourseCatalog().length);
		ws1.saveCourseCatalog("test-files/actual_catalog_export.txt");
		checkFiles("test-files/expected_catalog_export.txt", "test-files/actual_catalog_export.txt");
	}


	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {
			
			while (actScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			if (expScanner.hasNextLine()) {
				fail();
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
