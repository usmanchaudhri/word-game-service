package com.word.game.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "WORD")
public class Word {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "word_id", nullable = false, unique = true) private Long id;
	@Column(name = "name", nullable = true) private String word;
	@Column(name = "score", nullable = true) private Integer score;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="game_id", referencedColumnName="game_id", nullable = false)
	private Game game;

	public Long getId() {
		return id;
	}

	// two words are equal if there "word" is equal
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		
		Word obj = (Word) o;
		if(word  != null ? !word.equals(obj.word) : obj.word != null) return false;
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
		result = 31 * result + (word != null ? word.hashCode() : 0);
		return result;
	}
	
	@JsonIgnore	
	public void setId(Long id) {
		this.id = id;
	}

	public Game getGame() {
		return game;
	}

	@JsonIgnore
	public void setGame(Game game) {
		this.game = game;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	
}
