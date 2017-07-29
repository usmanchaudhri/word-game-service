package com.word.game.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import com.word.game.model.Game;

public class GameDao extends AbstractDAO<Game> {

	public GameDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public Game create(Game game) {		
		return persist(game);
	}	
	
	public Game findById(Long id) {
		return get(id);
	}

}
