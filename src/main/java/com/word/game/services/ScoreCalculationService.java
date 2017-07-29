package com.word.game.services;

import java.util.HashMap;
import java.util.Map;

/**
 * This service calculates the total weight of a given word based on the number of letters. The HashMap holds the
 * weight according to the length of the word
 * */
public class ScoreCalculationService {

	public static Map<Integer, Integer> weightLookupMap;
	static {
		weightLookupMap = new HashMap<Integer, Integer>();		
		weightLookupMap.put(2, 1);
		weightLookupMap.put(4, 2);
		weightLookupMap.put(5, 3);
		weightLookupMap.put(6, 4);
		weightLookupMap.put(7, 5);
	}
	
	/**
	 * NOTE - weight return will be zero if the word size is less than 3 letters
	 **/
	public static Integer calculate(String word) {
		int wordSize = word.length();	
		int weight = 0;
		if(wordSize >= 8) {
			weight = 6;
		} else {
			weight = weightLookupMap.get(wordSize);
		}		
		return weight;
	}
	
}
