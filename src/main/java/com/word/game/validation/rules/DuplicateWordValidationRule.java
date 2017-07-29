package com.word.game.validation.rules;

import com.word.game.model.Word;

public class DuplicateWordValidationRule extends ValidationRule {

	@Override
	public boolean isValid(Word word) {
		return false;
	}
	
}
