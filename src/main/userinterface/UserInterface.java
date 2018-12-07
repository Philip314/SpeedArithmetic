package main.userinterface;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import main.arithmetictest.ArithmeticTest;
import main.arithmetictest.ArithmeticTestManager;

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
	
	
	private static VBox quitToMain(Stage stage) {
		Button quit = new Button("Main menu");
		VBox header = new VBox();
		quit.setOnAction(actionEvent -> mainMenu(stage));
		header.getChildren().add(quit);
		header.setAlignment(Pos.BASELINE_RIGHT);
		return header;
	}
	
	public static void mainMenu(Stage stage) {
		
		BorderPane borderPane = new BorderPane();
		
		HBox hbox = new HBox();
		Button createTest = new Button("Create test");
		createTest.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				createTest(stage);
			}
		});
		hbox.getChildren().addAll(createTest, new Button("Create account"));
		hbox.setPadding(PADDING);
		hbox.setSpacing(SPACING);
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(new Label(TITLE), new Label("This is a program to train your arithmetic skills"), hbox);
		vbox.setPadding(PADDING);
		vbox.setSpacing(SPACING);
		
		borderPane.setCenter(vbox);
		Scene scene = new Scene(borderPane, WIDTH, HEIGHT);
		
		stage.setTitle(TITLE);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void createTest(Stage stage) {
		BorderPane borderPane = new BorderPane();
		
		Label difficultyTitle = new Label("Difficulty");
		Label difficultyInfo = new Label("Please select a difficulty");
		Label levelInfo = new Label("");
		ChoiceBox<Integer> difficulty = new ChoiceBox<Integer>();
		difficulty.getItems().addAll(1, 2, 3);
		difficulty.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				switch(difficulty.getValue()) {
					case 1: levelInfo.setText("Numbers will be 1 to 10");
						break;
					case 2: levelInfo.setText("Numbers will be 1 to 100");
						break;
					case 3: levelInfo.setText("Numbers will be 1 to 1000");
						break;
				}
			}
		});
		HBox difficultyRow = new HBox();
		difficultyRow.getChildren().addAll(difficulty, levelInfo);
		difficultyRow.setSpacing(SPACING);
		
		Label operatorTitle = new Label("Operator");
		Label operatorInfo = new Label("Please select which operation you would like to practice");
		ChoiceBox<String> operators = new ChoiceBox<String>();
		operators.getItems().addAll("+", "-", "*", "/");
		
		Button createTest = new Button("Create test");
		Label warning = new Label();
		createTest.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (difficulty.getValue() != null && operators.getValue() != null) {
					ArithmeticTest test = ArithmeticTestManager.createTest(difficulty.getValue(), operators.getValue());
					showTest(stage, test);
				} else {
					warning.setText("Please select a difficulty and operator");
				}
			}
		});
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(difficultyTitle, difficultyInfo, difficultyRow, operatorTitle, operatorInfo, operators, createTest, warning);
		vbox.setPadding(PADDING);
		vbox.setSpacing(SPACING);
		
		
		
		borderPane.setTop(quitToMain(stage));
		borderPane.setCenter(vbox);
		Scene scene = new Scene(borderPane, WIDTH, HEIGHT);
		
		stage.setTitle(TITLE);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void showTest(Stage stage, ArithmeticTest test) {
		BorderPane borderPane = new BorderPane();
		
		Label testTitle = new Label("Test \n Difficulty: " + test.getDifficulty() + "\n Operator: " + test.getOperator());
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(testTitle);
		vbox.setPadding(PADDING);
		vbox.setSpacing(SPACING);
		
		for (int i=0; i< test.getQuestions().size(); i++) {
			Label label = new Label("Question " + (i+1) + ": " + test.getQuestions().get(i));
			TextField textField = new TextField();
			HBox question = new HBox();
			question.setSpacing(SPACING);
			question.getChildren().addAll(label, textField);
			vbox.getChildren().add(question);
		}
		
		Label invalidInputWarning = new Label();
		Button submitTest = new Button("Submit");
		submitTest.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ArrayList<String> userAnswers = new ArrayList<String>();
				boolean validInput = true;
				for (Node nodeHBox : vbox.getChildren()) {
					if (nodeHBox instanceof HBox) {
						for (Node nodeTextField : ((HBox) nodeHBox).getChildren()) {
							if (nodeTextField instanceof TextField) {
								String answer = ((TextField) nodeTextField).getText();
								try {
									if (!answer.equals("")) {
										Integer.parseInt(answer);
										userAnswers.add(answer);
									} else if (answer.equals("")) {
										userAnswers.add(null);
									}
								} catch (Exception e) {
									validInput = false;
									break;
								}
							}
						}
					}
				}
				if (validInput) {
					ArithmeticTestManager.assignAnswers(test, userAnswers);
					showResults(stage, test);
				} else {
					invalidInputWarning.setText("Answers must be a number");
				}
			}
		});
		
		vbox.getChildren().addAll(submitTest, invalidInputWarning);
		
		borderPane.setTop(quitToMain(stage));
		borderPane.setCenter(vbox);
		Scene scene = new Scene(borderPane, WIDTH, HEIGHT);
		
		stage.setTitle(TITLE);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void showResults(Stage stage, ArithmeticTest test) {
		BorderPane borderPane = new BorderPane();
		
		Label resultTitle = new Label("Test results");
		VBox vbox = new VBox();
		vbox.getChildren().addAll(resultTitle);
		vbox.setPadding(PADDING);
		vbox.setSpacing(SPACING);
		
		for (int i=0; i< test.getQuestions().size(); i++) {
			String correctAnswer = Integer.toString(test.getQuestions().get(i).getAnswer());
			Label label = new Label("Question " + (i+1) + ": " + test.getQuestions().get(i) + " = " + correctAnswer);
			int userAnswerInt = test.getQuestions().get(i).getUserAnswer();
			Label userAnswer = new Label();
			if (userAnswerInt == -1) {
				userAnswer.setText("Your answer is: N/A");
			} else {
				userAnswer.setText("Your answer is: " + Integer.toString(test.getQuestions().get(i).getUserAnswer()));
			}
			
			HBox question = new HBox();
			question.setSpacing(SPACING);
			question.getChildren().addAll(label, userAnswer);
			vbox.getChildren().add(question);
		}
		
		borderPane.setTop(quitToMain(stage));
		borderPane.setCenter(vbox);
		Scene scene = new Scene(borderPane, WIDTH, HEIGHT);
		
		stage.setTitle(TITLE);
		stage.setScene(scene);
		stage.show();
	}
	
}
