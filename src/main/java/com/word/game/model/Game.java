package com.word.game.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 *	represents a game played by the user. Each game played will have a unique ID. A game is composed of 25 dices.
 **/
@Entity
@Table(name = "GAME")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "game_id", nullable = false, unique = true) private Long id;
	@Column(name = "score", nullable = true) private Integer score;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "game_board", joinColumns = @JoinColumn(name = "game_id"))
	@Column(name="DICE")
	private List<String> board;

	@OneToMany(mappedBy="game", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Word> words = new HashSet<Word>();	
	
	public Game() {}
	public Game(int score, List<String> board, Set<Word> words) {
		this.score = score;
		this.board = board;
		this.words = words;
	}	
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		
		Game game = (Game) o;
		if(id != null ? !id.equals(game.id) : game.id != null) return false;
		return true;
	}
	
	
	/**
	 *	Overriding hashcode
	 * 
	 * 	A good hashcode will optimize performance when this object is used in collection classes, 
	 *  also, when using in Object Relational Mapper (ORM) i.e Hibernate.
	 **/	
	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		return result;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Word> getWords() {
		return words;
	}

	public void setWords(Set<Word> words) {
		this.words = words;
	}

	public List<String> getBoard() {
		return board;
	}

	public void setBoard(List<String> board) {
		this.board = board;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	
}
