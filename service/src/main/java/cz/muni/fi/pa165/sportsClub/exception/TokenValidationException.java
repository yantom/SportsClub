package cz.muni.fi.pa165.sportsClub.exception;

public class TokenValidationException extends Exception {

	public TokenValidationException(String msg) {
		super(msg);
	}

	public TokenValidationException(Throwable cause) {
		super(cause);
	}

	public TokenValidationException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
