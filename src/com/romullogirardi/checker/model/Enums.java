package com.romullogirardi.checker.model;

public class Enums {

	public enum QuestionOptionLetters {
		A, B, C, D, E, VAZIA;
	}
	
	public enum KnowledgeArea {
		HUMAN_SCIENCE, NATURAL_SCIENCE, MATHEMATICS, LANGUAGES;
	}
	
	public enum Discipline {
		HUMAN_SCIENCE, NATURAL_SCIENCE, MATHEMATICS, LANGUAGES;
	}
	
	public enum ForeignLanguage {
		ENGLISH, SPANISH;
		
		public String toString(){
			
			switch (this) {
				case ENGLISH:
					return "Inglês";
				case SPANISH:
					return "Espanhol";
				default:
					return "";
			}
		}
		
		public static ForeignLanguage fromString(String label) {
			
			if(ENGLISH.toString().equals(label))
				return ENGLISH;
			else if(SPANISH.toString().equals(label))
				return SPANISH;
			else
				return ENGLISH;
		}
	}
}