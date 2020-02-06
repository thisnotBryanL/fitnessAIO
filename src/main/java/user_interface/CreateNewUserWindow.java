/**
 * Swing UI for creating a new user.
 *
 * @author Bryan Lee
 * @version 1.0
 */

package user_interface;

import database.Database;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CreateNewUserWindow extends JFrame implements ActionListener {

    private JPanel contentPane;
    private final JLabel lblCreateNewUser = new JLabel("Create New User");
    private final JLabel lblUserName = new JLabel("User Name:");
    private final JFormattedTextField userNameField = new JFormattedTextField();
    private final JLabel lblPassword = new JLabel("Password:");
    private final JPasswordField passwordField = new JPasswordField();
    private final JLabel lblReenterPassword = new JLabel("Re-enter Password:");
    private final JPasswordField passwordField_1 = new JPasswordField();
    private final JLabel lblNamel = new JLabel("Name:");
    private final JFormattedTextField nameField = new JFormattedTextField();
    private final JButton btnNewButton = new JButton("Create Account");

    private String userName = "";
    private String name  = "";

    /**
     * Launches the application from the Create New User window.
     * This is a main function.
     *
     * @param args a list of command line arguments, if there are any
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CreateNewUserWindow frame = new CreateNewUserWindow();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Creates the Create New User Swing frame. Constructor function.
     */
    public CreateNewUserWindow() {
        nameField.setBounds(262, 69, 130, 26);
        nameField.setColumns(10);
        userNameField.setBounds(262, 109, 130, 26);
        userNameField.setColumns(10);
        initGUI();
    }

    /**
     * Initialises the Swing UI for the Create New User window.
     *
     */
    private void initGUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 420);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        lblCreateNewUser.setBounds(235, 6, 117, 24);

        contentPane.add(lblCreateNewUser);
        lblUserName.setBounds(142, 114, 108, 16);

        contentPane.add(lblUserName);

        contentPane.add(userNameField);
        lblPassword.setBounds(142, 166, 74, 16);

        contentPane.add(lblPassword);
        passwordField.setBounds(262, 161, 130, 24);

        contentPane.add(passwordField);
        lblReenterPassword.setBounds(85, 212, 141, 16);

        contentPane.add(lblReenterPassword);
        passwordField_1.setBounds(262, 207, 130, 21);

        contentPane.add(passwordField_1);
        lblNamel.setBounds(155, 74, 61, 16);

        contentPane.add(lblNamel);

        contentPane.add(nameField);
        btnNewButton.setBounds(203, 274, 141, 29);



        userNameField.setValue(userName);
        nameField.setValue(name);

        btnNewButton.addActionListener(this);
        contentPane.add(btnNewButton);

    }

    /**
     * Listens for when the create user button is pressed to insert the new user into
     * the database
     *
     * @param actionEvent ActionEvent that is captured by the ActionListener
     *
     */
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == btnNewButton) {
            Database dbConnector = new Database();

            String user_name = (String) userNameField.getValue();
            String realName = (String) nameField.getValue();
            char[] passwordAttempt1 = passwordField.getPassword();
            char[] passwordAttempt2 = passwordField_1.getPassword();
            String goodPassword = new String(passwordAttempt2);


            goodPassword = dbConnector.convertStringForDatabase(goodPassword);
            realName = dbConnector.convertStringForDatabase(realName);



            if (!Arrays.equals(passwordAttempt1, passwordAttempt2)) {
                JOptionPane.showMessageDialog(null, "Passwords do not match", "Please Check", JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    if(dbConnector.select_UserName_fromDB(user_name,"USERS")){
                        JOptionPane.showMessageDialog(null, "Username is taken!", "Please Check", JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "New User Created!", "User created", JOptionPane.INFORMATION_MESSAGE);
                        dbConnector.insert_Into_Users_Table("USERS",user_name,realName,goodPassword);
                        this.dispose();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

