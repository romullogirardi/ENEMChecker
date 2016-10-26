package com.romullogirardi.checker.model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.romullogirardi.checker.model.Enums.QuestionOptionLetters;

public class AnswersCardReader {

	//CONSTANTS
	private int FIRST_ROW_Y_POSITION = 88;
	private float ROW_DISTANCE = 31.5f;
	private int FIRST_COLUMN_X_POSITION = 65;
	private int COLUMN_DISTANCE = 220;
	private int COLUMN_MIDDLE_GAP = 14;
	private int ANSWERS_X_DISTANCE = 34;
	private int MARKING_DIMENSION = 4;
	private int COLOR_REFERENCE = 180;
	
	//ATTRIBUTE
	private String filePath = null;
	
	//CONSTRUCTOR
	public AnswersCardReader(String filePath) {
		this.filePath = filePath;
	}
	
	//PUBLIC METHOD
	public QuestionOptionLetters getSelectedOption(int questionNumber) {
		
		QuestionOptionLetters selectedOption = QuestionOptionLetters.VAZIA;
		for(QuestionOptionLetters questionOptionLetter : QuestionOptionLetters.values()) {
			if(!questionOptionLetter.equals(QuestionOptionLetters.VAZIA) && isOptionSelected(questionNumber, questionOptionLetter))
				selectedOption = (selectedOption.equals(QuestionOptionLetters.VAZIA)) ? questionOptionLetter : QuestionOptionLetters.VAZIA;
		}
		System.out.println(questionNumber + " - " + selectedOption);
		return selectedOption;
	}
	
	//PRIVATE METHODS
	private boolean isOptionSelected(int questionNumber, QuestionOptionLetters questionOptionLetter) {

		int numberOfXJumps = 0;
		switch (questionOptionLetter) {
			case A:
				numberOfXJumps = 0;
				break;
			case B:
				numberOfXJumps = 1;
				break;
			case C:
				numberOfXJumps = 2;
				break;
			case D:
				numberOfXJumps = 3;
				break;
			case E:
				numberOfXJumps = 4;
				break;
			default:
				break;
		}

		System.out.println(questionNumber + "-" + questionOptionLetter + "(" + (getFirstOptionPosition(questionNumber).getX() + (numberOfXJumps * ANSWERS_X_DISTANCE)) + 
				", " + getFirstOptionPosition(questionNumber).getY() + ") - " +
				(((isSelected(getFirstOptionPosition(questionNumber).getX() + (numberOfXJumps * ANSWERS_X_DISTANCE), getFirstOptionPosition(questionNumber).getY())) ? 
				"MARCADO" : "DESMARCADO")));
		return isSelected((getFirstOptionPosition(questionNumber).getX() + (numberOfXJumps * ANSWERS_X_DISTANCE)), getFirstOptionPosition(questionNumber).getY());
	}
	
	private AnswersCardPosition getFirstOptionPosition(int questionNumber) {

		int numberOfXJumps = 0;
		int numberOfYJumps = 0;
		int columnMiddleGap = 0;
		if((1 <= questionNumber && questionNumber <= 15) || (91 <= questionNumber && questionNumber <= 105)) {
			if(91 <= questionNumber && questionNumber <= 105)
				questionNumber -= 90;
			numberOfYJumps = questionNumber - 1;
		}
		else if((16 <= questionNumber && questionNumber <= 30) || (106 <= questionNumber && questionNumber <= 120)) {
			if(106 <= questionNumber && questionNumber <= 120)
				questionNumber -= 90;
			numberOfXJumps = 1;
			numberOfYJumps = questionNumber - 16;
		}
		else if((31 <= questionNumber && questionNumber <= 45) || (121 <= questionNumber && questionNumber <= 135)) {
			if(121 <= questionNumber && questionNumber <= 135)
				questionNumber -= 90;
			numberOfXJumps = 2;
			numberOfYJumps = questionNumber - 31;
		}
		else if((46 <= questionNumber && questionNumber <= 60) || (136 <= questionNumber && questionNumber <= 150)) {
			if(136 <= questionNumber && questionNumber <= 150)
				questionNumber -= 90;
			numberOfXJumps = 3;
			numberOfYJumps = questionNumber - 46;
			columnMiddleGap = COLUMN_MIDDLE_GAP;
		}
		else if((61 <= questionNumber && questionNumber <= 75) || (151 <= questionNumber && questionNumber <= 165)) {
			if(151 <= questionNumber && questionNumber <= 165)
				questionNumber -= 90;
			numberOfXJumps = 4;
			numberOfYJumps = questionNumber - 61;
			columnMiddleGap = COLUMN_MIDDLE_GAP;
		}
		else if((76 <= questionNumber && questionNumber <= 90) || (166 <= questionNumber && questionNumber <= 180)) {
			if(166 <= questionNumber && questionNumber <= 180)
				questionNumber -= 90;
			numberOfXJumps = 5;
			numberOfYJumps = questionNumber - 76;
			columnMiddleGap = COLUMN_MIDDLE_GAP;
		}
		return new AnswersCardPosition(FIRST_COLUMN_X_POSITION + (numberOfXJumps * COLUMN_DISTANCE) + columnMiddleGap, Math.round(FIRST_ROW_Y_POSITION + (numberOfYJumps * ROW_DISTANCE)));
	}
	
	private boolean isSelected(int x, int y) {
		
		File file= new File(filePath);
		BufferedImage image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		float grayScaleSum = 0;
		int numberOfPoints = 0;
		for(int indexX = (x - MARKING_DIMENSION); indexX <= (x + MARKING_DIMENSION); indexX++) {
			grayScaleSum += getGrayScaleFromColor(new Color(image.getRGB(indexX, y)));
			numberOfPoints++;
		}
		for(int indexY = (y - MARKING_DIMENSION); indexY <= (y + MARKING_DIMENSION); indexY++) {
			grayScaleSum += getGrayScaleFromColor(new Color(image.getRGB(x, indexY)));
			numberOfPoints++;
		}
		
		int mediumGrayScale = Math.round(grayScaleSum / numberOfPoints);
//		System.out.println(mediumGrayScale);
		return (mediumGrayScale < COLOR_REFERENCE);
	}
	
	private float getGrayScaleFromColor(Color color) {
		return (color.getRed() + color.getGreen() + color.getBlue()) / 3;
	}
	
	//INNER CLASS
	static class AnswersCardPosition {
		
		int x;
		int y;

		public AnswersCardPosition(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}
	}
}