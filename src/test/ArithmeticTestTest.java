package test;

import org.junit.*;
import org.junit.jupiter.api.Assertions;

import main.arithmetictest.ArithmeticTest;

public class ArithmeticTestTest {
	
	@Test
	public void assignValidOperatorExceptionNotThrown() {
		ArithmeticTest test = new ArithmeticTest(1, "+");
		Assert.assertTrue(true);
	}
	
	@Test
	public void assignInvalidOperatorExceptionThrown() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			ArithmeticTest test = new ArithmeticTest(1, "//");
		});
	}
	
	@Test
	public void assignValidDifficultyExceptionNotThrown() {
		ArithmeticTest test = new ArithmeticTest(1,"+");
		Assert.assertTrue(true);
	}
	
	@Test
	public void assignInvalidDifficultyExceptionThrown() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			ArithmeticTest test = new ArithmeticTest(4, "+");
		});
	}
	
	@Test
	public void generateAdditionQuestionsWithSameOperatorExceptionNotThrown() {
		ArithmeticTest test = new ArithmeticTest(1, "+");
		test.generateAdditionQuestions();
		Assert.assertTrue(true);
	}
	
	@Test
	public void generateAdditionQuestionsWithDifferentOperatorExceptionThrown() {
		Assertions.assertThrows(RuntimeException.class, () -> {
			ArithmeticTest test = new ArithmeticTest(1, "/");
			test.generateAdditionQuestions();
		});
	}
	
}
