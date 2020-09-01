package com.example.demo.exception;

public class InSufficientChange extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public InSufficientChange(String message) {
		super(message);
	}

}
