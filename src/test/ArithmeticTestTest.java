package test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import main.arithmetictest.ArithmeticTest;
import main.arithmetictest.ArithmeticTestLogic;
import main.question.Question;
import main.enumeration.Operator;

public class ArithmeticTestTest {
	
	@Test
	public void testValidDifficultyReturnTrue() {
		Assert.assertTrue(ArithmeticTestLogic.validDifficulty(1));
	}
	
	@Test
	public void testInvalidDifficultyReturnFalse() {
		Assert.assertFalse(ArithmeticTestLogic.validDifficulty(4));
	}
	
	@Test
	public void assignValidOperatorExceptionNotThrown() {
		ArithmeticTest test = new ArithmeticTest();
		test.setDifficulty(1);
		test.setOperator(Operator.ADD);
		Assert.assertTrue(true);
	}
	
	@Test
	public void testValidOperatorReturnTrue() {
		Assert.assertTrue(ArithmeticTestLogic.validateOperator(Operator.ADD, Operator.ADD));
	}
	
	@Test
	public void testInvalidOperatorThrowException() {
		Assert.assertFalse(ArithmeticTestLogic.validateOperator(Operator.ADD, Operator.MULTIPLY));
	}
	
	@Test
	public void generateDivisionQuestionsWithSameOperatorExceptionNotThrown() {
		ArithmeticTest test = new ArithmeticTest();
		test.setDifficulty(1);
		test.setOperator(Operator.DIVIDE);
		test.generateDivisionQuestions();
		Assert.assertTrue(true);
	}
	
	@Test
	public void generateDivisionQuestionsWithDifferentOperatorExceptionThrown() {
		Assertions.assertThrows(RuntimeException.class, () -> {
			ArithmeticTest test = new ArithmeticTest();
			test.setDifficulty(1);
			test.setOperator(Operator.ADD);
			test.generateDivisionQuestions();
		});
	}
	
	@Test
	public void markTestCorrectlySameNumberOfCorrectAndIncorrectAnswers() {
		ArithmeticTest test = new ArithmeticTest();
		test.setDifficulty(1);
		test.setOperator(Operator.ADD);
		test.setQuestions(ArithmeticTestLogic.generateAdditionQuestions(test.getDifficulty()));
		for (Question q : test.getQuestions()) {
			q.setUserAnswer(q.getAnswer());
		}
		test.markTest();
		Assert.assertEquals(10, test.getNumberOfCorrectAnswers());
		Assert.assertEquals(0, test.getNumberOfIncorrectAnswers());
	}
	
}
