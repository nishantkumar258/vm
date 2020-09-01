package com.example.demo.exception;

public class InSufficientAmount extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public InSufficientAmount(String message) {
		super(message);
	}

}
