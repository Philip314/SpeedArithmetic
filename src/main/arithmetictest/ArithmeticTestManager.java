package main.arithmetictest;

import java.util.ArrayList;

import main.enumeration.Operator;

/*
 * This class is for methods that interact with the ArithmeticTest class.
 */

public class ArithmeticTestManager {
	
	public static ArithmeticTest createTest(int difficulty, Operator operator) {
		ArithmeticTest toReturn = new ArithmeticTest(difficulty, operator);
		switch(operator) {
			case ADD:
				toReturn.generateAdditionQuestions();
				break;
			case SUBTRACT:
				toReturn.generateSubtractionQuestions();
				break;
			case MULTIPLY:
				toReturn.generateMultiplicationQuestions();
				break;
			case DIVIDE:
				toReturn.generateDivisionQuestions();
				break;
			default:
				break;
		}
		return toReturn;
	}
	
	public static ArithmeticTest assignAnswers(ArithmeticTest test, ArrayList<String> answers) {
		for (int i=0; i<test.getQuestions().size(); i++) {
			if (answers.get(i) == null) {
				test.getQuestions().get(i).setUserAnswer(-1);
			} else {
				test.getQuestions().get(i).setUserAnswer(Integer.parseInt(answers.get(i)));
			}
		}
		return test;
	}
	
}
