package main.user;

import main.arithmetictest.ArithmeticTest;

/**
 * This class handles all the logic of the user class.
 * 
 * @author Philip
 *
 */
public class UserLogic {
	
	/**
	 * The active user of the application.
	 */
	private static User activeUser = null;
	
	/**
	 * Gets the current active user.
	 * 
	 * @return the active user
	 */
	public static User getActiveUser() {
		return activeUser;
	}
	
	/**
	 * Sets the active user.
	 * 
	 * @param user the user which will become active
	 */
	public static void setActiveUser(User user) {
		activeUser = user;
	}
	
	/**
	 * Creates a new user.
	 * 
	 * @param username the name of the user
	 */
	public static void createUser(String username) {
		User user = new User();
		user.setUsername(username);
		setActiveUser(user);
	}
	
	/**
	 * Saves an ArithmeticTest to the current active user.
	 * 
	 * @param test the ArithmeticTest to be saved
	 */
	public static void saveTest(ArithmeticTest test) {
		if (activeUser != null) {
			activeUser.addTest(test);
		}
	}
	
}
