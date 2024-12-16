package edu.ncsu.csc316.dsa.io;

import static org.junit.Assert.*;


import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * The StudentReaderTest class provides test cases for the StudentReaader class.
 * 
 * @author Siddhant Joshi
 */
public class StudentReaderTest {
	
	/**
	 * Test to read the contents of the csv file.
	 */
	@Test
	public void testReadFile() {
		Student[] contents = StudentReader.readInputAsArray("input/student_ascendingID.csv");
		
		assertEquals(16, contents.length);
		
		assertEquals("Amber", contents[0].getFirst());
		assertEquals("Ara", contents[1].getFirst());
		assertEquals("Lacie", contents[2].getFirst());
		assertEquals("Idalia", contents[3].getFirst());
		assertEquals("Evelin", contents[4].getFirst());
		assertEquals("Lewis", contents[5].getFirst());
		assertEquals("Alicia", contents[6].getFirst());
		assertEquals("Tyree", contents[7].getFirst());
		assertEquals("Loise", contents[8].getFirst());
		assertEquals("Roxann", contents[9].getFirst());
		assertEquals("Nichole", contents[10].getFirst());
		assertEquals("Charlene", contents[11].getFirst());
		assertEquals("Shanti", contents[12].getFirst());
		assertEquals("Cristine", contents[13].getFirst());
		assertEquals("Tanner", contents[14].getFirst());
		assertEquals("Dante", contents[15].getFirst());
	}
	
	
	
	/**
	 * Tests each lines contents. Checks if its in order
	 */
	@Test
	public void testInLineContent() {
		Student[] contents = StudentReader.readInputAsArray("input/student_ascendingID.csv");
		assertEquals("Amber", contents[0].getFirst());
        assertEquals("Michael", contents[0].getLast());
        assertEquals("michaea", contents[0].getUnityID());
        assertEquals(1, contents[0].getId());
        assertEquals(1.10, contents[0].getGpa(), 0.01);
        assertEquals(10, contents[0].getCreditHours());
	}
	
	/**
	 * Check if it works as expected for a non existent file
	 */
	@Test
    public void testReadNonExistentFile() {
        
        String fileNotFound = "input/FileNotExist.csv";
      
        assertThrows(IllegalArgumentException.class, () -> {
            StudentReader.readInputAsArray(fileNotFound);
        });
    }
	
}
