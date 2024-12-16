/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests for conflict exception messages
 */
class InvalidTransitionExceptionTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_scheduler.course.ConflictException#ConflictException(java.lang.String)}.
	 */
	@Test
	public void testInvalidTransitionExceptionString() {
		InvalidTransitionException ce = new InvalidTransitionException("Custom exception message");
	    assertEquals("Custom exception message", ce.getMessage());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_scheduler.course.ConflictException#ConflictException()}.
	 */
	@Test
	public void testInvalidTransitionException() {
		InvalidTransitionException ce = new InvalidTransitionException();
		assertEquals("Invalid FSM Transition.", ce.getMessage());
	}

}
