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

	@Override
	public String toString() {
		return "ProductChange [product=" + product + ", coins=" + coins + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coins == null) ? 0 : coins.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductChange other = (ProductChange) obj;
		if (coins == null) {
			if (other.coins != null)
				return false;
		} else if (!coins.equals(other.coins))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
	
	

}
