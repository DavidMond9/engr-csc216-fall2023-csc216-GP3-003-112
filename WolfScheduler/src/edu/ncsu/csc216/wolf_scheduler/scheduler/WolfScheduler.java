package edu.ncsu.csc216.wolf_scheduler.scheduler;	
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;
/**
 * Creating the WolfScheduler class, outline of the catalog and schedule. There are many methods
 * in this class that work with the catalog and schedule. There are three fields in this class,
 * the schedule and catalog which are ArrayLists of type Course, and then the title field which
 * is a string, and represents the schedule title.
 * @author David Mond
 */
public class WolfScheduler {
	/** Catalog for all the courses*/
	private ArrayList<Course> catalog = new ArrayList<Course>();
	/** Schedule to see which courses student is enrolled in */
	private ArrayList<Activity> schedule = new ArrayList<Activity>();
	/** Title of schedule, defaulted to My Schedule */
	private String title;

	/** 
	 * Constructor that finds the correct file and sets title to the schedule in the catalog.
	 * @param title Title of schedule, reads it into the Course if file is found.
	 */
	public WolfScheduler(String title) {
		schedule = new ArrayList<Activity>();
		//sets default title to 'My Schedule'.
		this.title = "My Schedule";
		//try to read title
		try {
			catalog = CourseRecordIO.readCourseRecords(title);
		}
		//if file doesn't exist, throw exception.
		catch(FileNotFoundException e) {
			throw new IllegalArgumentException("Cannot find file.");
		}
	}
	/**
	 * Get the catalog and set it up with correct rows.
	 * @return 2d string array for catalog with rows of name, section, and title.
	 */
	public String[][] getCourseCatalog() {
        String [][] catalogArray = new String[catalog.size()][4];
        //if empty catalog, return empty 2d array
        if(catalog.size() == 0) {
        	return new String[0][0];
        }
        for (int i = 0; i < catalog.size(); i++) {
            Course c = catalog.get(i);
            catalogArray[i] = c.getShortDisplayArray();
        }
        return catalogArray;
    }
	/**
	 * Get scheduled courses for name, section, and title.
	 * @return 2d string array for schedule with rows of name, section, and title.
	 */
	public String[][] getScheduledActivities() {
		String[][] catArray = new String[schedule.size()][4];
		//if empty schedule, return empty 2d array
		if(schedule.size() == 0) {
			return new String[0][0]; 
		}
		else {
			for(int i = 0; i < schedule.size(); i++) {
				Activity c = schedule.get(i);
				catArray[i] = c.getShortDisplayArray();
			}
		}
		return catArray;
	}
	/**
	 * Get full scheduled courses for name, section, title, credits, instructorId, and meeting string.
	 * @return 2d string array with all the correct implemented rows.
	 */
	public String[][] getFullScheduledActivities() {
		String[][] catArray = new String[schedule.size()][7];
		//if empty schedule, return empty 2d array
		if(schedule.size() == 0) {
			return new String[0][0]; 
		}
		else {
			//create schedule with correct rows
			for(int i = 0; i < schedule.size(); i++) {
				Activity c = schedule.get(i);
				catArray[i] = c.getLongDisplayArray();
			}
		}
		return catArray;
	}
	/**
	 * Get course from the catalog
	 * @param name name of the course
	 * @param section section number of the course
	 * @return Course object with correct name and section.
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for(int i = 0; i < catalog.size(); i++) {
			//if name and course match in catalog, return it
			if(catalog.get(i).getName().equals(name) && 
					catalog.get(i).getSection().equals(section)) {
				return catalog.get(i);
			}
		}
		//else name and course don't exist, return null
		return null;
	}
	/**
	 * Add course to schedule with correct name and section.
	 * @param name name of course
	 * @param section section number of course
	 * @return true if course successfully added, false if not.
	 */
	public boolean addCourseToSchedule(String name, String section) {
		
		Course addedCourse = catalog.get(0);
		//if course doesn't exist, return false
		if(getCourseFromCatalog(name, section) == null) {
			return false;
		}
		else {
			for(int i = 0; i < catalog.size(); i++) {
				//if course is in catalog with correct name and section, receive it
				if(catalog.get(i).getName().equals(name) && 
						catalog.get(i).getSection().equals(section)) {
					addedCourse = catalog.get(i);
					for(int j = 0; j < schedule.size(); j++) {
						if(schedule.get(j) instanceof Course && schedule.get(j).isDuplicate(addedCourse)) {
							//if name of course already in schedule, throw exception 
							throw new IllegalArgumentException("You are already enrolled in " + name);							
						}
					}
				}
			}
		}
		
		//add course to schedule
		schedule.add(addedCourse);
		return true;
	}
	/**
	 * Remove correct course from schedule if it exists.
	 * @param idx index of the activity you want to remove
	 * @return true if activity successfully removed, false if activity does not exist in schedule.
	 */
	public boolean removeActivityFromSchedule(int idx) {
		try {
			schedule.remove(idx);
		}
		catch(IndexOutOfBoundsException e) {
			return false;
		}
		return true;
	}
	/**
	 * Get schedule title
	 * @return title of schedule
	 */
	public String getScheduleTitle() {
		//returns schedule title.
		return title;
	}
	/**
	 * Exports schedule based off filename if it exists.
	 * @param filename filename to export schedule
	 */
	public void exportSchedule(String filename) {
		//try if filename exists with schedule
		try {
			ActivityRecordIO.writeActivityRecords(filename, schedule);
		}
		//if does not exist, throw exception
		catch(IOException e){
			throw new IllegalArgumentException("The file cannot be saved.");
		}
	}
	/**
	 * Resets the course schedule back to empty Array List.
	 */
	public void resetSchedule() {
		//empty array list
		schedule = new ArrayList<Activity>();
		
	}
	/**
	 * Sets the schedule title given it is not null.
	 * @param title name of title schedule, cannot be null.
	 */
	public void setScheduleTitle(String title) {
		//if title is null, throw exception
		if(title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		//sets title to input title name.
		this.title = title;
	}
	/**
	 * Adds an event to the schedule with the title, days, time, and details.
	 * @param eventTitle Title of the event.
	 * @param eventMeetingDays Meeting days of the event.
	 * @param eventStartTime Start time of the event.
	 * @param eventEndTime End time of the event.
	 * @param eventDetails Event details of the event.
	 */
	public void addEventToSchedule(String eventTitle, String eventMeetingDays, int eventStartTime, int eventEndTime, String eventDetails) {
		Event event = new Event(eventTitle, eventMeetingDays, eventStartTime, eventEndTime, eventDetails);
		for(int i = 0; i < schedule.size(); i++) {
			Activity act = schedule.get(i);
			if(act instanceof Event) {
				Event newEvent = (Event)schedule.get(i);
				if(newEvent.isDuplicate(event)) {
					throw new IllegalArgumentException("You have already created an event called " + event.getTitle());
				}
			}
		}
		schedule.add(event);
	}
	
}
