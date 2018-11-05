package test;

import org.junit.*;
import org.junit.jupiter.api.Assertions;

import main.user.User;

public class UserTest {
	
	@Test
	public void setUsernameExceptionNotThrown() {
		User user = new User();
		user.setUsername("Test");
		Assertions.assertEquals("Test", user.getUsername());
	}
	
	@Test
	public void setEmptyUsernameExceptionThrown() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			User user = new User();
			user.setUsername("");
		});
	}
	
}
