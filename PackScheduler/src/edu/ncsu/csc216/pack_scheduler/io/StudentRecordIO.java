package edu.ncsu.csc216.pack_scheduler.io;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;


/**
 * Provides static methods that support reading in student records 
 * from a file and writing student records to a file
 * @author Siddhant Joshi
 * @author Dolly Jani 
 * @author Chloe Israel
 */

public class StudentRecordIO {
	
	/**
	 * Reads in a file of student records
	 * @param fileName the name of the file
	 * @return studentRead the arrayList of students
	 * @throws FileNotFoundException if it can't find the file
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		SortedList<Student> studentRead = new SortedList<>();
		Scanner fileReader = new Scanner(new File(fileName));
		
		while(fileReader.hasNext()) {
			try {
				Student student = processStudent(fileReader.nextLine());
				studentRead.add(student);
			}
			catch(IllegalArgumentException e) {
				// Invalid
			}
		}
		fileReader.close();
		return studentRead;
	}
	/**
	 * Writes student records into directory
	 * @param fileName the name of the file 
	 * @param studentDirectory the arrayList of students
	 * @throws IOException since its unable to write on file
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < studentDirectory.size(); i++) {
		    fileWriter.println(studentDirectory.get(i).toString());
		}

		fileWriter.close();
		
	}
	
	/**
	 * Processes each individual student in the file
	 * @param line the current line of the student being read in
	 * @return stu the student object
	 * @throws IllegalArgumentException if the scanner cannot read the next value inthe String 
	 */
	private static Student processStudent(String line) {
		Scanner sc = new Scanner(line);
		sc.useDelimiter(",");
		
		String firstName = null;
		String lastName = null;
		String id = null;
		String email = null;
		String hashPW = null;
		int maxCredits = 0;
		
		try {
			firstName = sc.next();
			lastName = sc.next();
			id = sc.next();
			email = sc.next();
			hashPW = sc.next();
			maxCredits = sc.nextInt();
		}
		catch(NoSuchElementException e) {
			sc.close();
			throw new IllegalArgumentException("Invalid");
		}
		sc.close();
		Student stu = new Student(firstName, lastName, id, email, hashPW, maxCredits);
		return stu;
	}

}
