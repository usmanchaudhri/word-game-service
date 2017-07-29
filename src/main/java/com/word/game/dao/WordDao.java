package com.word.game.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import com.word.game.model.Word;

public class WordDao extends AbstractDAO<Word> {

	public WordDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public Word create(Word word) {		
		return persist(word);
	}	

}
