package test;

import org.junit.*;
import org.junit.jupiter.api.Assertions;

import main.question.Question;

public class QuestionTest {
	
	@Test
	public void assignValidOperatorExceptionNotThrown() {
		Question q = new Question();
		q.setOperator("+");
		Assert.assertTrue(true);
	}
	
	@Test
	public void assignValidOperatorExceptionThrown() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Question q = new Question();
			q.setOperator("//");
		});
	}
	
}
