package main.arithmetictest;

public class ArithmeticTestManager {
	
	public static ArithmeticTest createTest(int difficulty, String operator) {
		ArithmeticTest toReturn = new ArithmeticTest(difficulty, operator);
		switch(operator) {
			case "+":
				toReturn.generateAdditionQuestions();
				break;
			case "-":
				toReturn.generateSubtractionQuestions();
				break;
			case "*":
				toReturn.generateMultiplicationQuestions();
				break;
			case "/":
				toReturn.generateDivisionQuestions();
				break;
			default:
				break;
		}
		return toReturn;
	}
	
}
