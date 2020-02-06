/**
 * Swing UI for showing the user their basic info.
 * 
 * @author Bryan Lee
 * @version 1.0
 */

package user_interface;

import database.Database;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoWindow extends JFrame {

    private JPanel contentPane;
    private final JLabel lblUsername = new JLabel("UserName:");
    private final JLabel lblHeight = new JLabel("Height:");
    private final JLabel lblWeight = new JLabel("Weight:");
    private final JFormattedTextField heightTextField = new JFormattedTextField();
    private final JFormattedTextField weightTextField = new JFormattedTextField();
    private final JTextPane userNameTextField = new JTextPane();
    private final JButton btnProfile = new JButton("Profile");
    private final JButton btnDiary = new JButton("Diary");
    private final JButton btnSchedule = new JButton("Schedule");
    private final JButton btnOneRep = new JButton("One-Rep-Max");
    private final JButton btnCreateUserProfile = new JButton("Create User Profile");

    private final JComboBox comboBox = new JComboBox();
    private final JLabel lblCurrentMaxes = new JLabel("Current Maxes");
    private final JTextField textField = new JTextField();
    private final JLabel lblLbs = new JLabel("lbs");
    private final JLabel lblDietStatistics = new JLabel("Diet Statistics");
    private final JLabel lblCaloireIntake = new JLabel("Caloire Intake:");
    private final JTextField textField_1 = new JTextField();
    private final JLabel lblCals = new JLabel("cals");
    private final JLabel lblProteinfatcarbohydrates = new JLabel("Protein/Fat/Carbs:");
    private final JTextField textField_2 = new JTextField();
    private final JLabel lblGrams = new JLabel("grams");
    private final JLabel lblProfile = new JLabel("Profile");
    private final JLabel lblInches = new JLabel("inches");
    JButton updateButton = new JButton("Update");


    protected String userName = "";

    /**
     * Sets all the text fields to what they are describing after a query is done to retrieve a
     * ResultSet that holds the data.
     *
     *
     * @param rs is a ResultSet will hold the queried data of the User
     * @return void
     */
    protected void setallFields(ResultSet rs) throws SQLException {
        while(rs.next()) {
            String cals = rs.getString("CALORIES");
            String height = rs.getString("HEIGHT");
            String weight = rs.getString("WEIGHT");
            String fat = rs.getString("FAT");
            String carbs = rs.getString("CARB");
            String protien = rs.getString("PROTIEN");

            textField_1.setText(cals);
            textField_2.setText(protien + "/" + fat + "/" + carbs);
            heightTextField.setText(height);
            weightTextField.setText(weight);
            userNameTextField.setText(userName);
        }

    }

    /**
     * Creates the User Info Swing frame.
     */
    public UserInfoWindow(String user_name) throws SQLException {
        userName = user_name;
        weightTextField.setEditable(false);
        heightTextField.setEditable(false);
        textField_2.setEditable(false);
        textField_2.setBounds(450, 135, 120, 29);
        textField_2.setColumns(10);
        textField_1.setEditable(false);
        textField_1.setBounds(458, 109, 82, 21);
        textField_1.setColumns(10);
        textField.setEditable(false);
        textField.setText("0");
        textField.setBounds(63, 237, 130, 26);
        textField.setColumns(10);
        initGUI();
    }
    
    /**
     * Initialises the Swing UI for the User Info Window.
     */
    private void initGUI() throws SQLException {
        final Database dbConnect = new Database();
        if(!dbConnect.select_UserName_fromDB(userName,"USERPROFILE")){
            dbConnect.insertIntoUserProfile(userName,0,0,0,"0","0","0");
        }

        ResultSet rs = dbConnect.executeQuery("SELECT PROTIEN, CARB, FAT, CALORIES, HEIGHT, WEIGHT " +
                    "FROM USERPROFILE WHERE USERNAME = " + "'" +userName+ "'");
        setallFields(rs);

        setFont(new Font("Dialog", Font.BOLD, 14));
        setTitle("Fitness.AIO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 618, 430);
        contentPane = new JPanel();
        contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblUsername.setBounds(63, 87, 77, 16);

        contentPane.add(lblUsername);
        lblHeight.setBounds(63, 112, 61, 16);

        contentPane.add(lblHeight);
        lblWeight.setBounds(63, 140, 61, 16);

        contentPane.add(lblWeight);
        heightTextField.setBounds(136, 112, 77, 16);

        contentPane.add(heightTextField);
        lblInches.setBounds(219, 112, 61, 16);

        contentPane.add(lblInches);
        weightTextField.setBounds(136, 140, 77, 16);

        contentPane.add(weightTextField);
        userNameTextField.setEditable(false);
        userNameTextField.setBounds(152, 87, 96, 16);

        contentPane.add(userNameTextField);
        btnProfile.setBounds(0, 0, 117, 29);

        contentPane.add(btnProfile);

        contentPane.add(btnDiary);
        btnSchedule.setBounds(227, 0, 117, 29);
        btnSchedule.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Schedule newSchedWindow = new Schedule(userName);
                newSchedWindow.showSchedule();
            }
        });

        contentPane.add(btnSchedule);
        btnOneRep.setBounds(342, 0, 117, 29);
        btnOneRep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                try {
                    OneRepWindow frame = new OneRepWindow(userName);
                    frame.setVisible(true);
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        });
        contentPane.add(btnOneRep);


        
        JButton btnDiary = new JButton("Diary");
        btnDiary.setBounds(112, 0, 117, 29);
        btnDiary.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                dispose();
                DiaryWindow frame = new DiaryWindow(userName);
                frame.setVisible(true);
            }
        });
        contentPane.add(btnDiary);


        comboBox.setModel(new DefaultComboBoxModel(new String[] {" ","Bench", "Squat", "DeadLift"}));
        comboBox.setBounds(63, 206, 141, 29);

        ActionListener cbActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String s = (String) comboBox.getSelectedItem();//get the selected item
                try {
                    //check for a match
                    if ("Bench".equals(s)) {
                        String benchMax = dbConnect.select_fromDB("BENCH",userName);
                        textField.setText(benchMax);
                    } else if ("Squat".equals(s)) {
                        String squatMax = dbConnect.select_fromDB("SQUAT",userName);
                        textField.setText(squatMax);
                    } else if ("DeadLift".equals(s)) {
                         String deadMax = dbConnect.select_fromDB("DEADLIFT",userName);
                        textField.setText(deadMax);
                    } else {
                        System.out.println("No match selected");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        };

        comboBox.addActionListener(cbActionListener);

        contentPane.add(comboBox);
        lblCurrentMaxes.setBounds(79, 186, 108, 16);

        contentPane.add(lblCurrentMaxes);

        contentPane.add(textField);
        lblLbs.setBounds(203, 242, 61, 16);

        contentPane.add(lblLbs);
        lblDietStatistics.setBounds(436, 87, 96, 16);

        contentPane.add(lblDietStatistics);
        lblCaloireIntake.setBounds(357, 112, 96, 16);

        contentPane.add(lblCaloireIntake);

        contentPane.add(textField_1);
        lblCals.setBounds(538, 112, 61, 16);

        contentPane.add(lblCals);
        lblProteinfatcarbohydrates.setBounds(330, 140, 129, 16);

        contentPane.add(lblProteinfatcarbohydrates);

        contentPane.add(textField_2);
        lblGrams.setBounds(568, 141, 44, 16);

        contentPane.add(lblGrams);
        lblProfile.setBounds(283, 24, 77, 35);

        contentPane.add(lblProfile);

        btnCreateUserProfile.setBounds(300,237,140,29);
        btnCreateUserProfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                FormWindow formWindow = new FormWindow(userName);
                formWindow.setVisible(true);
            }
        });
        contentPane.add(btnCreateUserProfile);


        updateButton.setBounds(300,277,140,29);
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                ResultSet rs = dbConnect.executeQuery("SELECT PROTIEN, CARB, FAT, CALORIES, HEIGHT, WEIGHT " +
                        "FROM USERPROFILE WHERE USERNAME = " + "'" +userName+ "'");
                try {
                    setallFields(rs);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        contentPane.add(updateButton);

    }

    public String getCalorieIntake() {
        return textField_2.getText();
    }
}

