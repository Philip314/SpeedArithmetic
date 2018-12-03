package main.arithmetictest;

import java.util.ArrayList;

public class ArithmeticTestManager {
	
	public static ArithmeticTest createTest(int difficulty, String operator) {
		ArithmeticTest toReturn = new ArithmeticTest(difficulty, operator);
		switch(operator) {
			case "+":
				toReturn.generateAdditionQuestions();
				break;
			case "-":
				toReturn.generateSubtractionQuestions();
				break;
			case "*":
				toReturn.generateMultiplicationQuestions();
				break;
			case "/":
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
