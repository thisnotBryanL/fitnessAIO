package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 * This class is used to access the Apache Derby database and the methods are used
 * to query information from the database
 *
 * @author Jackson O'Donnell, Bryan Lee
 * @version 1.0
 */
public class Database {

	private static Logger logger = Logger.getLogger(Database.class.getName());

	private static final String DB_DRIVER
			= "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String DB_CONNECTION = "jdbc:derby:./fitDB;";

	private static Connection connection = null;
	private static Statement statement = null;

	static {
		logger.info("connecting to data base");
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			connection = DriverManager.getConnection(DB_CONNECTION,
					"", "");
		} catch (SQLException e) {
			logger.severe(e.getMessage());
		}
	}

	/**
	 * This method allows communication with the database by way
	 * of specified SQL statements. This method is specifically for
	 * updating the database and will not return a {@link ResultSet}.
	 *
	 * @param instructions an SQL command to be run
	 * @return True if the SQL command runs properly, False otherwise
	 */
	public static Boolean executeUpdate(String instructions) {
		if (connection != null) {
			try {
				statement = connection.createStatement();
				statement.executeUpdate(instructions);
				return true;
			} catch (SQLException e) {
				logger.severe(e.getMessage());
			}
		} else {
			logger.severe("no database connected");
		}
		return false;
	}

	/**
	 * This method allows communication with the database by way
	 * of specified SQL statements. This method is specifically for
	 * retrieving information from the database and subsequently
	 * returns a {@link ResultSet}
	 *
	 * @param instructions an SQL command to be run
	 * @return A {@link ResultSet} that contains the requested information,
	 * or a null value if the SQL statement fails.
	 */
	public static ResultSet executeQuery(String instructions) {
		ResultSet rs = null;
		System.out.println(instructions);
		if (connection != null) {
			try {
				statement = connection.createStatement();
				rs = statement.executeQuery(instructions);
			} catch (SQLException e) {
				logger.severe(e.getMessage());
			}
		} else {
			logger.severe("no database connected");
		}

		return rs;
	}

	/**
	 * This method allows any SQL statement specified to be run.
	 * This method does not allow the retrieval of information as it
	 * does not return a {@link ResultSet}.
	 *
	 * @param instructions an SQL command to be run
	 * @return Returns True if the SQL command executes properly,
	 * False otherwise.
	 */
	public static Boolean executeInstruction(String instructions) {
		if (connection != null) {
			try {
				statement = connection.createStatement();
				statement.execute(instructions);
				return true;
			} catch (SQLException e) {
				logger.severe(e.getMessage());
			}
		} else {
			logger.severe("no database connected");
		}
		return false;
	}

	/**
	 * This method allows a SQL statement specified to be run.
	 * This method does not allow the retrieval of information as it
	 * does not return a {@link ResultSet}.
	 *
	 * @param userName  the Key used to access the database
	 * @param tableName the SQL table to be accessed
	 * @return Returns True if the SQL command locates the User information
	 * in the given table.
	 */
	public boolean select_UserName_fromDB(String userName, String tableName) throws SQLException {
		Statement statement = null;

		userName = convertStringForDatabase(userName);
		String selectTableSQL = "SELECT USERNAME from " + tableName + " where USERNAME = " + userName;
		System.out.println(selectTableSQL);
		try {
			statement = connection.createStatement();
			// execute select SQL stetement
			ResultSet rs = statement.executeQuery(selectTableSQL);
			if (rs.next() == false) {
				return false;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return true;
	}

	/**
	 * This method allows a SQL statement specified to be run.
	 * This method specifically inserts new User Login information into the database
	 *
	 * @param userName  the Key used to access the database
	 * @param realname  the User's real name
	 * @param password  the User's desire login password
	 * @param tableName the SQL table to be accessed
	 * @return void
	 * @throws SQLException if query is incorrect
	 */
	public void insert_Into_Users_Table(String tableName, String userName, String realname, String password) throws SQLException {
		Statement statement = null;
		userName = convertStringForDatabase(userName);
		String insertTableSQL = "INSERT INTO " + tableName + "(USERNAME, NAME, PASSWORD) " + "VALUES"
				+ "(" + userName + ',' + realname + ',' + password + ")";
		try {
			statement = connection.createStatement();
			System.out.println(insertTableSQL);
			// execute insert SQL stetement
			statement.executeUpdate(insertTableSQL);
			System.out.println("Record is inserted into USERS table!");

		} catch (SQLException e) {
			logger.severe(e.getMessage());
		}
	}


	/**
	 * This method allows a SQL statement specified to be run.
	 * This method checks the UserLogin database to authenticate the password
	 *
	 * @param password the user entered item to be checked
	 * @return Returns True if the SQL command locates the User password
	 * in the given table.
	 * @throws SQLException if query is incorrect
	 */
	public boolean select_Password_fromDB(String password) throws SQLException {
		Statement statement = null;

		password = convertStringForDatabase(password);
		String selectTableSQL = "SELECT PASSWORD from USERS where PASSWORD = " + password;
		try {
			statement = connection.createStatement();
			System.out.println(selectTableSQL);
			// execute select SQL stetement
			ResultSet rs = statement.executeQuery(selectTableSQL);
			if (rs.next() == false) {
				return false;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return true;
	}

	/**
	 * This method allows a SQL statement specified to be run.
	 * This method quickly checks the OneRep database to see if the user is
	 * already in the database
	 *
	 * @param userName the key to be checked
	 * @param exercise also part of the primary key needed to be checked
	 * @return Returns a the amount lifted in the exercise or -1 if the user is not
	 * in the database
	 * @throws SQLException if query is incorrect
	 */
	public String select_fromDB(String exercise, String userName) throws SQLException {
		Statement statement = null;
		String number = "";

		userName = convertStringForDatabase(userName);

		String selectTableSQL = "SELECT " + exercise + " from ONEREP where USERNAME = " + userName;
		try {
			statement = connection.createStatement();
			System.out.println(selectTableSQL);
			// execute select SQL stetement
			ResultSet rs = statement.executeQuery(selectTableSQL);
			if (rs.next() == false) {
				return "-1";
			} else {
				number = rs.getString(exercise);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return number;
	}

	/**
	 * This method allows any SQL statement specified to be run.
	 * This method specifically inserts new User Maximum rep information into the database
	 *
	 * @param username  the Key used to access the database
	 * @param deadlift  exercise type
	 * @param squat     exercise type
	 * @param tableName the SQL table to be accessed
	 * @param bench     exercise type
	 * @return void
	 * @throws SQLException if query is incorrect
	 */
	public void insert_into_onerep_table(String tableName, String bench, String squat, String deadlift, String username) throws SQLException {
		Statement statement = null;
		username = convertStringForDatabase(username);

		String insertTableSQL = "INSERT INTO " + tableName + "(DEADLIFT, SQUAT, BENCH, USERNAME) " + "VALUES"
				+ "(" + bench + ',' + squat + ',' + deadlift + "," + username + ")";
		try {
			statement = connection.createStatement();
			System.out.println(insertTableSQL);
			// execute insert SQL stetement
			statement.executeUpdate(insertTableSQL);
			System.out.println("Record is inserted into ONEREP table!");

		} catch (SQLException e) {
			logger.severe(e.getMessage());
		}
	}

	/**
	 * This method updates the information in the OneRep table with a simple
	 * SQL query.
	 *
	 * @param exercise the type of Exercise to be updated
	 * @param amount   updated Weight that can be lifted
	 * @param userName the key used for the table
	 * @return void
	 * @throws SQLException if query is incorrect
	 */
	public void updateRecordIntoDbUserTable(String exercise, String amount, String userName) throws SQLException {
		Statement statement = null;
		amount = convertStringForDatabase(amount);
		userName = convertStringForDatabase(userName);

		String updateTableSQL = "UPDATE ONEREP" + " SET " + exercise + " = " + amount + " WHERE USERNAME = " + userName;
		try {
			statement = connection.createStatement();
			System.out.println(updateTableSQL);
			// execute update SQL stetement
			statement.execute(updateTableSQL);
			System.out.println("Record is updated to ONEREP table!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * This method allows a SQL statement specified to be run.
	 * This method specifically inserts new User Profile information into the database
	 *
	 * @param userName the Key used to access the database
	 * @param protein  macronutrient in grams
	 * @param carbs    macronutrient in grams
	 * @param fat      macronutrient in grams
	 * @param height   users height
	 * @param caloires users total calorie consumption per day
	 * @return void
	 * @throws SQLException if query is incorrect
	 */
	public void insertIntoUserProfile(String userName, int protein, int carbs, int fat, String height, String weight, String caloires) throws SQLException {
		Statement statement = null;

		height = convertStringForDatabase(height);
		weight = convertStringForDatabase(weight);
		caloires = convertStringForDatabase(caloires);

		String insertTableSQL = ("INSERT INTO USERPROFILE " +
				"(PROTIEN, CARB, FAT, USERNAME,HEIGHT,WEIGHT,CALORIES)" + "VALUES(" + "'" + protein + "'," +
				"'" + carbs + "','" + fat + "','" + userName + "'," + height + ',' + weight + ',' + caloires + ")");
		try {
			statement = connection.createStatement();
			System.out.println(insertTableSQL);
			// execute insert SQL stetement
			statement.executeUpdate(insertTableSQL);
			System.out.println("Record is inserted into USERPROFILE table!");

		} catch (SQLException e) {
			logger.severe(e.getMessage());
		}
	}

	/**
	 * This method updates the information in the User Profile table with a simple
	 * SQL query.
	 *
	 * @param userName the Key used to access the database
	 * @param protein  macronutrient in grams
	 * @param carbs    macronutrient in grams
	 * @param fat      macronutrient in grams
	 * @param height   users height
	 * @param calories users total calorie consumption per day
	 * @param userName the key used for the table
	 * @return void
	 * @throws SQLException if query is incorrect
	 */
	public void updateRecordIntoDbProfileTable(String userName, int protein, int carbs, int fat, String height, String weight, String calories) throws SQLException {
		Statement statement = null;

		userName = convertStringForDatabase(userName);
		height = convertStringForDatabase(height);
		weight = convertStringForDatabase(weight);
		calories = convertStringForDatabase(calories);

		String updateTableSQL = "UPDATE USERPROFILE" + " SET " + "PROTIEN" + " = " + "'" + protein + "',CARB ="
				+ "'" + carbs + "',FAT =" + "'" + fat + "'," + "HEIGHT = " + height + ',' + "WEIGHT = " + weight + ",CALORIES = " +
				calories + " WHERE USERNAME = " + userName;
		try {
			statement = connection.createStatement();
			System.out.println(updateTableSQL);
			// execute update SQL stetement
			statement.execute(updateTableSQL);
			System.out.println("Record is updated to USER PROFILE table!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void createDummyAccount(String userName) throws SQLException {
		if(!select_UserName_fromDB(userName,"USERS")) {
			insert_Into_Users_Table
					("USERS", userName, "'bob'", "'pass'");
		}
	}

	public void deleteAccount(String userName){
		boolean statement = executeInstruction("DELETE FROM USERS WHERE USERNAME = " + "'" + userName +"'");
	}

	/**
	 * This method is used to quickly convert strings into SQL compatible strings
	 *
	 * @param str the string to be converted into SQL string
	 * @return A string that is compatible with SQL commands
	 */
	public String convertStringForDatabase(String str) {
		StringBuilder sb = new StringBuilder(str);
		sb.insert(0, '\'');
		sb.insert(str.length() + 1, '\'');
		return sb.toString();


	}
}
