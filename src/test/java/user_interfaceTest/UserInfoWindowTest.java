package user_interfaceTest;

import database.Database;
import junit.framework.TestCase;
import user_interface.UserInfoWindow;

import java.sql.SQLException;

/*
This class tests the user info window
 */
public class UserInfoWindowTest extends TestCase {
    UserInfoWindow testFrame;
    Database dbConnector;
    String testAccount = "thisnotbryan";
    String newAccount = "newAccount";

    public void setUp() throws SQLException {
        dbConnector = new Database();
        dbConnector.createDummyAccount(testAccount);
        this.testFrame = new UserInfoWindow("thisnotbryan");
    }

    public void testCaloireString() throws SQLException {
        String testStr = this.testFrame.getCalorieIntake();


        assertTrue(testStr.contains("/"));

        Database dbConnector = new Database();
        dbConnector.createDummyAccount(newAccount);

        UserInfoWindow newUserWindow = new UserInfoWindow(newAccount);
        testStr = newUserWindow.getCalorieIntake();
        assertEquals("0/0/0", testStr);


        newUserWindow.dispose();

    }


    public void tearDown() throws Exception {
        if (this.testFrame != null) {
            this.testFrame.dispose();
            this.testFrame = null;
        }
        dbConnector.deleteAccount(newAccount);
    }
}
