package com.example.demo.service;

import java.util.EnumMap;
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
	
	private EnumMap<Product, Integer> productMap;
	private EnumMap<Coin, Integer> coinMap;
	
	VendingMachineService(){
		
		productMap = new EnumMap<>(Product.class);
		productMap.put(Product.CANDY, 3);
		productMap.put(Product.CHOCOLATE, 3);
		productMap.put(Product.COLDDRINK, 3);
		
		coinMap = new EnumMap<>(Coin.class);
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
		if(amount >= product.getProductValue()) {
			return true;
		}
		throw new InSufficientAmount("InSufficient Amount");
	}
	
	private ProductChange getProductChange(Product product, Integer amount) {
		int change = amount - product.getProductValue();
		EnumMap<Coin,Integer> coins = new EnumMap<>(Coin.class);
		int quarterCount = 0;
		int	dimeCount = 0; 
		int	nickleCount = 0; 
		int pennyCount = 0;
		int totalQuarterCount = coinMap.get(Coin.QUARTER);
		int	totalDimeCount = coinMap.get(Coin.DIME); 
		int	totalNickleCount = coinMap.get(Coin.NICKLE);
		int totalPennyCount = coinMap.get(Coin.PENNY);
		
		while(change > 0) {
			if(change >= Coin.QUARTER.getCoinValue() && totalQuarterCount > 0) {
				quarterCount = quarterCount + 1;
				totalQuarterCount = totalQuarterCount - 1;
				change = change - Coin.QUARTER.getCoinValue();
			}else if(change >= Coin.DIME.getCoinValue() && totalDimeCount > 0) {
				dimeCount = dimeCount + 1;
				totalDimeCount = totalDimeCount - 1;
				change = change - Coin.DIME.getCoinValue();
			}else if(change >= Coin.NICKLE.getCoinValue() && totalNickleCount > 0) {
				nickleCount = nickleCount + 1;
				totalNickleCount = totalNickleCount - 1;
				change = change - Coin.NICKLE.getCoinValue();
			}else if(change >= Coin.PENNY.getCoinValue() && totalPennyCount > 0){
				pennyCount = pennyCount + 1;
				totalPennyCount = totalPennyCount - 1;
				change = change - Coin.PENNY.getCoinValue();
			}else {
				throw new InSufficientChange("InSufficient Change");
			}
		}
		coins.put(Coin.QUARTER,quarterCount);
		coinMap.put(Coin.QUARTER,totalQuarterCount);
		coins.put(Coin.DIME,dimeCount);
		coinMap.put(Coin.DIME,totalDimeCount);
		coins.put(Coin.NICKLE,nickleCount);
		coinMap.put(Coin.NICKLE,totalNickleCount);
		coins.put(Coin.PENNY,pennyCount);
		coinMap.put(Coin.PENNY,totalPennyCount);
		productMap.put(product, productMap.get(product) - 1);
		return new ProductChange(product, coins);
	}

}
