package Exceptions;

public class InvalidFanaticismException extends Exception {


	private static final long serialVersionUID = 1L;
	private static final String INVALID_FANATICISM_LIST = "Invalid fanaticism list!";

	public InvalidFanaticismException() {
		super(INVALID_FANATICISM_LIST);
	}



}
