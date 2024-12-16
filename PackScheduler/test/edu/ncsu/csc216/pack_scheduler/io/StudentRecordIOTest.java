package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Tests Student Record IO Class on its functionality of reading and writing.
 * @author Siddhant Joshi
 * @author Chloe Israel
 */

public class StudentRecordIOTest {

	/**Sample student data used for testing StudentRecordIO*/
	private String validStudent0 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	/**Sample student data used for testing StudentRecordIO*/
	private String validStudent1 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
	/**Sample student data used for testing StudentRecordIO*/
	private String validStudent2 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
	/**Sample student data used for testing StudentRecordIO*/
	private String validStudent3 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
	/**Sample student data used for testing StudentRecordIO*/
	private String validStudent4 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
	/**Sample student data used for testing StudentRecordIO*/
	private String validStudent5 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
	/**Sample student data used for testing StudentRecordIO*/
	private String validStudent6 = "Lane,Berg,lberg,sociis@non.org,pw,14";
	/**Sample student data used for testing StudentRecordIO*/
	private String validStudent7 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";
	/**Sample student data used for testing StudentRecordIO*/
	private String validStudent8 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
	/**Sample student data used for testing StudentRecordIO*/
	private String validStudent9 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";


	/**Placing all the data into an array called validStudents, which is ordered alphabetically by last name*/
	private String [] validStudents = {validStudent3, validStudent6, validStudent4, validStudent5, validStudent2,
			validStudent8, validStudent0, validStudent9, validStudent1, validStudent7};

	/** Hashed Password variable*/
	private String hashPW;
	/** Type of hashing algorithm used*/
	private static final String HASH_ALGORITHM = "SHA-256";

	/**
	 * Generates the hashed password before each test is run.
	 */
	@BeforeEach
	public void setUp() {
	    try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = Base64.getEncoder().encodeToString(digest.digest());
	        
	        for (int i = 0; i < validStudents.length; i++) {
	            validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}
	

	/**
	 * Test Read student records for Valid student file
	 * 
	 */

	@Test
	public void testReadStudentRecordsValid() {
		try {
			SortedList<Student> expectedStudents = StudentRecordIO.readStudentRecords("test-files/student_records.txt");
			//Check if correct number of students have been checked
			int expectedSizeOfStudents = 10;
			int actualSizeOfStudents = expectedStudents.size();
			assertEquals(expectedSizeOfStudents, actualSizeOfStudents);
			
			// Now testing if all of the students are read
			for (int i = 0; i < validStudents.length; i++) {
                assertEquals(validStudents[i], expectedStudents.get(i).toString());
            }
	
		} catch (IOException e) {
            fail("Could not read the File");
        }
		
	}
	
	/**
	 * Tests for Read student record invalid
	 */
	@Test
    public void testReadStudentRecordsInvalidFile() {
        try {
            SortedList<Student> students = StudentRecordIO.readStudentRecords("test-files/invalid_student_records.txt");
            // No Students are read in from invalid_student_records.txt. The ArrayList should be empty.	
			assertEquals(0, students.size());

        } catch (IOException e) {
            fail("Could not read the File");
        }
    }
 
	/**
	 * Tests for read student record if file is not there
	 */
	 @Test
	    public void testReadStudentRecordsInvalidNoFile() {
		 //FileNotFoundException is thrown if the method is passed a file that doesn’t exist.
	        assertThrows(FileNotFoundException.class, () -> {
	            StudentRecordIO.readStudentRecords("abcRandomFile.txt");
	        });
	    }

	
	
	/**
	 * 
	 * Tests writeStudentRecords with an invalid file location.
	 */
	@Test
	void testWriteStudentRecordsValid() {
		 try {
	            SortedList<Student> students = new SortedList<>();
	            students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));

	            StudentRecordIO.writeStudentRecords("test-files/output_student_records.txt", students);
	            
	           
	            checkFiles("test-files/expected_student_records.txt", "test-files/output_student_records.txt");
	        } 
		 catch (IOException e) {
	            fail("Could not write on the File");
	        }
	}
	

	/**
    * Tests writing student records to a file with no write permissions.
    */
	@Test
	public void testWriteStudentRecordsNoPermissions() {
		SortedList<Student> students = new SortedList<Student>();
		students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
		
		Exception exception = assertThrows(IOException.class, 
				() -> StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students));
		assertEquals("/home/sesmith5/actual_student_records.txt (No such file or directory)", exception.getMessage());
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
