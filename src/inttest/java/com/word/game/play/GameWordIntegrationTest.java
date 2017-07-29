package com.word.game.play;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import org.mockito.Mockito;
import javax.ws.rs.client.Client;
import org.junit.Rule;
import io.dropwizard.testing.junit.ResourceTestRule;
import javax.ws.rs.client.Entity;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ws.rs.core.MediaType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.word.game.dao.GameDao;
import com.word.game.init.Dictionary;
import com.word.game.model.Game;
import com.word.game.model.Word;
import com.word.game.resources.GameResource;

public class GameWordIntegrationTest {

	private static final GameDao dao = mock(GameDao.class);
	
	private static List<String> board = Arrays.asList("nenhc", "oehli", "diboc", "riytw", "asema");	
	private static Dictionary dictionary = new Dictionary();
	static {
		dictionary.getWords().add("ehl");
	}

    @Rule
	public final ResourceTestRule resources =    
			ResourceTestRule.builder().addResource(new GameResource(dao, dictionary)).build();

	
	@Before
	public void setup() {			
		
	}
	
	@After
	public void tearDown() {
		reset(dao);
	}
	
	@Test
	public void testPlayGameWord() {		

		// expected output
		Word word = new Word();		
		word.setWord("ehl");
		word.setScore(0);
		word.setId(1L);		
		Game game = new Game(0, board, new HashSet<Word>());
		game.setId(1L);
		
		// mock dao call
		Mockito.when(dao.findById(Mockito.any(Long.class))).thenReturn(game);
		Mockito.when(dao.create(Mockito.any(Game.class))).thenReturn(game);
		
		Word wordPlay = new Word();		
		wordPlay.setWord("ehl");
		wordPlay.setScore(0);
		
		Client client = resources.client();
		Word gamePersisted = resources
				.client()
				.target(new StringBuilder("/game/2").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(word), Word.class);

		System.out.println("");
		
//		assertThat(gamePersisted.getId()).isNotNull();
//        assertThat(resources.  target("/person/blah").request().get(Person.class))
//        	.isEqualTo(person);
//        verify(dao).fetchPerson("blah");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
