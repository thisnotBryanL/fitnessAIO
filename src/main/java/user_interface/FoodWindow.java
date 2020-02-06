
/**
 * This class is used to display and edit food diary entries.
 * 
 * @author Jackson O'Donnell
 * @version 1.0
 */
package user_interface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.javatuples.Pair;

import database.Database;
import diary_objects.Food;
import diary_objects.FoodDiary;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodWindow{
	
	//FoodDiary object passed in for data
	private FoodDiary diary;
	//String to hold current user name
	private String userName;
	//String to hold SQL instructions
	private String command;
	
	public FoodWindow(FoodDiary foodDiary, String u) {
		diary = foodDiary;//new FoodDiary(foodDiary.getDate(), foodDiary.getNote(), (ArrayList) foodDiary.getFood());
		userName = u;
	}
	
	/**
	 * builds the table
	 */
	public void buildTable(Object[] row,  DefaultTableModel foodDiaryModel) {
		//clear table
		foodDiaryModel.setRowCount(0);
		
	    //put the food in the table
	    for(Pair<Food, Integer> f : diary.getFood()) {
		    row[0] = f.getValue0().getName();
		    row[1] = f.getValue1();
		    row[2] = f.getValue0().getCalories();
		    row[3] = f.getValue0().getFat();
		    row[4] = f.getValue0().getProtein();
		    row[5] = f.getValue0().getCarbs();
		    System.out.println(row[1].toString());
		    foodDiaryModel.addRow(row);
	    }
	    
	}


	/**
	 * Shows the food diary panel
	 * 
	 * @return void
	 */
    public void showFoodDiary(){
        final JFrame frame = new JFrame("FoodDiarys");
        JTable foodDiaryTable = new JTable();

        Object[] columns = {"Food", "Quantity", "Calories", "Fat", "Protien", "Carbs"};
        final DefaultTableModel foodDiaryModel = new DefaultTableModel();
        foodDiaryModel.setColumnIdentifiers(columns);
        foodDiaryTable.setModel(foodDiaryModel);
        foodDiaryTable.setBackground(Color.white);
        foodDiaryTable.setForeground(Color.black);

        final JTextField name_text = new JTextField();
        final JTextField num_text = new JTextField();
        final JTextField kCal_text = new JTextField();
        final JTextField fat_text = new JTextField();
        final JTextField protien_text = new JTextField();
        final JTextField carb_text = new JTextField();

        final JLabel name_label = new JLabel("Food: ");
        final JLabel num_label = new JLabel("Quantity: ");
        final JLabel kCal_label = new JLabel("Calories: ");
        final JLabel fat_label = new JLabel("Fat: ");
        final JLabel protien_label = new JLabel("Protien: ");
        final JLabel carb_label = new JLabel("Carbs: ");

        name_label.setBounds(20, 205, 100, 25);
        num_label.setBounds(140, 205, 100, 25);
        kCal_label.setBounds(260, 205, 100, 25);
        fat_label.setBounds(380, 205, 100, 25);
        protien_label.setBounds(500, 205, 100, 25);
        carb_label.setBounds(620, 205, 100, 25);

        JButton add_NewFood_Button = new JButton("Add New Food");
        JButton add_OldFood_Button = new JButton("Add Existing Food");
        JButton remove_Food_Button = new JButton("Remove Food");

        name_text.setBounds(20, 225, 100, 25);
        num_text.setBounds(140, 225, 100, 25);
        kCal_text.setBounds(260, 225, 100, 25);
        fat_text.setBounds(380, 225, 100, 25);
        protien_text.setBounds(500, 225, 100, 25);
        carb_text.setBounds(620, 225, 100, 25);

        add_NewFood_Button.setBounds(20, 255, 200, 25);
        add_OldFood_Button.setBounds(150, 325, 200, 25);
        remove_Food_Button.setBounds(400, 325, 200, 25);

        JScrollPane routinePane = new JScrollPane(foodDiaryTable);
        routinePane.setBounds(0, 0, 880, 200);

        frame.setLayout(null);

        frame.add(routinePane);

        frame.add(name_label);
        frame.add(num_label);
        frame.add(kCal_label);
        frame.add(fat_label);
        frame.add(protien_label);
        frame.add(carb_label);

        frame.add(name_text);
        frame.add(num_text);
        frame.add(kCal_text);
        frame.add(fat_text);
        frame.add(protien_text);
        frame.add(carb_text);

        frame.add(add_NewFood_Button);
        frame.add(add_OldFood_Button);
        //frame.add(remove_Food_Button);
        

        final Object[] row = new Object[6];
        
        buildTable(row, foodDiaryModel);
        
        add_NewFood_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	//See if the food already exists
            	command = "SELECT * FROM food WHERE food_name = '" + name_text.getText()
            				+ "'";
            	ResultSet rs = Database.executeQuery(command);
            	//if the food does not exist, add it to the database and the table
            	try {
					if(!rs.next()) {
					    
					    //put the food in an object and add to diary
					    Integer quant = Integer.parseInt(num_text.getText());
					    Food newFood = new Food(name_text.getText(),
					    				Integer.parseInt(kCal_text.getText()),
					    				Integer.parseInt(fat_text.getText()),
					    				Integer.parseInt(protien_text.getText()),
					    				Integer.parseInt(carb_text.getText()));
					    diary.addFood(newFood, quant);
					    
					    //put the food in the database
					    command = "INSERT INTO food VALUES ('"+newFood.getName()
					    			+ "', "+newFood.getCalories()
					    			+ ", "+newFood.getFat()
					    			+ ", "+newFood.getProtein()
					    			+ ", "+newFood.getCarbs()+
					    			")";
					    Database.executeUpdate(command);
					    //update diary entry
					    command = "INSERT INTO food_list VALUES ('"+userName
		    			+ "', '"+diary.getDate()
		    			+ "', '"+newFood.getName()
		    			+ "', "+quant+
		    			")";
					    Database.executeUpdate(command);
					    command = "INSERT INTO diary VALUES ('"+userName
				    			+ "', '"+diary.getDate()+
				    			"')";
					    Database.executeUpdate(command);
					}
					else { //otherwise tell the user the food already exists
						JOptionPane.showMessageDialog(null, "The food already exists", "Please Check", JOptionPane.WARNING_MESSAGE);
					}
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	
            	//update table
            	buildTable(row, foodDiaryModel);
            	frame.repaint();
            }
        });

        add_OldFood_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	//get the current foods in the database
            	command = "SELECT * FROM food ORDER BY food_name";
            	ResultSet rs = Database.executeQuery(command);
            	//put the results in a list of Food objects
            	List<Food> currFood = new ArrayList<Food>();
            	try {
					while(rs.next()) {
						currFood.add(new Food(rs.getString("food_name"), 
											rs.getInt("calories"),
											rs.getInt("fat"),
											rs.getInt("protien"),
											rs.getInt("carbs")));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	//add number box
            	SpinnerModel numModel = new SpinnerNumberModel(1, 1, 666, 1);
            	JSpinner spinner = new JSpinner(numModel);
            	
            	//create a popup to select the food
            	JPanel fPanel = new JPanel();
            	fPanel.add(new JLabel("Choose a food:"));
                DefaultComboBoxModel model = new DefaultComboBoxModel();
                JComboBox comboBox = new JComboBox(model);
                fPanel.add(comboBox);
                for(Food f : currFood) {
                	model.addElement(f.getName());
                }
                fPanel.add(spinner);
                int result = JOptionPane.showConfirmDialog(null, fPanel, "Add Existing Food", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                switch (result) {
                	//If they hit OK, update table and diary
                    case JOptionPane.OK_OPTION:
                    	command = "SELECT * FROM food WHERE food_name = '"+comboBox.getSelectedItem()+"'";
                    	ResultSet res = Database.executeQuery(command);
					try {
						//generate the food object
						if(res.next()) {
                    		Food newFood = new Food(res.getString("food_name"),
                    								res.getInt("calories"),
                    								res.getInt("fat"),
                    								res.getInt("protien"),
                    								res.getInt("carbs"));
    						//add to the diary object
    						diary.addFood(newFood, (Integer) spinner.getValue());
    						//update the diary
    						String checkExists = "SELECT * FROM food_list WHERE username = '"+userName
    										+"' AND food_name = '" + newFood.getName()+"'"
    										+ " AND dat = '"+diary.getDate()+"'";
    						ResultSet exists = Database.executeQuery(checkExists);
    						if(exists.next()) {
    							ArrayList<Pair<Food, Integer>> foodList = new ArrayList<Pair<Food, Integer>>();
    							for(Pair<Food, Integer> f : diary.getFood()) {
    								if(f.getValue0().getName().equals(newFood.getName())){
    									f = new Pair<Food, Integer>(f.getValue0(), (Integer) spinner.getValue()+f.getValue1());
    									command = "UPDATE food_list SET quantity = "+ f.getValue1()
    											+"WHERE username = '"+userName+"'"
    											+ "	AND dat = '"+diary.getDate()+"'"
    											+ " AND food_name = '"+newFood.getName()+"'";
    		    						Database.executeUpdate(command);
    								}
    								foodList.add(new Pair<Food, Integer>(f.getValue0(), f.getValue1()));
    							}
    							diary = new FoodDiary(diary.getDate(), diary.getNote(), foodList);
    							
    						}
    						else {
    							command = "INSERT INTO food_list VALUES ('"+userName
    					    			+ "', '"+diary.getDate()
    					    			+ "', '"+newFood.getName()
    					    			+ "', "+(Integer) spinner.getValue()+
    					    			")";
    							Database.executeUpdate(command);
    						    command = "INSERT INTO diary VALUES ('"+userName
    					    			+ "', '"+diary.getDate()+
    					    			"')";
    						}
    						//update database and table
    						Database.executeUpdate(command);
                    	}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                        break;
                }
                //update table
                buildTable(row, foodDiaryModel);
                frame.repaint();
            }
        });
        /*
        remove_Food_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                
            }
        });*/

        frame.setSize(900, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);


    }

}