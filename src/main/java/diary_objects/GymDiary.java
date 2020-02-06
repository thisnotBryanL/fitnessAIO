/**
 * Contains information for actual gym performance.
 * 
 * @author Jackson O'Donnell
 * @version 1.0
 */

package diary_objects;

import java.util.Date;

public class GymDiary extends Diary{
	
	private Integer hours;
	
	/**
	 * A default constructor for GymDiary.
	 */
	public GymDiary(){
		super();
		hours = 0;
	}
	
	/**
	 * A specific constructor for GymDiary.
	 * 
	 * @param date The date of the Gym Diary entry
	 * @param note The note corresponding to the gym diary entry
	 * @param hrs The number of hours worked out for the date
	 */
	public GymDiary(String date, String note, Integer hrs){
		super(date, note);
		hours = hrs;
	}

	/**
	 * Getter function for workout hours
	 * 
	 * @return the number of hours of exercise
	 */
	public Integer getHours() {
		return hours;
	}

	/**
	 * Setter function for workout hours
	 * 
	 * @param hours The number of hours of exercise
	 */
	public void setHours(Integer hours) {
		this.hours = hours;
	}
	
	/*TODO: find other attributes needed and make getters/setters*/
	
}
