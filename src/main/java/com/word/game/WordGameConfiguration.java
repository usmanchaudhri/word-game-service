package com.word.game;

import java.util.Collections;
import java.util.Map;

import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.DatabaseConfiguration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.word.game.core.Template;

import org.hibernate.validator.constraints.*;

import javax.validation.Valid;
import javax.validation.constraints.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WordGameConfiguration extends Configuration {
    private static final Logger LOGGER = LoggerFactory.getLogger(WordGameConfiguration.class);

    @Valid
    @NotNull
    @JsonProperty
    private DataSourceFactory database = new DataSourceFactory();

    @NotNull
    private Map<String, Map<String, String>> viewRendererConfiguration = Collections.emptyMap();

    @Valid
    @NotNull
    private JerseyClientConfiguration jerseyClient = new JerseyClientConfiguration();
	
	@NotEmpty
	@JsonProperty
	private String template;
	
	@NotEmpty
	@JsonProperty
	private String defaultName = "Stranger";

	@NotEmpty
	@JsonProperty
	private String localdev = "yes";
	
    @JsonProperty("database")
	public DataSourceFactory getDatabase() {
//        LOGGER.info("Dropwizard dummy DB URL (will be overridden)=" + database.getUrl());
//        DatabaseConfiguration databaseConfiguration = HerokuDatabaseConfiguration.create(System.getenv("DATABASE_URL"));
//        database = databaseConfiguration.getDataSourceFactory(null);
//        LOGGER.info("Heroku DB URL=" + database.getUrl());
        return database;        
	}
    
    @JsonProperty("database")
	public void setDatabase(DataSourceFactory database) {
		this.database = database;
	}

	public Map<String, Map<String, String>> getViewRendererConfiguration() {
		return viewRendererConfiguration;
	}

	public void setViewRendererConfiguration(
			Map<String, Map<String, String>> viewRendererConfiguration) {
		this.viewRendererConfiguration = viewRendererConfiguration;
	}
	
	@JsonProperty("jerseyClient")
	public JerseyClientConfiguration getJerseyClient() {
		return jerseyClient;
	}

	public void setJerseyClient(JerseyClientConfiguration jerseyClient) {
		this.jerseyClient = jerseyClient;
	}

	public Template buildTemplate() {
		return new Template(template, defaultName);
	}

	public String getTemplate() {
		return template;
	}

}
