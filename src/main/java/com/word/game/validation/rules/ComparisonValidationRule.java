package com.word.game.validation.rules;

import java.util.Iterator;
import java.util.Set;
import com.word.game.model.Word;

public class ComparisonValidationRule extends ValidationRule {
	
	private Set<Word> words;
	
	public ComparisonValidationRule(Set<Word> words) {
		this.words = words;
	}
	
	@Override
	public boolean isValid(Word word) {		
		Iterator<Word> ite = words.iterator();
		while(ite.hasNext()) {
			Word exiting = ite.next();
			if(exiting.equals(word)) {
				return true;
			}
		}
		return false;
	}

}
