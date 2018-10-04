package com.romullogirardi.checker.model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.romullogirardi.checker.model.Enums.QuestionOptionLetters;

public class AnswersCardReader {

	//CONSTANTS
//	private int FIRST_ROW_Y_POSITION_1 = 1612;
//	private float ROW_DISTANCE_1 = 31.5f;
//	private int FIRST_COLUMN_X_POSITION_1 = 215;
//	private int COLUMN_DISTANCE_1 = 220;
//	private int ANSWERS_X_DISTANCE_1 = 33;
//	private int COLUMN_MIDDLE_GAP_1 = 14;
	private int FIRST_ROW_Y_POSITION_1 = 2390;
	private float ROW_DISTANCE_1 = 46f;
	private int FIRST_COLUMN_X_POSITION_1 = 342;
	private int COLUMN_DISTANCE_1 = 320;
	private int ANSWERS_X_DISTANCE_1 = 50;
	private int COLUMN_MIDDLE_GAP_1 = 19;
	
//	private int FIRST_ROW_Y_POSITION_2 = 1616;
//	private float ROW_DISTANCE_2 = 31.8f;
//	private int FIRST_COLUMN_X_POSITION_2 = 216;
//	private int COLUMN_DISTANCE_2 = 223;
//	private int ANSWERS_X_DISTANCE_2 = 34;
//	private int COLUMN_MIDDLE_GAP_2 = 12;
	private int FIRST_ROW_Y_POSITION_2 = 2405;
	private float ROW_DISTANCE_2 = 46f;
	private int FIRST_COLUMN_X_POSITION_2 = 353;
	private int COLUMN_DISTANCE_2 = 323;
	private int ANSWERS_X_DISTANCE_2 = 49;
	private int COLUMN_MIDDLE_GAP_2 = 19;
	
//	private int MARKING_DIMENSION = 5;
//	private int COLOR_REFERENCE = 180;
	private int MARKING_DIMENSION = 10;
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
//		System.out.println(questionNumber + " - " + selectedOption);
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

		int answersXDistance = (questionNumber > 90) ? ANSWERS_X_DISTANCE_2 : ANSWERS_X_DISTANCE_1;

//		System.out.println(questionNumber + "-" + questionOptionLetter + "(" + (getFirstOptionPosition(questionNumber).getX() + (numberOfXJumps * ANSWERS_X_DISTANCE_1)) + 
//				", " + getFirstOptionPosition(questionNumber).getY() + ") - " +
//				(((isSelected(getFirstOptionPosition(questionNumber).getX() + (numberOfXJumps * answersXDistance), getFirstOptionPosition(questionNumber).getY())) ? 
//				"MARCADO" : "DESMARCADO")));

		return isSelected((getFirstOptionPosition(questionNumber).getX() + (numberOfXJumps * answersXDistance)), getFirstOptionPosition(questionNumber).getY());
	}
	
	private AnswersCardPosition getFirstOptionPosition(int questionNumber) {

		int numberOfXJumps = 0;
		int numberOfYJumps = 0;
		int firstColumnXPosition = (questionNumber > 90) ? FIRST_COLUMN_X_POSITION_2 : FIRST_COLUMN_X_POSITION_1;
		int columnDistance = (questionNumber > 90) ? COLUMN_DISTANCE_2 : COLUMN_DISTANCE_1;
		int firstRowYPosition = (questionNumber > 90) ? FIRST_ROW_Y_POSITION_2 : FIRST_ROW_Y_POSITION_1;
		float rowDistance = (questionNumber > 90) ? ROW_DISTANCE_2 : ROW_DISTANCE_1;
		int columnMiddleGap = (questionNumber > 90) ? COLUMN_MIDDLE_GAP_2 : COLUMN_MIDDLE_GAP_1;
		if((1 <= questionNumber && questionNumber <= 15) || (91 <= questionNumber && questionNumber <= 105)) {
			if(91 <= questionNumber && questionNumber <= 105)
				questionNumber -= 90;
			numberOfYJumps = questionNumber - 1;
			columnMiddleGap = 0;
		}
		else if((16 <= questionNumber && questionNumber <= 30) || (106 <= questionNumber && questionNumber <= 120)) {
			if(106 <= questionNumber && questionNumber <= 120)
				questionNumber -= 90;
			numberOfXJumps = 1;
			numberOfYJumps = questionNumber - 16;
			columnMiddleGap = 0;
		}
		else if((31 <= questionNumber && questionNumber <= 45) || (121 <= questionNumber && questionNumber <= 135)) {
			if(121 <= questionNumber && questionNumber <= 135)
				questionNumber -= 90;
			numberOfXJumps = 2;
			numberOfYJumps = questionNumber - 31;
			columnMiddleGap = 0;
		}
		else if((46 <= questionNumber && questionNumber <= 60) || (136 <= questionNumber && questionNumber <= 150)) {
			if(136 <= questionNumber && questionNumber <= 150)
				questionNumber -= 90;
			numberOfXJumps = 3;
			numberOfYJumps = questionNumber - 46;
		}
		else if((61 <= questionNumber && questionNumber <= 75) || (151 <= questionNumber && questionNumber <= 165)) {
			if(151 <= questionNumber && questionNumber <= 165)
				questionNumber -= 90;
			numberOfXJumps = 4;
			numberOfYJumps = questionNumber - 61;
		}
		else if((76 <= questionNumber && questionNumber <= 90) || (166 <= questionNumber && questionNumber <= 180)) {
			if(166 <= questionNumber && questionNumber <= 180)
				questionNumber -= 90;
			numberOfXJumps = 5;
			numberOfYJumps = questionNumber - 76;
		}
		
		return new AnswersCardPosition(firstColumnXPosition + (numberOfXJumps * columnDistance) + columnMiddleGap, Math.round(firstRowYPosition + (numberOfYJumps * rowDistance)));
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