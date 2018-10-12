package main.test;

import java.util.ArrayList;

import main.question.QuestionMultiplication;

public class TestMultiplication extends Test {
	
	private ArrayList<QuestionMultiplication> questions;
	
	public TestMultiplication(int difficulty) {
		setDifficulty(difficulty);
		setOperator("+");
		generateMultiplicationQuestions();
	}
	
	public ArrayList<QuestionMultiplication> getQuestions() {
		return questions;
	}
	public void setQuestions(ArrayList<QuestionMultiplication> question) {
		this.questions = question;
	}
	
	public void generateMultiplicationQuestions() {
		ArrayList<QuestionMultiplication> generateQuestions = new ArrayList<QuestionMultiplication>();
		for (int i=0; i<10; i++) {
			QuestionMultiplication q = new QuestionMultiplication(getDifficulty());
			generateQuestions.add(q);
		}
		setQuestions(generateQuestions);
	}
	
}
