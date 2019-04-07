package main.user;

import java.util.ArrayList;
import java.util.List;

import main.arithmetictest.ArithmeticTest;

public class User {
	
	private String username;
	private List<ArithmeticTest> tests = new ArrayList<ArithmeticTest>();

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
	
	public List<ArithmeticTest> getTests() {
		return tests;
	}
	
	public void addTest(ArithmeticTest test) {
		if (test.getQuestions().isEmpty()) {
			throw new NullPointerException("Arithmetic test cannot be empty");
		}
		tests.add(test);
	}

}
