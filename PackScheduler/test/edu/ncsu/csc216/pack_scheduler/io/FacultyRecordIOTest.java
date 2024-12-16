package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;


class FacultyRecordIOTest {

	@Test
	void testReadFacultyRecords() {
		try {
			LinkedList<Faculty> actualFacultyList = FacultyRecordIO.readFacultyRecords("test-files/faculty_records.txt");
			FacultyRecordIO.writeFacultyRecords("test-files/actual_faculty_records.txt", actualFacultyList);
			checkFiles("test-files/expected_full_faculty_records.txt", "test-files/actual_faculty_records.txt");
			
			LinkedList<Faculty> actualFacultyList2 = FacultyRecordIO.readFacultyRecords("test-files/invalid_faculty_records.txt");
			assertEquals(0, actualFacultyList2.size());
			
		} catch (IOException e) {
            fail("Could not read the File");
        }
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new FileInputStream(expFile));
			 Scanner actScanner = new Scanner(new FileInputStream(actFile));) {
			
			while (expScanner.hasNextLine()  && actScanner.hasNextLine()) {
				String exp = expScanner.nextLine();
				String act = actScanner.nextLine();
				assertEquals(exp, act, "Expected: " + exp + " Actual: " + act); 
				//The third argument helps with debugging!
			}
			if (expScanner.hasNextLine()) {
				fail("The expected results expect another line " + expScanner.nextLine());
			}
			if (actScanner.hasNextLine()) {
				fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
			}
			 
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}
