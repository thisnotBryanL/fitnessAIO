/**
 * Logic to allow users to log in.
 * 
 * @author Bryan Lee
 * @version 1.0
 */

package user_interface;

public class Login {
    private String userName;
    private String passWord;

    /**
     * Holds the logic to test if the user's entered information is valid
     * 
     * @return boolean True: the information is valid, False: the information is invalid
     */
    private boolean authenicate(){
        boolean login_is_good = false;



        return login_is_good;
    }

    /**
     * Gets the username from the username field.
     * 
     * @return String containing the user's username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user's username to the specified value.
     * 
     * @param userName A String object containing the desired username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the user's password entry
     * 
     * @return A String object containing the user's password
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * Sets the user's password
     * 
     * @param passWord A String object containing the desired password
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
