/**
 * Dialog window that pops up so the user can enter in fitness information
 *
 * @author Bryan Lee
 * @version 1.0
 */
package user_interface;

import database.Database;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class FormWindow extends JFrame
        implements ActionListener {
    //Values for the fields
    private String userHeight = "";
    private String userWeight = "";
    private String calorieIntake = "";

    private String userName = "";


    //Labels to identify the fields
    private JLabel userHeightLabel;
    private JLabel userWeightLabel;
    private JLabel calorieIntakeLabel;


    //Fields for data entry
    public JFormattedTextField userHeightField;
    public JFormattedTextField userWeightField;
    public JFormattedTextField calorieIntakeField;


    protected JButton okButton;

    private JPanel contentPane;


    /**
     * Constructor that creates the Form window and takes in the username
     * as a parameter to keep track of who is using it
     *
     * @param user_name Username that is passed to the constructor to keep track of who is logged on
     */
    public FormWindow(String user_name) {
        //super(new BorderLayout());
        userName = user_name;
        initGui();

    }
    private void initGui(){

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contentPane = new JPanel();
        setContentPane(contentPane);

        //Create the labels.
        userHeightLabel = new JLabel("Height: ");
        userWeightLabel = new JLabel("Weight: ");
        calorieIntakeLabel = new JLabel("Calorie Intake: ");



        //Create the text fields and set them up.
        userHeightField = new JFormattedTextField();
        userHeightField.setValue(userHeight);
        userHeightField.setColumns(10);


        userWeightField = new JFormattedTextField();
        userWeightField.setValue(userWeight);
        userWeightField.setColumns(10);


        calorieIntakeField = new JFormattedTextField();
        calorieIntakeField.setValue(calorieIntake);
        calorieIntakeField.setColumns(10);



        //Tell accessibility tools about label/textfield pairs.
        userHeightLabel.setLabelFor(userHeightField);
        userWeightLabel.setLabelFor(userWeightField);
        calorieIntakeLabel.setLabelFor(calorieIntakeField);


        //Lay out the labels in a panel.
        JPanel labelPane = new JPanel(new GridLayout(0,1));
        labelPane.add(userHeightLabel);
        labelPane.add(userWeightLabel);
        labelPane.add(calorieIntakeLabel);


        //Layout the text fields in a panel.
        JPanel fieldPane = new JPanel(new GridLayout(0,1));
        fieldPane.add(userHeightField);
        fieldPane.add(userWeightField);
        fieldPane.add(calorieIntakeField);


        okButton = new JButton("Create New profile");
        okButton.addActionListener(this);


        contentPane.add(labelPane, BorderLayout.CENTER);
        contentPane.add(fieldPane, BorderLayout.EAST);
        contentPane.add(okButton,BorderLayout.PAGE_END);

        pack();
    }

    /**
     * Listens for when the create button is pressed to insert the new user info into
     * the database
     *
     * @param e ActionEvent that is captured by the ActionListener
     */
    public void actionPerformed(ActionEvent e) {

        try{
            double testingingHeightValue = Double.parseDouble((String)userHeightField.getValue());
            double testingWeightValue = Double.parseDouble((String)userWeightField.getValue());
            double testingCalorieValue = Double.parseDouble((String)calorieIntakeField.getValue());

            String heightValue = (String) userHeightField.getValue();
            String weightValue = (String) userWeightField.getValue();
            String calorieIntakeValue = (String) calorieIntakeField.getValue();
            int calorieIntakeCalculation = Integer.parseInt(calorieIntakeValue);
            int protein = (int) Math.round(calorieIntakeCalculation * .35);
            int carbs = (int) Math.round(calorieIntakeCalculation * .50);
            int fat = (int) Math.round(calorieIntakeCalculation * .15);

            System.out.println(protein + " " + carbs + " " + fat);

            Database dbConnector = new Database();

                //dbConnector.deleteRecordFromDbUserTable();

                if(dbConnector.select_UserName_fromDB(userName,"USERPROFILE")){
                    dbConnector.updateRecordIntoDbProfileTable(userName,protein,carbs,fat,heightValue,weightValue,calorieIntakeValue);
                }else{
                    dbConnector.insertIntoUserProfile(userName,protein,carbs,fat,heightValue,weightValue,calorieIntakeValue);
                }

                dispose();


        }catch (NumberFormatException numExpt) {
            JOptionPane.showMessageDialog(null, "Error--Invalid Values", "Error", JOptionPane.WARNING_MESSAGE);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * This method sets a new height for the user
     *
     * @param height sets the users new Height as a string
     */
    public void setUserHeight(String height){
        userHeight = height;
    }

    /**
     * This method sets a new weight for the user
     *
     * @param userWeight sets the users new Weight as a string
     */
    public void setUserWeight(String userWeight) {
        this.userWeight = userWeight;
    }

    /**
     * This method sets the calorie intake for the user
     *
     * @param calorieIntake sets the users new calorie intake as a string
     */
    public void setCalorieIntake(String calorieIntake) {
        this.calorieIntake = calorieIntake;
    }
}




