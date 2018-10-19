package main.question;

public class QuestionDivision extends Question {
	
	public QuestionDivision(int difficulty) {
		setOperator("/");
		setDifficulty(difficulty);
		switch(difficulty) {
		case 1:
			generateDivisibleNumbers(generateNumber(LOWER, EASY_UPPER), generateNumber(LOWER, EASY_UPPER), EASY_UPPER);
			break;
		case 2:
			generateDivisibleNumbers(generateNumber(LOWER, MED_UPPER), generateNumber(LOWER, MED_UPPER), MED_UPPER);
			break;
		case 3:
			generateDivisibleNumbers(generateNumber(LOWER, HARD_UPPER), generateNumber(LOWER, HARD_UPPER), HARD_UPPER);
			break;
		}
		setAnswer(getFirstNumber() / getSecondNumber());
	}
	
	public void generateDivisibleNumbers(int first, int second, int upper) {
		if (first % second == 0 && first != second) {
			setFirstNumber(first);
			setSecondNumber(second);
		} else {
			first = generateNumber(LOWER, upper);
			second = generateNumber(LOWER, upper);
			generateDivisibleNumbers(first, second, upper);
		}
	}
	
}
