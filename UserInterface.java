import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class UserInterface {
	
	private Scanner user;
	
	public UserInterface() {
		user = new Scanner(System.in);
	}
	
	/**
	 * Prompts the user for what actor they would like to search for and calls the search and searchAlt methods on their input
	 * 
	 * @param database	the hashmap that contains the actors and their movie appearances
	 * @return			true if the user is ready to exit the program, false if they are still inputting text
	 */
	public boolean askUser(HashMap<String, ArrayList<MovieAppearance>> database) {
		
		// Asks user for input
		System.out.print("Enter the name of an actor (or \"EXIT\" to quit): ");
		String inp = user.nextLine().toLowerCase();
		
		// Carries out tasks based on user's input
		if(inp.compareTo("exit") == 0) {
			return true;
		} else if(inp != null) {			
			if(search(database, inp) == false) {
				if(searchAlt(database, inp) == false) {
					System.out.println("Actor not found, please try again.");
					askUser(database);
				}
			}
		}
		return false;
	}
	
	/**
	 * Searches for an actor in the database, called in askUser()
	 * 
	 * @param database	the hashmap that contains the actors and their movie appearances
	 * @param inp		user's search
	 * @return			boolean value of whether actor is in database
	 */
	public boolean search(HashMap<String, ArrayList<MovieAppearance>> database, String inp) {
		
		// Searches for the given actor in the hashmap with a runtime O(1)
		// because hashmap's get() has a runtime O(1)
		ArrayList<MovieAppearance> list = database.get(inp);
		
		// Prints the movie wall if the actor was found
		if(list != null) {
			System.out.println("Actor: " + nameFormatter(inp));
			for(int i = 0; i < list.size(); i++) {
				System.out.println("Movie: " + list.get(i));
			}
			System.out.println();
			return true;
		}
		
		return false;
	}
	
	/**
	 * Compares the user's input to the actor names in the database and suggests similar actors, called in askUser()
	 * 
	 * @param database	the hashmap that contains the actors and their movie appearances
	 * @param inp		the user's input
	 * @return			true if there is a similar actor in the database, false if no similar actor is found
	 */
	public boolean searchAlt(HashMap<String, ArrayList<MovieAppearance>> database, String inp) {
		char[] chars = inp.toCharArray();
		
		Iterator<Map.Entry<String, ArrayList<MovieAppearance>>> itr = database.entrySet().iterator();
		
		while(itr.hasNext()) {
			double count = 0;
			String actor = (String) ((Map.Entry<String, ArrayList<MovieAppearance>>) itr.next()).getKey();
			char[] act = actor.toCharArray();
			
			// Calculates similarity between the actor in the database and the user's input
			for(int i = 0; i < chars.length && i < act.length; i++) {
				if(act[i] == chars[i]) {
					count++;
				}
			}
			count /= act.length;
			
			// If the similarity is high enough, suggest this actor to the user
			if(count >= 0.75) {
				System.out.print("Actor not found, did you mean " + nameFormatter(actor) + " (Y/N): ");
				String newinp = user.nextLine().toUpperCase();
				while(!(newinp.equals("Y") || newinp.equals("N"))) {
					System.out.print("Please input (Y/N), did you mean " + nameFormatter(actor) + " (Y/N): ");
					newinp = user.nextLine().toUpperCase();
				}
				if(newinp.equals("Y")) {
					search(database, actor);
					return true;
				}
			}
			
			count = 0;
		}
		return false;
	}
	
	/**
	 * Formats an actor's name with capitalization for printing
	 * @param inp	the name user has input
	 * @return	the newly formatted name
	 */
	public String nameFormatter(String inp) {
		String[] name = inp.split(" ");
		inp = "";
		for(String n:name) {
			inp += n.substring(0,1).toUpperCase() + n.substring(1) + " ";
		}
		return inp.trim();
	}
	
	/**
	 * Closes the scanner that takes user input
	 */
	public void closeScanner() {
		user.close();
	}

}
