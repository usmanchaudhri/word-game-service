package com.word.game.model;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WordTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
		Word word = new Word();
		word.setWord("dei");
		word.setScore(1);
		
		final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/word.json"), Word.class));

        assertThat(MAPPER.writeValueAsString(word)).isEqualTo(expected);
    }    

}
