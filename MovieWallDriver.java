public class MovieWallDriver {

	public static void main(String[] args) {
		
		MovieDatabase database = new MovieDatabase(movie-dataset.csv); // reads the file and fills in the database
		
		UserInterface ui = new UserInterface();
		
		System.out.println("Welcome to the Movie Wall!");
		while(!ui.askUser(database.getDatabase())) {} // prompts the user for input until they type EXIT
		ui.closeScanner(); // closes the user input scanner
		System.out.println("Thank you for using the Movie Wall!");

	}

}
