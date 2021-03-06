package test;

import org.junit.*;
import org.junit.jupiter.api.Assertions;

import main.question.Question;
import main.question.QuestionLogic;
import main.enumeration.Operator;

public class QuestionTest {
	
	@Test
	public void assignValidOperatorExceptionNotThrown() {
		Question q = new Question();
		q.setOperator(Operator.ADD);
		Assert.assertTrue(true);
	}
	
	@Test
	public void assignValidDifficultyExceptionNotThrown() {
		Question q = new Question();
		q.setDifficulty(1);
		Assert.assertTrue(true);
	}
	
	@Test
	public void assignInvalidDifficultyExceptionThrown() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Question q = new Question();
			q.setDifficulty(4);
		});
	}
	
	@Test
	public void generateNumberCorrectlyBetweenUpperAndLowerBoundExceptionNotThrown() {
		int number = QuestionLogic.generateNumber(1, 11);
		Assert.assertTrue(number >= 1);
		Assert.assertTrue(number <= 10);
	}
	
	@Test
	public void generateNumberIncorrectlyWithSmallerBoundNumberExceptionThrown() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			QuestionLogic.generateNumber(5, 1);
		});
	}
	
	@Test
	public void generateNumberIncorrectlyWithEqualNumbersExceptionThrown() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			QuestionLogic.generateNumber(5, 5);
		});
	}
	
}
