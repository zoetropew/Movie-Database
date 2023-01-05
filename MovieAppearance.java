/**
 * A wrapper class that can be used to print what movie an actor appeared in as which role
 * 
 * @author Zoe Wong
 *
 */
public class MovieAppearance {
	
	private String movie; // the movie the actor appeared in
	private String character; // the character the actor played in the movie
	
	public MovieAppearance(String mov, String chr){
		movie = mov;
		character = chr;
	}
	
	/**
	 * For printing this actor's movie appearance
	 * @return a string of what movie the actor appeared in as what character
	 */
	@Override
	public String toString() {
		return movie + " as " + character;
	}
	
}
