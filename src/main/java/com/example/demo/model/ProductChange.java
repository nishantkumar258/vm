package com.example.demo.model;

import java.util.Map;

public class ProductChange {
	
	Product product;
	Map<Coin,Integer> coins;
	
	public ProductChange(Product product, Map<Coin,Integer> coins) {
		super();
		this.product = product;
		this.coins = coins;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Map<Coin,Integer> getCoins() {
		return coins;
	}

	public void setCoins(Map<Coin,Integer> coins) {
		this.coins = coins;
	}

}
