	/**

 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * Schedule class
 * @author Siddhant Joshi
 * @author Arnav Gnaguly
 * @author Shaurya Deepak
 */
public class Schedule {

	/** schedule custom ArrayList */
	ArrayList<Course> schedule;
	/** Schedule's title*/
	String title;
	
	/**
	 * Constructor for schedule
	 */
	public Schedule() {
		this.schedule = new ArrayList<Course>();
		this.title = "My Schedule";
	}
	
	
	/**
	 * Creates a 2D array of courses in schedule
	 * @return display of courses in schedule or empty array
	 */
	public String[][] getScheduledCourses() {
		if (schedule.size() == 0){
			String[][] empty = new String [0][0];
			return empty;
		}
		int row = schedule.size();
		int col = 4;
		
		String[][] display = new String [row][col];
		for(int i = 0; i < row; i++){
			display[i] = schedule.get(i).getShortDisplayArray();
		}
		return display;
	}
	

	/**
	 * Adds a course into the schedule only if it is not a duplicate course and has no schedule conflicts.
	 *
	 * @param course the course to be added to the schedule
	 * @return true if the course has been added to the schedule, false otherwise
	 * @throws NullPointerException     if the provided course is null
	 * @throws IllegalArgumentException if the course has a conflict with an existing course in the schedule,
	 *                                  or if an attempt is made to add a duplicate course
	 */
	public boolean addCourseToSchedule(Course course) {
		if(course == null) {
			throw new NullPointerException("Course to add is null.");
		}
		for(int i = 0; i < schedule.size(); i++){
			try{
				course.checkConflict(schedule.get(i));
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
			if(course.getName().equals(schedule.get(i).getName())){
				throw new IllegalArgumentException("You are already enrolled in " + course.getName());
			}
		} 
		schedule.add(schedule.size(), course);
		return true;
	}
	
	/**
	 * Removes course from schedule
	 * @param course the Course to remove from the Schedule
	 * @return true if it can be removed, false if otherwise
	 */
	public boolean removeCourseFromSchedule(Course course) {
		if (course == null) {
			return false;
		}
		for (int i = 0; i < schedule.size(); i++) {
			Course c = schedule.get(i);
			if (c.getName().equals(course.getName()) && c.getSection().equals(course.getSection())) {
				schedule.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Resets the schedule
	 */
	public void resetSchedule() {
		this.schedule = new ArrayList<Course>();
		this.title = "My Schedule";
		
	}
	
	
	/**
	 * gets the title
	 * @return title of the schedule
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * Calculates the total number of credits
	 * @return total credits
	 */
	public int getScheduleCredits() {
	    int tCredits = 0;
	    for (int i = 0; i < schedule.size(); i++) {
	        tCredits += schedule.get(i).getCredits();
	    }
	    return tCredits;
	}
	
	
	/**
	 * Sets the title
	 * @param newTitle sets new title name
	 * @throws IllegalArgumentException if title is null
	 */
	public void setTitle(String newTitle) {
		if(newTitle == null) {
			throw new IllegalArgumentException("Title cannot be null");
		}
		this.title = newTitle;
	}
	
	/**
	 * Determines if a Course can be added.
	 * @param course the Course to be added
	 * @return true if the Course can be added, false otherwise
	 */
	public boolean canAdd(Course course) {
	    if (course == null) {
	        return false;
	    }
	    for (int i = 0; i < schedule.size(); i++) {
	        Course current = schedule.get(i);
	        if (current.isDuplicate(course)) {
	            return false;
	        }
	        try {
	            course.checkConflict(current);
	        } catch (ConflictException e) {
	            return false;
	        }
	    }
	    
	    return true;
	}
}
