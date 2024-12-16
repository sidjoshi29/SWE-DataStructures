package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * This is the class that helps us read and write files for the faculty records. It consists of read and write functionality
 * with different methods and one private helper method for the read functionality
 */
public class FacultyRecordIO {
	
	/**
	 * This is the method that reads from the file. It also calls it's private helper method processFaculty
	 * @param fileName is the file name and path
	 * @return a linkedList containing all of the Faculty objects
	 * @throws FileNotFoundException if the file that needs to be read isn't found
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		Scanner fileInput = new Scanner(new FileInputStream(fileName));
		LinkedList<Faculty> faculty = new LinkedList<Faculty>();
		while (fileInput.hasNextLine()) {
			try {
				Faculty f = processFaculty(fileInput.nextLine());
				faculty.add(f);
			} catch (Exception e) {
				// skips the faculty record
			}
		}
		fileInput.close();
		return faculty;
	}
	
	/**
	 * This is the private helper method that processes the token from the line
	 * @param line that contains the token from the file
	 * @return the Faculty object after its been constructed
	 * @throws IllegalArgumentException if one of the field is missing
	 */
	private static Faculty processFaculty(String line) {
		Scanner sc = new Scanner(line);
		sc.useDelimiter(","); 
		String firstName = null;
		String lastName = null;
		String id = null;
		String email = null;
		String hashPW = null;
		int maxCourses = 0;
		
		try {
			firstName = sc.next();
			lastName = sc.next();
			id = sc.next();
			email = sc.next();
			hashPW = sc.next();
			maxCourses = sc.nextInt();
		}
		catch(NoSuchElementException e) {
			sc.close();
			throw new IllegalArgumentException("Invalid");
		}
		sc.close();
		Faculty faculty = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		return faculty;
	}
	
	/**
	 * Writes faculty records into directory
	 * @param fileName the name of the file 
	 * @param facultyDirectory the arrayList of students
	 * @throws IOException since its unable to write on file
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < facultyDirectory.size(); i++) {
		    fileWriter.println(facultyDirectory.get(i).toString());
		}

		fileWriter.close();
		
	}
}
