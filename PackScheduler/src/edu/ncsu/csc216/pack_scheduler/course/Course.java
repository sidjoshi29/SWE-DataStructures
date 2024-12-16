package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Course class for PackScheduler. It stores information 
 * such as the course name, title, section, credit hours, instructor ID, meeting days, and 
 * meeting times.
 *
 * @author Arnav Ganguly
 * @author Shaurya Deepak
 * @author Siddhant Joshi
 */
public class Course extends Activity implements Comparable<Course> {
	
	/**Minimum Length of name*/
	public static final int MIN_NAME_LENGTH  = 4;
	
	/**Maximum name length*/
	public static final int MAX_NAME_LENGTH  = 8;
	
	/**Minimum letter count*/
	public static final int MIN_LETTER_COUNT = 1;
	
	/**Maximum letter count*/
	public static final int MAX_LETTER_COUNT = 1;
	
	/**Digit count*/
	public static final int DIGIT_COUNT = 3;
	
	/**Section length*/
	public static final int SECTION_LENGTH = 3;
	
	/**Maximum credits*/
	public static final int MAX_CREDITS = 5;
	
	/**Minimum credits*/
	public static final int MIN_CREDITS = 1;
	
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/**
	 * Instance of CourseNameValidator Class	
	 */
	private CourseNameValidator courseNameValidator;
	/**
	 * Course roll
	 */
	CourseRoll roll;
	/**
	 * Constructs a Course object with values for all fields.
	 * @param name name of Course
	 * @param title Title of course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param enrollmentCap is the enrollment cap for this course
	 * @param meetingDays meeting Days for the course
	 * @param startTime start time of course
	 * @param endTime end time of course
	 */
    public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays,
            int startTime, int endTime) {
        super(title, meetingDays, startTime, endTime);
        courseNameValidator = new CourseNameValidator();
        setName(name);
        //setTitle(title);  <-- DELETE THIS, called in super
        setSection(section);
        setCredits(credits);
        setInstructorId(instructorId);
        this.roll = new CourseRoll(enrollmentCap, this);
        //setMeetingDaysAndTime(meetingDays, startTime, endTime); <-- DELETE THIS, called in super
    }
	/**
	 * Creates a Course with the given name, title, section, credits, instructorId, and meetingDays for 
	 * courses that are arranged.
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param enrollmentCap is the enrollment cap for this course
	 * @param meetingDays meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays) {
	    this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
	}
	
	
	
	
	
	/**
	 * Returns the Course's name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the Course's name.  If the name is null, has a length less than 5 or more than 8,
	 * does not contain a space between letter characters and number characters, has less than 1
	 * or more than 4 letter characters, and not exactly three trailing digit characters, an
	 * IllegalArgumentException is thrown.
	 * @param name the name to set
	 * @throws IllegalArgumentException if the name parameter is invalid
	 */
	
	private void setName(String name) {
//		if(name == null) {
//			/*
//			 * @throw exception if name is null
//			 */
//			throw new IllegalArgumentException("Invalid course name.");
//		}
//		
//		if(name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
//			
//			/*
//			 * @throw exception if the name is an empty string
//			 * @throw exception if the name contains less than 5 character or greater than 8 characters
//			 */
//			throw new IllegalArgumentException("Invalid course name.");
//		}
//		
//		try {
//            if (courseNameValidator.isValid(name)) {
//                this.name = name;
//            }
//        } catch (InvalidTransitionException e) {
//            throw new IllegalArgumentException("Invalid course name.", e);
//        }
//		
//		int numOfLetters = 0;
//		int numOfDigits = 0;
//		boolean flagSpace = false;
//		
//		
//		 //Check for pattern of L[LLL] NNN
//		for(int i = 0; i < name.length(); i++ ) {
//			
//			char c = name.charAt(i);
//			
//			if(!flagSpace) {
//				if(Character.isLetter(c)) {
//					numOfLetters++;
//				}
//				else if (c == ' ') {
//					flagSpace = true;
//				}
//				else {
//					throw new IllegalArgumentException("Invalid course name.");
//				}
//			}
//			else if(flagSpace) {
//				if(Character.isDigit(c)){
//					numOfDigits++;
//				}
//				else {
//					throw new IllegalArgumentException("Invalid course name.");
//				}
//			}
		
//		}
//		
//		//Check that the number of letters is correct
//		if (numOfLetters < 1 || numOfLetters > 4 ) {
//			throw new IllegalArgumentException("Invalid course name.");
//		}
//		
//		//Check that the number of digits is correct
//		if(numOfDigits != 3) {
//			throw new IllegalArgumentException("Invalid course name.");
//		}
//		
//		this.name = name;
		if (name == null) {
	        throw new IllegalArgumentException("Invalid course name.");
	    }

	    if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
	        throw new IllegalArgumentException("Invalid course name."); 
	    }
	    
	    try {
	        if (!courseNameValidator.isValid(name)) {
	            throw new IllegalArgumentException("Invalid course name.");
	        }
	    } catch (InvalidTransitionException e) {
	        throw new IllegalArgumentException("Invalid course name.", e);
	    }
	    		
	    this.name = name;
	}
	
	/**
	 * Returns the Course's Section
	 * @return the section
	 */
	public String getSection() {
		return section;
	}
	
	/**
	 * Sets the Course's section.
	 * @param section the section to set
	 * @throws IllegalArgumentException if section is invalid
	 */
	public void setSection(String section) {
		
		if(section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}
		
		for(int i = 0; i < section.length(); i++) {
			if(!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException("Invalid section.");
			}
		}
		
		this.section = section;
	}
	
	/**
	 * Returns the Course's credits.
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}
	
	/**
	 * Sets the Course's credits.
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if the credits are invalid
	 */
	public void setCredits(int credits) {
        if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
            throw new IllegalArgumentException("Invalid credits.");
        }

        this.credits = credits;
    }
	
	
	
	/**
	 * Returns the Course's Instructor ID.
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}
	
	/**
	 * Sets the Course's Instructor ID.
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if instructor id is invalid
	 */
	public void setInstructorId(String instructorId) {
		if("".equals(instructorId)) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		this.instructorId = instructorId;
	}
	
	
	
	/**
	 * Sets the Course's meeting days and Time.
	 *  @param meetingDays the meetingDays to set
	 *  @param startTime the startTime to set
	 *  @param endTime the endTime to set
	 *  @throws IllegalArgumentException if meeting days and time is invalid
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		
		if ("A".equals(meetingDays)) {
		    if (startTime != 0 || endTime != 0) {
		        throw new IllegalArgumentException("Invalid meeting days and times.");
		    }
		} 
		
		else {
		    int monCount = 0;
		    int tueCount = 0;
		    int wedCount = 0;
		    int thuCount = 0;
		    int friCount = 0;

		    for (int i = 0; i < meetingDays.length(); i++) {
		        char day = meetingDays.charAt(i);
		        switch (day) {
		            case 'M':
		            	monCount++;
		                break;
		            case 'T':
		            	tueCount++;
		                break;
		            case 'W':
		            	wedCount++;
		                break;
		            case 'H':
		                thuCount++;
		                break;
		            case 'F':
		                friCount++;
		                break;
		            default:
		                throw new IllegalArgumentException("Invalid meeting days and times.");
		        }
		    }
		    if (monCount > 1 || tueCount > 1 || wedCount > 1 || thuCount > 1 || friCount > 1) {
		    	throw new IllegalArgumentException("Invalid meeting days and times.");
		    }
		}
		
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		
		
	}
	
	
	/**
	 *  The short display array is used to populate the rows of the course catalog and 
	 *  student schedule.
	 *  
	 *  Returns an array of length 4 containing the Course name, section, title, and meeting string.
	 * 
	 *  It is an overridden implementation
	 *  
	 *  @return shortDisplayArr an array which stores the 4 course objects mentioned above
	 *  
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplayArr = new String[5];
		shortDisplayArr[0] = this.getName();
		shortDisplayArr[1] = this.getSection();
		shortDisplayArr[2] = this.getTitle();
		shortDisplayArr[3] = this.getMeetingString();
		shortDisplayArr[4] = "" + this.roll.getOpenSeats();
		
		
		return shortDisplayArr;
	}
	

	/**
	 * The full display array is used to display the final schedule
	 * 
	 * Returns an array of length 7 containing the Course name, section, title, 
	 * credits, instructorId, meeting string, empty string
	 * 
	 * 
	 * It is an overridden implementation
	 * 
	 * 
	 * @return longDisplayArr an array which stores the 7 course objects mentioned above
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplayArr = new String[7];
		longDisplayArr[0] = this.getName();
		longDisplayArr[1] = this.getSection();
		longDisplayArr[2] = this.getTitle();
		longDisplayArr[3] = String.valueOf(getCredits());
		longDisplayArr[4] = this.getInstructorId();
		longDisplayArr[5] = this.getMeetingString();
		longDisplayArr[6] = "";
		
		
		return longDisplayArr;
	}
	
	/**
	 * Overriding the method from activity class.
	 * Abstract method header to make sure this method is in both Course and Event
	 * @param activity the activity to compare against for duplication 
	 * @return if the activity is a duplicate or not
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		return activity instanceof Course && name.equals(((Course) activity).getName());
	}
	
	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 * Overrides toString method
	 */
	@Override
	public String toString() {
	    if ("A".equals(getMeetingDays())){
	        return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() + "," + getMeetingDays();
	    }
	    return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime(); 
	}
	
	/**
	 * Generates a hashCode for Course using all fields.
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}
	
	/**
	 * Compares a given object to this object for equality on all fields.
	 * @param obj the object to compare
	 * @return true if objects are the same on all fields
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}
	
	/**
	 * Overrides compareTo() in Comparable to compare two Courses
	 * Will compare two courses by name and section to see if this course is greater than, less than, or
	 * equal to the other course
	 * Note: This class has a natural ordering that is inconsistent with equals, two course are considered "equal"
	 *        by compareTo() if they have the same name and section.
	 * @param c the Course to compare to
	 * @return a negative one if this Course is less than the c, a positive one if this course is greater than c, or
	 *         zero if this course and c are equal
	 * @throws NullPointerException if this Course or c is null
	 * @throws ClassCastException if this Course and c are not the same Object type and can't be compared
	 */
	@Override
	public int compareTo(Course c) {
		// Check if either Course Object is null
		if (c == null || this == null) {
			throw new NullPointerException();
		}
		
		// Check if the Object s is an instance of Course and can be compared
		if (c.getClass() != this.getClass()) {
			throw new ClassCastException();
				
		}
		
		int comparison = 0;
		
		String courseName = this.getName();
		String courseSection = this.getSection();
		
		String otherCourseName = c.getName();
		String otherCourseSection = c.getSection();
		
		
		int loopSize = 0;
		
		// Compare the names
		if (!otherCourseName.equals(courseName)) {
			// Check which name is longer
			if (courseName.length() > otherCourseName.length()) {
				loopSize = otherCourseName.length();
			} else {
				loopSize = courseName.length();
			}
			
			// Iterate through the characters in each name
			for (int i = 0; i < loopSize; i++) {
				if (courseName.charAt(i) > otherCourseName.charAt(i)) {
					comparison = 1;
					break;
				} else if (otherCourseName.charAt(i) > courseName .charAt(i)) {
					comparison = -1;
					break;
				}
			}
			
		// If the names are the same, compare the sections
		} else if (!otherCourseSection.equals(courseSection)) {
			// Check which section is longer
			if (courseSection.length() > otherCourseSection.length()) {
				loopSize = otherCourseSection.length();
			} else {
				loopSize = courseSection.length();
			}
			
			// Iterate through the characters in each section
			for (int i = 0; i < loopSize; i++) {
				if (courseSection.charAt(i) > otherCourseSection.charAt(i)) {
					comparison = 1;
					break;
				} else if (otherCourseSection.charAt(i) > courseSection .charAt(i)) {
					comparison = -1;
					break;
				}
			}
			
		}
			
		
		// If both names and section are the same, the two objects are considered "equal"
		return comparison;
	}
	
	/**
	 * Returns the courseRoll object for this Course
	 * @return the courseRoll object
	 */
	public CourseRoll getCourseRoll() {
		return this.roll;
	}
	
	
	

	
	

}
