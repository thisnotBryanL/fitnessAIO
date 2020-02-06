/**
 * Contains information for sleep.
 * 
 * @author Jackson O'Donnell
 * @version 1.0
 */

package diary_objects;

import java.util.Date;

public class SleepDiary extends Diary{

	private Integer sleep;
	
	/**
	 * Default constructor for a SleepDiary.
	 */
	public SleepDiary(){
		super();
		sleep = 0;
	}
	
	/**
	 * Specific constructor for a SleepDiary
	 * 
	 * @param date A Date object to hold the date for the diary entry.
	 * @param note A String object to hold the 
	 * @param hrs
	 */
	public SleepDiary(String date, String note, Integer hrs){
		super(date, note);
		sleep = hrs;
	}

	/**
	 * Getter for sleep.
	 * 
	 * @return A Double containing the number of hours slept
	 */
	public Integer getSleep() {
		return sleep;
	}

	/**
	 * Setter for sleep.
	 * 
	 * @param sleep A Double containing the number of hours slept
	 */
	public void setSleep(Integer sleep) {
		this.sleep = sleep;
	}
	
}
