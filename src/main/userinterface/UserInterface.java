package main.userinterface;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.*;

public class UserInterface {
	
	public static void mainMenu() {
		Stage stage = new Stage();
		
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
		hbox.setPadding(new Insets(20,0,0,0));
		hbox.setSpacing(10);
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(new Label("SpeedArithmetic"), new Label("This is a program to train your arithmetic skills"), hbox);
		vbox.setPadding(new Insets(10,10,10,10));
		vbox.setSpacing(10);
		
		borderPane.setCenter(vbox);
		Scene scene = new Scene(borderPane, 500, 300);
		
		stage.setTitle("SpeedArithmetic");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void createTest(Stage stage) {
		
		BorderPane borderPane = new BorderPane();
		
		Label difficultyTitle = new Label("Difficulty");
		Label difficultyInfo = new Label("Please select a difficulty");
		Label levelInfo = new Label("");
		ChoiceBox<String> difficulty = new ChoiceBox<String>();
		difficulty.getItems().addAll("Level 1", "Level 2", "Level 3");
		difficulty.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				switch(difficulty.getValue()) {
					case "Level 1": levelInfo.setText("Numbers will be 1 to 10");
						break;
					case "Level 2": levelInfo.setText("Numbers will be 1 to 100");
						break;
					case "Level 3": levelInfo.setText("Numbers will be 1 to 1000");
						break;
				}
			}
		});
		HBox difficultyRow = new HBox();
		difficultyRow.getChildren().addAll(difficulty, levelInfo);
		difficultyRow.setSpacing(10);
		
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
					showTest(stage);
				} else {
					warning.setText("Please select a difficulty and operator");
				}
			}
		});
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(difficultyTitle, difficultyInfo, difficultyRow, operatorTitle, operatorInfo, operators, createTest, warning);
		vbox.setPadding(new Insets(10,10,10,10));
		vbox.setSpacing(10);
		
		borderPane.setCenter(vbox);
		Scene scene = new Scene(borderPane, 500, 300);
		
		stage.setTitle("SpeedArithmetic");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void showTest(Stage stage) {
		
		BorderPane borderPane = new BorderPane();
		
		//borderPane.setCenter();
		Scene scene = new Scene(borderPane, 500, 300);
		
		stage.setTitle("SpeedArithmetic");
		stage.setScene(scene);
		stage.show();
		
	}
	
}
