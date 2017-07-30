package com.word.game.validation.rules;

import java.util.ArrayList;
import java.util.List;
import com.word.game.model.Word;

/**
 * Rule for validating if the given word is a valid play on the board or not.
 **/
public class AdjacentSquareValidationRule extends ValidationRule {

	public AdjacentSquareValidationRule(List<String> board) {
		super(board);
	}

	/**
	 *	this will track the current position	 
	 **/
	class Point {
		int row;
		int col;
		public Point() {}
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}		
		
		@Override
		public boolean equals(Object o) {
			Point p = (Point) o;
			return p.row == row && p.col == col;
		}
	}

	/**
	 *	check if the word is a valid play on the board. 
	 *	
	 *	@param word		- the given word to find in the grid.
	 **/
	@Override
	public boolean isValid(Word word) {		
		if(word == null || word.getWord().isEmpty()) {
			return false;
		}
		
		int n = gameBoard.length;
		int m = gameBoard[0].length;
		List<List<Point>> allpaths = new ArrayList<List<Point>>();
		for(int row=0; row<n; row++) {
			for(int col=0; col<m; col++) {
				List<Point> path = new ArrayList<Point>();
				if(gameBoard[row][col] == word.getWord().charAt(0)) {
					String s = word.getWord();					
					Point startPos = new Point(row, col);
					path.add(new Point(row, col));
					
					List<Point> result = search(gameBoard, startPos, s.substring(1), path);
					if(result.size() == word.getWord().length()) {
						allpaths.add(result);
					}
				}
			}
		}
		
		// if all occurrence of paths are equal to the word than valid play
		int wordLength = word.getWord().length();
		for(List<Point> path : allpaths) {
			if(wordLength == path.size()) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}	
	
	/**
	 *	Once the first letter of the word is found this method recursively finds the subsequent letters of the words either horizontally
	 *	or vertically, it does not searches diagonally.
	 *	
	 *	@param	grid 		- 2D matrix for all characters on the board. Each location on the grid represents the face of a Dice.
	 *	@param	position	- the current position from where to navigate to find the subsequent letters.
	 *	@param	word		- list of subsequent letters to find.
	 *	@param	path		- the constructed path which constructs the word.
	 **/
	public List<Point> search(char[][] grid, Point position, String word, List<Point> path) {		
		if(word.isEmpty()) {return path;}
		
		char current = word.charAt(0);
		int row = position.row;
		int col = position.col;
		int size = grid.length;
		
		// check all four directions - handle array index out of bound
		if((row-1 >= 0) && grid[row-1][col] == current) {
			position.row = row-1;
			position.col = col;
		} else if((row+1 < size) && grid[row+1][col] == current) {
			position.row = row+1;
			position.col = col;
		} else if((col-1 >= 0) && grid[row][col-1] == current) {
			position.row = row;
			position.col = col-1;
		} else if((col+1 < size) && grid[row][col+1] == current) {
			position.row = row;
			position.col = col+1;
		}
		
		if(path.contains(position)) {
			return new ArrayList<Point>();
		}
		
		List<Point> result = new ArrayList<Point>();
		List<Point> newPath = new ArrayList<Point>();
		newPath.addAll(path);		
		newPath.add(position);
		
		result.addAll(search(grid, new Point(position.row, position.col), word.substring(1), newPath));
		return result;
	}	
	
}
