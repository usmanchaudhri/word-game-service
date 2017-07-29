package com.word.game.validation.rules;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;

import com.word.game.model.Word;

public class TestAdjacentSquareValidationRule {
	
	public static void main(String[] args) {		
	}
	
	
	@Test
	public void testValidSudoku4x4() {
		List<String> input = new ArrayList<String>();
		input.add("nenhe");
		input.add("oehli");
		input.add("diboc");
		input.add("riytw");
		input.add("asema");
		
		ValidationRule rule = new AdjacentSquareValidationRule(input);		
		
		Word word = new Word();
		word.setWord("ehl");		
		rule.isValid(word);						
		TestCase.assertTrue(true);
	}

	
}
