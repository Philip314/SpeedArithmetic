package test;

import org.junit.Assert;
import org.junit.Test;

import main.arithmetictest.ArithmeticTest;
import main.arithmetictest.ArithmeticTestLogic;
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
	
}
