package com.word.game.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *	This class holds all the 25 dices 
 **/
public class Board {

	private static List<String> dices = new ArrayList<String>(Arrays.asList("aaafrs", "aaeeee", "aafirs", "adennn", "aeeeem", "aeegmu", 
			"aegmnn", "afirsy", "bjkqxz", "ccenst", "ceiilt", "ceilpt", "ceipst", "ddhnot", "dhhlor", "dhlnor", "dhlnor", "eiiitt", 
			"emottt", "ensssu", "fiprsy", "gorrvw", "iprrry", "nootuw", "ooottu"));
	
	/**
	 * shuffles the list of 25 dices and shuffles their position as well
	 * 
	 * @return List<String>	- shuffled dices with current facing sides constructed in a List of Strings
	 * */
	public static List<String> shuffleDices() {		
		List<String> board = new ArrayList<String>();
		
		// shuffle the dices
		Collections.shuffle(dices);

		// shuffle dice face
		// generate random numbers for selecting random face of the 5 faces of a dice
		int[] randIndex = new int[25];
		Random rand = new Random();		
		for(int i=0; i<dices.size(); i++) {
			randIndex[i] = rand.nextInt(6); 
		}				
		
		// now extract one character from each dice as the face of the dice for the board
		StringBuilder builder = new StringBuilder();
		for(int pos=0, count = 0; pos<dices.size(); pos++, count++) {
			String temp = dices.get(pos);
			
			char ch = temp.charAt(randIndex[pos]);
			if(count == 4) {
				// construct a string of 5 letters - 1 row in the board
				builder.append(ch);
				board.add(builder.toString());		
				
				// re-initialize to construct the next row on the board
				count = -1;
				builder = new StringBuilder();
			} else {
				builder.append(ch);			 
			}
		}		
		return board;
	}

	
}
