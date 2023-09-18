package edu.ncsu.csc216.wolf_scheduler.course;
/**
 * Activity class has 4 main fields of title, meeting days, start time and end time. It
 * creates an activity that links to the event class. This class also has many methods,
 * including getters, setters, and more.
 * 
 * @author David Mond
 */
public abstract class Activity {

	/** Activity's title. */
	private String title;
	/** Activity's meeting days */
	private String meetingDays;
	/** Activity's starting time */
	private int startTime;
	/** Activity's ending time */
	private int endTime;
	/**
	 * Creates an Activity with the given title, meetingDays, startTime, and endTime.
	 * @param title Represents activity's title
	 * @param meetingDays Represents activity's meeting days of the week.
	 * @param startTime Represents start time for the activity.
	 * @param endTime Represents end time for the activity.
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
        super();
        setTitle(title);
        setMeetingDaysAndTime(meetingDays, startTime, endTime);
    }
	
	

	/**
	 * Returns the Activity's title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Activity's title.
	 * 
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		// Throw exception if the title is null or empty
		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}
		this.title = title;
	}

	/**
	 * Returns the Activity's meeting days.
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the Activity's start time.
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the activity's end time.
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the meeting days and times with a start and end time.	 * 
	 * @param meetingDays the meeting days for the activities.
	 * @param startTime   the starting time for the activities.
	 * @param endTime     the ending time for the activities.
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		// Throw exception if the meetingDays is null or empty
		if (meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
	
		if ("A".equals(meetingDays)) { // Arranged
			// Throw exception if the start or end time is not 0 after being arranged
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			this.meetingDays = meetingDays;
			this.startTime = 0;
			this.endTime = 0;
		}
	
		else { // not arranged
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
				
				// If not m, t, w, h, f, throw an exception
				else {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
			}
			// If a day shows up more than once, throw an exception
			if (mCount > 1 || tCount > 1 || wCount > 1 || hCount > 1 || fCount > 1 || sCount > 1 || uCount > 1) { // checks for duplicates
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			// Convert military time to standard time
			int startHour = startTime / 100;
			int endHour = endTime / 100;
			int startMin = startTime % 100;
			int endMin = endTime % 100;
			final int upperHour = 23;
			final int upperMinute = 59;
	
			// Throw an exception if time out of range
			if (startHour < 0 || startHour > upperHour) { // not between 0 and 23, inclusive
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
	
			if (startMin < 0 || startMin > upperMinute) { // not between 0 and 59, inclusive
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
	
			if (endHour < 0 || endHour > upperHour) { // not between 0 and 23, inclusive
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
	
			if (endMin < 0 || endMin > upperMinute) { // not between 0 and 59, inclusive
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
	
			if (endTime < startTime) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			// everything is valid and works together!
			this.meetingDays = meetingDays;
			this.startTime = startTime;
			this.endTime = endTime;
		}
	}

	/**
	 * Converts military time to regular time and gets the meeting string of meeting
	 * days, start time, and end time.
	 * 
	 * @return the meeting string which consists of meeting days, start time, and
	 *         end time.
	 */
	public String getMeetingString() {
		// Use getter methods to get days and times
		String meet = getMeetingDays();
		// Convert military to standard
		int startHour = getStartTime() / 100;
		int startMin = getStartTime() % 100;
		int endHour = getEndTime() / 100;
		int endMin = getEndTime() % 100;
		boolean extraZeroStart = false;
		boolean extraZeroEnd = false;
		String res = "";
		String startTimer = "AM";
		String endTimer = "AM";
		// If military hour past or equal to 12, it is PM
		if (startHour >= 12) {
			if (startHour != 12) {
				startHour -= 12;
			}
			startTimer = "PM";
		}
		if (endHour >= 12) {
			if (endHour != 12) {
				endHour -= 12;
			}
			endTimer = "PM";
		}
		// If minute is single digit, add extra 0
		if (startMin < 10) {
			extraZeroStart = true;
		}
	
		if (endMin < 10) {
			extraZeroEnd = true;
		}
		// If arranged, return arranged
		if ("A".equals(meet)) {
			return "Arranged";
		}
		// Check if it is midnight
		if (startHour == 0) {
			startHour = 12;
			startTimer = "AM";
		}
		if (endHour == 0) {
			endHour = 12;
			endTimer = "AM";
		}
		// Check to see if the starting or ending times have a leading 0, adjust res
		// accordingly
		if (extraZeroStart || extraZeroEnd) {
			if (extraZeroEnd && extraZeroStart) {
				res += meet + " " + startHour + ":" + "0" + startMin + startTimer + "-" + endHour + ":" + "0" + endMin
						+ endTimer;
			} else if (extraZeroStart) {
				res += meet + " " + startHour + ":" + "0" + startMin + startTimer + "-" + endHour + ":" + endMin
						+ endTimer;
			} else {
				res += meet + " " + startHour + ":" + startMin + startTimer + "-" + endHour + ":" + "0" + endMin
						+ endTimer;
			}
		} else {
			res += meet + " " + startHour + ":" + startMin + startTimer + "-" + endHour + ":" + endMin + endTimer;
		}
		// Return the string result
		return res;
	}
	/** Returns the hash code of whatever is being called with this method.
	 * @return int Returns the hash code as an integer.
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
	 * Checks to see if two objects are equal to each other
	 * @return Returns true if they are equal, false if they are not equal.
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
	
	/**
	 * Provide a short version of the array of information to provide to the GUI.
	 * @return 2d String array that gets the shorter display.
	 */
	public abstract String[] getShortDisplayArray();
	/**
	 * Provide a long version of the array of information to provide to the GUI.
	 * @return 2d String array that gets the longer display.
	 */
	public abstract String[] getLongDisplayArray();
	/**
	 * Checks to see if the activity is a duplicate.
	 * @param activity Type of activity.
	 * @return True if it is a duplicate, false if not.
	 */
	public abstract boolean isDuplicate(Activity activity);
	
	

}