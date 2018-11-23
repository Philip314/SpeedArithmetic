package main.userinterface;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.*;

public class UserInterface {
	
	private EventHandler<ActionEvent> a;
	
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
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(new Label("Difficulty"), new Label("Please select a difficulty"));
		vbox.setPadding(new Insets(10,10,10,10));
		vbox.setSpacing(10);
		
		borderPane.setCenter(vbox);
		Scene scene = new Scene(borderPane, 500, 300);
		
		stage.setTitle("SpeedArithmetic");
		stage.setScene(scene);
		stage.show();
	}
	
}
