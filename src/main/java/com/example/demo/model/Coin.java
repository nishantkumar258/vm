package com.example.demo.model;

public enum Coin {
	
	PENNY(1),
	NICKLE(5),
	DIME(10),
	QUARTER(25);
	
	private int coinValue;
	
	Coin(int coinValue){
		this.coinValue = coinValue;		
	}
	
	public int getCoinValue() {
		return this.coinValue;
	}

}
