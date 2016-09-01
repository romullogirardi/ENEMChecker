package com.romullogirardi.checker.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.DocumentException;
import com.romullogirardi.checker.model.Enums.ForeignLanguage;
import com.romullogirardi.checker.model.Enums.KnowledgeArea;
import com.romullogirardi.checker.model.Enums.QuestionOptionLetters;

public class QuestionsChecker {

	//IMPLEMENTATION AS A SINGLETON
	private static QuestionsChecker instance = null;
	
	public static QuestionsChecker getInstance() {
		if(instance == null) {
			instance = new QuestionsChecker();
		}
		return instance;
	}
	
	//ATTRIBUTES
	private String studentName = new String();
	private ForeignLanguage foreignLanguage;
	private List<Question> questions = new ArrayList<Question>();

	//GETTERS AND SETTERS
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public ForeignLanguage getForeignLanguage() {
		return foreignLanguage;
	}

	public void setForeignLanguage(ForeignLanguage foreignLanguage) {
		this.foreignLanguage = foreignLanguage;
	}

	//OTHER METHODS
	public void setSelectedOptions(List<QuestionOptionLetters> selectedOptions) {
		
		for(int index = 0; index < questions.size(); index++) {
			questions.get(index).setSelectedOptionIndex(selectedOptions.get(index));
		}
	}
	
	public void showResult() {
		
		for(Question question : this.questions) {
			System.out.println(question.toString());
		}
		
		PDFGenerator myPDFGenerator = new PDFGenerator();
		try {
			myPDFGenerator.generatePdf();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		myPDFGenerator.openGeneratedFile();
	}
	
	public float getPoints(KnowledgeArea knowledgeArea) {
		
		int rightQuestionsCounter = 0;
		for(Question question : questions) {
			if(question.isRight() && question.getKnowledgeArea().equals(knowledgeArea)) {
				rightQuestionsCounter++;
			}
		}

		return (float) rightQuestionsCounter * 1000 / 45;
	}
	
	public float getPointsAverage() {
		return (getPoints(KnowledgeArea.HUMAN_SCIENCE) + getPoints(KnowledgeArea.NATURAL_SCIENCE) + getPoints(KnowledgeArea.LANGUAGES) + getPoints(KnowledgeArea.MATHEMATICS)) / 4;
	}
}
