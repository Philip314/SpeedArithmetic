package main.database;

import java.sql.*;
import java.util.ArrayList;

import main.arithmetictest.ArithmeticTest;
import main.enumeration.Operator;

public class DatabaseLogic {
	
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
	static String getAllUserTests = "SELECT * FROM " + testTableName + " WHERE USER_ID = ";
	static String insertUser = "INSERT INTO " + userTableName + "(USER_ID, USERNAME) VALUES (DEFAULT, ?)";
	static String insertTest = "INSERT INTO " + testTableName + "(TEST_ID, DIFFICULTY, NUM_CORRECT_ANSWERS, NUM_INCORRECT_ANSWERS, USER_ID) VALUES (DEFAULT, ?, ?, ?, ?)";
	static String selectUserID = "SELECT USER_ID FROM " + userTableName + " WHERE USERNAME = ?";
	
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
	
	public static void insertTest(ArithmeticTest test, String username) {
		try {
			int id = selectUserID(username);
			pstate = conn.prepareStatement(insertTest);
			pstate.setInt(1, test.getDifficulty());
			pstate.setInt(2, test.getNumberOfCorrectAnswers());
			pstate.setInt(3, test.getNumberOfIncorrectAnswers());
			pstate.setInt(4, id);
			pstate.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static ArrayList<ArithmeticTest> getAllUserTests(String username) {
		ResultSet result = null;
		try {
			int id = selectUserID(username);
			pstate = conn.prepareStatement(getAllUserTests + id);
			result = pstate.executeQuery();
		} catch (SQLException el) {
			el.printStackTrace();
		}
		if (result == null) {
			return null;
		} else {
			return convertToTest(result);
		}
	}
	
	private static ArrayList<ArithmeticTest> convertToTest(ResultSet rs) {
		ArrayList<ArithmeticTest> toReturn = new ArrayList<ArithmeticTest>();
		try {
			while (rs.next()) {
				ArithmeticTest test = new ArithmeticTest();
				test.setDifficulty(rs.getInt("DIFFICULTY"));
				test.setNumberOfCorrectAnswers(rs.getInt("NUM_CORRECT_ANSWERS"));
				test.setNumberOfIncorrectAnswers(rs.getInt("NUM_INCORRECT_ANSWERS"));
				toReturn.add(test);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
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
	
	private static int selectUserID(String username) {
		int toReturn = -1;
		try {
			pstate = conn.prepareStatement(selectUserID);
			pstate.setString(1, username);
			ResultSet rs = pstate.executeQuery();
			while(rs.next()) {
				toReturn = rs.getInt(1);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return toReturn;
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
