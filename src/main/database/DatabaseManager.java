package main.database;

import java.sql.*;

public class DatabaseManager {
	
	// Database variables
	static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	static String dbName="SpeedArithmetic";
	static String connectionURL = "jdbc:derby:" + dbName + ";create=true";
	static Connection conn = null;
	static Statement statement;
	static PreparedStatement pstate;
	static DatabaseMetaData meta = null;
	
	static String userTableName = "SPEEDARITHMETIC_USER";
	static String testTableName = "SPEEDARITHMETIC_TEST";
	
	// SQL statements
	static String createUserTableString = "CREATE TABLE " + userTableName + " (USER_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
			+ " USERNAME VARCHAR(250),"
			+ " PRIMARY KEY(USER_ID))";
	static String createTestTableString = "CREATE TABLE " + testTableName + "(TEST_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
			+ " DIFFICULTY VARCHAR(8),"
			+ " NUM_CORRECT_ANSWERS INT,"
			+ " NUM_INCORRECT_ANSWERS INT,"
			+ " USER_ID INT NOT NULL, PRIMARY KEY(TEST_ID),"
			+ " FOREIGN KEY(USER_ID) REFERENCES SPEEDARITHMETIC_USER(USER_ID))";
	static String insertUser = "INSERT INTO " + userTableName + "(USER_ID, USERNAME) VALUES (DEFAULT, ?)";
	
	public static void connect() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(connectionURL);
			statement = conn.createStatement();
			meta = conn.getMetaData();
			
			System.out.println("Connected to " + dbName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void createTable() {
		dropTables();
		if (!tablesExists()) {
			try {
				statement.execute(createUserTableString);
				statement.execute(createTestTableString);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeDatabase() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertUser(String username) {
		try {
			pstate = conn.prepareStatement(insertUser);
			pstate.setString(1, username);
			pstate.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	private static boolean tablesExists() {
		try {
			ResultSet result = meta.getTables(null, null, userTableName, null);
			if (result.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// TEMPORARY
	private static void dropTables() {
		try {
			statement.executeUpdate("DROP TABLE " + testTableName);
			statement.executeUpdate("DROP TABLE " + userTableName);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
}
