package main.userinterface;

import java.util.ArrayList;

import javafx.scene.control.TextField;
import main.arithmetictest.ArithmeticTest;
import main.arithmetictest.ArithmeticTestManager;
import main.enumeration.Operator;
import main.question.Question;

/*
 * This class contains the logic for the GUI.
 */

public class InterfaceManager {
	
	public static String setDifficultyText(int difficulty) {
		switch (difficulty) {
				case 1: return "Numbers will be 1 to 10";
				case 2: return "Numbers will be 1 to 100";
				case 3: return "Numbers will be 1 to 1000";
				default: return "";
		}
	}
	
	public static ArithmeticTest createTest(Integer difficulty, Operator operators) throws NullPointerException {
		if (difficulty != null && operators != null) {
			return ArithmeticTestManager.createTest(difficulty, operators);
		} else {
			throw new NullPointerException("Something is null");
		}
	}
	
	public static void getUserAnswersFromTextField(ArrayList<TextField> textField, ArrayList<String> userAnswers) {
		for (TextField field : textField) {
			userAnswers.add(field.getText());
		}
	}
	
	public static boolean validateUserAnswers(ArrayList<String> userAnswers) {
		for (String answer : userAnswers) {
			if (answer.equals("")) {
			} else if (!isInteger(answer)) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean isInteger(String test) {
		try {
			Integer.parseInt(test);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static void adjustAnswers(ArrayList<String> userAnswers) {
		for (int i=0 ; i<userAnswers.size() ; i++) {
			if (userAnswers.get(i).equals("")) {
				userAnswers.set(i, null);
			}
		}
	}
	
	public static String displayQuestion(int questionNumber, Question question) {
		return "Question " + questionNumber + ": " + question;
	}
	
	public static String generateAnswerLabelText(int userAnswerInt) {
		if (userAnswerInt == -1) {
			return "Your answer is: N/A";
		} else {
			return "Your answer is: " + Integer.toString(userAnswerInt);
		}
	}
	
}
