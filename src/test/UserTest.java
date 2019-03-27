package test;

import org.junit.*;
import org.junit.jupiter.api.Assertions;

import main.arithmetictest.ArithmeticTest;
import main.arithmetictest.ArithmeticTestLogic;
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
		ArithmeticTest test = new ArithmeticTest();
		test.setDifficulty(1);
		test.setOperator(Operator.ADD);
		test.setQuestions(ArithmeticTestLogic.generateAdditionQuestions(test.getDifficulty()));
		user.addTest(test);
		Assertions.assertEquals(1, user.getTests().size());
	}
	
	@Test
	public void addEmptyTestExceptionThrown() {
		Assertions.assertThrows(NullPointerException.class, () -> {
			User user = new User();
			ArithmeticTest test = new ArithmeticTest();
			test.setDifficulty(1);
			test.setOperator(Operator.ADD);
			user.addTest(test);
		});
	}
	
}
