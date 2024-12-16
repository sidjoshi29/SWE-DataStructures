/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests for the Activity class to check for conflicts between activities.
 */
class ActivityTest {

	/**
	 * Tests conflicts on different days
	 * tests Activity.checkConflict()
	 *
	 * Test method for {@link edu.ncsu.csc216.wolf_scheduler.course.Activity#checkConflict(edu.ncsu.csc216.wolf_scheduler.course.Activity)}.
	 */
	@Test
	public void testCheckConflict() {
	    Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 15, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 15, "TH", 1330, 1445);
	    
	    assertDoesNotThrow(() -> a1.checkConflict(a2));
	    assertDoesNotThrow(() -> a2.checkConflict(a1));
	}
	
	/**
	 * Tests conflicts on the same day with different times.
	 */
	@Test
    public void testCheckConflictSameDayDifferentTimes() {
        // Create two activities on the same day but different times
        Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 15, "MW", 900, 1000);
        Activity a2 = new Course("CSC216", "Software Development Fundamentals", "002", 3, "sjoshi29", 15, "MW", 1030, 1130);

        // Check for conflict, they should not conflict
        assertDoesNotThrow(() -> a1.checkConflict(a2));
        assertDoesNotThrow(() -> a2.checkConflict(a1));
    }

	/**
	 * Tests conflicts on the same day with different times.
	 */
    @Test
    public void testCheckConflictDifferentDays() {
        // Create two activities on different days
        Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 15, "MW", 900, 1000);
        Activity a2 = new Course("MA242", "Calculus 3", "002", 3, "johndoe", 15, "TH", 900, 1000);

        // Check for conflict, they should not conflict
        assertDoesNotThrow(() -> a1.checkConflict(a2));
        assertDoesNotThrow(() -> a2.checkConflict(a1));
    }
    
    /**
     * Tests conflict for courses with conflict in it.
     */
    @Test
    public void testCheckConflictWithConflict() {
        Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 15, "MW", 1330, 1445);
        Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 15, "M", 1330, 1445);
    	
        Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
        assertEquals("Schedule conflict.", e1.getMessage());
    	
        Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
        assertEquals("Schedule conflict.", e2.getMessage());
    }
	
}	
