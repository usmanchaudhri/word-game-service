package com.word.game;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;






//import io.dropwizard.assets.AssetsBundle;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.ws.rs.ext.ExceptionMapper;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.word.game.cli.RenderCommand;
import com.word.game.dao.GameDao;
import com.word.game.dao.WordDao;
import com.word.game.exception.mappers.HttpBadRequestException;
import com.word.game.init.Dictionary;
import com.word.game.init.InitDataLoaderService;
import com.word.game.model.Game;
import com.word.game.model.Word;
import com.word.game.resources.GameResource;

/**
 *	This is the startup file where all the entities, daos, and services are registered with-in the Environment. 
 **/
public class WordGameApplication extends Application<WordGameConfiguration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(WordGameApplication.class);	

    public static void main(final String[] args) throws Exception {
        new WordGameApplication().run(args);
    }
    
    private final HibernateBundle<WordGameConfiguration> hibernateBundle =
            new HibernateBundle<WordGameConfiguration>(
            		Game.class,
            		Word.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(WordGameConfiguration configuration) {
                	return configuration.getDatabase();
                }
            };    
    

    @Override
    public String getName() {
        return "word-game";
    }

    @Override
    public void initialize(final Bootstrap<WordGameConfiguration> bootstrap) {
        LOGGER.info("Initializing configuration");
		bootstrap.addCommand(new RenderCommand());	// custom class
		bootstrap.addBundle(new AssetsBundle());
        bootstrap.addBundle(new MigrationsBundle<WordGameConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(WordGameConfiguration configuration) {
                return configuration.getDatabase();
            }
        });
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(final WordGameConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(RolesAllowedDynamicFeature.class);        

        // Allows Cross-origin resource sharing (CORS)
        // Enable CORS headers
        final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
//        cors.setInitParameter("Access-Control-Allow-Origin", "*");
        cors.setInitParameter("allowCredentials", "true");
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin,Authorization");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        // Enable CORS headers
        
		final GameDao gameDao = new GameDao(hibernateBundle.getSessionFactory());
		final WordDao wordDao = new WordDao(hibernateBundle.getSessionFactory());
		
		// initialize look-up services i.e. dictionary
		Dictionary dictionary = new Dictionary();
        InitDataLoaderService dataLoader = new InitDataLoaderService(dictionary);
        dataLoader.init();          
		
        // explicitly register exception mapper
        removeDefaultExceptionMappers(Boolean.TRUE, environment);

        // task are things that should run triggered by a POST, but don't need to respond
        // environment.admin().addTask(new MyJobTask());
        
        environment.jersey().register(new HttpBadRequestException());        
        environment.jersey().register(new GameResource(gameDao, dictionary));
//        environment.healthChecks().register("TemplateHealth", new TemplateHealthCheck(template));
        
		// load the files here        
    }
    
    /**
     * removes any default exception mapper since we want to override
     * the exception mappers with our own
     * */
	private void removeDefaultExceptionMappers(boolean deleteDefault, Environment environment) {
	    if(deleteDefault) {
	        ResourceConfig jrConfig = environment.jersey().getResourceConfig();
	        Set<Object> dwSingletons = jrConfig.getSingletons();
	        List<Object> singletonsToRemove = new ArrayList<Object>();

	        for (Object singletons : dwSingletons) {
	            if (singletons instanceof ExceptionMapper && !singletons.getClass().getName().contains("DropwizardResourceConfig")) {
	                singletonsToRemove.add(singletons);
	            }
	        }

	        for (Object singletons : singletonsToRemove) {
	        	LOGGER.info("Deleting this ExceptionMapper: " + singletons.getClass().getName());
	            jrConfig.getSingletons().remove(singletons);
	        }
	    }
	}


}
