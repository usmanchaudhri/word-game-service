package com.word.game.resources;

import io.dropwizard.hibernate.UnitOfWork;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.annotation.Timed;
import com.word.game.dao.GameDao;
import com.word.game.init.Dictionary;
import com.word.game.model.Board;
import com.word.game.model.Game;
import com.word.game.model.Word;
import com.word.game.services.ScoreCalculationService;
import com.word.game.validation.rules.AdjacentSquareValidationRule;
import com.word.game.validation.rules.ComparisonValidationRule;
import com.word.game.validation.rules.DictionaryValidationRule;
import com.word.game.validation.rules.ValidationRule;

@Path("/game")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GameResource {
	
	private GameDao gameDao;
	private Dictionary dictionary;
	
	public GameResource(GameDao gameDao, Dictionary dictionary) {
		this.gameDao = gameDao;
		this.dictionary = dictionary;
	}	
		
	/**
	 *	Create a new game 
	 **/
	@POST
	@UnitOfWork
	@Timed	
	public Response initGame(@Valid Game game) {
		if(game == null) {
			game = new Game();
		}
		
		List<String> board = Board.shuffleDices();
		game.setBoard(board);
		game.setScore(0);
		Game persistedGame = gameDao.create(game);		
		return Response.status(Response.Status.OK).entity(persistedGame).build();
	}	
	
	/**
	 *	Submits a word to the game 
	 **/
	@POST
	@Path("/{game_id}")
	@UnitOfWork
	@Timed	
	public Response newGame(@Valid Word word, @PathParam("game_id") Long game_id) {
		Game persistedGame = gameDao.findById(game_id);
		List<String> persistedBoard = persistedGame.getBoard();
		
		// check if the new word already exists for that game
		ComparisonValidationRule compRule = new ComparisonValidationRule(persistedGame.getWords());
		if(compRule.isValid(word)) {
			return Response.status(Response.Status.CONFLICT).entity("Word is a duplicate").build();
		}
		
		// word is not a valid play
		ValidationRule validPlayRule = new AdjacentSquareValidationRule(persistedBoard);
		if(!validPlayRule.isValid(word)) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Word is not playable on this game board").build();
		}

		// word in not in dictionary
		ValidationRule dictionaryRule = new DictionaryValidationRule(this.dictionary);
		if(!dictionaryRule.isValid(word)) {
			return Response.
					status(Response.Status.NOT_ACCEPTABLE).
					type(MediaType.APPLICATION_JSON).
					entity("Word is not in the dictionary").
					build();		
		}
		
		// calculate word score
		int wordScore = ScoreCalculationService.calculate(word.getWord());
		word.setScore(wordScore);
		word.setGame(persistedGame);		

		int gameScore = persistedGame.getScore();		
		persistedGame.setScore( (gameScore+wordScore) );		
		persistedGame.getWords().add(word);		
		
		gameDao.create(persistedGame);		
		return Response.status(Response.Status.OK).entity(word).build();
	}
	
	/**
	 *	Get a game by ID
	 **/
	@GET
	@Path("/{game_id}")
	@UnitOfWork
	@Timed
	public Response getGame(@PathParam("game_id") Long game_id) {
		Game persistedGame = gameDao.findById(game_id);
		return Response.status(Response.Status.OK).entity(persistedGame).build();
	}
	
	
}
