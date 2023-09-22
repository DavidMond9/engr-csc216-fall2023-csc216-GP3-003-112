/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the Activity Class for conflicts.
 */
class ActivityTest {

	/**
	 * Tests for possible conflicts between two activities.
	 */
	@Test
	public void testCheckConflictWithConflict() {
	    Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
	    Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1330, 1445);
	    Activity a3 = new Course("CSC 216", "Software Development Fundamentals", "002", 3, "ixdoming", "MWF", 1445, 1600);
		Activity a4 = new Course("CSC 316", "Data Structures and Algorithms", "001", 3, "sesmith5", "TH", 1400, 1515);
		Activity a5 = new Course("CSC 226", "Discrete Mathematics for Computer Scientists", "001", 3, "sesmith5", "THF", 1515, 1605);
		
	    Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
	    assertEquals("Schedule conflict.", e1.getMessage());
		
	    Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
	    assertEquals("Schedule conflict.", e2.getMessage());
	    
	    Exception e3 = assertThrows(ConflictException.class, () -> a1.checkConflict(a3));
	    assertEquals("Schedule conflict.", e3.getMessage());
	    
	    Exception e4 = assertThrows(ConflictException.class, () -> a3.checkConflict(a5));
	    assertEquals("Schedule conflict.", e4.getMessage());
	    
	    Exception e5 = assertThrows(ConflictException.class, () -> a4.checkConflict(a5));
	    assertEquals("Schedule conflict.", e5.getMessage());
	}

}
