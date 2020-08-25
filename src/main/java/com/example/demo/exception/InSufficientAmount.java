package com.example.demo.exception;

public class InSufficientAmount extends RuntimeException{
	
	public InSufficientAmount(String message) {
		super(message);
	}

}
