package user_interfaceTest;

import database.Database;
import junit.framework.TestCase;
import user_interface.LoginWindow;

import javax.swing.*;
import java.sql.SQLException;

public class LoginWindowTest extends TestCase {
    private LoginWindow testFrame;
    protected String testUserAccount = "thisnotbryan";
    protected String testPass = "hi";

    protected void setUp(){
        this.testFrame = new LoginWindow();
    }

    public void testUserName() {
        //Testing for null
        assertEquals("Username Not null", "",
                this.testFrame.getUsernameField().getText(  ));

    }

    public void testPassword() {
        //Testing for null
        assertEquals("Password Not null", "",
                this.testFrame.getPasswordField().getText(  ));

    }

    public void testAuthenticate() throws SQLException {
        Database dbConnect = new Database();
        if(!dbConnect.select_UserName_fromDB(testUserAccount,"USERS"));{
            dbConnect.createDummyAccount(testUserAccount);
        }
        boolean goodLogin = this.testFrame.authenicate(testUserAccount, testPass);
        assertTrue(goodLogin);
    }


    protected void tearDown() throws Exception {
        if (this.testFrame != null) {
            this.testFrame.dispose(  );
            this.testFrame = null;
        }
    }

    public JFrame getTestFrame() {
        if (this.testFrame == null) {
            this.testFrame = new LoginWindow();
        }
        return this.testFrame;
    }
}
