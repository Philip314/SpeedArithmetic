package main.userinterface;

import main.arithmetictest.ArithmeticTest;
import main.arithmetictest.ArithmeticTestManager;
import main.enumeration.Operator;

/*
 * This class contains the logic for the GUI.
 */

public class InterfaceManager {
	
	public static String setDifficultyText(int difficulty) {
		switch (difficulty) {
				case 1: return "Numbers will be 1 to 10";
				case 2: return "Numbers will be 1 to 100";
				case 3: return "Numbers will be 1 to 1000";
				default: return "";
		}
	}
	
	public static ArithmeticTest createTest(Integer difficulty, Operator operators) throws NullPointerException {
		if (difficulty != null && operators != null) {
			return ArithmeticTestManager.createTest(difficulty, operators);
		} else {
			throw new NullPointerException("Something is null");
		}
	}
	
}
