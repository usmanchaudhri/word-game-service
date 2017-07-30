package com.word.game.validation.rules;

import org.junit.Test;
import com.word.game.init.Dictionary;
import com.word.game.model.Word;
import static org.assertj.core.api.Assertions.assertThat;

public class DictionaryValidationRuleTest {

	@Test
	public void testDictionaryLookupForUpperCaseWord() {
		Dictionary dictionary = new Dictionary();
		dictionary.getWords().add("PLACE");		
		DictionaryValidationRule rule = new DictionaryValidationRule(dictionary);		
		Word word = new Word();
		word.setWord("PLACE");		

		assertThat(rule.isValid(word)).isTrue();		
	}
	
	@Test
	public void testDictionaryLookupForLowerCaseWord() {
		Dictionary dictionary = new Dictionary();
		dictionary.getWords().add("PLACE");		
		DictionaryValidationRule rule = new DictionaryValidationRule(dictionary);		
		Word word = new Word();
		word.setWord("place");		

		assertThat(rule.isValid(word)).isTrue();		
	}
	
	
}
