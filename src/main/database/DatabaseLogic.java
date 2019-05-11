package main.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import main.arithmetictest.ArithmeticTest;
import main.question.Question;

/**
 * This class handles all the database operations.
 * 
 * @author Philip
 *
 */
public class DatabaseLogic {
	
	// Database variables
	static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	static String dbName="SpeedArithmetic";
	static String connectionURL = "jdbc:derby:" + dbName + ";create=true";
	static Connection conn = null;
	static Statement statement;
	static PreparedStatement pstate;
	static DatabaseMetaData meta = null;
	
	// Table names
	static String userTableName = "speed_arithmetic_user";
	static String testTableName = "speed_arithmetic_test";
	static String questionTableName = "speed_arithmetic_question";
	
	// SQL statements
	// Create strings
	static String createUserTableString = String.format("CREATE TABLE %s (user_id INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
			+ "username VARCHAR(250),"
			+ "PRIMARY KEY(user_id))", userTableName);
	static String createTestTableString = String.format("CREATE TABLE %s (test_id INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
			+ "difficulty INT,"
			+ "num_correct_answers INT,"
			+ "num_incorrect_answers INT,"
			+ "user_id INT NOT NULL,"
			+ "PRIMARY KEY(test_id),"
			+ "FOREIGN KEY(user_id) REFERENCES %s (user_id))", testTableName, userTableName);
	static String createQuestionTableString = String.format("CREATE TABLE %s (question_id INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
			+ "difficulty INT,"
			+ "correct BOOLEAN,"
			+ "first_number INT,"
			+ "second_number INT,"
			+ "answer INT,"
			+ "user_answer INT,"
			+ "test_id INT NOT NULL,"
			+ "PRIMARY KEY(question_id),"
			+ "FOREIGN KEY(test_id) REFERENCES %s (test_id))", questionTableName, testTableName);
	// Select strings
	static String getAllUserTests = String.format("SELECT * FROM %s WHERE user_id = ?", testTableName);
	static String getLatestTestID = String.format("SELECT MAX(test_id) FROM %s", testTableName);
	static String selectUserID = String.format("SELECT user_id FROM %s WHERE username = ?", userTableName);
	// Insert strings
	static String insertUser = String.format("INSERT INTO %s (user_id, username) VALUES (DEFAULT, ?)", userTableName) ;
	static String insertTest = String.format("INSERT INTO %s (test_id, difficulty, num_correct_answers, num_incorrect_answers, user_id) VALUES (DEFAULT, ?, ?, ?, ?)", testTableName);
	static String insertQuestions = String.format("INSERT INTO %s (question_id, difficulty, correct, first_number, second_number, answer, user_answer, test_id) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?)", questionTableName);
	
	/**
	 * Connects to the database.
	 */
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
	
	/**
	 * Creates all the tables and delete existing tables.
	 */
	public static void createTable() {
		dropTables();
		if (!tablesExists()) {
			try {
				statement.execute(createUserTableString);
				statement.execute(createTestTableString);
				statement.execute(createQuestionTableString);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Closes the database.
	 */
	public static void closeDatabase() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Inserts a user with the given username
	 * 
	 * @param username string of the user's name
	 */
	public static void insertUser(String username) {
		try {
			pstate = conn.prepareStatement(insertUser);
			pstate.setString(1, username);
			pstate.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Inserts an ArithmeticTest that belongs to a user.
	 * 
	 * @param test ArithmeticTest to be inserted
	 * @param username string of username which the test belongs to
	 */
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
	
	/**
	 * Inserts a list of question.
	 * 
	 * @param questions list of questions to be inserted
	 */
	public static void insertQuestions(List<Question> questions) {
		try {
			for (Question question : questions) {
				pstate = conn.prepareStatement(insertQuestions);
				pstate.setInt(1, question.getDifficulty());
				pstate.setBoolean(2, question.isCorrect());
				pstate.setInt(3, question.getFirstNumber());
				pstate.setInt(4, question.getSecondNumber());
				pstate.setInt(5, question.getAnswer());
				pstate.setInt(6, question.getUserAnswer());
				pstate.setInt(7, getLatestTestID());
			} 
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets all the ArithmeticTest that belong to a user.
	 * 
	 * @param username user that owns all requested ArithmeticTests
	 * @return list of ArithmeticTest
	 */
	public static List<ArithmeticTest> getAllUserTests(String username) {
		ResultSet result = null;
		try {
			int id = selectUserID(username);
			pstate = conn.prepareStatement(getAllUserTests);
			pstate.setInt(1, id);
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
	
	/**
	 * Gets the id of the latest ArithmeticTest that was inserted.
	 * 
	 * @return int id of the latest ArithmeticTest
	 */
	private static int getLatestTestID() {
		ResultSet result = null;
		int toReturn = 0;
		try {
			pstate = conn.prepareStatement(getLatestTestID);
			result = pstate.executeQuery();
			while (result.next()) {
				toReturn = result.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	
	/**
	 * Converts a ResultSet of ArithmeticTest to a list of ArithmeticTest.
	 * 
	 * @param rs ResultSet of the ArithmeticTest
	 * @return list of ArithmeticTest
	 */
	private static List<ArithmeticTest> convertToTest(ResultSet rs) {
		List<ArithmeticTest> toReturn = new ArrayList<ArithmeticTest>();
		try {
			while (rs.next()) {
				ArithmeticTest test = new ArithmeticTest();
				test.setDifficulty(rs.getInt("difficulty"));
				test.setNumberOfCorrectAnswers(rs.getInt("num_correct_answers"));
				test.setNumberOfIncorrectAnswers(rs.getInt("num_incorrect_answers"));
				toReturn.add(test);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	
	/**
	 * Checks if a table exist.
	 * 
	 * @return true if the table exists, false otherwise
	 */
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
	
	/**
	 * Gets the id of a user from the given username.
	 * 
	 * @param username string username that the id is needed from
	 * @return int id of the user
	 */
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
	
	/**
	 * Drops all the tables.
	 */
	private static void dropTables() {
		try {
			statement.executeUpdate("DROP TABLE " + questionTableName);
			statement.executeUpdate("DROP TABLE " + testTableName);
			statement.executeUpdate("DROP TABLE " + userTableName);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
}
