package main.question;

import java.util.Random;

import main.enumeration.Operator;

public class QuestionLogic {
	
	public static int generateNumber(int lower, int upper) {
		Random random = new Random();
		return random.ints(lower, upper).findFirst().getAsInt();
	}
	
	public static void generateAdditionQuestion(Question question, int difficulty) {
		question.setOperator(Operator.ADD);
		question.setDifficulty(difficulty);
		switch(difficulty) {
		case 1:
			question.setFirstNumber(generateNumber(Question.LOWER, Question.EASY_UPPER));
			question.setSecondNumber(generateNumber(Question.LOWER, Question.EASY_UPPER));
			break;
		case 2:
			question.setFirstNumber(generateNumber(Question.LOWER, Question.MED_UPPER));
			question.setSecondNumber(generateNumber(Question.LOWER, Question.MED_UPPER));
			break;
		case 3:
			question.setFirstNumber(generateNumber(Question.LOWER, Question.HARD_UPPER));
			question.setSecondNumber(generateNumber(Question.LOWER, Question.HARD_UPPER));
			break;
		}
		question.setAnswer(question.getFirstNumber() + question.getSecondNumber());
	}
	
	public static void generateSubtractionQuestion(Question question, int difficulty) {
		question.setOperator(Operator.SUBTRACT);
		question.setDifficulty(difficulty);
		switch(difficulty) {
		case 1:
			question.setFirstNumber(generateNumber(Question.SUBTRACT_LOWER, Question.EASY_UPPER));
			question.setSecondNumber(generateNumber(Question.LOWER, question.getFirstNumber()));
			break;
		case 2:
			question.setFirstNumber(generateNumber(Question.SUBTRACT_LOWER, Question.MED_UPPER));
			question.setSecondNumber(generateNumber(Question.LOWER, question.getFirstNumber()));
			break;
		case 3:
			question.setFirstNumber(generateNumber(Question.SUBTRACT_LOWER, Question.HARD_UPPER));
			question.setSecondNumber(generateNumber(Question.LOWER, question.getFirstNumber()));
			break;
		}
		question.setAnswer(question.getFirstNumber() - question.getSecondNumber());
	}
	
	public static void generateMultiplicationQuestion(Question question, int difficulty) {
		question.setOperator(Operator.MULTIPLY);
		question.setDifficulty(difficulty);
		switch(difficulty) {
		case 1:
			question.setFirstNumber(generateNumber(Question.LOWER, Question.EASY_UPPER));
			question.setSecondNumber(generateNumber(Question.LOWER, Question.EASY_UPPER));
			break;
		case 2:
			question.setFirstNumber(generateNumber(Question.LOWER, Question.MED_UPPER));
			question.setSecondNumber(generateNumber(Question.LOWER, Question.MED_UPPER));
			break;
		case 3:
			question.setFirstNumber(generateNumber(Question.LOWER, Question.HARD_UPPER));
			question.setSecondNumber(generateNumber(Question.LOWER, Question.HARD_UPPER));
			break;
		}
		question.setAnswer(question.getFirstNumber() * question.getSecondNumber());
	}
	
	public static void generateDivisionQuestion(Question question, int difficulty) {
		question.setOperator(Operator.DIVIDE);
		question.setDifficulty(difficulty);
		switch(difficulty) {
		case 1:
			generateDivisibleNumbers(question, generateNumber(Question.LOWER, Question.EASY_UPPER), generateNumber(Question.LOWER, Question.EASY_UPPER), Question.EASY_UPPER);
			break;
		case 2:
			generateDivisibleNumbers(question, generateNumber(Question.LOWER, Question.MED_UPPER), generateNumber(Question.LOWER, Question.MED_UPPER), Question.MED_UPPER);
			break;
		case 3:
			generateDivisibleNumbers(question, generateNumber(Question.LOWER, Question.HARD_UPPER), generateNumber(Question.LOWER, Question.HARD_UPPER), Question.HARD_UPPER);
			break;
		}
		question.setAnswer(question.getFirstNumber() / question.getSecondNumber());
	}
	
	private static void generateDivisibleNumbers(Question question, int first, int second, int upper) {
		if (first % second == 0 && first != second) {
			question.setFirstNumber(first);
			question.setSecondNumber(second);
		} else {
			first = generateNumber(Question.LOWER, upper);
			second = generateNumber(Question.LOWER, upper);
			generateDivisibleNumbers(question, first, second, upper);
		}
	}
	
}
