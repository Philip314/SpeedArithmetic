package main.userinterface;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
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
		
		Button level1 = new Button("Level 1");
		Label level1Info = new Label("Numbers will be 1 to 10");
		Button level2 = new Button("Level 2");
		Label level2Info = new Label("Numbers will be 1 to 100");
		Button level3 = new Button("Level 3");
		Label level3Info = new Label("Numbers will be 1 to 1000");
		
		HBox level1Row = new HBox();
		level1Row.getChildren().addAll(level1, level1Info);
		level1Row.setSpacing(10);
		
		HBox level2Row = new HBox();
		level2Row.getChildren().addAll(level2, level2Info);
		level2Row.setSpacing(10);
		
		HBox level3Row = new HBox();
		level3Row.getChildren().addAll(level3, level3Info);
		level3Row.setSpacing(10);
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(new Label("Difficulty"), new Label("Please select a difficulty"), level1Row, level2Row, level3Row);
		vbox.setPadding(new Insets(10,10,10,10));
		vbox.setSpacing(10);
		
		borderPane.setCenter(vbox);
		Scene scene = new Scene(borderPane, 500, 300);
		
		stage.setTitle("SpeedArithmetic");
		stage.setScene(scene);
		stage.show();
	}
	
}
