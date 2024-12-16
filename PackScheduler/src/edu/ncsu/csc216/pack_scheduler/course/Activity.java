package edu.ncsu.csc216.pack_scheduler.course;


/**
 * Abstract class that contains code for both Course and Event Classes
 * for easier readability and usage of larger code.
 * Implements Conflict and Exception too.
 * @author Chloe Israel
 * @author Dolly Jani
 * @author Siddhant Joshi
 * 
 */
public abstract class Activity implements Conflict { 

	/**Upper Hour time limit*/
	private static final int UPPER_HOUR = 24;
	/**Upper Minute time limit*/
	private static final int UPPER_MINUTE = 60;
	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;

	
	/**Abstract Activity constructor
	 * @param title title of the activity
	 * @param meetingDays meeting days for the activity
	 * @param startTime starting time for the activity
	 * @param endTime ending time for the activity
	 */
    public Activity(String title, String meetingDays, int startTime, int endTime) {
        super();
        setTitle(title);
        setMeetingDaysAndTime(meetingDays, startTime, endTime);
    }

	/**
	 * Returns the Course's Title
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Course's title.
	 * @param title the title to set
	 * @throws IllegalArgumentException if the string provided is null or empty
	 */
	public void setTitle(String title) {
		
		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}
		
		if (title == null || title.length() == 0) {
			throw new IllegalArgumentException("Invalid title.");
		}
		
		this.title = title;
	}

	/**
	 * Returns the Course's meeting days.
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets the meeting days and Time.
	 * @param meetingDays the meetingDays to set
	 *  @param startTime the startTime to set
	 *  @param endTime the endTime to set
	 *  @throws IllegalArgumentException for invalid days and times
	 */
	
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
	
		
		if(meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
	
		
		int startHours = startTime / 100;
		int endHours = endTime / 100;
		int startMin = startTime % 100;
		int endMin = endTime % 100;
		
		if(startTime > endTime) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		if(startHours < 0 || startHours >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		if(endHours < 0 || endHours >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		if(startMin < 0 || startMin >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		if(endMin < 0 || endMin >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		this.startTime = startTime;
		this.endTime = endTime;
		this.meetingDays = meetingDays;
		
		
	}

	

	/**Returns a string with the days and times a course takes place.
	 * Converts the military times into 12-hour, adds "am" and "pm".
	 * @return String A string describing the days a course meets
	 */
	public String getMeetingString() {
		String meetDays = getMeetingDays();
		if ("A".equals(meetDays)) {
			return "Arranged";
		}
	
		int timeOfStart = getStartTime();
		int startHours = timeOfStart / 100;
		int startMinutes = timeOfStart % 100;
		String startTimeNew = "";
	
		if (startHours < 12) {
			startTimeNew = startHours + ":" + String.format("%02d", startMinutes) + "AM";
		}
		if (startHours == 12) {
			startTimeNew = startHours + ":" + String.format("%02d", startMinutes) + "PM";
		}
		if (startHours > 12) {
			//convert to standard time
			startHours = startHours - 12;
			startTimeNew = startHours + ":" + String.format("%02d", startMinutes) + "PM";
		}
	
		int timeOfEnd = getEndTime();
		int endHours = timeOfEnd / 100;
		int endMinutes = timeOfEnd % 100;
		String endTimeNew = "";
	
		if (endHours < 12) {
			endTimeNew = endHours + ":" + String.format("%02d", endMinutes) + "AM";
		}
		if (endHours == 12) {
			endTimeNew = endHours + ":" + String.format("%02d", endMinutes) + "PM";
		}
		if (endHours > 12) {
			//convert to standard time
			endHours = endHours - 12;
			endTimeNew = endHours + ":" + String.format("%02d", endMinutes) + "PM";
		}
	
		return meetDays + " " + startTimeNew + "-" + endTimeNew;
	}

	/**
	 * Returns the Course's start time.
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Course's end time.
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	
	/** 
	 *  Returns an array of length 4 containing the Course name, section, 
	 *  title, and meeting string.
	 *  
	 *  @return shortDisplayArr[]
	 */
	public abstract String[] getShortDisplayArray();
	
	
	/**
	 * Returns an array of length 7 containing the Course name, section, title, credits, 
	 * instructorId, meeting string, empty string
	 * 
	 * @return longDistanceArr[]
	 */
	public abstract String[] getLongDisplayArray();
	
	
	/**
	 * Abstract method header to make sure this method is in both Course and Event
	 * @param activity the activity to compare against for duplication 
	 * @return if the activity is a duplicate or not
	 */
	public abstract boolean isDuplicate(Activity activity);
	
	/**
	 * Checks conflicts in activity. Overrides method from Conflict interface.
	 * @param possibleConflictingActivity is an activity.
	 * @throws ConflictException if there is any conflicts in activities.
	 */

	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		
		// Get first Activity information
		String a1MeetingDays = this.getMeetingDays();
		int a1StartTime = this.getStartTime();
		int a1EndTime = this.getEndTime();
		
		// Get second activity information
		Activity a2 = possibleConflictingActivity;
		String a2MeetingDays = a2.getMeetingDays();
		int a2StartTime = a2.getStartTime();
		int a2EndTime = a2.getEndTime();
		
		
		// Check for overlapping meeting days
		for (int i = 0; i < a1MeetingDays.length(); i++) {
			for (int j = 0; j < a2MeetingDays.length(); j++) {
				if ('A' == a1MeetingDays.charAt(i) || 'A' == a2MeetingDays.charAt(j)) {  // If Courses are arranged, overlap doesn't matter
					break;
				} else if (a1MeetingDays.charAt(i) == a2MeetingDays.charAt(j)) {
					
					// Check for overlapping times if there are overlapping days
					if (a1StartTime == a2StartTime || a1StartTime == a2EndTime) {  // Overlapping start/end times
						throw new ConflictException();
					} else if (a1EndTime == a2StartTime || a1EndTime == a2EndTime) {  // Overlapping start/end times
						throw new ConflictException();	
					} else if (a2StartTime > a1StartTime && a2StartTime < a1EndTime) {  // Start time in another Activity window
						throw new ConflictException();	
					} else if (a2EndTime > a1StartTime && a2EndTime < a1EndTime) {  // End time in another Activity window
						throw new ConflictException();	
					} else if (a1StartTime > a2StartTime && a1StartTime < a2EndTime) { // Start time in another Activity window
						throw new ConflictException();
					} else if (a1EndTime > a2StartTime && a1EndTime < a2EndTime) {  // End time in another Activity window
						throw new ConflictException();
					} 
					
				} // First if-else- if
				
			} // Second for loop

		} // First for loop
	}
	
	
	/**
	 * Generates a hashCode for Course using all fields.
	 * @return hashCode for Course 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	

}