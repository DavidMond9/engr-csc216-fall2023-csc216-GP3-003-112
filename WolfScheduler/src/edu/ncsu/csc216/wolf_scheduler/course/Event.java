/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Event class has many methods, and one field for the event details. It extends the class
 * Activity as well and has one super constructor which initializes the title, startTime,
 * endTime, and the eventDetails.
 * @author David Mond
 */
public class Event extends Activity {
	/** Event details for activity. */
	private String eventDetails;
	

	/** Constructor to create the event with a title, start time, end time, meeting days, and
	 * event details.
	 * @param title title for the event.
	 * @param meetingDays days that the event will meet.
	 * @param startTime time that the event will start.
	 * @param endTime time that the event will end.
	 * @param eventDetails details of the event.
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setEventDetails(eventDetails);
	}

	/** Gets the event details.
	 * @return the eventDetails
	 */
	public String getEventDetails() {
		return eventDetails;
	}

	/** Sets the event details to the parameter.
	 * @param eventDetails the eventDetails to set
	 */
	public void setEventDetails(String eventDetails) {
		if(eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details.");
		}
		this.eventDetails = eventDetails;
	}
	/**
	 * Gets the short display array of size 4.
	 * @return 2D String array that has the short display.
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] arr = new String[4];
		arr[0] = "";
		arr[1] = "";
		arr[2] = getTitle();
		arr[3] = getMeetingString();
		return arr;
	}
	/**
	 * Gets the long display array of size 7.
	 * @return 2D String array that has the long display.
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] arr = new String[7];
		arr[0] = "";
		arr[1] = "";
		arr[2] = getTitle();
		arr[3] = "";
		arr[4] = "";
		arr[5] = getMeetingString();
		arr[6] = getEventDetails();
		return arr;
	}
	
	/**
	 * Converts the required fields into a string with commas between.
	 * @return String with fields and commas.
	 */
	@Override
	public String toString() {
		
		return getTitle() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime()
		+ "," + getEventDetails();
	}
	
	/** Sets the meeting days and time from the activity class
	 * @param meetingDays represents the meeting days
	 * @param startTime represents the start time
	 * @param endTime represents the end time
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		// Throw exception if the meetingDays is null or empty
			if (meetingDays == null || "".equals(meetingDays)) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			int mCount = 0;
			int tCount = 0;
			int wCount = 0;
			int hCount = 0;
			int fCount = 0;
			int sCount = 0;
			int uCount = 0;
			for (int i = 0; i < meetingDays.length(); i++) {
				// Count each day
				if (meetingDays.charAt(i) == 'M') {
					mCount++;
				} else if (meetingDays.charAt(i) == 'T') {
					tCount++;
				} else if (meetingDays.charAt(i) == 'W') {
					wCount++;
				} else if (meetingDays.charAt(i) == 'H') {
					hCount++;
				} else if (meetingDays.charAt(i) == 'F') {
					fCount++;
				} else if (meetingDays.charAt(i) == 'S') {
					sCount++;
				} else if (meetingDays.charAt(i) == 'U') {
					uCount++;
				}
				// If not m, t, w, h, f, s, u throw an exception
				else {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
			}
			// If a day shows up more than once, throw an exception
			if (mCount > 1 || tCount > 1 || wCount > 1 || hCount > 1 || fCount > 1 || sCount > 1 || uCount > 1) { // checks for duplicates
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Checks to see if the activity is already in the schedule.
	 * @param activity Represents the activity that is being checked for duplicates.
	 * @return boolean True if the activity exists already, false if not.
	 */ 
	@Override
	public boolean isDuplicate(Activity activity) {
		boolean isInstance = activity instanceof Event;
		return getTitle().equals(((Event) activity).getTitle()) && isInstance;	
	}
	

}
