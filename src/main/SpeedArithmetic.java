package main;

import java.util.Scanner;

import main.question.Question;
import main.test.Test;

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
		System.out.println("Which difficulty would you like? (1, 2, 3)");
		int difficulty;
		while (true) {
			try {
				difficulty = scanner.nextInt();
				if(difficulty == 1 || difficulty == 2 || difficulty == 3) {
					break;
				} else {
					System.out.println("Invalid input. Please input one of the difficulties: 1, 2, 3");
				}
			} catch (Exception e) {
				System.out.println("Invalid input. Please input a number");
				scanner.next();
			}
		}
		
		// Generate questions for test
		int answer;
		Test test = new Test(difficulty, operation);
		switch(operation) {
			case "+":
				test.generateAdditionQuestions();
				break;
			case "-":
				test.generateSubtractionQuestions();
				break;
			case "*":
				test.generateMultiplicationQuestions();
				break;
			case "/":
				test.generateDivisionQuestions();
				break;
			default:
				break;
		}
		
		// Ask user the questions
		for (Question q : test.getQuestions()) {
			System.out.println(q);
			while (true) {
				try {
					answer = scanner.nextInt();
					q.setUserAnswer(answer);
					break;
				} catch (Exception e) {
					System.out.println("Invalid input. Please input a number");
					scanner.next();
				}
			}
		}
		
		// Output results
		for (Question q: test.getQuestions()) {
			System.out.println(q.toString() + " = " + q.getAnswer());
			System.out.println("Your answer is: " + q.getUserAnswer());
		}
		
		scanner.close();
		
	}
	
}
