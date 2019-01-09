package main.database;

import java.sql.*;

public class DatabaseManager {
	
	// Database variables
	static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	static String dbName="SpeedArithmetic";
	static String connectionURL = "jdbc:derby:" + dbName + ";create=true";
	static Connection conn = null;
	static Statement statement;
	static DatabaseMetaData meta = null;
	
	static String userTableName = "SPEEDARITHMETIC_USER";
	
	// SQL strings
	static String createUserTableString = "CREATE TABLE " + userTableName + " (USER_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY, USERNAME VARCHAR(250), PRIMARY KEY(USER_ID))";
	
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
		// TEMPORARY
		try {
			statement.executeUpdate("DROP TABLE " + userTableName);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (!userTableExists()) {
			try {
				statement.execute(createUserTableString);
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
	
	private static boolean userTableExists() {
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
	
}
