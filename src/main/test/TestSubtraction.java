package main.test;

import java.util.ArrayList;

import main.question.QuestionAddition;

public class TestSubtraction extends Test {
	
	private ArrayList questions;
	
	public TestSubtraction(int difficulty) {
		setDifficulty(difficulty);
		setOperator("-");
		//generateSubtractionQuestions();
	}
	
	public ArrayList getQuestions() {
		return questions;
	}
	public void setQuestions(ArrayList question) {
		this.questions = question;
	}
	
	public void generateSubtractionQuestions() {
		ArrayList generateQuestions = new ArrayList();
		for (int i=0; i<10; i++) {
		}
		setQuestions(generateQuestions);
	}
	
}
