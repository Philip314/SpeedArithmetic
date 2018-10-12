package main.question;

public class QuestionMultiplication extends Question {
	
	public QuestionMultiplication(int difficulty) {
		setOperator("*");
		setDifficulty(difficulty);
		switch(difficulty) {
		case 1:
			setFirstNumber(generateNumber(1, 11));
			setSecondNumber(generateNumber(1, 11));
			break;
		case 2:
			setFirstNumber(generateNumber(1, 101));
			setSecondNumber(generateNumber(1, 101));
			break;
		case 3:
			setFirstNumber(generateNumber(1, 1001));
			setSecondNumber(generateNumber(1, 1001));
			break;
		}
		setAnswer(getFirstNumber() + getSecondNumber());
	}
	
}
