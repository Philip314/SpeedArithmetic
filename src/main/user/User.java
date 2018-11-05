package main.user;

public class User {
	
	private String username;

	public User() {
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		if (username.equals("")) {
			throw new IllegalArgumentException("Username cannot be empty");
		}
		this.username = username;
	}

}
