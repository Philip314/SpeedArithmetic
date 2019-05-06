package main.userinterface;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.arithmetictest.ArithmeticTest;
import main.arithmetictest.ArithmeticTestLogic;
import main.database.DatabaseLogic;
import main.enumeration.Operator;
import main.question.Question;
import main.user.UserLogic;

/**
 * This class contains the logic of UserInterface class.
 * 
 * @author Philip
 *
 */
public class UserInterfaceLogic {
	
	/**
	 * Decides which text should be set based on difficulty.
	 * 
	 * @param difficulty integer which decides what string is to be returned
	 * @return string of description
	 */
	public static String setDifficultyText(int difficulty) {
		switch (difficulty) {
				case 1: return "Numbers will be 1 to 10";
				case 2: return "Numbers will be 1 to 100";
				case 3: return "Numbers will be 1 to 1000";
				default: return "";
		}
	}
	
	/**
	 * Creates a test if the parameters are not null.
	 * 
	 * @param difficulty integer of the difficulty
	 * @param operators enum of the operator
	 * @return test that was created
	 * @throws NullPointerException
	 */
	public static ArithmeticTest createTest(Integer difficulty, Operator operators) throws NullPointerException {
		if (difficulty != null && operators != null) {
			return ArithmeticTestLogic.createTest(difficulty, operators);
		} else {
			throw new NullPointerException("Something is null");
		}
	}
	
	/**
	 * Call other methods to validate and adjust the test and saves the test.
	 * 
	 * @param textFieldAnswers list of TextFields that contain the answers of the user that needs to be extracted
	 * @param test the ArithmeticTest that needs to be saved
	 * @return true if the test was successfully processed, false otherwise
	 */
	public static boolean processTestSubmission(List<TextField> textFieldAnswers, ArithmeticTest test) {
		List<String> userAnswers = getUserAnswersFromTextField(textFieldAnswers);
		boolean validInput = true;
		validInput = validateUserAnswers(userAnswers);
		userAnswers = adjustAnswers(userAnswers);
		if (validInput) {
			ArithmeticTestLogic.assignAnswers(test, userAnswers);
			UserLogic.saveTest(test);
			if (UserLogic.getActiveUser() != null) {
				String username = UserLogic.getActiveUser().getUsername();
				DatabaseLogic.insertTest(test, username);
				DatabaseLogic.insertQuestions(test.getQuestions());
			}
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Extracts the answers from the list of Textfields into a list of string.
	 * 
	 * @param textField list of TextFields
	 * @return list of answers as string
	 */
	public static List<String> getUserAnswersFromTextField(List<TextField> textField) {
		List<String> userAnswers = new ArrayList<String>();
		for (TextField field : textField) {
			userAnswers.add(field.getText());
		}
		return userAnswers;
	}
	
	/**
	 * Validate that the list of string contain only integer answers or empty string.
	 * 
	 * @param userAnswers list of string answers
	 * @return true if the input only contains integer or empty string, false otherwise
	 */
	public static boolean validateUserAnswers(List<String> userAnswers) {
		for (String answer : userAnswers) {
			if (answer.equals("")) {
			} else if (!isInteger(answer)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks whether the string input is a number.
	 * 
	 * @param test string value to be checked
	 * @return true if the input is an integer, false otherwise
	 */
	private static boolean isInteger(String test) {
		try {
			Integer.parseInt(test);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Iterates through list of string to change empty string elements to null.
	 * 
	 * @param userAnswers list of string to be iterated through
	 * @return list of string
	 */
	public static List<String> adjustAnswers(List<String> userAnswers) {
		for (int i=0 ; i<userAnswers.size() ; i++) {
			if (userAnswers.get(i).equals("")) {
				userAnswers.set(i, null);
			}
		}
		return userAnswers;
	}
	
	/**
	 * Constructs the question in a readable format.
	 * 
	 * @param questionNumber integer to be used as part of the string
	 * @param question the entire question to be used as part of the string
	 * @return string of the question
	 */
	public static String displayQuestion(int questionNumber, Question question) {
		return "Question " + questionNumber + ": " + question;
	}
	
	/**
	 * Generates the text for the labels from the user's answers. This is used after the user has finished the test and the results need to be displayed.
	 * 
	 * @param userAnswerInt integer of the user's answers. -1 if the user did not answer the question
	 * @return string for the label of the user's answer
	 */
	public static String generateAnswerLabelText(int userAnswerInt) {
		if (userAnswerInt == -1) {
			return "Your answer is: N/A";
		} else {
			return "Your answer is: " + Integer.toString(userAnswerInt);
		}
	}
	
	/**
	 * Validates the username.
	 * 
	 * @param username string to be validated
	 * @return true if the username is valid, false otherwise
	 */
	public static boolean validateUsername(String username) {
		if (username.equals("") || username.trim().length() == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Gets all of the tests belonging to the active user.
	 * 
	 * @return ObservableList of the tests.
	 */
	public static ObservableList<ArithmeticTest> getAllUserTests() {
		List<ArithmeticTest> result = DatabaseLogic.getAllUserTests(UserLogic.getActiveUser().getUsername());
		ObservableList<ArithmeticTest> obList = FXCollections.observableArrayList();
		obList.addAll(result);
		return obList;
	}
	
	/**
	 * Decides whether the account details button should be enabled or not.
	 * Disabled if there is no active user, otherwise enable.
	 * 
	 * @param button button which will be disabled or enabled.
	 */
	public static void setAccountDetailsButtonStatus(Button button) {
		if (UserLogic.getActiveUser() == null) {
			button.setDisable(true);
		} else {
			button.setDisable(false);
		}
	}
	
}
