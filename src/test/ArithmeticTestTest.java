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
	
	@Test
	public void generateSubtractionQuestionsWithSameOperatorExceptionNotThrown() {
		ArithmeticTest test = new ArithmeticTest(1, "-");
		test.generateSubtractionQuestions();
		Assert.assertTrue(true);
	}
	
	@Test
	public void generateSubtractionQuestionsWithDifferentOperatorExceptionThrown() {
		Assertions.assertThrows(RuntimeException.class, () -> {
			ArithmeticTest test = new ArithmeticTest(1, "/");
			test.generateSubtractionQuestions();
		});
	}
	
	@Test
	public void generateMultiplicationQuestionsWithSameOperatorExceptionNotThrown() {
		ArithmeticTest test = new ArithmeticTest(1, "*");
		test.generateMultiplicationQuestions();
		Assert.assertTrue(true);
	}
	
	@Test
	public void generateMultiplicationQuestionsWithDifferentOperatorExceptionThrown() {
		Assertions.assertThrows(RuntimeException.class, () -> {
			ArithmeticTest test = new ArithmeticTest(1, "/");
			test.generateMultiplicationQuestions();
		});
	}
	
	@Test
	public void generateDivisionQuestionsWithSameOperatorExceptionNotThrown() {
		ArithmeticTest test = new ArithmeticTest(1, "/");
		test.generateDivisionQuestions();
		Assert.assertTrue(true);
	}
	
	@Test
	public void generateDivisionQuestionsWithDifferentOperatorExceptionThrown() {
		Assertions.assertThrows(RuntimeException.class, () -> {
			ArithmeticTest test = new ArithmeticTest(1, "+");
			test.generateDivisionQuestions();
		});
	}
	
}
