package com.word.game.init;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *	Initializes with any seed data we could choose to either load the data from a file or db.
 *	In this example this class loads the dice information stored in a file, also, it loads
 *	the dictionary from the text file and stores it into memory.
 **/
public class InitDataLoaderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitDataLoaderService.class);	

	public Dictionary dictionary;
		
	public InitDataLoaderService(Dictionary dictionary) {
		this.dictionary = dictionary;
	}
	
	public void init() {
		loadDictionary();
	}
	
    public void loadDictionary() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("dictionary.txt").getFile());
		Scanner scanner = null;			

		try {			
			scanner = new Scanner(file);			
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				dictionary.getWords().add(line);
			}			
			
		} catch(IOException e) {
			// TODO throw file read error
			e.printStackTrace();
			LOGGER.error("error reading dictionary file" + e.getMessage());
			throw new RuntimeException();
		} finally {
			scanner.close();
		}   	
    }

	
}
