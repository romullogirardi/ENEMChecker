package com.romullogirardi.checker.model;

import com.romullogirardi.checker.model.Enums.Discipline;
import com.romullogirardi.checker.model.Enums.KnowledgeArea;
import com.romullogirardi.checker.model.Enums.QuestionOptionLetters;

public class Question {

	//ATTRIBUTES
	private KnowledgeArea knowledgeArea;
	private Discipline discipline;
	private String questionStatement;
	private String[] options = new String[5];
	private QuestionOptionLetters correctOptionIndex;
	private QuestionOptionLetters selectedOptionIndex;
	
	//CONSTRUCTORS
	public Question(KnowledgeArea knowledgeArea, Discipline discipline, String questionStatement, String[] options, QuestionOptionLetters correctOptionIndex) {
		this.knowledgeArea = knowledgeArea;
		this.discipline = discipline;
		this.questionStatement = questionStatement;
		this.options = options;
		this.correctOptionIndex = correctOptionIndex;
	}

	public Question(KnowledgeArea knowledgeArea, Discipline discipline, QuestionOptionLetters correctOptionIndex) {
		this.knowledgeArea = knowledgeArea;
		this.discipline = discipline;
		this.correctOptionIndex = correctOptionIndex;
	}

	//GETTERS AND SETTERS
	public KnowledgeArea getKnowledgeArea() {
		return knowledgeArea;
	}

	public void setKnowledgeArea(KnowledgeArea knowledgeArea) {
		this.knowledgeArea = knowledgeArea;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	public String getQuestionStatement() {
		return questionStatement;
	}

	public void setQuestionStatement(String questionStatement) {
		this.questionStatement = questionStatement;
	}

	public String[] getOptions() {
		return options;
	}
	
	public void setOptions(String[] options) {
		this.options = options;
	}
	
	public QuestionOptionLetters getCorrectOptionIndex() {
		return correctOptionIndex;
	}
	
	public void setCorrectOptionIndex(QuestionOptionLetters correctOptionIndex) {
		this.correctOptionIndex = correctOptionIndex;
	}
	
	public QuestionOptionLetters getSelectedOptionIndex() {
		return selectedOptionIndex;
	}
	
	public void setSelectedOptionIndex(QuestionOptionLetters selectedOptionIndex) {
		this.selectedOptionIndex = selectedOptionIndex;
	}

	//OTHER METHODS
	public boolean isRight() {
		return (correctOptionIndex == selectedOptionIndex);
	}
	
	public String toString() {
		return knowledgeArea + " - " + discipline + " - " + correctOptionIndex + " - " + selectedOptionIndex + " - " + ((isRight()) ? "CERTO" : "ERRADO");
	}
	
	public String showResult() {
		return correctOptionIndex + " / " + selectedOptionIndex;
	}
}