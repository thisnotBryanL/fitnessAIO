/**
 * Swing UI to allow users to customise their schedules.
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

public class Schedule {
    protected String userName = "";


    public Schedule(String user_name){
        userName = user_name;
    }

	/**
	 * Shows the Schedule Swing UI window
	 * 
	 * @return void
	 */
    public void showSchedule() {
        JFrame schedule_frame = new JFrame("Schedule");
        JTable schedule_table = new JTable();

        Object[] columns = {"Week", "Sunday", "Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday"};

        final DefaultTableModel schedule_model = new DefaultTableModel();
        schedule_model.setColumnIdentifiers(columns);
        schedule_table.setModel(schedule_model);
        schedule_table.setBackground(Color.white);
        schedule_table.setForeground(Color.black);

        final JLabel add_instr_Label = new JLabel("Specify Week: " +
                "  Then insert Routine Name in textbox under preferred day:");

        add_instr_Label.setBounds(20, 195, 500, 25);

        final JTextField title_text = new JTextField();

        final JTextField week_text = new JTextField();
        final JTextField Sun_text = new JTextField();
        final JTextField Mon_text = new JTextField();
        final JTextField Tues_text = new JTextField();
        final JTextField Wed_text = new JTextField();
        final JTextField Thur_text = new JTextField();
        final JTextField Fri_text = new JTextField();
        final JTextField Sat_text = new JTextField();

        JButton add_week = new JButton("Add New Week");
        add_week.setBounds(20, 255, 200, 25);
        final JButton show_Routines = new JButton("To Routines...");
        show_Routines.setBounds(20, 300, 200, 25);
        show_Routines.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Routine routine = new Routine();
                routine.showRoutine();
            }
        });

        schedule_frame.add(show_Routines);

        JScrollPane schedule_pane = new JScrollPane(schedule_table);
        schedule_pane.setBounds(0,0,880,200);

        schedule_frame.setLayout(null);
        schedule_frame.add(schedule_pane);
        schedule_frame.add(add_instr_Label);

        week_text.setBounds(20, 225, 30, 25);
        Sun_text.setBounds(110, 225, 80, 25);
        Mon_text.setBounds(235, 225, 80, 25);
        Tues_text.setBounds(340, 225, 80, 25);
        Wed_text.setBounds(450, 225, 80, 25);
        Thur_text.setBounds(560, 225, 80, 25);
        Fri_text.setBounds(670, 225, 80, 25);
        Sat_text.setBounds(800, 225, 80, 25);

        schedule_frame.add(add_week);
        schedule_frame.add(week_text);
        schedule_frame.add(Sun_text);
        schedule_frame.add(Mon_text);
        schedule_frame.add(Tues_text);
        schedule_frame.add(Wed_text);
        schedule_frame.add(Thur_text);
        schedule_frame.add(Fri_text);
        schedule_frame.add(Sat_text);

        final Object[] row = new Object[8];
        add_week.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                row[0] = week_text.getText();
                row[1] = Sun_text.getText();
                row[2] = Mon_text.getText();
                row[3] = Tues_text.getText();
                row[4] = Wed_text.getText();
                row[5] = Thur_text.getText();
                row[6] = Fri_text.getText();
                row[7] = Sat_text.getText();

                schedule_model.addRow(row);
            }
        });

        schedule_frame.setSize(900, 400);
        schedule_frame.setLocationRelativeTo(null);
        schedule_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        schedule_frame.setVisible(true);


    }
}
