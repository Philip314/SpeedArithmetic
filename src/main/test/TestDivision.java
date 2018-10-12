package main.test;

import java.util.ArrayList;

import main.question.QuestionDivision;

public class TestDivision extends Test {
	
	private ArrayList<QuestionDivision> questions;
	
	public TestDivision(int difficulty) {
		setDifficulty(difficulty);
		setOperator("/");
		generateDivisionQuestions();
	}
	
	public ArrayList<QuestionDivision> getQuestions() {
		return questions;
	}
	public void setQuestions(ArrayList<QuestionDivision> question) {
		this.questions = question;
	}
	
	public void generateDivisionQuestions() {
		ArrayList<QuestionDivision> generateQuestions = new ArrayList<QuestionDivision>();
		for (int i=0; i<10; i++) {
			QuestionDivision q = new QuestionDivision(getDifficulty());
			generateQuestions.add(q);
		}
		setQuestions(generateQuestions);
	}
	
}
