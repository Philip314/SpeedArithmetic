package main.test;

import java.util.ArrayList;

import main.question.Question;

public class Test {
	
	private int difficulty;
	private String operator;
	private ArrayList<Question> question;
	private int numberOfCorrectAnswers;
	private int numberOfIncorrectAnswers;
	
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public ArrayList<Question> getQuestion() {
		return question;
	}
	public void setQuestion(ArrayList<Question> question) {
		this.question = question;
	}
	public int getNumberOfCorrectAnswers() {
		return numberOfCorrectAnswers;
	}
	public void setNumberOfCorrectAnswers(int numberOfCorrectAnswers) {
		this.numberOfCorrectAnswers = numberOfCorrectAnswers;
	}
	public int getNumberOfIncorrectAnswers() {
		return numberOfIncorrectAnswers;
	}
	public void setNumberOfIncorrectAnswers(int numberOfIncorrectAnswers) {
		this.numberOfIncorrectAnswers = numberOfIncorrectAnswers;
	}
	
}