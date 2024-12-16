package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;

/**
 * CourseRoll class that helps a student enroll, drop and checks that ensure that a student can enroll in a class
 * This class uses various methods to ensure that the student is added to the class or to the waitlist and the order
 * in which the student will be added from the waitlist.
 * 
 * @author Siddhant Joshi
 * @author Arnav Ganguly
 * @author Shaurya Deepak
 */

public class CourseRoll {
    /** List of students enrolled in the course */
    private LinkedAbstractList<Student> roll;
    /** The enrollment capacity for the course */
    private int enrollmentCap;
    /** The minimum enrollment capacity allowed for a course */
    private static final int MIN_ENROLLMENT = 10;
    /** The maximum enrollment capacity allowed for a course */
    private static final int MAX_ENROLLMENT = 250;
    /** The waitlist is the list of student that want to enroll but there's no open seats in the course */
    private LinkedQueue<Student> waitlist;
    /** this is the course instance for this course roll */
    private Course course;

    /**
     * Constructs a CourseRoll with the given enrollment capacity.
     *
     * @param enrollmentCap the maximum number of students allowed to enroll in the course
     * @param course is the course this course roll is associated with
     * @throws IllegalArgumentException if enrollmentCap is less than MIN_ENROLLMENT or greater than MAX_ENROLLMENT
     */
    public CourseRoll(int enrollmentCap, Course course) {
    	if (course == null) {
    		throw new IllegalArgumentException();
    	}
    	this.course = course;
        setEnrollmentCap(enrollmentCap);
        this.waitlist = new LinkedQueue<Student>(10);
        this.roll = new LinkedAbstractList<Student>(this.enrollmentCap);
    }

    /**
     * Sets the enrollment capacity for the course.
     *
     * @param enrollmentCap the maximum number of students allowed to enroll in the course
     * @throws IllegalArgumentException if enrollmentCap is not within the valid range
     */
    public void setEnrollmentCap(int enrollmentCap) {
        if (enrollmentCap < MIN_ENROLLMENT || enrollmentCap > MAX_ENROLLMENT) {
            throw new IllegalArgumentException("Enrollment capacity must be between " + MIN_ENROLLMENT + " and " + MAX_ENROLLMENT);
        }

        if (roll == null) {
            this.enrollmentCap = enrollmentCap;
        } 
        else if (enrollmentCap >= roll.size()) {
        	roll.setCapacity(enrollmentCap); 
        	this.enrollmentCap = enrollmentCap; 
        }
        else {
            throw new IllegalArgumentException("Enrollment capacity cannot be set to a value that is less than the current number of enrolled students.");
        }
    }

    /**
     * Gets the maximum enrollment capacity for the course.
     *
     * @return the maximum number of students allowed to enroll in the course
     */
    public int getEnrollmentCap() {
        return this.enrollmentCap;
    }

    /**
     * Gets the number of open seats left in the course.
     *
     * @return the number of open seats for the course
     */
    public int getOpenSeats() {
        return this.enrollmentCap - roll.size();
    }

    /**
     * This method enrolls a student in the course or in the waitlist. If the class is full (there are no open seats), then
     * the student is added to the waitlist. 
     *
     * @param s the student to be enrolled
     * @throws IllegalArgumentException if the student cannot be enrolled (e.g., no open seats in class or waitlist or already enrolled)
     */
    public void enroll(Student s) {
        if (!canEnroll(s)) {
        	throw new IllegalArgumentException("Unable to enroll the student in the course.");
        }
        else if(this.getOpenSeats() == 0) {
            waitlist.enqueue(s);
        }
        else {
        	roll.add(s);
        	
        }
        
    }

    /**
     * This method drops a student from the course. If the student is in the roll (already enrolled in class)
     * the student is dropped from the course and the first student in the waitlist is added to the roll. If
     * the student is in the waitlist, the student is simply removed and the order of the waitlist is maintained
     *
     * @param s the student to be dropped from the course
     * @throws IllegalArgumentException if the provided student is null
     */
    public void drop(Student s) {
        if (s == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        else {
        	for (int i = 0; i < roll.size(); i++) {
        		if (s.equals(roll.get(i))) {
        			roll.remove(s);
        			if (!waitlist.isEmpty()) {
        				Student newStudent = waitlist.dequeue();
        				roll.add(newStudent);
        				newStudent.getSchedule().addCourseToSchedule(course);
        			}
        			
        		}
        	}
        	if (waitlist.contains(s)) {
        		LinkedQueue<Student> tempWaitList = new LinkedQueue<Student>(10);
            	while (!waitlist.isEmpty()) {
            		Student removedStudent = waitlist.dequeue();
            		if (!s.equals(removedStudent)) {
            			tempWaitList.enqueue(removedStudent);
            		}
            	}
            	this.waitlist = tempWaitList;
        	}
        }
    }

    /**
     * Checks if a student can enroll in the course.
     *
     * @param s the student trying to enroll
     * @return true if the student is allowed to enroll
     * @throws IllegalArgumentException if the provided student is null
     */
    public boolean canEnroll(Student s) {
        if (s == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        else if (getOpenSeats() == 0 && waitlist.size() == 10) {
            return false;
        }
        else if (waitlist.contains(s)) {
        	return false;
        }
        for (int i = 0; i < roll.size(); i++) {
    		if (s.equals(roll.get(i))) {
    			return false;
    		}
    	}
        return true;
    }
    
    /**
     * This is the method that returns the number of students on the wait list
     * @return the number of students on the wait list
     */
    public int getNumberOnWaitlist() {
    	return waitlist.size();
    }
}

