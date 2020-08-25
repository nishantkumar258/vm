package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exception.InSufficientAmount;
import com.example.demo.exception.InSufficientChange;
import com.example.demo.exception.ProductNotFound;
import com.example.demo.model.CommonError;

@RestControllerAdvice
public class GlobalRestControllerAdvice {
	
	@ExceptionHandler({
		ProductNotFound.class,
		InSufficientAmount.class,
		InSufficientChange.class
	})
	public ResponseEntity<CommonError> handleError(RuntimeException ex){
		CommonError commonError = new CommonError();
		commonError.setStatus("404");
		commonError.setMessage(ex.getMessage());
		return ResponseEntity.ok().body(commonError);
		
	}

}
