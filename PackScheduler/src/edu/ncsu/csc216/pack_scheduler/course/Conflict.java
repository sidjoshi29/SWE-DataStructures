/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Interface to ensure that the conflict methods are implemented
 * 
 * @author Siddhant Joshi
 */
public interface Conflict {


	/**Method header for checkConflict() for Activity classes
	 * @param possibleConflictingActivity The Activity to compare to for conflicting times
	 * @throws ConflictException if there is a conflict in activity
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

}
