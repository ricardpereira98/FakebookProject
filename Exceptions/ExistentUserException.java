package Exceptions;

public class ExistentUserException extends Exception {
	

	private static final String ALREADY_EXISTS = " already exists!";
	private static final long serialVersionUID = 1L;

	public ExistentUserException(String User) {
		super(User + ALREADY_EXISTS);
	}

}
