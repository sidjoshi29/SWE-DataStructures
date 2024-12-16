/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * For conflicting activities, this is a custom class
 * 
 * @author Siddhant Joshi
 */
public class InvalidTransitionException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for Exception. This is a custom exception for when there
	 * are two activities occurring at conflicting times.
	 * @param str custom exception message
	 */
	public InvalidTransitionException (String str) {
		super(str);
	}
	
	
	/**
	 * Constructor for Exception. This is a custom exception for when there
	 * are two activities occurring at conflicting times.
	 * 
	 */
	public InvalidTransitionException () {
		super("Invalid FSM Transition.");
	}
}

