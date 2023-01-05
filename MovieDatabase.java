import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvMalformedLineException;

public class MovieDatabase {
	
	private CSVReader csvreader;
	
	// database: stores the data from the csv file using the actor as the key 
	// and an ArrayList of MovieAppearance objects as the value. Refer to 
	// MovieAppearances.java to see what a MovieAppearance contains.
	private HashMap<String, ArrayList<MovieAppearance>> database;
	
	public MovieDatabase(String data) {
		csvreader = null;
		database = new HashMap<String, ArrayList<MovieAppearance>>();
		setDatabase(data); // fills in the data to the database
	}
	
	/**
	 * Reads the file of the given path and stores the data into a database
	 * 
	 * @param data	the path of a file to read
	 * @return		the database of actors and their movie appearance from the file all stored in a Hashmap
	 */
	public HashMap<String, ArrayList<MovieAppearance>> setDatabase(String data) {
		
		// Uses csvreader to read the file and uses json simple to read the name and character fields in json. 
		// Stores the data in the hashmap database
		
		try {
			
			CSVParser parser = new CSVParserBuilder().withEscapeChar('\0').build(); // makes sure \ is not ignored/escaped
			csvreader = new CSVReaderBuilder(new FileReader(data)).withCSVParser(parser).build();
			List<String[]> columns;
				
			try {
				
				columns = csvreader.readAll(); // Reads the file, splitting each line into an element of a list, each element containing the 4 columns
				
				for(int j = 1; j < columns.size(); j++) {
					JSONArray arr = (JSONArray) JSONValue.parse(columns.get(j)[2]); // splits and stores the json arrays in the cast column
					for(int i = 0; i < arr.size(); i++) {
						
						// For each actor in the cast, create a MovieAppearance with their movie and character
						MovieAppearance movie = new MovieAppearance(columns.get(j)[1], (String) ((JSONObject)arr.get(i)).get("character"));
						
						// If the actor has been added to the database already, add their new MovieAppearance to their ArrayList,
						// if they have not already been added, create a new MovieAppearance ArrayList for them and add them
						ArrayList<MovieAppearance> wall = database.get((String) ((JSONObject)arr.get(i)).get("name"));
						if(wall == null) {
							wall = new ArrayList<MovieAppearance>();
						}
						wall.add(movie);
						database.put((String) ((JSONObject)arr.get(i)).get("name"), wall);
					}
				}
				
			} catch(CsvMalformedLineException e) {
				System.out.println(e);
			}

			csvreader.close(); // close reader when done reading file
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return database;
	}
	
	/**
	 * Gets the database/hashmap that contains the actors and their movie appearances
	 * 
	 * @return	the database/hashmap that contains the actors and their movie appearances
	 */
	public HashMap<String, ArrayList<MovieAppearance>> getDatabase(){
		return database;
	}

}
