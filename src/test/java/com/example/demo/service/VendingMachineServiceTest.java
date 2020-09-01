package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.exception.InSufficientAmount;
import com.example.demo.exception.InSufficientChange;
import com.example.demo.model.Coin;
import com.example.demo.model.Product;
import com.example.demo.model.ProductChange;

@SpringBootTest
class VendingMachineServiceTest {
	
	VendingMachineService vendingMachineService = new VendingMachineService();
		
	@Test
	void testGetEqualProductPrice() {
		int price = 5;
		assertEquals(price, vendingMachineService.getProductPrice(Product.CHOCOLATE));
	}
	
	@Test
	void testGetNotEqualProductPrice() {
		int price = 15;
		assertNotEquals(price, vendingMachineService.getProductPrice(Product.COLDDRINK));		
	}
	
	@Test
	void testGetEqualProductChange() {
		Map<Coin,Integer> coins = new HashMap<>();
		coins.put(Coin.PENNY, 0);
		coins.put(Coin.NICKLE, 0);
		coins.put(Coin.DIME, 0);
		coins.put(Coin.QUARTER, 0);
		ProductChange productChange = new ProductChange(Product.CANDY, coins);
		assertEquals(productChange, vendingMachineService.getProductAndChange(Product.CANDY, 1));				
	}
	
	@Test
	void testGetMoreProductChange() {
		Map<Coin,Integer> coins = new HashMap<>();
		coins.put(Coin.PENNY, 0);
		coins.put(Coin.NICKLE, 0);
		coins.put(Coin.DIME, 2);
		coins.put(Coin.QUARTER, 1);
		ProductChange productChange = new ProductChange(Product.CHOCOLATE, coins);
		assertEquals(productChange, vendingMachineService.getProductAndChange(Product.CHOCOLATE, 50));						
	}
	
	@Test
	void testInSufficientAmountException() {
		Assertions.assertThrows(InSufficientAmount.class, () -> {
			vendingMachineService.getProductAndChange(Product.COLDDRINK, 5);
		});
	}
	
	@Test
	void testInSufficientChangeException() {
		Assertions.assertThrows(InSufficientChange.class, () -> {
			vendingMachineService.getProductAndChange(Product.CHOCOLATE, 500);
		});
	}

}
