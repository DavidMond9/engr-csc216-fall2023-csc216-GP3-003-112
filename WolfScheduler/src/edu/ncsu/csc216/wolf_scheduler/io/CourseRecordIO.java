package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_scheduler.course.Course;

/**
 * Reads Course records from text files.  Writes a set of CourseRecords to a file. It contains
 * 3 methods, readCourseStrings, readCourse, and writeCourseRecords. These methods
 * are how the class actually reads the course records as well as write them. It has no fields
 * as they are not needed for the implementation.
 * 
 * @author David Mond
 */
public class CourseRecordIO {

    /**
     * Reads course records from a file and generates a list of valid Courses.  Any invalid
     * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Course records from
     * @return a list of valid Courses
     * @throws FileNotFoundException if the file cannot be found or read
     */
	public static ArrayList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
	    Scanner fileReader = new Scanner(new FileInputStream(fileName));  //Create a file scanner to read the file
	    ArrayList<Course> courses = new ArrayList<Course>(); //Create an empty array of Course objects
	    while (fileReader.hasNextLine()) { //While we have more lines in the file
	        try { //Attempt to do the following
	            //Read the line, process it in readCourse, and get the object
	            //If trying to construct a Course in readCourse() results in an exception, flow of control will transfer to the catch block, below
	            Course course = readCourse(fileReader.nextLine()); 

	            //Create a flag to see if the newly created Course is a duplicate of something already in the list  
	            boolean duplicate = false;
	            //Look at all the courses in our list
	            for (int i = 0; i < courses.size(); i++) {
	                //Get the course at index i
	                Course current = courses.get(i);
	                //Check if the name and section are the same
	                if (course.getName().equals(current.getName()) &&
	                        course.getSection().equals(current.getSection())) {
	                    //It's a duplicate!
	                    duplicate = true;
	                    break; //We can break out of the loop, no need to continue searching
	                }
	            }
	            //If the course is NOT a duplicate
	            if (!duplicate) {
	                courses.add(course); //Add to the ArrayList!
	            } //Otherwise ignore
	        } catch (IllegalArgumentException e) {
	            //The line is invalid b/c we couldn't create a course, skip it!
	        }
	    }
	    //Close the Scanner b/c we're responsible with our file handles
	    fileReader.close();
	    //Return the ArrayList with all the courses we read!
	    return courses;
	}
	/**
	 * Reads course string and returns a course object that defines the courses characteristics.
	 * @param nextLine nextLine input for the scanner to read the course string.
	 * @return Course returns a course object for the inputed course string.
	 */
    private static Course readCourse(String nextLine) {
    	//create scanner
		Scanner courseReader = new Scanner(nextLine);
		//set , as delimiter
		courseReader.useDelimiter(",");
		
		try {
			// store each token in a local variable
			String courseName = courseReader.next();
			String courseTitle = courseReader.next();
			String courseSection = courseReader.next();
			int courseCredits = courseReader.nextInt();
			String courseInstructorId = courseReader.next();
			String courseMeetingDays = courseReader.next();
			
			//arranged
			if("A".equals(courseMeetingDays)) {
				//if there is a time listed, throw exception
				if(courseReader.hasNext()) {
					courseReader.close();
					throw new IllegalArgumentException("Too many tokens.");
				}
				else {
					courseReader.close();
					//course constructor without times
					return new Course(courseName, courseTitle, courseSection, courseCredits, courseInstructorId, courseMeetingDays);
				}
			}
			else {
				//store time tokens in local variables
				int courseStartTime = courseReader.nextInt();
				int courseEndTime = courseReader.nextInt();
				
				//if too many tokens, throw exception
				if(courseReader.hasNext()) {
					courseReader.close();
					throw new IllegalArgumentException("Too many tokens.");
				}
				courseReader.close();
				//course constructor with times
				return new Course(courseName, courseTitle, courseSection, courseCredits, courseInstructorId, courseMeetingDays, courseStartTime, courseEndTime);
			}
		}
		//throw IAE if NoSuchElementException
		catch(NoSuchElementException e) {
			courseReader.close();
			throw new IllegalArgumentException("IllegalArgumentException.");
		}
    }

}