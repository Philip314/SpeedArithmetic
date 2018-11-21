package main.userinterface;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
		hbox.getChildren().addAll(new Button("Create test"), new Button("Create account"));
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
	
}
