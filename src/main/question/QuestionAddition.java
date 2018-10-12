package main.question;

public class QuestionAddition extends Question {
	
	public QuestionAddition(int difficulty) {
		setOperator("+");
		setDifficulty(difficulty);
		switch(difficulty) {
		case 1:
			setFirstNumber(generateNumber(LOWER, EASY_UPPER));
			setSecondNumber(generateNumber(LOWER, EASY_UPPER));
			break;
		case 2:
			setFirstNumber(generateNumber(LOWER, MED_UPPER));
			setSecondNumber(generateNumber(LOWER, MED_UPPER));
			break;
		case 3:
			setFirstNumber(generateNumber(LOWER, HARD_UPPER));
			setSecondNumber(generateNumber(LOWER, HARD_UPPER));
			break;
		}
		setAnswer(getFirstNumber() + getSecondNumber());
	}
	
}
