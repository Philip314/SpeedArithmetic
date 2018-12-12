package main.question;

import java.util.Random;

/*
 * This class is to hold the question for the ArithmeticTest class.
 * It contains variables for each part of a question.
 */

public class Question {
	
	private Operator operator;
	private int difficulty;
	private boolean correct;
	private int firstNumber;
	private int secondNumber;
	private int answer;
	private int userAnswer;
	
	public static final int LOWER = 1;
	public static final int EASY_UPPER = 11;
	public static final int MED_UPPER = 101;
	public static final int HARD_UPPER = 1001;
	
	public static final int SUBTRACT_LOWER = 2;
	
	public enum Operator {
		ADD {
			@Override
			public String toString() {
				return "+";
			}
		},
		SUBTRACT {
			@Override
			public String toString() {
				return "-";
			}
		},
		MULTIPLY {
			@Override
			public String toString() {
				return "*";
			}
		},
		DIVIDE {
			@Override
			public String toString() {
				return "/";
			}
		}
	}
	
	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		if (difficulty == 1 || difficulty == 2 || difficulty == 3) {
			this.difficulty = difficulty;
		} else {
			throw new IllegalArgumentException("Only 1, 2, or 3 is allowed");
		}
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public int getFirstNumber() {
		return firstNumber;
	}

	public void setFirstNumber(int firstNumber) {
		this.firstNumber = firstNumber;
	}

	public int getSecondNumber() {
		return secondNumber;
	}

	public void setSecondNumber(int secondNumber) {
		this.secondNumber = secondNumber;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public int getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(int userAnswer) {
		this.userAnswer = userAnswer;
	}
	
	public int generateNumber(int lower, int upper) {
		
		Random random = new Random();
		
		return random.ints(lower, upper).findFirst().getAsInt();
	}
	
	public void generateAdditionQuestion(int difficulty) {
		setOperator(Operator.ADD);
		setDifficulty(difficulty);
		switch(difficulty) {
		case 1:
			setFirstNumber(generateNumber(LOWER, EASY_UPPER));
			setSecondNumber(generateNumber(LOWER, EASY_UPPER));
			break;
		case 2:
			setFirstNumber(generateNumber(LOWER, MED_UPPER));
			setSecondNumber(generateNumber(LOWER, MED_UPPER));
			break;
		case 3:
			setFirstNumber(generateNumber(LOWER, HARD_UPPER));
			setSecondNumber(generateNumber(LOWER, HARD_UPPER));
			break;
		}
		setAnswer(getFirstNumber() + getSecondNumber());
	}
	
	public void generateSubtractionQuestion(int difficulty) {
		setOperator(Operator.SUBTRACT);
		setDifficulty(difficulty);
		switch(difficulty) {
		case 1:
			setFirstNumber(generateNumber(SUBTRACT_LOWER, EASY_UPPER));
			setSecondNumber(generateNumber(LOWER, getFirstNumber()));
			break;
		case 2:
			setFirstNumber(generateNumber(SUBTRACT_LOWER, MED_UPPER));
			setSecondNumber(generateNumber(LOWER, getFirstNumber()));
			break;
		case 3:
			setFirstNumber(generateNumber(SUBTRACT_LOWER, HARD_UPPER));
			setSecondNumber(generateNumber(LOWER, getFirstNumber()));
			break;
		}
		setAnswer(getFirstNumber() - getSecondNumber());
	}
	
	public void generateMultiplicationQuestion(int difficulty) {
		setOperator(Operator.MULTIPLY);
		setDifficulty(difficulty);
		switch(difficulty) {
		case 1:
			setFirstNumber(generateNumber(LOWER, EASY_UPPER));
			setSecondNumber(generateNumber(LOWER, EASY_UPPER));
			break;
		case 2:
			setFirstNumber(generateNumber(LOWER, MED_UPPER));
			setSecondNumber(generateNumber(LOWER, MED_UPPER));
			break;
		case 3:
			setFirstNumber(generateNumber(LOWER, HARD_UPPER));
			setSecondNumber(generateNumber(LOWER, HARD_UPPER));
			break;
		}
		setAnswer(getFirstNumber() * getSecondNumber());
	}
	
	public void generateDivisionQuestion(int difficulty) {
		setOperator(Operator.DIVIDE);
		setDifficulty(difficulty);
		switch(difficulty) {
		case 1:
			generateDivisibleNumbers(generateNumber(LOWER, EASY_UPPER), generateNumber(LOWER, EASY_UPPER), EASY_UPPER);
			break;
		case 2:
			generateDivisibleNumbers(generateNumber(LOWER, MED_UPPER), generateNumber(LOWER, MED_UPPER), MED_UPPER);
			break;
		case 3:
			generateDivisibleNumbers(generateNumber(LOWER, HARD_UPPER), generateNumber(LOWER, HARD_UPPER), HARD_UPPER);
			break;
		}
		setAnswer(getFirstNumber() / getSecondNumber());
	}
	
	private void generateDivisibleNumbers(int first, int second, int upper) {
		if (first % second == 0 && first != second) {
			setFirstNumber(first);
			setSecondNumber(second);
		} else {
			first = generateNumber(LOWER, upper);
			second = generateNumber(LOWER, upper);
			generateDivisibleNumbers(first, second, upper);
		}
	}
	
	public void markQuestion() {
		if (userAnswer == answer) {
			correct = true;
		} else {
			correct = false;
		}
	}
	
	@Override
	public String toString() {
		switch(operator) {
			case ADD:
				return firstNumber + " " + operator + " " + secondNumber;
				//return firstNumber + " + " + secondNumber;
			case SUBTRACT:
				return firstNumber + " - " + secondNumber;
			case MULTIPLY:
				return firstNumber + " * " + secondNumber;
			case DIVIDE:
				return firstNumber + " / " + secondNumber;
			default:
				return null;
		}
	}
	
}
