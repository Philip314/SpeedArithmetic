package main.user;

import main.arithmetictest.ArithmeticTest;

/*
 * A class to interact with the User class
 */

public class UserManager {
	
	private static User activeUser = null;
	
	public static User getActiveUser() {
		return activeUser;
	}
	
	public static void setActiveUser(User user) {
		activeUser = user;
	}
	
	public static void createUser(String username) {
		User user = new User();
		user.setUsername(username);
		setActiveUser(user);
	}
	
	public static void saveTest(ArithmeticTest test) {
		if (activeUser != null) {
			activeUser.addTest(test);
		}
	}
	
}
