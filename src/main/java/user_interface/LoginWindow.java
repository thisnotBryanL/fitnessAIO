/**
 * Swing UI to allow users to login.
 *
 * @author Bryan Lee
 * @version 1.0
 */

package user_interface;


import database.Database;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;


public class LoginWindow extends JFrame {
    private final JPanel contentPanel = new JPanel();
    private JTextField usernameField;
    private JPasswordField passwordField;
    private String user_Name = "";
    private Login login;


    /**
     * Launches the application from the Login window (should be main start of app).
     *
     * @param args A list of command line arguments, if there are any
     */
    public static void main(String[] args) {
        try {
            LoginWindow dialog = new LoginWindow();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog for the Login window.
     */
    public LoginWindow() {
        login = new Login();

        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        {
            JLabel lblUsername = new JLabel("UserName");
            lblUsername.setBounds(89, 76, 67, 20);
            contentPanel.add(lblUsername);
        }
        {
            JLabel lblPassword = new JLabel("Password");
            lblPassword.setBounds(89, 119, 63, 20);
            contentPanel.add(lblPassword);
        }

        usernameField = new JTextField();
        usernameField.setBounds(173, 76, 152, 20);
        contentPanel.add(usernameField);
        usernameField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(173, 119, 152, 20);
        contentPanel.add(passwordField);

        JButton btnLogin = new JButton("Login");
        JButton btncreateUser = new JButton("Create New User");

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                try {
                    String inputUserName = usernameField.getText();
                    String inputPassword = passwordField.getText();


                    if (authenicate(inputUserName,inputPassword)) {
                        dispose();
                        user_Name = usernameField.getText();
                        UserInfoWindow frame = new UserInfoWindow(user_Name);
                        frame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Wrong Inputs", "Please Check", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        btncreateUser.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                CreateNewUserWindow newUserWindow =  new CreateNewUserWindow();
                newUserWindow.setVisible(true);
            }
        });

        btnLogin.setBounds(100, 165, 90, 23);
        btncreateUser.setBounds(200, 165, 150, 23);

        contentPanel.add(btnLogin, BorderLayout.EAST);
        contentPanel.add(btncreateUser,BorderLayout.SOUTH);

        JLabel lblLogin = new JLabel("Fitness.AIO");
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblLogin.setBounds(104, 23, 230, 23);
        contentPanel.add(lblLogin);
    }

    public boolean authenicate(String userName, String password) throws SQLException {
        Database dbConnector = new Database();
        if (dbConnector.select_UserName_fromDB(userName,"USERS") && dbConnector.select_Password_fromDB(password)){
            return true;
        }else{
            return false;
        }

    }

    public JTextField getUsernameField(  ) {
        return this.usernameField;
    }

    public JPasswordField getPasswordField(  ) {
        return this.passwordField;
    }
}
