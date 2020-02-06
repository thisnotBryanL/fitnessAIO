/**
 * Swing UI to show the user their One-Rep-Max values.
 *
 * @author Bryan Lee
 * @version 1.0
 */

package user_interface;

import database.Database;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class OneRepWindow extends JFrame implements ActionListener {

    private JPanel contentPane;

    private final JButton btnDiary = new JButton("Diary");
    private final JButton btnSchedule = new JButton("Schedule");
    private final JButton btnOneRep = new JButton("One-Rep-Max");
    private final JLabel lblOneRepMax = new JLabel("One Rep Max");
    private final JLabel lblExercise = new JLabel("Exercise:");
    protected String[] exercise = {"","Bench", "Squat", "DeadLift"};
    private final JComboBox comboBox = new JComboBox(exercise);
    private final JFormattedTextField currentMaxField = new JFormattedTextField();
    private final JLabel lblCurrentMax = new JLabel("Current Max:");
    private final JLabel lblNewMax = new JLabel("New Max:");
    private final JFormattedTextField frmtdtxtfldEnterNewMax = new JFormattedTextField();
    private final JButton btnCalculateNewMax = new JButton("Calculate New Max");
    private final JFormattedTextField formattedTextField_1 = new JFormattedTextField();
    private final JFormattedTextField formattedTextField_2 = new JFormattedTextField();
    private final JFormattedTextField formattedTextField_3 = new JFormattedTextField();
    private final JFormattedTextField formattedTextField_4 = new JFormattedTextField();
    private final JFormattedTextField formattedTextField_5 = new JFormattedTextField();
    private final JLabel lblMaxPercents = new JLabel("Max Percents:");
    private final JLabel label = new JLabel("50%");
    private final JLabel label_1 = new JLabel("60%");
    private final JLabel label_2 = new JLabel("65%");
    private final JLabel label_3 = new JLabel("75%");
    private final JLabel label_4 = new JLabel("85%");
    private final JTextArea txtrDirections = new JTextArea();

    protected String user_name = "";
    protected String deadMax = "";
    protected String benchMax = "";
    protected String squatMax = "";

    protected Database dbConnectToOneRep = new Database();


    /**
     * Create the Swing UI for the One Rep frame.
     */
    public OneRepWindow(String username) throws SQLException {
        user_name = username;

        initGUI();
    }

    private void initGUI() throws SQLException {

        if(dbConnectToOneRep.select_fromDB("BENCH", user_name).equals("-1")){
            dbConnectToOneRep.insert_into_onerep_table("ONEREP","'0'","'0'","'0'",user_name);
        }


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnProfile = new JButton("Profile");
        btnProfile.setBounds(0, 0, 117, 29);
        btnProfile.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                UserInfoWindow frame = null;
                try {
                    frame = new UserInfoWindow(user_name);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                frame.setVisible(true);
            }
        });
        contentPane.add(btnProfile);

        JButton btnDiary = new JButton("Diary");
        btnDiary.setBounds(112, 0, 117, 29);
        btnDiary.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                DiaryWindow frame = new DiaryWindow(user_name);
                frame.setVisible(true);
            }
        });
        contentPane.add(btnDiary);

        btnDiary.setBounds(114, 0, 117, 29);

        contentPane.add(btnDiary);
        btnSchedule.setBounds(228, 0, 117, 29);
        btnSchedule.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Schedule newSchedWindow = new Schedule(user_name);
                newSchedWindow.showSchedule();
            }
        });

        contentPane.add(btnSchedule);
        btnOneRep.setBounds(340, 0, 117, 29);

        contentPane.add(btnOneRep);

        lblOneRepMax.setBounds(253, 41, 86, 16);

        contentPane.add(lblOneRepMax);
        lblExercise.setBounds(72, 77, 61, 16);

        contentPane.add(lblExercise);
        comboBox.setBounds(43, 96, 117, 27);

        currentMaxField.setText("100");
        currentMaxField.setBounds(100, 125, 107, 29);

        ActionListener cbActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String s = (String) comboBox.getSelectedItem();//get the selected item
                try {
                    //check for a match
                    if ("Bench".equals(s)) {
                        benchMax = dbConnectToOneRep.select_fromDB("BENCH",user_name);
                        currentMaxField.setText(benchMax);
                        convertPercentages();
                    } else if ("Squat".equals(s)) {
                        squatMax = dbConnectToOneRep.select_fromDB("SQUAT",user_name);
                        currentMaxField.setText(squatMax);
                        convertPercentages();
                    } else if ("DeadLift".equals(s)) {
                        deadMax = dbConnectToOneRep.select_fromDB("DEADLIFT",user_name);
                        currentMaxField.setText(deadMax);
                        convertPercentages();
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
        currentMaxField.setEditable(false);

        contentPane.add(currentMaxField);
        lblCurrentMax.setBounds(17, 125, 86, 29);

        contentPane.add(lblCurrentMax);
        lblNewMax.setBounds(17, 158, 61, 29);

        contentPane.add(lblNewMax);
        frmtdtxtfldEnterNewMax.setText("0");
        frmtdtxtfldEnterNewMax.setBounds(100, 158, 107, 29);

        contentPane.add(frmtdtxtfldEnterNewMax);
        btnCalculateNewMax.setBounds(16, 190, 175, 29);

        btnCalculateNewMax.addActionListener(this);

        contentPane.add(btnCalculateNewMax);
        formattedTextField_1.setEditable(false);
        formattedTextField_1.setText("0");
        formattedTextField_1.setBounds(17, 302, 86, 29);

        contentPane.add(formattedTextField_1);
        formattedTextField_2.setEditable(false);
        formattedTextField_2.setText("0");
        formattedTextField_2.setBounds(114, 302, 86, 28);

        contentPane.add(formattedTextField_2);
        formattedTextField_3.setEditable(false);
        formattedTextField_3.setText("0");
        formattedTextField_3.setBounds(228, 302, 91, 28);

        contentPane.add(formattedTextField_3);
        formattedTextField_4.setEditable(false);
        formattedTextField_4.setText("0");
        formattedTextField_4.setBounds(340, 302, 86, 29);

        contentPane.add(formattedTextField_4);
        formattedTextField_5.setEditable(false);
        formattedTextField_5.setText("0");
        formattedTextField_5.setBounds(451, 302, 86, 28);

        contentPane.add(formattedTextField_5);
        lblMaxPercents.setBounds(253, 237, 102, 16);

        contentPane.add(lblMaxPercents);
        label.setBounds(27, 281, 61, 16);

        contentPane.add(label);
        label_1.setBounds(130, 281, 61, 16);

        contentPane.add(label_1);
        label_2.setBounds(246, 281, 61, 16);

        contentPane.add(label_2);
        label_3.setBounds(357, 281, 61, 16);

        contentPane.add(label_3);
        label_4.setBounds(465, 281, 61, 16);

        contentPane.add(label_4);


        txtrDirections.append("Directions: \n" +
                "1) Select what type of exercise \n  you want to measure\n" +
                "2) Enter in the maximum amount of \n   weight you can lift for one rep\n" +
                "3) For optimum workouts, use \n    the percentages listed below as a guide \n  for amount of weights to use!");
        txtrDirections.setEditable(false);
        txtrDirections.setBounds(250, 70, 320, 150);


        contentPane.add(txtrDirections);
    }

    /**
     * Listens for when the calculate new max button is pressed to insert the new info into
     * the database
     *
     * @param actionEvent ActionEvent that is captured by the ActionListener
     * @return void
     */
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == btnCalculateNewMax){
            currentMaxField.setText(frmtdtxtfldEnterNewMax.getText());


            convertPercentages();

            //UPDATE DATABASE BY CHANGING TUPLE
            String exerciseToChange = (String) comboBox.getSelectedItem();
            if(exerciseToChange.equals("")){
                JOptionPane.showMessageDialog(null, "Please select an exercise from the combo box!",
                        "Please Select an exercise", JOptionPane.WARNING_MESSAGE);
            }else {
                try {
                    dbConnectToOneRep.updateRecordIntoDbUserTable(exerciseToChange.toUpperCase(), frmtdtxtfldEnterNewMax.getText(),
                            user_name);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * Calculates the diff percentages and fills them into the corresponding text field and
     * rounds to the nearest multiple of 5 since weights are in 5lb increments
     *
     */
    public void convertPercentages(){
        int newMax = Integer.parseInt(currentMaxField.getText());

        int percent85 = (int)Math.round(newMax * .85);
        int percent75 = (int)Math.round(newMax * .75);
        int percent65 = (int)Math.round(newMax * .65);
        int percent60 = (int)Math.round(newMax * .60);
        int percent50 = (int)Math.round(newMax * .50);

        percent85 = 5*(Math.round(percent85/5));
        percent75 = 5*(Math.round(percent75/5));
        percent65 = 5*(Math.round(percent65/5));
        percent60 = 5*(Math.round(percent60/5));
        percent50 = 5*(Math.round(percent50/5));

        formattedTextField_1.setText(String.valueOf(percent50) + " lbs");
        formattedTextField_2.setText(String.valueOf(percent60) + " lbs");
        formattedTextField_3.setText(String.valueOf(percent65) + " lbs");
        formattedTextField_4.setText(String.valueOf(percent75) + " lbs");
        formattedTextField_5.setText(String.valueOf(percent85) + " lbs");
    }

}

