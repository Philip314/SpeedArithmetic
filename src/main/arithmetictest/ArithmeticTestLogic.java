package main.arithmetictest;

import java.util.ArrayList;
import java.util.List;

import main.enumeration.Operator;
import main.question.Question;
import main.question.QuestionLogic;

/*
 * This class is for the logic of ArithmeticTest class.
 */

public class ArithmeticTestLogic {
	
	public static boolean validDifficulty(int difficulty) {
		if (difficulty == 1 || difficulty == 2 || difficulty == 3) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean validateOperator(Operator operator, Operator expectedOperator) {
		if (operator.equals(expectedOperator)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static List<Question> generateAdditionQuestions(int difficulty) {
		List<Question> generateQuestions = new ArrayList<Question>();
		for (int i=0; i<10; i++) {
			Question q = new Question();
			QuestionLogic.generateAdditionQuestion(q, difficulty);
			generateQuestions.add(q);
		}
		return generateQuestions;
	}
	
	public static List<Question> generateSubtractionQuestions(int difficulty) {
		List<Question> generateQuestions = new ArrayList<Question>();
		for (int i=0; i<10; i++) {
			Question q = new Question();
			QuestionLogic.generateSubtractionQuestion(q, difficulty);
			generateQuestions.add(q);
		}
		return generateQuestions;
	}
	
	public static List<Question> generateMultiplicationQuestions(int difficulty) {
		List<Question> generateQuestions = new ArrayList<Question>();
		for (int i=0; i<10; i++) {
			Question q = new Question();
			QuestionLogic.generateMultiplicationQuestion(q, difficulty);
			generateQuestions.add(q);
		}
		return generateQuestions;
	}
	
	public static List<Question> generateDivisionQuestions(int difficulty) {
		List<Question> generateQuestions = new ArrayList<Question>();
		for (int i=0; i<10; i++) {
			Question q = new Question();
			QuestionLogic.generateDivisionQuestion(q, difficulty);
			generateQuestions.add(q);
		}
		return generateQuestions;
	}
	
	public static ArithmeticTest createTest(int difficulty, Operator operator) {
		ArithmeticTest toReturn = new ArithmeticTest();
		toReturn.setDifficulty(difficulty);
		toReturn.setOperator(operator);
		switch(operator) {
			case ADD:
				toReturn.setQuestions(generateAdditionQuestions(difficulty));
				break;
			case SUBTRACT:
				toReturn.setQuestions(generateSubtractionQuestions(difficulty));
				break;
			case MULTIPLY:
				toReturn.setQuestions(generateMultiplicationQuestions(difficulty));
				break;
			case DIVIDE:
				toReturn.setQuestions(generateDivisionQuestions(difficulty));
				break;
			default:
				break;
		}
		return toReturn;
	}
	
	public static ArithmeticTest assignAnswers(ArithmeticTest test, List<String> answers) {
		int numberOfCorrectAnswers = 0;
		int numberOfIncorrectAnswers = 0;
		for (int i=0; i<test.getQuestions().size(); i++) {
			if (answers.get(i) == null) {
				test.getQuestions().get(i).setUserAnswer(-1);
				numberOfIncorrectAnswers++;
			} else {
				test.getQuestions().get(i).setUserAnswer(Integer.parseInt(answers.get(i)));
				numberOfCorrectAnswers++;
			}
		}
		test.setNumberOfCorrectAnswers(numberOfCorrectAnswers);
		test.setNumberOfIncorrectAnswers(numberOfIncorrectAnswers);
		return test;
	}
	
}
