package com.word.game.validation.rules;

import java.util.ArrayList;
import java.util.List;
import com.word.game.model.Word;

/**
 *	make sure the squares are horizontally or vertically adjacent 
 **/
public abstract class ValidationRule {
	
	private List<String> baord = new ArrayList<String>();	
	protected char[][] gameBoard;	
	
	public ValidationRule() {}
	
	public ValidationRule(List<String> input) {
		this.baord = input;
		int size = baord.size();
		gameBoard = new char[size][size];		// we know the board is a square and has 5x5 characters
		init();		
	}
	
	// initialize the game board into a 2D matrix to apply validation rules
	public void init() {
		int size = baord.size();				
		for(int i=0; i<size; i++) {
			String word = baord.get(i);			
			for(int j=0; j<size; j++) {
				 gameBoard[i][j] = word.charAt(j);
			}
		}		
	}
	
	public abstract boolean isValid(Word word);

	public List<String> getBaord() {
		return baord;
	}

	public void setBaord(List<String> baord) {
		this.baord = baord;
	}	
	
}
