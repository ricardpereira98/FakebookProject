package Exceptions;

public class InvalidUserKindException extends Exception {

	private static final String INVALID_KIND = " is an invalid user kind!";

	private static final long serialVersionUID = 1L;

	public InvalidUserKindException(String kind) {
		super(kind + INVALID_KIND);
	}

}
