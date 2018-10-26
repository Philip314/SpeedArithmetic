package main.question;

import java.util.Random;

public class Question {
	
	private String operator;
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
	
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		if (operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/")) {
			this.operator = operator;
		} else {
			throw new IllegalArgumentException("Only +, -, *, or / is allowed");
		}
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
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
		setOperator("+");
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
		setOperator("-");
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
		setOperator("*");
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
		setOperator("/");
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
		return firstNumber + " " + operator + " " + secondNumber;
	}
	
}
