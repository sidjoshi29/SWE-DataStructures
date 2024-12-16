package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * CourseCatalog used to manage courses
 * @author Siddhant Joshi
 */

public class CourseCatalog {
	
	/** A list of Courses to make up the catalog*/
	private SortedList<Course> catalog;
	
	/**
	 * Constructs an empty catalog.
	 */
	public CourseCatalog() {
		newCourseCatalog();
	}
	
	/**
	 * Constructs an empty catalog.
	 */
	public void newCourseCatalog() {
		catalog = new SortedList<Course>();
	}
	
	/**
	 * Loads course records into the catalog. Any FileNotFoundExceptions are caught and an 
	 * IllegalArgumentException is thrown to the client.
	 * @param fileName file containing list of students
	 * @throws IllegalArgumentException if a FileNotFoundException is caught.
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	
	/**
	 * Adds a Course with the following fields to the catalog
	 * @param name name of course
	 * @param title title of course
	 * @param section section of the course
	 * @param credits number of credits of course
	 * @param instructorId instructor id of the course
	 * @param enrollmentCap of the course
	 * @param meetingDays meeting days of the course
	 * @param startTime course startTime 
	 * @param endTime course end time
	 * @return true if the Course is added and false if the Course already exists in the catalog.
	 * @throws IllegalArgumentException f there is an error constructing the Course, the IllegalArgumentException is allowed to propagate to the client.
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, 
			String instructorId, int enrollmentCap, String meetingDays, int startTime, int endTime) {
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			if (c.getName().equals(name) && c.getSection().equals(section)) {
				return false;
			}
		}
		try{
			Course c = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);
			catalog.add(c);
			return true;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage()); 
		}
	}
	
	
	/**
	 * remove course using course's name and section
	 * @param name name of the course
	 * @param section section section of the course
	 * @return whether the course can be removed
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			if (c.getName().equals(name) && c.getSection().equals(section)) {
				catalog.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * get Course from catalog
	 * @param name name name of the course
	 * @param section section of the course
	 * @return the required Course
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				return catalog.get(i);
			}
		}
		return null;
	}
	
	
	/**
	 * get the information about all the course in the catalog
	 * @return 2D String of course information
	 */
	public String[][] getCourseCatalog() {
		String[][] cata = new String[catalog.size()][3];
		for (int i = 0; i < catalog.size(); i++) {
			cata[i] = catalog.get(i).getShortDisplayArray();
		}
		return cata;
	}
	
	/**
     * saves the catalog course records to the given file
     * @param fileName fileName for output
     * @throws IllegalArgumentException if the file can't be written to
     */
	public void saveCourseCatalog(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}

	}
	
	
}
