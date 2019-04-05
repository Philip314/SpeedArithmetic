package main.question;

import main.enumeration.Operator;

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
