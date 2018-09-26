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
	
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
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
	
	@Override
	public String toString() {
		return firstNumber + " " + operator + " " + secondNumber;
	}
	
}
