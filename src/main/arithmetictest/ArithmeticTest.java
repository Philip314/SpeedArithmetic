package main.arithmetictest;

import java.util.ArrayList;

import main.question.Question;

public class ArithmeticTest {
	
	private int difficulty;
	private String operator;
	private int numberOfCorrectAnswers;
	private int numberOfIncorrectAnswers;
	
	private ArrayList<Question> questions;
	
	public ArithmeticTest(int difficulty, String operator) {
		setDifficulty(difficulty);
		setOperator(operator);
	}
	
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
		ArrayList<Question> generateQuestions = new ArrayList<Question>();
		for (int i=0; i<10; i++) {
			Question q = new Question();
			q.generateAdditionQuestion(getDifficulty());
			generateQuestions.add(q);
		}
		setQuestions(generateQuestions);
	}
	
	public void generateSubtractionQuestions() {
		ArrayList<Question> generateQuestions = new ArrayList<Question>();
		for (int i=0; i<10; i++) {
			Question q = new Question();
			q.generateSubtractionQuestion(getDifficulty());
			generateQuestions.add(q);
		}
		setQuestions(generateQuestions);
	}
	
	public void generateMultiplicationQuestions() {
		ArrayList<Question> generateQuestions = new ArrayList<Question>();
		for (int i=0; i<10; i++) {
			Question q = new Question();
			q.generateMultiplicationQuestion(getDifficulty());
			generateQuestions.add(q);
		}
		setQuestions(generateQuestions);
	}
	
	public void generateDivisionQuestions() {
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
			if (q.isCorrect()) {
				numberOfCorrectAnswers++;
			} else {
				numberOfIncorrectAnswers++;
			}
		}
	}
	
}