package main.userinterface;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import main.arithmetictest.ArithmeticTest;
import main.database.DatabaseLogic;
import main.enumeration.Operator;
import main.user.UserLogic;

/*
 * This class is for the GUI.
 * Each method is a different interface.
 */

public class UserInterface {
	
	private static final String TITLE = "SpeedArithmetic";
	private static final int WIDTH = 400;
	private static final int HEIGHT = 550;
	private static final int SPACING = 10;
	private static final Insets PADDING = new Insets(10,10,10,10);
	
	// The header for each interface
	private static ToolBar header(Stage stage) {
		// Main menu button on the right
		Button mainMenu = new Button("Main menu");
		mainMenu.setOnAction(actionEvent -> mainMenu(stage));
		
		// Username on the left
		String username = "(No account)";
		try {
			username = UserLogic.getActiveUser().getUsername();
		} catch (NullPointerException e) {
		}
		Label usernameLabel = new Label(username);
		
		ToolBar toolBar = new ToolBar();
		toolBar.getItems().addAll(usernameLabel, new Separator(), mainMenu);
		
		return toolBar;
	}
	
	public static void mainMenu(Stage stage) {
		
		BorderPane borderPane = new BorderPane();
		
		// A row of buttons
		HBox hbox = new HBox();
		Button createTest = new Button("Create test");
		createTest.setOnAction(actionEvent -> createTest(stage));
		Button createAccount = new Button("Create account");
		createAccount.setOnAction(actionEvent -> createUser(stage));
		Button accountDetails = new Button("Account details");
		UserInterfaceLogic.setAccountDetailsButtonStatus(accountDetails);
		accountDetails.setOnAction(actionEvent -> accountDetails(stage));
		hbox.getChildren().addAll(createTest, createAccount, accountDetails);
		hbox.setPadding(PADDING);
		hbox.setSpacing(SPACING);
		
		// Title and introduction
		VBox mainV = new VBox();
		mainV.getChildren().addAll(new Label(TITLE), new Label("This is a program to train your arithmetic skills"), hbox);
		mainV.setPadding(PADDING);
		mainV.setSpacing(SPACING);
		
		borderPane.setTop(header(stage));
		borderPane.setCenter(mainV);
		Scene scene = new Scene(borderPane, WIDTH, HEIGHT);
		
		stage.setTitle(TITLE);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void createTest(Stage stage) {
		BorderPane borderPane = new BorderPane();
		
		// Select difficulty
		Label difficultyTitle = new Label("Difficulty");
		Label difficultyInfo = new Label("Please select a difficulty");
		Label levelInfo = new Label("");
		ChoiceBox<Integer> difficulty = new ChoiceBox<Integer>();
		difficulty.getItems().addAll(1, 2, 3);
		difficulty.setOnAction(actionEvent -> levelInfo.setText(UserInterfaceLogic.setDifficultyText(difficulty.getValue())));
		HBox difficultyRow = new HBox();
		difficultyRow.getChildren().addAll(difficulty, levelInfo);
		difficultyRow.setSpacing(SPACING);
		
		// Select operator
		Label operatorTitle = new Label("Operator");
		Label operatorInfo = new Label("Please select which operation you would like to practice");
		ChoiceBox<Operator> operators = new ChoiceBox<Operator>();
		operators.getItems().addAll(Operator.ADD, Operator.SUBTRACT, Operator.MULTIPLY, Operator.DIVIDE);
		
		// Create test
		Button createTest = new Button("Create test");
		Label warning = new Label();
		createTest.setOnAction(actionEvent -> {
			ArithmeticTest test;
			try {
				test = UserInterfaceLogic.createTest(difficulty.getValue(), operators.getValue());
				showTest(stage, test);
			} catch (NullPointerException e) {
				warning.setText("Please select a difficulty and operator");
			}
		});
		
		VBox mainV = new VBox();
		mainV.getChildren().addAll(difficultyTitle, difficultyInfo, difficultyRow, operatorTitle, operatorInfo, operators, createTest, warning);
		mainV.setPadding(PADDING);
		mainV.setSpacing(SPACING);
		
		borderPane.setTop(header(stage));
		borderPane.setCenter(mainV);
		Scene scene = new Scene(borderPane, WIDTH, HEIGHT);
		
		stage.setTitle(TITLE);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void showTest(Stage stage, ArithmeticTest test) {
		BorderPane borderPane = new BorderPane();
		
		Label testTitle = new Label("Test \n Difficulty: " + test.getDifficulty() + "\n Operator: " + test.getOperator());
		
		VBox mainV = new VBox();
		mainV.getChildren().addAll(testTitle);
		mainV.setPadding(PADDING);
		mainV.setSpacing(SPACING);
		
		// List questions and textfields
		ArrayList<TextField> textFieldAnswers = new ArrayList<TextField>();
		for (int i=0; i< test.getQuestions().size(); i++) {
			Label label = new Label(UserInterfaceLogic.displayQuestion(i+1, test.getQuestions().get(i)));
			TextField textField = new TextField();
			textFieldAnswers.add(textField);
			HBox question = new HBox();
			question.setSpacing(SPACING);
			question.getChildren().addAll(label, textField);
			mainV.getChildren().add(question);
		}
		
		// Submit test and to validate the input are numbers
		Label invalidInputWarning = new Label();
		Button submitTest = new Button("Submit");
		submitTest.setOnAction(actionEvent -> {
			boolean processResult = UserInterfaceLogic.processTestSubmission(textFieldAnswers, test);
			if (processResult) {
				showResults(stage, test);
			} else {
				invalidInputWarning.setText("Answers must be a number");
			}
		});
		
		mainV.getChildren().addAll(submitTest, invalidInputWarning);
		
		borderPane.setTop(header(stage));
		borderPane.setCenter(mainV);
		Scene scene = new Scene(borderPane, WIDTH, HEIGHT);
		
		stage.setTitle(TITLE);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void showResults(Stage stage, ArithmeticTest test) {
		BorderPane borderPane = new BorderPane();
		
		Label resultTitle = new Label("Test results");
		VBox mainV = new VBox();
		mainV.getChildren().addAll(resultTitle);
		mainV.setPadding(PADDING);
		mainV.setSpacing(SPACING);
		
		// Display questions and answers
		for (int i=0; i< test.getQuestions().size(); i++) {
			String correctAnswer = Integer.toString(test.getQuestions().get(i).getAnswer());
			Label label = new Label(UserInterfaceLogic.displayQuestion(i+1, test.getQuestions().get(i)) + " = " + correctAnswer);
			int userAnswerInt = test.getQuestions().get(i).getUserAnswer();
			Label userAnswer = new Label();
			userAnswer.setText(UserInterfaceLogic.generateAnswerLabelText(userAnswerInt));
			
			HBox question = new HBox();
			question.setSpacing(SPACING);
			question.getChildren().addAll(label, userAnswer);
			mainV.getChildren().add(question);
		}
		
		borderPane.setTop(header(stage));
		borderPane.setCenter(mainV);
		Scene scene = new Scene(borderPane, WIDTH, HEIGHT);
		
		stage.setTitle(TITLE);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void createUser(Stage stage) {
		BorderPane borderPane = new BorderPane();
		
		Label createUserTitle = new Label("Create account");
		HBox usernameRow = new HBox();
		usernameRow.setSpacing(SPACING);
		Label usernameLabel = new Label("Enter username");
		TextField usernameInput = new TextField();
		Label warning = new Label();
		usernameRow.getChildren().addAll(usernameLabel, usernameInput);
		Button submitUsername = new Button("Create account");
		submitUsername.setOnAction(actionEvent -> {
			String username = usernameInput.getText();
			if (!UserInterfaceLogic.validateUsername(username)) {
				warning.setText("Please enter a valid username");
			} else {
				UserLogic.createUser(username);
				DatabaseLogic.insertUser(username);
				mainMenu(stage);
			}
		});
		
		VBox mainV = new VBox();
		mainV.getChildren().addAll(createUserTitle, usernameRow, submitUsername, warning);
		mainV.setPadding(PADDING);
		mainV.setSpacing(SPACING);
		
		borderPane.setTop(header(stage));
		borderPane.setCenter(mainV);
		Scene scene = new Scene(borderPane, WIDTH, HEIGHT);
		
		stage.setTitle(TITLE);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void accountDetails(Stage stage) {
		BorderPane borderPane = new BorderPane();
		
		Label accountDetailsTitle = new Label("Account details");
		
		ObservableList<ArithmeticTest> tests = UserInterfaceLogic.getAllUserTests();
		
		// Display tests
		TableView<ArithmeticTest> table = new TableView<ArithmeticTest>();
		table.setEditable(false);
		TableColumn<ArithmeticTest, Integer> difficulty = new TableColumn<ArithmeticTest, Integer>("Difficulty");
		TableColumn<ArithmeticTest, Integer> numCorrect = new TableColumn<ArithmeticTest, Integer>("Number of Correct Answers");
		TableColumn<ArithmeticTest, Integer> numIncorrect = new TableColumn<ArithmeticTest, Integer>("Number of Incorrect Answers");
		difficulty.setCellValueFactory(new PropertyValueFactory<ArithmeticTest, Integer>("difficulty"));
		numCorrect.setCellValueFactory(new PropertyValueFactory<ArithmeticTest, Integer>("numberOfCorrectAnswers"));
		numIncorrect.setCellValueFactory(new PropertyValueFactory<ArithmeticTest, Integer>("numberOfIncorrectAnswers"));
		table.setItems(tests);
		table.getColumns().addAll(difficulty, numCorrect, numIncorrect);
		
		VBox mainV = new VBox();
		mainV.getChildren().addAll(accountDetailsTitle, table);
		mainV.setPadding(PADDING);
		mainV.setSpacing(SPACING);
		
		borderPane.setTop(header(stage));
		borderPane.setCenter(mainV);
		Scene scene = new Scene(borderPane, WIDTH, HEIGHT);
		
		stage.setTitle(TITLE);
		stage.setScene(scene);
		stage.show();
	}
	
}
