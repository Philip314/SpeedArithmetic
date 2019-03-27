package main.userinterface;

import java.util.ArrayList;

import javafx.scene.control.TextField;
import main.arithmetictest.ArithmeticTest;
import main.arithmetictest.ArithmeticTestLogic;
import main.database.DatabaseLogic;
import main.enumeration.Operator;
import main.question.Question;
import main.user.UserLogic;

/*
 * This class is for the logic of UserInterface class.
 */

public class UserInterfaceLogic {
	
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
			return ArithmeticTestLogic.createTest(difficulty, operators);
		} else {
			throw new NullPointerException("Something is null");
		}
	}
	
	public static boolean processTestSubmission(ArrayList<TextField> textFieldAnswers, ArithmeticTest test) {
		ArrayList<String> userAnswers = getUserAnswersFromTextField(textFieldAnswers);
		boolean validInput = true;
		validInput = validateUserAnswers(userAnswers);
		userAnswers = adjustAnswers(userAnswers);
		if (validInput) {
			ArithmeticTestLogic.assignAnswers(test, userAnswers);
			UserLogic.saveTest(test);
			if (UserLogic.getActiveUser() != null) {
				DatabaseLogic.insertTest(test, UserLogic.getActiveUser().getUsername());
			}
			return true;
		} else {
			return false;
		}
	}
	
	public static ArrayList<String> getUserAnswersFromTextField(ArrayList<TextField> textField) {
		ArrayList<String> userAnswers = new ArrayList<String>();
		for (TextField field : textField) {
			userAnswers.add(field.getText());
		}
		return userAnswers;
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
	
	public static ArrayList<String> adjustAnswers(ArrayList<String> userAnswers) {
		for (int i=0 ; i<userAnswers.size() ; i++) {
			if (userAnswers.get(i).equals("")) {
				userAnswers.set(i, null);
			}
		}
		return userAnswers;
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
	
	public static boolean validateUsername(String username) {
		if (username.equals("") || username.trim().length() == 0) {
			return false;
		} else {
			return true;
		}
	}
	
}
