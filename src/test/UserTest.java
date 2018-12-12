package test;

import org.junit.*;
import org.junit.jupiter.api.Assertions;

import main.arithmetictest.ArithmeticTest;
import main.enumeration.Operator;
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
	
	@Test
	public void addTestExceptionNotThrown() {
		User user = new User();
		ArithmeticTest test = new ArithmeticTest(1, Operator.ADD);
		test.generateAdditionQuestions();
		user.addTest(test);
		Assertions.assertEquals(1, user.getTests().size());
	}
	
	@Test
	public void addEmptyTestExceptionThrown() {
		Assertions.assertThrows(NullPointerException.class, () -> {
			User user = new User();
			ArithmeticTest test = new ArithmeticTest(1, Operator.ADD);
			user.addTest(test);
		});
	}
	
}
