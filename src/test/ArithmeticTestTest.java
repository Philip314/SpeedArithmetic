package test;

import org.junit.*;
import org.junit.jupiter.api.Assertions;

import main.arithmetictest.ArithmeticTest;
import main.question.Question;
import main.enumeration.Operator;

public class ArithmeticTestTest {
	
	@Test
	public void assignValidOperatorExceptionNotThrown() {
		ArithmeticTest test = new ArithmeticTest(1, Operator.ADD);
		Assert.assertTrue(true);
	}
	
	@Test
	public void assignValidDifficultyExceptionNotThrown() {
		ArithmeticTest test = new ArithmeticTest(1, Operator.ADD);
		Assert.assertTrue(true);
	}
	
	@Test
	public void assignInvalidDifficultyExceptionThrown() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			ArithmeticTest test = new ArithmeticTest(4, Operator.ADD);
		});
	}
	
	@Test
	public void generateAdditionQuestionsWithSameOperatorExceptionNotThrown() {
		ArithmeticTest test = new ArithmeticTest(1, Operator.ADD);
		test.generateAdditionQuestions();
		Assert.assertTrue(true);
	}
	
	@Test
	public void generateAdditionQuestionsWithDifferentOperatorExceptionThrown() {
		Assertions.assertThrows(RuntimeException.class, () -> {
			ArithmeticTest test = new ArithmeticTest(1, Operator.SUBTRACT);
			test.generateAdditionQuestions();
		});
	}
	
	@Test
	public void generateSubtractionQuestionsWithSameOperatorExceptionNotThrown() {
		ArithmeticTest test = new ArithmeticTest(1, Operator.SUBTRACT);
		test.generateSubtractionQuestions();
		Assert.assertTrue(true);
	}
	
	@Test
	public void generateSubtractionQuestionsWithDifferentOperatorExceptionThrown() {
		Assertions.assertThrows(RuntimeException.class, () -> {
			ArithmeticTest test = new ArithmeticTest(1, Operator.MULTIPLY);
			test.generateSubtractionQuestions();
		});
	}
	
	@Test
	public void generateMultiplicationQuestionsWithSameOperatorExceptionNotThrown() {
		ArithmeticTest test = new ArithmeticTest(1, Operator.MULTIPLY);
		test.generateMultiplicationQuestions();
		Assert.assertTrue(true);
	}
	
	@Test
	public void generateMultiplicationQuestionsWithDifferentOperatorExceptionThrown() {
		Assertions.assertThrows(RuntimeException.class, () -> {
			ArithmeticTest test = new ArithmeticTest(1, Operator.DIVIDE);
			test.generateMultiplicationQuestions();
		});
	}
	
	@Test
	public void generateDivisionQuestionsWithSameOperatorExceptionNotThrown() {
		ArithmeticTest test = new ArithmeticTest(1, Operator.DIVIDE);
		test.generateDivisionQuestions();
		Assert.assertTrue(true);
	}
	
	@Test
	public void generateDivisionQuestionsWithDifferentOperatorExceptionThrown() {
		Assertions.assertThrows(RuntimeException.class, () -> {
			ArithmeticTest test = new ArithmeticTest(1, Operator.ADD);
			test.generateDivisionQuestions();
		});
	}
	
	@Test
	public void markTestCorrectlySameNumberOfCorrectAndIncorrectAnswers() {
		ArithmeticTest test = new ArithmeticTest(1, Operator.ADD);
		test.generateAdditionQuestions();
		for (Question q : test.getQuestions()) {
			q.setUserAnswer(q.getAnswer());
		}
		test.markTest();
		Assert.assertEquals(10, test.getNumberOfCorrectAnswers());
		Assert.assertEquals(0, test.getNumberOfIncorrectAnswers());
	}
	
}
