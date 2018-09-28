package main.question;

public class QuestionSubtraction extends Question {
	
	public QuestionSubtraction(int difficulty) {
		setOperator("-");
		setDifficulty(difficulty);
		switch(difficulty) {
		case 1:
			setFirstNumber(generateNumber(2, 11));
			setSecondNumber(generateNumber(1, getFirstNumber()));
			break;
		case 2:
			setFirstNumber(generateNumber(2, 101));
			setSecondNumber(generateNumber(1, getFirstNumber()));
			break;
		case 3:
			setFirstNumber(generateNumber(2, 1001));
			setSecondNumber(generateNumber(1, getFirstNumber()));
			break;
		}
		setAnswer(getFirstNumber() + getSecondNumber());
	}
	
}
