/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * FacyltyDirectory Test
 * 
 * @author Siddhant Joshi
 * @author Arnav Gangully
 * @author Shuarya Deepak
 */
class FacultyDirectoryTest {
	
	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#FacultyDirectory()}.
	 */
	@Test
	void testFacultyDirectory() {
		FacultyDirectory fd = new FacultyDirectory();
		assertEquals(0, fd.getFacultyDirectory().length);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#newFacultyDirectory()}.
	 */
	@Test
	void testNewFacultyDirectory() {
		FacultyDirectory fd = new FacultyDirectory();
		assertEquals(0, fd.getFacultyDirectory().length);
		fd.addFaculty("Arnav", "Ganguly", "asgangul", "asgangul@ncsu.edu", "123", "123", 2);
		assertEquals(1, fd.getFacultyDirectory().length);
		fd.newFacultyDirectory();
		assertEquals(0, fd.getFacultyDirectory().length);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#loadFacultyFromFile(java.lang.String)}.
	 */
	@Test
	void testLoadFacultyFromFile() {
		FacultyDirectory fd = new FacultyDirectory();
		assertEquals(0, fd.getFacultyDirectory().length);
		fd.loadFacultyFromFile("test-files/faculty_records.txt");
		assertEquals(8, fd.getFacultyDirectory().length);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#addFaculty(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int)}.
	 */
	@Test
	void testAddFaculty() {
		FacultyDirectory fd = new FacultyDirectory();
		assertEquals(0, fd.getFacultyDirectory().length);
		fd.addFaculty("Arnav", "Ganguly", "asgangul", "asgangul@ncsu.edu", "123", "123", 2);
		assertEquals(1, fd.getFacultyDirectory().length);
		assertEquals("Arnav", fd.getFacultyById("asgangul").getFirstName());
		assertEquals("Ganguly", fd.getFacultyById("asgangul").getLastName());
		assertEquals("asgangul@ncsu.edu", fd.getFacultyById("asgangul").getEmail());
		assertEquals(2, fd.getFacultyById("asgangul").getMaxCourses());
		assertThrows(IllegalArgumentException.class, () -> fd.addFaculty("Arnav", "Ganguly", "asgangul", "asgangul@ncsu.edu", "123", "123", 2));
		assertThrows(IllegalArgumentException.class, () -> fd.addFaculty("Arnav", "Ganguly", "notasgangul", "asgangul@ncsu.edu", "456", "123", 2));
		fd.newFacultyDirectory();
		assertThrows(IllegalArgumentException.class, () -> fd.addFaculty("", "Ganguly", "asgangul", "asgangul@ncsu.edu", "123", "123", 2));
		assertThrows(IllegalArgumentException.class, () -> fd.addFaculty("Arnav", "", "asgangul", "asgangul@ncsu.edu", "123", "123", 2));
		assertThrows(IllegalArgumentException.class, () -> fd.addFaculty("Arnav", "Ganguly", "", "asgangul@ncsu.edu", "123", "123", 2));
		assertThrows(IllegalArgumentException.class, () -> fd.addFaculty("Arnav", "Ganguly", "asgangul", "", "123", "123", 2));
		assertThrows(IllegalArgumentException.class, () -> fd.addFaculty("Arnav", "Ganguly", "asgangul", "asgangul@ncsu.edu", "", "123", 2));
		assertThrows(IllegalArgumentException.class, () -> fd.addFaculty("Arnav", "Ganguly", "asgangul", "asgangul@ncsu.edu", "123", "123", 4));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#removeFaculty(java.lang.String)}.
	 */
	@Test
	void testRemoveFaculty() {
		FacultyDirectory fd = new FacultyDirectory();
		assertEquals(0, fd.getFacultyDirectory().length);
		fd.addFaculty("Arnav", "Ganguly", "asgangul", "asgangul@ncsu.edu", "123", "123", 2);
		assertEquals(1, fd.getFacultyDirectory().length);
		fd.removeFaculty("asgangul");
		assertEquals(0, fd.getFacultyDirectory().length);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#getFacultyDirectory()}.
	 */
	@Test
	void testGetFacultyDirectory() {
		FacultyDirectory fd = new FacultyDirectory();
		assertEquals(0, fd.getFacultyDirectory().length);
		fd.addFaculty("Arnav", "Ganguly", "asgangul", "asgangul@ncsu.edu", "123", "123", 2);
		assertEquals(1, fd.getFacultyDirectory().length);
		assertEquals("Arnav", fd.getFacultyDirectory()[0][0]);
		assertEquals("Ganguly", fd.getFacultyDirectory()[0][1]);
		assertEquals("asgangul", fd.getFacultyDirectory()[0][2]);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#saveFacultyDirectory(java.lang.String)}.
	 */
	@Test
	void testSaveFacultyDirectory() {
		FacultyDirectory fd = new FacultyDirectory();
		assertEquals(0, fd.getFacultyDirectory().length);
		fd.loadFacultyFromFile("test-files/faculty_records.txt");
		assertEquals(8, fd.getFacultyDirectory().length);
		fd.removeFaculty("kpatel");
		fd.removeFaculty("nbrady");
		fd.saveFacultyDirectory("test-files/actual_faculty_records.txt");
		fd.loadFacultyFromFile("test-files/actual_faculty_records.txt");
		assertEquals(6, fd.getFacultyDirectory().length);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#getFacultyById(java.lang.String)}.
	 */
	@Test
	void testGetFacultyById() {
		FacultyDirectory fd = new FacultyDirectory();
		assertEquals(0, fd.getFacultyDirectory().length);
		fd.loadFacultyFromFile("test-files/faculty_records.txt");
		assertEquals(8, fd.getFacultyDirectory().length);
		assertEquals("Kevyn", fd.getFacultyById("kpatel").getFirstName());
		assertEquals("Patel", fd.getFacultyById("kpatel").getLastName());
		assertEquals(1, fd.getFacultyById("kpatel").getMaxCourses());
		
		assertEquals("Norman", fd.getFacultyById("nbrady").getFirstName());
		assertEquals("Brady", fd.getFacultyById("nbrady").getLastName());
		assertEquals(1, fd.getFacultyById("nbrady").getMaxCourses());
	}

}
