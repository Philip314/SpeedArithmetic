package main.arithmetictest;

import java.util.ArrayList;
import java.util.List;

import main.enumeration.Operator;
import main.question.Question;
import main.question.QuestionLogic;

/**
 * This class is for the logic of ArithmeticTest class.
 * 
 * @author Philip
 *
 */
public class ArithmeticTestLogic {
	
	/**
	 * Validates whether the given integer is a valid difficulty.
	 * 
	 * @param difficulty integer to be validated
	 * @return true if the integer is a valid difficulty, false otherwise
	 */
	public static boolean validDifficulty(int difficulty) {
		if (difficulty == 1 || difficulty == 2 || difficulty == 3) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks whether the two operator are equal to each other.
	 * 
	 * @param operator Operator to be checked
	 * @param expectedOperator Operator which is expected
	 * @return true if they are equal, false otherwise
	 */
	public static boolean validateOperator(Operator operator, Operator expectedOperator) {
		if (operator.equals(expectedOperator)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Generate a list of addition questions.
	 * 
	 * @param difficulty integer to decide the difficulty of the questions
	 * @return list of questions
	 */
	public static List<Question> generateAdditionQuestions(int difficulty) {
		List<Question> generateQuestions = new ArrayList<Question>();
		for (int i=0; i<10; i++) {
			Question q = new Question();
			QuestionLogic.generateAdditionQuestion(q, difficulty);
			generateQuestions.add(q);
		}
		return generateQuestions;
	}
	
	/**
	 * Generate a list of subtraction questions.
	 * 
	 * @param difficulty integer to decide the difficulty of the questions
	 * @return list of questions
	 */
	public static List<Question> generateSubtractionQuestions(int difficulty) {
		List<Question> generateQuestions = new ArrayList<Question>();
		for (int i=0; i<10; i++) {
			Question q = new Question();
			QuestionLogic.generateSubtractionQuestion(q, difficulty);
			generateQuestions.add(q);
		}
		return generateQuestions;
	}
	
	/**
	 * Generate a list of multiplication questions.
	 * 
	 * @param difficulty integer to decide the difficulty of the questions
	 * @return list of questions
	 */
	public static List<Question> generateMultiplicationQuestions(int difficulty) {
		List<Question> generateQuestions = new ArrayList<Question>();
		for (int i=0; i<10; i++) {
			Question q = new Question();
			QuestionLogic.generateMultiplicationQuestion(q, difficulty);
			generateQuestions.add(q);
		}
		return generateQuestions;
	}
	
	/**
	 * Generate a list of division questions.
	 * 
	 * @param difficulty integer to decide the difficulty of the questions
	 * @return list of questions
	 */
	public static List<Question> generateDivisionQuestions(int difficulty) {
		List<Question> generateQuestions = new ArrayList<Question>();
		for (int i=0; i<10; i++) {
			Question q = new Question();
			QuestionLogic.generateDivisionQuestion(q, difficulty);
			generateQuestions.add(q);
		}
		return generateQuestions;
	}
	
	/**
	 * Creates an ArithmeticTest.
	 * 
	 * @param difficulty integer to decide the difficulty of the questions and test
	 * @param operator decides which questions are to be generated
	 * @return ArithmeticTest that was created
	 */
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
	
	/**
	 * Assign the user answers to the ArithmeticTest. Calculates the total number of correct and incorrect answers and assigns that to ArithmeticTest.
	 * 
	 * @param test ArithmeticTest that will be assigned values
	 * @param answers list of user's answers
	 * @return ArithmeticTest that was assigned values
	 */
	public static ArithmeticTest assignAnswers(ArithmeticTest test, List<String> answers) {
		int numberOfCorrectAnswers = 0;
		int numberOfIncorrectAnswers = 0;
		List<Integer> intList = convertStringListToInt(answers);
		for (int i=0; i<test.getQuestions().size(); i++) {
			int userAnswer = intList.get(i);
			int correctAnswer = test.getQuestions().get(i).getAnswer();
			int toAssign = userAnswer;
			if (userAnswer == -1) {
				toAssign = -1;
				numberOfIncorrectAnswers++;
			} else if (correctAnswer != userAnswer) {
				numberOfIncorrectAnswers++;
			} else {
				numberOfCorrectAnswers++;
			}
			test.getQuestions().get(i).setUserAnswer(toAssign);
		}
		test.setNumberOfCorrectAnswers(numberOfCorrectAnswers);
		test.setNumberOfIncorrectAnswers(numberOfIncorrectAnswers);
		return test;
	}
	
	/**
	 * Converts a list of string to a list of integers. If the value is null then -1 is assigned.
	 * 
	 * @param stringList list to be converted
	 * @return list of integer
	 */
	private static List<Integer> convertStringListToInt(List<String> stringList) {
		List<Integer> intList = new ArrayList<Integer>();
		int toAdd = 0;
		for (String e : stringList) {
			try {
				toAdd = Integer.parseInt(e);
			} catch (NumberFormatException exception) {
				toAdd = -1;
			}
			intList.add(toAdd);
		}
		return intList;
	}
	
}
