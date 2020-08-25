package com.example.demo.model;

public enum Product {
	
	CANDY(1),
	CHOCOLATE(5),
	COLDDRINK(10);
	
	private int productValue;
	
	Product(int productValue){
		this.productValue = productValue;		
	}
	
	public int getProductValue() {
		return this.productValue;
	}

}
