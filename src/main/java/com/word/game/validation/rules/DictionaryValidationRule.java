package com.word.game.validation.rules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.word.game.init.Dictionary;
import com.word.game.model.Word;

/**
 *	Check if the word is in dictionary. The Dictionary object is populated with an external file loaded when this
 *	application starts.
 **/
public class DictionaryValidationRule extends ValidationRule {
    private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryValidationRule.class);	

	private Dictionary dictionary;
	
	public DictionaryValidationRule(Dictionary dictionary) {
		this.dictionary = dictionary;
	}
	
	@Override
	public boolean isValid(Word word) {		
		String w = word.getWord().toUpperCase();
		if(dictionary.getWords().contains(w)) {
			return true;
		}		
		return false;
	}	
	
}
