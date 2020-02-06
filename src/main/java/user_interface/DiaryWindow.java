/**
 * Swing UI to show the user's diary.
 * 
 * @author Jackson O'Donnell
 * @version 1.0
 */

package user_interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import org.javatuples.Pair;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import database.Database;
import diary_objects.Diary;
import diary_objects.Food;
import diary_objects.FoodDiary;
import diary_objects.GymDiary;
import diary_objects.SleepDiary;

public class DiaryWindow extends JFrame{
	
	private JPanel contentPane;
    private final JLabel lblSleep = new JLabel("Sleep (hrs):");
    private final JButton btnSleep = new JButton("Details");
    private final JLabel lblWorkout = new JLabel("Gym (hrs):");
    private final JButton btnWorkout = new JButton("Details");
    private final JLabel lblFood = new JLabel("Food (kCal):");
    private final JButton btnFood = new JButton("Details");
    private final JFormattedTextField sleepTextField = new JFormattedTextField();
    private final JFormattedTextField workoutTextField = new JFormattedTextField();
    private final JFormattedTextField foodTextField = new JFormattedTextField();
    private final JButton btnProfile = new JButton("Profile");
    private final JButton btnDiary = new JButton("Diary");
    private final JButton btnSchedule = new JButton("Schedule");
    private final JButton btnRoutine = new JButton("One-Rep-Max");

    protected String userName = "";
    
    //Create a string to hold the current date
    private String selectedDate = "";
	//Create a list of the diary parts
	SleepDiary sleepDiary = new SleepDiary();
	GymDiary gymDiary = new GymDiary();
	FoodDiary foodDiary = new FoodDiary();
	//String to hold sql commands
	private String command = "";
	
    /**
     * Create the Diary Swing UI window.
     */
    public DiaryWindow(String userName) {
    	this.userName = userName;
        initGUI();
    }
    
    /**
     * Initialises the Swing UI for the Diary window.
     * 
     * @return void
     */
    private void initGUI() {
    	
        setFont(new Font("Dialog", Font.BOLD, 14));
        setTitle("Fitness.AIO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 618, 430);
        contentPane = new JPanel();
        contentPane.setBorder(new LineBorder(new Color(0, 5, 0)));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        //adds a calendar
        UtilDateModel model = new UtilDateModel();

        model.setSelected(true);
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        final JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setBounds(200, 300, 200, 50);
        
        contentPane.add(datePicker);
        
        //create a string to hold the selected date
        selectedDate = datePicker.getJFormattedTextField().getText();
    	//fill the diary with the start data
        
		foodDiary = getFoodEntry(selectedDate);
		sleepDiary = getSleepEntry(selectedDate);
		gymDiary = getGymEntry(selectedDate);
        
        datePicker.addActionListener(new ActionListener() {
        	//triggers on date selection, modifies
        	public void actionPerformed(ActionEvent e) {
        		//gets the date from the date picker
        		selectedDate = datePicker.getJFormattedTextField().getText();
        		//Create a list of the diary parts

        		foodDiary = getFoodEntry(selectedDate);
        		sleepDiary = getSleepEntry(selectedDate);
        		gymDiary = getGymEntry(selectedDate);
        		
        		sleepTextField.setText(sleepDiary.getSleep().toString());
        		workoutTextField.setText(gymDiary.getHours().toString());
        		foodTextField.setText(foodDiary.getCalories().toString());
        	}
        });
        
        //Adds sleep field
        lblSleep.setBounds(63, 87, 77, 16);
        contentPane.add(lblSleep);
        sleepTextField.setText(sleepDiary.getSleep().toString());
        sleepTextField.setBounds(187, 87, 77, 16);
        contentPane.add(sleepTextField);
        btnSleep.setBounds(311, 87, 77, 16);
        btnSleep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
            	
            	JTextField[] entryFields = new JTextField[2];
            	entryFields[0] = new JTextField(5);
            	entryFields[0].setText(sleepDiary.getSleep().toString());
            	entryFields[1] = new JTextField(15);
            	entryFields[1].setText(sleepDiary.getNote());
            	
            	//make popup panel for options
    			JPanel popPanel = new JPanel();
    			//add sleep field
    			popPanel.add(new JLabel("Sleep (hrs):"));
    			popPanel.add(entryFields[0]);
    			popPanel.add(Box.createHorizontalStrut(15)); // a spacer
    			//add note field
    			popPanel.add(new JLabel("Notes:"));
    			popPanel.add(entryFields[1]);
    			
    			int result = JOptionPane.showConfirmDialog(null, popPanel, 
    					"Enter new info", JOptionPane.OK_CANCEL_OPTION);
    			if (result == JOptionPane.OK_OPTION) {
    				sleepDiary.setSleep(Integer.parseInt(entryFields[0].getText()));
    				sleepDiary.setNote(entryFields[1].getText());
    			}
    			
    			sleepTextField.setText(sleepDiary.getSleep().toString());
    			setSleepEntry(sleepDiary);
            }
        });
        contentPane.add(btnSleep);
        
        //Adds workout field
        lblWorkout.setBounds(63, 112, 61, 16);
        contentPane.add(lblWorkout);
        workoutTextField.setText(gymDiary.getHours().toString());
        workoutTextField.setBounds(187, 112, 77, 16);
        contentPane.add(workoutTextField);
        btnWorkout.setBounds(311, 112, 77, 16);
        btnWorkout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
            	JTextField[] entryFields = new JTextField[2];
            	entryFields[0] = new JTextField(5);
            	entryFields[0].setText(gymDiary.getHours().toString());
            	entryFields[1] = new JTextField(15);
            	entryFields[1].setText(gymDiary.getNote());
            	
            	//make popup panel for options
    			JPanel popPanel = new JPanel();
    			//add sleep field
    			popPanel.add(new JLabel("Exercise (hrs):"));
    			popPanel.add(entryFields[0]);
    			popPanel.add(Box.createHorizontalStrut(15)); // a spacer
    			//add note field
    			popPanel.add(new JLabel("Notes:"));
    			popPanel.add(entryFields[1]);
    			
    			int result = JOptionPane.showConfirmDialog(null, popPanel, 
    					"Enter new info", JOptionPane.OK_CANCEL_OPTION);
    			if (result == JOptionPane.OK_OPTION) {
    				gymDiary.setHours(Integer.parseInt(entryFields[0].getText()));
    				gymDiary.setNote(entryFields[1].getText());
    			}
    			
    			workoutTextField.setText(gymDiary.getHours().toString());
    			setGymEntry(gymDiary);
            }
        });
        contentPane.add(btnWorkout);
        
        //Adds food item fields
        lblFood.setBounds(63, 147, 71, 16);
        contentPane.add(lblFood);
        foodTextField.setText(foodDiary.getCalories().toString());
        foodTextField.setBounds(187, 147, 77, 16);
        contentPane.add(foodTextField);
        btnFood.setBounds(311, 147, 77, 16);
        btnFood.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
            	
            	foodDiary = getFoodEntry(selectedDate);
            	
            	//bring up food diary details menu
            	FoodWindow foodWin = new FoodWindow(foodDiary, userName);
            	foodWin.showFoodDiary();
    			
            	//update fields
            	foodDiary = getFoodEntry(selectedDate);
            	foodTextField.setText(foodDiary.getCalories().toString());
            	for(Pair<Food, Integer> f : foodDiary.getFood()) {
                	System.out.println(f.getValue1().toString());
            	}
            }
        });
        contentPane.add(btnFood);
        //foodTable.setBounds(187, 147, 220, 80);
        //foodTable.setPreferredSize(new Dimension(100, 120));
        //contentPane.add(foodTable);
        
        //Block of header tabs/buttons
        //adds diary button
        btnDiary.setBounds(112, 0, 117, 29);
        contentPane.add(btnDiary);
        
        //adds schedule button
        btnSchedule.setBounds(227, 0, 117, 29);
        btnSchedule.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Schedule newSchedWindow = new Schedule(userName);
                newSchedWindow.showSchedule();
            }
        });
        contentPane.add(btnSchedule);
        
        //adds routine button
        btnRoutine.setBounds(342, 0, 117, 29);
        contentPane.add(btnRoutine);
        btnRoutine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                OneRepWindow frame = null;
                try {
                    frame = new OneRepWindow(userName);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                frame.setVisible(true);
            }
        });
        
        //adds profile button
        JButton btnProfile = new JButton("Profile");
        btnProfile.setBounds(0, 0, 117, 29);
        btnProfile.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                dispose();
                UserInfoWindow frame = null;
                try {
                    frame = new UserInfoWindow(userName);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                frame.setVisible(true);
            }
        });
        contentPane.add(btnProfile);
        
        
    }
    
    /**
	 * 	Gets a food diary object using the values stored in the database. If
	 * there are no values at the specified date, a new object will be created
	 * with default blank list of food.
	 * 
	 * @param date The date the food diary entry is being pulled from
	 * 
	 * @return A FoodDiary object holding the values of the database
	 */
    private FoodDiary getFoodEntry(String date) {
    	//Create an array to hold the food objects + quantities
    	List<Pair<Food, Integer>> feed = new ArrayList<Pair<Food, Integer>>();
    	
    	//query for the note
    	command = "SELECT * FROM diary, food_notes"
    			+ "		WHERE diary.username = food_notes.username"
    			+ "		  AND diary.dat = food_notes.dat"
    			+ "       AND diary.username = '" + userName +"'"
    			+ "		  AND diary.dat = '" + date + "'";
    	ResultSet rs = Database.executeQuery(command);
    	String foodNote = "";
    	try {
    		if(rs.next()) {
    			foodNote = rs.getString("note");
    		}
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	//query for the food
    	command = "SELECT * FROM diary, food_list, food"
    			+ "		WHERE diary.username = food_list.username"
    			+ "		  AND diary.dat = food_list.dat"
    			+ "       AND diary.username = '" + userName +"'"
    			+ "		  AND diary.dat = '" + date + "'"
    			+ "       AND food.food_name = food_list.food_name";
    	rs = Database.executeQuery(command);
    	String foodName = null;
    	int quantity = 0, calories = 0, carbs = 0, fat = 0, protein = 0;
    	try {
    		while(rs.next()) {
    			foodName = rs.getString("food_name");
    			quantity = rs.getInt("quantity");
    			calories = rs.getInt("calories");
    			carbs = rs.getInt("carbs");
    			fat = rs.getInt("fat");
    			protein = rs.getInt("protien");
    			
    			if(foodName != null && quantity > 0) {
    				feed.add(new Pair<Food, Integer>(new Food(foodName, calories, fat, protein, carbs), quantity));
    			}
    	}
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	//return the food diary object
    	return new FoodDiary(date, foodNote, (ArrayList) feed);
    }
    
    /**
	 * 	Gets a sleep object using the values stored in the database. If
	 * there are no values at the specified date, a new object will be created
	 * with default 0 hours of sleep.
	 * 
	 * @param date The date the sleep diary entry is being pulled from
	 * 
	 * @return A SleepDiary object holding the values of the database
	 */
    private SleepDiary getSleepEntry(String date) {
    	command = "SELECT * FROM diary, sleep "
    			+ "		WHERE diary.username = sleep.username"
    			+ "		  AND diary.dat = sleep.dat"
    			+ "       AND diary.username = '" + userName +"'"
    			+ "		  AND diary.dat = '" + date + "'";
    	
    	//execute the query
    	ResultSet rs = Database.executeQuery(command);
    	
    	//variables to hold the values to construct a sleep diary entry
		int hrs = 0;
		String sleepNote = null;
		//get the values for the sleep diary entry
		try {
			if(rs.next()) {
				hrs = rs.getInt("hours");
				sleepNote = rs.getString("note");
			}
    		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
    	//make sure there are no null values
		if(sleepNote == null) {
			sleepNote = "";
		}
		
    	return new SleepDiary(date, sleepNote, hrs);
    }

    /**
     * 	Gets a gym object using the values stored in the database. If
	 * there are no values at the specified date, a new object will be created
	 * with default 0 hours of exercise.
	 * 
	 * @param date The date the gym diary entry is being pulled from
     * 
     * @return A GymDiary object holding the values of the database
     */
	private GymDiary getGymEntry(String date) {
		
		command = "SELECT * FROM diary, work_out "
    			+ "		WHERE diary.username = work_out.username"
    			+ "		  AND diary.dat = work_out.dat"
    			+ "       AND diary.username = '" + userName +"'"
    			+ "		  AND diary.dat = '" + date + "'";
    	
    	//execute the query
    	ResultSet rs = Database.executeQuery(command);
    	
    	//variables to hold the values to construct a sleep diary entry
		int hrs = 0;
		String gymNote = null;
		//get the values for the sleep diary entry
		try {
			if(rs.next()) {
				hrs = rs.getInt("hours");
				gymNote = rs.getString("note");
			}
			getSleepEntry(selectedDate);
    		
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return new GymDiary(date, gymNote, hrs);
	}
	
	/**
	 * Updates the database with a new Sleep Diary entry
	 * 
	 * @param gym A GymDiary object containing the values to put in the database
	 */
	private void setGymEntry(GymDiary gym) {
		command = "SELECT * FROM work_out"
				+ "  WHERE work_out.dat = '" + gym.getDate()
				+ "'    AND work_out.username = '" + userName +"'";
		ResultSet rs = Database.executeQuery(command);
		try {
			//if not in there, insert
			if(!rs.next()) {
				command = "INSERT INTO work_out VALUES ('"
						+ userName +"', " + gym.getDate()
						+ "', "+gym.getHours()+", '"+gym.getNote()+"')";
				Database.executeUpdate(command);
			}
			else { //if in there, update
				command = "UPDATE work_out SET  hours = "+gym.getHours()+", note = '"+gym.getNote()
						+ "' WHERE work_out.username = '" + userName
						+ "'   AND work_out.dat = '" + gym.getDate() +"'";
				Database.executeUpdate(command);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDiaryEntry(gym.getDate());
	}
	
	/**
	 * Updates the database with a new Sleep Diary entry
	 * 
	 * @param sleep The SleepDiary object holding the attributes to be stored
	 */
	private void setSleepEntry(SleepDiary sleep) {
		command = "SELECT * FROM sleep"
				+ "  WHERE sleep.dat = '" + sleep.getDate()
				+ "'    AND sleep.username = '" + userName +"'";
		ResultSet rs = Database.executeQuery(command);
		try {
			//if not in there, insert
			if(!rs.next()) {
				command = "INSERT INTO sleep VALUES ('"
						+ userName +"', '" + sleep.getDate()
						+ "', "+sleep.getSleep()+", '"+sleep.getNote()+"')";
				Database.executeUpdate(command);
			}
			else { //if in there, update
				command = "UPDATE sleep SET  hours = "+sleep.getSleep()+", note = '"+sleep.getNote()
						+ "' WHERE sleep.username = '" + userName
						+ "'   AND sleep.dat = '" + sleep.getDate() +"'";
				Database.executeUpdate(command);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDiaryEntry(sleep.getDate());
	}
	
	/**
	 * 	Makes sure that a diary entry is present whenever the database is
	 * updated with a new diary entry. This method will be included in the
	 * other methods updating the database to ensure there are no "zombie"
	 * entries.
	 * 
	 * @param date The date of the diary entry (dd/MM/YYYY)
	 */
	private void setDiaryEntry(String date) {
		command = "SELECT * FROM diary"
				+ "  WHERE diary.dat = '" + date
				+ "'    AND diary.username = '" + userName +"'";
		ResultSet rs = Database.executeQuery(command);
		try {
			//if not in there, insert
			if(!rs.next()) {
				command = "INSERT INTO diary VALUES ('"
						+ userName +"', '" + date + "')";
				Database.executeUpdate(command);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Updates the database with the new food diary entry.
	 * 
	 * @param fd A FoodDiary object that holds the data to be stored in the database
	 */
	private void setFoodEntry(FoodDiary fd) {
		
		setDiaryEntry(fd.getDate());
	}
}
