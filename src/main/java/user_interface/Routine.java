/**
 * Swing UI for letting users customise routines.
 * 
 * @author Trent Strickland
 * @version 1.0
 */

package user_interface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Routine{
	
	/**
	 * Creates a routine object.
	 * 
	 * @return void
	 */
    public void showRoutine(){
        final JFrame frame = new JFrame("Routines");
        JTable routineTable = new JTable();

        Object[] columns = {"Exercise", "Type", "Sets", "Weight", "Reps"};
        final DefaultTableModel routineModel = new DefaultTableModel();
        routineModel.setColumnIdentifiers(columns);
        routineTable.setModel(routineModel);
        routineTable.setBackground(Color.white);
        routineTable.setForeground(Color.black);

        final JTextField exercise_text = new JTextField();
        final JTextField type_text = new JTextField();
        final JTextField sets_text = new JTextField();
        final JTextField weight_text = new JTextField();
        final JTextField reps_text = new JTextField();

        final JLabel exercise_label = new JLabel("Exercise: ");
        final JLabel type_label = new JLabel("Type: ");
        final JLabel sets_label = new JLabel("Sets: ");
        final JLabel weight_label = new JLabel("Weight: ");
        final JLabel reps_label = new JLabel("Reps: ");

        exercise_label.setBounds(20, 205, 100, 25);
        type_label.setBounds(140, 205, 100, 25);
        sets_label.setBounds(260, 205, 100, 25);
        weight_label.setBounds(380, 205, 100, 25);
        reps_label.setBounds(500, 205, 100, 25);

        JButton add_Button = new JButton("Add Exercise");
        JButton build_Routine_Button = new JButton("Build New Routine");
        JButton gen_Routine_Button = new JButton("Generate New Routine");

        exercise_text.setBounds(20, 225, 100, 25);
        type_text.setBounds(140, 225, 100, 25);
        sets_text.setBounds(260, 225, 100, 25);
        weight_text.setBounds(380, 225, 100, 25);
        reps_text.setBounds(500, 225, 100, 25);

        add_Button.setBounds(20, 255, 200, 25);
        build_Routine_Button.setBounds(150, 325, 200, 25);
        gen_Routine_Button.setBounds(400, 325, 200, 25);

        JScrollPane routinePane = new JScrollPane(routineTable);
        routinePane.setBounds(0, 0, 880, 200);

        frame.setLayout(null);

        frame.add(routinePane);

        frame.add(exercise_label);
        frame.add(type_label);
        frame.add(sets_label);
        frame.add(weight_label);
        frame.add(reps_label);

        frame.add(exercise_text);
        frame.add(type_text);
        frame.add(sets_text);
        frame.add(weight_text);
        frame.add(reps_text);

        frame.add(add_Button);
        frame.add(build_Routine_Button);
        frame.add(gen_Routine_Button);

        final Object[] row = new Object[5];
        add_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                row[0] = exercise_text.getText();
                row[1] = type_text.getText();
                row[2] = sets_text.getText();
                row[3] = weight_text.getText();
                row[4] = reps_text.getText();

                routineModel.addRow(row);
            }
        });

        gen_Routine_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Bulk","Cut"};
                int n = JOptionPane.showOptionDialog(frame,
                        "Please select Routine Goal...","Type Selection",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, options[1]);
                if (n == JOptionPane.YES_OPTION){
                    row[0] = "Incline";
                    row[1] = "Bench";
                    row[2] = 6;
                    row[3] = "85%";
                    row[4] = "4-6";
                    routineModel.addRow(row);
                }else if( n == JOptionPane.NO_OPTION){
                    row[0] = "Lunges";
                    row[1] = "Squat";
                    row[2] = 4;
                    row[3] = "65%";
                    row[4] = "8-12";
                    routineModel.addRow(row);
                }
            }
        });

        frame.setSize(900, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);


    }

}

