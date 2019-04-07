package main.arithmetictest;

import java.util.List;

/*
 * This is the main class that holds the arithmetic test.
 * This class holds multiple Question objects.
 * This class can be adapted to different difficulty and arithmetic operators.
 * 
 */

import main.question.Question;
import main.enumeration.Operator;

public class ArithmeticTest {
	
	private int difficulty;
	private Operator operator;
	private int numberOfCorrectAnswers;
	private int numberOfIncorrectAnswers;
	
	private List<Question> questions;
	
	public int getDifficulty() {
		return difficulty;
	}
	
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	
	public Operator getOperator() {
		return operator;
	}
	
	public void setOperator(Operator operator) {
		this.operator = operator;
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

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
}