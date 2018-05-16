package Database;

public class PersistensException extends Exception {
	public PersistensException(Exception e, String explanation) {
		super(explanation, e);
	}
}
