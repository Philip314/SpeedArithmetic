package main.test;

import java.util.ArrayList;

import main.question.QuestionSubtraction;

public class TestSubtraction extends Test {
	
	private ArrayList<QuestionSubtraction> questions;
	
	public TestSubtraction(int difficulty) {
		setDifficulty(difficulty);
		setOperator("-");
		generateSubtractionQuestions();
	}
	
	public ArrayList<QuestionSubtraction> getQuestions() {
		return questions;
	}
	public void setQuestions(ArrayList<QuestionSubtraction> question) {
		this.questions = question;
	}
	
	public void generateSubtractionQuestions() {
		ArrayList<QuestionSubtraction> generateQuestions = new ArrayList<QuestionSubtraction>();
		for (int i=0; i<10; i++) {
			QuestionSubtraction q = new QuestionSubtraction(getDifficulty());
			generateQuestions.add(q);
		}
		setQuestions(generateQuestions);
	}
	
}
