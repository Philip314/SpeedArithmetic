package main.user;

import main.arithmetictest.ArithmeticTest;

/*
 * This class is for the logic of user class.
 */

public class UserLogic {
	
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
