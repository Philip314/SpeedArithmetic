package main.manager;

import main.user.User;

/*
 * A class to interact with the User class
 */

public class UserManager {
	
	public static User activeUser;
	
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
	
}
