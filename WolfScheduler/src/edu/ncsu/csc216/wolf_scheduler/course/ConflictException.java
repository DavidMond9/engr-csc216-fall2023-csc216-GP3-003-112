/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * ConflictException class which is an extension of Exception. This class defines the actual
 * exception of when the times or meeting days of events and courses align up. 
 * @author David Mond
 */
public class ConflictException extends Exception {
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ConflictException constructor with a message parameter.
	 * @param message Message for the conflict.
	 */
	public ConflictException(String message) {
		super(message);
	}
	/**
	 * ConflictException constructor with no parameters.
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}

}
