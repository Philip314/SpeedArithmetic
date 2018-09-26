package main;

import java.util.Scanner;

import main.test.TestAddition;

public class SpeedArithmetic {
	
	public static void main (String[] args) {
		System.out.println("Welcome to SpeedArithmetic");
		
		Scanner scanner = new Scanner(System.in);
		
		// Ask user for operation
		System.out.println("Which operation would you like to practice? (+, -, *, /)");
		String operation;
		while (true) {
			operation = scanner.next();
			if(operation.equals("+") || operation.equals("-") || operation.equals("*") || operation.equals("/")) {
				break;
			} else {
				System.out.println("Invalid input. Please input one of the operators: +, -, *, /");
			}
		}
		
		// Ask user for difficulty
		System.out.println("Which difficult would you like? (1, 2, 3)");
		int difficulty;
		while (true) {
			difficulty = scanner.nextInt();
			if(difficulty == 1 || difficulty == 2 || difficulty == 3) {
				break;
			} else {
				System.out.println("Invalid input. Please input one of the difficulties: 1, 2, 3");
			}
		}
		
		switch(operation) {
			case "+":
				TestAddition test = new TestAddition(difficulty);
				test.getQuestions().stream().forEach(System.out::println);
				break;
			case "-":
				break;
			case "*":
				break;
			case "/":
				break;
			default:
				break;
		}
		
		scanner.close();
		
	}
	
}
