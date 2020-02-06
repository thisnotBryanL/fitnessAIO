package user_interfaceTest;

import database.Database;
import junit.framework.TestCase;
import user_interface.OneRepWindow;

import java.sql.SQLException;

/*
This class tests the one rep max window
 */
public class OneRepMaxWindowTest extends TestCase {
    OneRepWindow testFrame;
    Database dbConnector;
    String testAccount = "thisnotbryan";
    String newAccount = "newAccount";

    public void setUp() throws SQLException {
        dbConnector = new Database();
        dbConnector.createDummyAccount(newAccount);
        this.testFrame = new OneRepWindow("newAccount");
    }

    public void testOneRep() throws SQLException {
        String testStr = dbConnector.select_fromDB("BENCH",newAccount);
        assertEquals("0",testStr);
    }


    public void tearDown() throws Exception {
        if (this.testFrame != null) {
            this.testFrame.dispose();
            this.testFrame = null;
        }
        dbConnector.deleteAccount(newAccount);
    }
}


