package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.InSufficientAmount;
import com.example.demo.exception.InSufficientChange;
import com.example.demo.exception.ProductNotFound;
import com.example.demo.model.Coin;
import com.example.demo.model.Product;
import com.example.demo.model.ProductChange;
import com.example.demo.repository.VendingMachineRepository;

@Service
public class VendingMachineService {
	
	Logger logger = LoggerFactory.getLogger(VendingMachineService.class);
	
	@Autowired
	VendingMachineRepository vendingMachineRepository;
	
	private Map<Product, Integer> productMap;
	private Map<Coin, Integer> coinMap;
	
	VendingMachineService(){
		
		productMap = new HashMap<>();
		productMap.put(Product.CANDY, 3);
		productMap.put(Product.CHOCOLATE, 3);
		productMap.put(Product.COLDDRINK, 3);
		
		coinMap = new HashMap<>();
		coinMap.put(Coin.PENNY, 5);
		coinMap.put(Coin.NICKLE, 5);
		coinMap.put(Coin.DIME, 5);
		coinMap.put(Coin.QUARTER, 5);
		
	}
	
	public Integer getProductPrice(Product product) {
		logger.info("Inside getProductPrice method");
		if(productMap.get(product) > 0) {
			return product.getProductValue();
		}
		throw new ProductNotFound("Product Not Found");
	}
	
	public ProductChange getProductAndChange(Product product, Integer amount) {
		logger.info("Inside getProductAndChange method");
		checkProduct(product);
		checkAmount(product, amount);
		return getProductChange(product, amount);
	}
	
	private boolean checkProduct(Product product) {
		if(productMap.get(product) > 0) {
			return true;
		}
		throw new ProductNotFound("Product Not Found");
	}
	
	private boolean checkAmount(Product product, Integer amount) {
		if(amount > product.getProductValue()) {
			return true;
		}
		throw new InSufficientAmount("InSufficient Amount");
	}
	
	private ProductChange getProductChange(Product product, Integer amount) {
		int change = amount - product.getProductValue();
		Map<Coin,Integer> coins = new HashMap<>();
		int quartercount = 0;
		int	dimeCount = 0; 
		int	nickleCount = 0;
		int pennyCount = 0;
		
		while(change > 0) {
			if(change >= Coin.QUARTER.getCoinValue() && coinMap.get(Coin.QUARTER) > 0) {
				quartercount = quartercount + 1;
				coins.put(Coin.QUARTER,quartercount);
				coinMap.put(Coin.QUARTER, coinMap.get(Coin.QUARTER) - 1);
				change = change - Coin.QUARTER.getCoinValue();
				continue;
			}else if(change >= Coin.DIME.getCoinValue() && coinMap.get(Coin.DIME) > 0) {
				dimeCount = dimeCount + 1;
				coins.put(Coin.DIME,dimeCount);
				coinMap.put(Coin.DIME, coinMap.get(Coin.DIME) - 1);
				change = change - Coin.DIME.getCoinValue();
				continue;
			}else if(change >= Coin.NICKLE.getCoinValue() && coinMap.get(Coin.NICKLE) > 0) {
				nickleCount = nickleCount + 1;
				coins.put(Coin.NICKLE,nickleCount);
				coinMap.put(Coin.NICKLE, coinMap.get(Coin.NICKLE) - 1);
				change = change - Coin.NICKLE.getCoinValue();
				continue;
			}else if(change >= Coin.PENNY.getCoinValue() && coinMap.get(Coin.PENNY) > 0){
				pennyCount = pennyCount + 1;
				coins.put(Coin.PENNY,pennyCount);
				coinMap.put(Coin.PENNY, coinMap.get(Coin.PENNY) - 1);
				change = change - Coin.PENNY.getCoinValue();
				continue;
			}else {
				throw new InSufficientChange("InSufficient Change");
			}
		}
		productMap.put(product, productMap.get(product) - 1);
		return new ProductChange(product, coins);
	}

}
