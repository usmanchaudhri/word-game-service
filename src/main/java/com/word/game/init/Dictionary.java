package com.word.game.init;

import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *	contains dictionary words - loaded from external file i.e. dictionary.txt 
 **/
public class Dictionary {
    private static final Logger LOGGER = LoggerFactory.getLogger(Dictionary.class);	

	public Set<String> words = null;	
	public Dictionary() {	
		words = new HashSet<String>();
	}

	public Set<String> getWords() {
		return words;
	}
	public void setWords(HashSet<String> words) {
		this.words = words;
	}
	
}
