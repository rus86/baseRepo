package com.ruscorporation.exceptions;

public class InvalidUserException extends Exception{

	private static final long serialVersionUID = 2210492300374874170L;
	private String message;
	
	public InvalidUserException(String message) {
		super(message);
		this.message = message;
	}
	
	

}
