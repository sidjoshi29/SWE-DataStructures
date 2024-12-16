package edu.ncsu.csc216.pack_scheduler.io;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.File;


import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 *  Reads Course records from text files.  Writes a set of CourseRecords to a file.
 *  It allows the system to read course records from a file and generate a list
 *  of valid Courses, and also to write a list of Courses to a file. Any invalid 
 *  Courses are ignored during the reading process. If the file to read cannot be found
 *  or the permissions are incorrect,
 * 
 * 
 * @author Sarah Heckman
 * @author Siddhant Joshi
 */
public class CourseRecordIO {

    /**
     * Reads course records from a file and generates a list of valid Courses.  Any invalid
     * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Course records from
     * @return a list of valid Courses
     * @throws FileNotFoundException if the file cannot be found or read
     */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
	    Scanner fileReader = new Scanner(new FileInputStream(fileName));  //Create a file scanner to read the file
	    SortedList<Course> courses = new SortedList<Course>(); //Create an empty array of Course objects
	    while (fileReader.hasNextLine()) { //While we have more lines in the file
	        try { //Attempt to do the following
	            //Read the line, process it in readCourse, and get the object
	            //If trying to construct a Course in readCourse() results in an exception, flow of control will transfer to the catch block, below
	            Course course = readCourse(fileReader.nextLine()); 

	            //Create a flag to see if the newly created Course is a duplicate of something already in the list  
	            boolean duplicate = false;
	            //Look at all the courses in our list
	            for (int i = 0; i < courses.size(); i++) {
	                //Get the course at index i
	                Course current = courses.get(i);
	                //Check if the name and section are the same
	                if (course.getName().equals(current.getName()) &&
	                        course.getSection().equals(current.getSection())) {
	                    //It's a duplicate!
	                    duplicate = true;
	                    break; //We can break out of the loop, no need to continue searching
	                }
	            }
	            //If the course is NOT a duplicate
	            if (!duplicate) {
	                courses.add(course); //Add to the ArrayList!
	            } //Otherwise ignore
	        } catch (IllegalArgumentException e) {
	            //The line is invalid b/c we couldn't create a course, skip it!
	        }
	    }
	    //Close the Scanner b/c we're responsible with our file handles
	    fileReader.close();
	    //Return the ArrayList with all the courses we read!
	    return courses;
	}
    

	/**Makes line from input to token
	 * @throws IllegalArgumentException if there is an issue reading in the line
	 * @param line is input line
	 * @return new Course
	 */

    private static Course readCourse(String line) {
    	Scanner readLineScan = new Scanner(line);
    	readLineScan.useDelimiter(",");
    	
    	try {
    		//read in tokens for name, title, section, credits, 
    		//instructorId, and meetingDays and store in local variables
    		String name = readLineScan.next();
    		String title = readLineScan.next();
    		String section = readLineScan.next();
    		int creditHours = readLineScan.nextInt();
    		String instructorId = readLineScan.next();
    		int enrollmentCap = readLineScan.nextInt();
    		String meetingDays = readLineScan.next();
    		
    		
    		if("A".equals(meetingDays)) {
    			//if it has more tokens
    			if(readLineScan.hasNext()) {
    				readLineScan.close();
    				//change message later
    				throw new IllegalArgumentException ("Invalid");
    			}
    			else {
    				readLineScan.close();
    				Course course = new Course(name, title, section, creditHours, null, enrollmentCap, meetingDays);
        			Faculty faculty = RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId);
        			if (faculty != null) {
        				faculty.getSchedule().addCourseToSchedule(course);
        				course.setInstructorId(instructorId);
        			}
    				return course;
    			}
    		}
    		else {
    			//read the tokens
    			int startTime = readLineScan.nextInt();
    			int endTime = readLineScan.nextInt();
    			
    			if (readLineScan.hasNext()) {
    				readLineScan.close();
    				throw new IllegalArgumentException ("Invalid");
    			}
    			readLineScan.close();
    			Course course = new Course(name, title, section, creditHours, null, enrollmentCap, meetingDays, startTime, endTime);
    			Faculty faculty = RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId);
    			if (faculty != null) {
    				faculty.getSchedule().addCourseToSchedule(course);
    				course.setInstructorId(instructorId);
    			}
    			return course;
    		}

    	}
    	catch (NoSuchElementException e){
    		readLineScan.close();
    		throw new IllegalArgumentException("Invalid input format");
    	}
	}

	/**
     * Writes the given list of Courses to 
     * @param fileName file to write schedule of Courses to
     * @param catalog list of Courses to write
     * @throws IOException if cannot write to file
     */
    public static void writeCourseRecords(String fileName, SortedList<Course> catalog) throws IOException {
    	PrintStream fileWriter = new PrintStream(new File(fileName));

    	for (int i = 0; i < catalog.size(); i++) {
    	    fileWriter.println(catalog.get(i).toString());
    	}

    	fileWriter.close();
        
    }

}
