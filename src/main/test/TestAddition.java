package main.test;

import java.util.ArrayList;

import main.question.QuestionAddition;

public class TestAddition extends Test {
	
	private ArrayList<QuestionAddition> questions;
	
	public TestAddition(int difficulty) {
		setDifficulty(difficulty);
		setOperator("+");
		generateAdditionQuestions();
	}
	
	public ArrayList<QuestionAddition> getQuestions() {
		return questions;
	}
	public void setQuestions(ArrayList<QuestionAddition> question) {
		this.questions = question;
	}
	
	public void generateAdditionQuestions() {
		ArrayList<QuestionAddition> generateQuestions = new ArrayList<QuestionAddition>();
		for (int i=0; i<10; i++) {
			QuestionAddition q = new QuestionAddition(getDifficulty());
			generateQuestions.add(q);
		}
		setQuestions(generateQuestions);
	}
	
}
