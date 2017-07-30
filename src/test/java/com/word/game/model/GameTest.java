package com.word.game.model;

import static io.dropwizard.testing.FixtureHelpers.*;
import io.dropwizard.jackson.Jackson;
import java.util.ArrayList;
import java.util.HashSet;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *	Test serializing Game class 
 **/
public class GameTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
		final Game game = new Game(0, new ArrayList<String>(), new HashSet<Word>());
		game.setId(1L);

		final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/game.json"), Game.class));

        assertThat(MAPPER.writeValueAsString(game)).isEqualTo(expected);
    }    
}
