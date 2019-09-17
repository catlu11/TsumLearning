package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Term {
	private StringProperty word;
	private StringProperty definition;
	
	public Term() {
		
	}
	public void setTerm(String word, String definition) {
		this.word = new SimpleStringProperty(word);
		this.definition = new SimpleStringProperty(definition);
	}
	public StringProperty getWord() {
		return word;
	}
	public StringProperty getDefinition() {
		return definition;
	}
}
