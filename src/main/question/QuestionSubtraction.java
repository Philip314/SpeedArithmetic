package main.question;

public class QuestionSubtraction extends Question {
	
	public static final int SUBTRACT_LOWER = 2;
	
	public QuestionSubtraction(int difficulty) {
		setOperator("-");
		setDifficulty(difficulty);
		switch(difficulty) {
		case 1:
			setFirstNumber(generateNumber(SUBTRACT_LOWER, EASY_UPPER));
			setSecondNumber(generateNumber(LOWER, getFirstNumber()));
			break;
		case 2:
			setFirstNumber(generateNumber(SUBTRACT_LOWER, MED_UPPER));
			setSecondNumber(generateNumber(LOWER, getFirstNumber()));
			break;
		case 3:
			setFirstNumber(generateNumber(SUBTRACT_LOWER, HARD_UPPER));
			setSecondNumber(generateNumber(LOWER, getFirstNumber()));
			break;
		}
		setAnswer(getFirstNumber() + getSecondNumber());
	}
	
}
