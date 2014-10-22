package sun.exception;

import sun.exception.ServletException;


public class IncorrectEmailException extends ServletException {

	public IncorrectEmailException(String s) {
		super(s);
	}

}