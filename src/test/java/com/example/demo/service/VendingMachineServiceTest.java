package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.example.demo.model.Coin;
import com.example.demo.model.Product;

class VendingMachineServiceTest {
	
	@Test
	public void testGetProductPrice() {
		int price = 5;
		assertEquals(Product.CHOCOLATE.getProductValue(), price);
		
	}
	
	@Test
	public void testGetProductChange() {
		Product product = Product.CHOCOLATE;
		assertEquals(Product.CHOCOLATE, product);
		
		Map<Coin,Integer> coins = new HashMap<>();
		coins.put(Coin.QUARTER, 1);
		assertNotNull(coins);
				
	}

}
