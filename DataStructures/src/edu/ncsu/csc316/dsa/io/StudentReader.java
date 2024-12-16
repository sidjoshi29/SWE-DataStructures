package edu.ncsu.csc316.dsa.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import edu.ncsu.csc316.dsa.data.Student;

/**
 * StudentReader processes input CSV files that contain
 * student information.
 * 
 * Input CSV files should be in the following format:
 * 
 *    FIRST_NAME,LAST_NAME,UNITY_ID,STUDENT_ID,GPA,CREDIT_HOURS
 * 
 * @author Dr. King
 * @author Siddhant Joshi
 *
 */
public class StudentReader {

	/**
	 * Returns the input CSV file as an array of Student objects
	 * @param filePath - the path to the input CSV file
	 * @return an array of Student objects
	 */
	public static Student[] readInputAsArray(String filePath)
	{
		Student[] list = new Student[10];
		try(Scanner scan = new Scanner(new FileInputStream(filePath), "UTF8"))
		{
			scan.nextLine(); // SKIP HEADER LINE
			int index = 0;
			while(scan.hasNextLine())
			{
				if(index >= list.length)
				{
					list = Arrays.copyOf(list, list.length * 2 + 1);
				}
				list[index] = processLine(scan.nextLine());
				index++;
			}
			list = Arrays.copyOf(list, index);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File not found: " + e.getMessage());
		}
		return list;
	}

	/**
	 * Processes a single line from the input file to construct a Student.
	 * @param line - the input line from the input file
	 * @return a Student representation of the input line
	 * @throws IllegalArgumentException if number of parameters are wrong
	 */
	private static Student processLine(String line) {
		String[] lineContents = line.split(",");
		
	    if (lineContents.length != 6) {
	        throw new IllegalArgumentException("Invalid number of parameters");
	    }
	    try {
	        String firstName = lineContents[0];
	        String lastName = lineContents[1];
	        String unityId = lineContents[2];
	        int studentId = Integer.parseInt(lineContents[3]);
	        double gpa = Double.parseDouble(lineContents[4]);
	        int creditHours = Integer.parseInt(lineContents[5]);
	        return new Student(firstName, lastName, studentId, creditHours, gpa, unityId);
	    } catch (Exception e) {
	        throw new IllegalArgumentException();
	    }
	}
}
