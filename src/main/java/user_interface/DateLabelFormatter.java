/**
 * Date formatter for reading in date objects.
 * 
 * @author Jackson O'Donnell
 * @version 1.0
 */

package user_interface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFormattedTextField.AbstractFormatter;

public class DateLabelFormatter extends AbstractFormatter {

    private String datePattern = "dd/MM/yyyy";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
    
    /**
     * Gets a date object from a string.
     * 
     * @param text The text from which the date object will be extracted
     * @return A Date Object
     */
    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }
    
    /**
     * Gets a string from a Calendar object.
     * 
     * @param value The Calendar object from which the string will be extracted.
     * @return A string that represents a date object
     */
    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }

}