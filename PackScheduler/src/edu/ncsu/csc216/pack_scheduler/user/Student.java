package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/** Represents an individual Student record
 * 
 * Will handle the Student firstName, lastName, id, email, password, and max credits
 * 
 * Implements Comparable to compare two Students to correctly order them
 * 
 * @author Chloe Israel
 * @author Dolly Jani
 * @author Siddhant Joshi
 */
public class Student extends User implements Comparable<Student> {
	/** The maximum number of credits any student can have */
	public static final int MAX_CREDITS = 18;
	
	/** The student's maximum credits */
	private int maxCredits;

	/** created an instance of schedule */
	private Schedule schedule = new Schedule();
	
	
	/** 
	 * Constructor for Student class that will set all fields
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param id the id 
	 * @param email the email 
	 * @param hashPW the hashed password 
	 * @param maxCredits the maximum amount of credits
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW, int maxCredits) {
		super(firstName, lastName, id, email, hashPW);
		setMaxCredits(maxCredits);
		
		
	}
	
	/** 
	 * Constructor for Student class that sets all fields, but defaults max credits to 18
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param id the id 
	 * @param email the email
	 * @param hashPW the hashed password
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW) {
		this(firstName, lastName, id, email, hashPW, MAX_CREDITS);
	}

	/**
	 * Gets the student's maximum amount of credits
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets the students maximum amount of credits
	 * @param maxCredits the maxCredits to set
	 * @throws IllegalArgumentException if credits of less than 3 or greater than 18
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits < 3 || maxCredits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.maxCredits = maxCredits;
	}
	
	/**
	 * Overrides compareTo() in Comparable to compare two Students
	 * Will compare two students by last name, first name, and id to see if this Student is greater than, less than, or
	 * equal to the other Student
	 * Note: This class has a natural ordering that is inconsistent with equals, two Students are considered "equal"
	 *        by compareTo() if they have the same last name, first name, and id
	 * @param s the Student to compare to
	 * @return a negative one if this Student is less than the s, a positive one if this student is greater than s, or
	 *         zero if this Student and s are equal
	 * @throws NullPointerException if this Student or s is null
	 * @throws ClassCastException if this Student and s are not the same Object type and can't be compared
	 */
	@Override
	public int compareTo(Student s) {
		
		// Check if either Student Object is null
		if (s == null || this == null) {
			throw new NullPointerException();
		}
		
		// Check if the Object s is an instance of Student and can be compared
		if (s.getClass() != this.getClass()) {
			throw new ClassCastException();
				
		}
		
		int comparison = 0;
		
		String studentLastName = this.getLastName();
		String studentFirstName = this.getFirstName();
		String studentId = this.getId();
		
		String otherLastName = s.getLastName();
		String otherFirstName = s.getFirstName();
		String otherId = s.getId();
		
		int loopSize = 0;
		
		// Compare the last names
		if (!otherLastName.equals(studentLastName)) {
			// Check which last name is longer
			if (studentLastName.length() > otherLastName.length()) {
				loopSize = otherLastName.length();
			} else {
				loopSize = studentLastName.length();
			}
			
			// Iterate through the characters in each last name
			for (int i = 0; i < loopSize; i++) {
				if (studentLastName.charAt(i) > otherLastName.charAt(i)) {
					comparison = 1;
					break;
				} else if (otherLastName.charAt(i) > studentLastName .charAt(i)) {
					comparison = -1;
					break;
				}
			}
			
		// If the last names are the same, compare the first names
		} else if (!otherFirstName.equals(studentFirstName)) {
			// Check which first name is longer
			if (studentFirstName.length() > otherFirstName.length()) {
				loopSize = otherFirstName.length();
			} else {
				loopSize = studentFirstName.length();
			}
			
			// Iterate through the characters in each first name
			for (int i = 0; i < loopSize; i++) {
				if (studentFirstName.charAt(i) > otherFirstName.charAt(i)) {
					comparison = 1;
					break;
				} else if (otherFirstName.charAt(i) > studentFirstName .charAt(i)) {
					comparison = -1;
					break;
				}
			}
			
		// If the first and last names are the same, compare the ids	
		} else if (!otherId.equals(studentId)){
			// Check which id is longer
			if (studentId.length() > otherId.length()) {
				loopSize = otherId.length();
			} else {
				loopSize = studentId.length();
			}
			
			// Iterate through the characters in each id
			for (int i = 0; i < loopSize; i++) {
				if (studentId.charAt(i) > otherId.charAt(i)) {
					comparison = 1;
					break;
				} else if (otherId.charAt(i) > studentId .charAt(i)) {
					comparison = -1;
					break;
				}
			}
			
		}
		
		// If both last names, first names, and ids are the same, the two objects are considered "equal"
		return comparison;
	}
	
	
	
	/**
	 * This is a method to convert a unique hashcode for every student
	 * @return result which is the unique hashcode for the student
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}
	
	/**
	 * this is the method that compares two students and checks if they are equal
	 * @return boolean value for if the students are equal or not
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return!(maxCredits != other.maxCredits);
	}

	/**
	 * Returns a comma separated value string of all Student fields.
	 * @return String representation of Student
	 */
	@Override
	public String toString() {
		return super.getFirstName() + "," + super.getLastName() + "," + super.getId() + "," + super.getEmail() + "," + super.getPassword() + "," + maxCredits;
	}
	
	
	/**
	 * Gets the Students current schedule
	 * @return this Schedule
	 */
	public Schedule getSchedule() {
		return this.schedule;
	}
	
	/**
	 * Determines if the student can add the given course
	 * @param course the course to add
	 * @return true if the course can be added, false otherwise
	 */
	public boolean canAdd(Course course) {
		
		if (course == null) {
	        return false;
	    }
		if (!getSchedule().canAdd(course)) {
	        return false;
	    }
	    
	    int nowCredits = getSchedule().getScheduleCredits();
	    int courCredits = course.getCredits();
	    return nowCredits + courCredits <= getMaxCredits();
	}
	
	
	

}
