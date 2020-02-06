package user_interfaceTest;

import database.Database;
import junit.framework.TestCase;
import user_interface.FormWindow;

import java.sql.SQLException;


public class FormWindowTest extends TestCase {
    private FormWindow testFrame;
    private String testUserName = "thisnotbryan";
    Database dbConnect;

    protected void setUp() throws SQLException {
        dbConnect = new Database();
        if(!dbConnect.select_UserName_fromDB(testUserName, "USERS")){
            dbConnect.createDummyAccount(testUserName);
        }
        this.testFrame = new FormWindow(testUserName);
    }


    public void testHeightValue(){
        try {
            this.testFrame.setUserHeight("NotANumber");
            double testingingHeightValue = Double.parseDouble((String)this.testFrame.userHeightField.getValue());
            fail("Exception not thrown");

        }catch (NumberFormatException ignored){}
    }

    public void testWeightValue(){
        try {
            this.testFrame.setUserHeight("NotANumber");
            double testingingHeightValue = Double.parseDouble((String)this.testFrame.userWeightField.getValue());
            fail("Exception not thrown");

        }catch (NumberFormatException ignored){}
    }


    public void testCalorieValue(){
        try {
            this.testFrame.setUserHeight("NotANumber");
            double testingingHeightValue = Double.parseDouble((String)this.testFrame.calorieIntakeField.getValue());
            fail("Exception not thrown");

        }catch (NumberFormatException ignored){}
    }


    protected void tearDown() throws Exception {
        if (this.testFrame != null) {
           // this.testFrame.dispose(  );
            this.testFrame = null;
        }
    }

}

