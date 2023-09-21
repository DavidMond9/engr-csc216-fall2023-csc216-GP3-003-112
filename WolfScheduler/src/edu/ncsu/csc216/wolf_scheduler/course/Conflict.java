/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Conflict interface has many methods. It defines what a conflict is where a course or event
 * is overlapped by another course or event in the meeting days or times.
 */
public interface Conflict {
	/**
	 * checkConflict checks to see if there is a conflict between two activities.
	 * @param possibleConflictingActivity Activity that is being checked for a conflict.
	 * @throws ConflictException Throws an exception which takes you to the ConflictException class.
	 */ 
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
	
}