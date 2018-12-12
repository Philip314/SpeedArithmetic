package main.arithmetictest;

import java.util.ArrayList;

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
	
	private ArrayList<Question> questions;
	
	public ArithmeticTest(int difficulty, Operator operator) {
		setDifficulty(difficulty);
		setOperator(operator);
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

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	
	public void generateAdditionQuestions() {
		if (!getOperator().equals(Operator.ADD)) {
			throw new RuntimeException("Operator does not match the type of question about to be generated");
		}
		ArrayList<Question> generateQuestions = new ArrayList<Question>();
		for (int i=0; i<10; i++) {
			Question q = new Question();
			q.generateAdditionQuestion(getDifficulty());
			generateQuestions.add(q);
		}
		setQuestions(generateQuestions);
	}
	
	public void generateSubtractionQuestions() {
		if (!getOperator().equals(Operator.SUBTRACT)) {
			throw new RuntimeException("Operator does not match the type of question about to be generated");
		}
		ArrayList<Question> generateQuestions = new ArrayList<Question>();
		for (int i=0; i<10; i++) {
			Question q = new Question();
			q.generateSubtractionQuestion(getDifficulty());
			generateQuestions.add(q);
		}
		setQuestions(generateQuestions);
	}
	
	public void generateMultiplicationQuestions() {
		if (!getOperator().equals(Operator.MULTIPLY)) {
			throw new RuntimeException("Operator does not match the type of question about to be generated");
		}
		ArrayList<Question> generateQuestions = new ArrayList<Question>();
		for (int i=0; i<10; i++) {
			Question q = new Question();
			q.generateMultiplicationQuestion(getDifficulty());
			generateQuestions.add(q);
		}
		setQuestions(generateQuestions);
	}
	
	public void generateDivisionQuestions() {
		if (!getOperator().equals(Operator.DIVIDE)) {
			throw new RuntimeException("Operator does not match the type of question about to be generated");
		}
		ArrayList<Question> generateQuestions = new ArrayList<Question>();
		for (int i=0; i<10; i++) {
			Question q = new Question();
			q.generateDivisionQuestion(getDifficulty());
			generateQuestions.add(q);
		}
		setQuestions(generateQuestions);
	}
	
	public void markTest() {
		for (Question q : questions) {
			q.markQuestion();
			if (q.isCorrect()) {
				numberOfCorrectAnswers++;
			} else {
				numberOfIncorrectAnswers++;
			}
		}
	}
	
}