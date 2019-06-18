package JavaFinalProject;

public class NotEnoughArgumentException extends Exception {
	
	public NotEnoughArgumentException() {
		super("Not Enough Argument! Put at least two path");
	}
	
	public NotEnoughArgumentException (String message) {
		super(message);
	}

}
