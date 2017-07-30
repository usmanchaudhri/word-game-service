package com.word.game.validation.rules;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import com.word.game.model.Word;

/**
 * 
 **/
public class AdjacentSquareValidationRuleTest {
	
	@Test
	public void testHorizontalThanVerticalTraversal() {
		List<String> board = Arrays.asList("glecu","oyece","nkhfn","enrso","nydmr");
		AdjacentSquareValidationRule rule = new AdjacentSquareValidationRule(board);
		Word word = new Word();
		word.setWord("nye");

		assertThat(rule.isValid(word)).isFalse();		
	}
	
	
	@Test
	public void testHorizontallyAdjacentWord() {		
		List<String> board = Arrays.asList("rdeie","oaoho","nancc","meobe","sripe");
		AdjacentSquareValidationRule rule = new AdjacentSquareValidationRule(board);
		Word word = new Word();
		word.setWord("dei");

		assertThat(rule.isValid(word)).isTrue();		
	}

	@Test
	public void testVerticalyAdjacentWord() {
		List<String> board = Arrays.asList("rdeie","oaoho","nancc","meobe","sripe");
		AdjacentSquareValidationRule rule = new AdjacentSquareValidationRule(board);
		Word word = new Word();
		word.setWord("daa");

		assertThat(rule.isValid(word)).isTrue();		
	}

	@Test	
	public void testVerticalAndHorizontalAdjacentWord() {
		List<String> board = Arrays.asList("rdeie","oaoho","nancc","meobe","sripe");
		AdjacentSquareValidationRule rule = new AdjacentSquareValidationRule(board);
		Word word = new Word();
		word.setWord("deo");

		assertThat(rule.isValid(word)).isTrue();		
	}
	
	@Test
	public void testWordDoesNotExist() {
		List<String> board = Arrays.asList("rdeie","oaoho","nancc","meobe","sripe");
		AdjacentSquareValidationRule rule = new AdjacentSquareValidationRule(board);
		Word word = new Word();
		word.setWord("xyz");

		assertThat(rule.isValid(word)).isFalse();		
	}
	
	@Test
	public void testNonAdjacentString() {
		List<String> board = Arrays.asList("rdeie","oaoho","nancc","meobe","sripe");
		AdjacentSquareValidationRule rule = new AdjacentSquareValidationRule(board);
		Word word = new Word();
		word.setWord("dez");

		assertThat(rule.isValid(word)).isFalse();		
	}	
	
	@Test	
	public void testEmptyWord() {
		List<String> board = Arrays.asList("rdeie","oaoho","nancc","meobe","sripe");
		AdjacentSquareValidationRule rule = new AdjacentSquareValidationRule(board);
		Word word = new Word();
		word.setWord("");

		assertThat(rule.isValid(word)).isFalse();		
	}
	
	
}
