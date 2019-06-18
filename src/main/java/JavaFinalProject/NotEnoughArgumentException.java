package JavaFinalProject;


/**
 * If a user put less than two arguments, this program be automatically terminated.
 * It's a customized exception class.
 */
public class NotEnoughArgumentException extends Exception {
	
	public NotEnoughArgumentException() {
		super("Not Enough Argument! Put at least two path");
	}
	
	public NotEnoughArgumentException (String message) {
		super(message);
	}

}
