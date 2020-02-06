/**
 * This is a class to be the parent of all diary objects.
 * 
 * @author Jackson O'Donnell
 * @version 1.0
 */

package diary_objects;

import java.util.Date;

public class Diary {
	
	private String date;
	private String note;
	
	/**
	 * Default constructor for a diary object.
	 */
	Diary(){
		date = null;
		note = "";
	}
	
	/**
	 * Specific constructor for a diary object.
	 * 
	 * @param d A String object that will hold the date to enter
	 * @param n A String that will hold the note to enter
	 */
	Diary(String d, String n){
		date = d;
		note = n;
	}

	/**
	 * Getter for the Date.
	 * 
	 * @return Date corresponding to the Diary entry
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * Setter for the Date.
	 * 
	 * @param date A String object to replace the current Date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Getter for the note.
	 * 
	 * @return String holding the description of the Diary entry
	 */
	public String getNote() {
		return note;
	}

	/**
	 * Setter for the note.
	 * 
	 * @param note A String holding the note to be added to the Diary entry
	 */
	public void setNote(String note) {
		this.note = note;
	}
	
	
	
}
