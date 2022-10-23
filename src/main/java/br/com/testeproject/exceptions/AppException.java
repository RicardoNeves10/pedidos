package br.com.testeproject.exceptions;

public class AppException extends Exception {

	private static final long serialVersionUID = -6447648254543924998L;
	
	public AppException( String message ){
		super( message );
	}
	
	public AppException( String message, Throwable cause ){
		super( message, cause );
	}
	
}
