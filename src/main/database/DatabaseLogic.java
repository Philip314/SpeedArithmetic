package main.database;

import java.sql.*;
import java.util.ArrayList;

import main.arithmetictest.ArithmeticTest;
import main.question.Question;

public class DatabaseLogic {
	
	// Database variables
	static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	static String dbName="SpeedArithmetic";
	static String connectionURL = "jdbc:derby:" + dbName + ";create=true";
	static Connection conn = null;
	static Statement statement;
	static PreparedStatement pstate;
	static DatabaseMetaData meta = null;
	
	static String userTableName = "speed_arithmetic_user";
	static String testTableName = "speed_arithmetic_test";
	static String questionTableName = "speed_arithmetic_question";
	
	// SQL statements
	static String createUserTableString = "CREATE TABLE " + userTableName + " (user_id INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
			+ " username VARCHAR(250),"
			+ " PRIMARY KEY(user_id))";
	static String createTestTableString = "CREATE TABLE " + testTableName + "(test_id INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
			+ " difficulty INT,"
			+ " num_correct_answers INT,"
			+ " num_incorrect_answers INT,"
			+ " user_id INT NOT NULL, PRIMARY KEY(test_id),"
			+ " FOREIGN KEY(user_id) REFERENCES speed_arithmetic_user(user_id))";
	static String createQuestionTableString = String.format("CREATE TABLE %s (question_id INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
			+ "difficulty INT,"
			+ "correct BOOLEAN,"
			+ "first_number INT,"
			+ "second_number INT,"
			+ "answer INT,"
			+ "user_answer INT,"
			+ "PRIMARY KEY(question_id))", questionTableName);
	static String getAllUserTests = "SELECT * FROM " + testTableName + " WHERE user_id = ";
	static String insertUser = "INSERT INTO " + userTableName + "(user_id, username) VALUES (DEFAULT, ?)";
	static String insertTest = "INSERT INTO " + testTableName + "(test_id, difficulty, num_correct_answers, num_incorrect_answers, user_id) VALUES (DEFAULT, ?, ?, ?, ?)";
	static String insertQuestions = String.format("INSERT INTO %s (question_id, difficulty, correct, first_number, second_number, answer, user_answer) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)", questionTableName);
	static String selectUserID = "SELECT user_id FROM " + userTableName + " WHERE username = ?";
	
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
				statement.execute(createQuestionTableString);
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
	
	public static void insertQuestions(ArrayList<Question> questions) {
		try {
			for (Question question : questions) {
				pstate = conn.prepareStatement(insertQuestions);
				pstate.setInt(1, question.getDifficulty());
				pstate.setBoolean(2, question.isCorrect());
				pstate.setInt(3, question.getFirstNumber());
				pstate.setInt(4, question.getSecondNumber());
				pstate.setInt(5, question.getAnswer());
				pstate.setInt(6, question.getUserAnswer());
			} 
		}catch (SQLException e) {
			e.printStackTrace();
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
			statement.executeUpdate("DROP TABLE " + questionTableName);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
}
