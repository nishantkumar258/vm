package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.model.ProductChange;
import com.example.demo.service.VendingMachineService;

@RestController
@RequestMapping(value = "vendingmachine", produces = MediaType.APPLICATION_JSON_VALUE)
public class VendingMachineController {
	
	@Autowired
	VendingMachineService vendingMachineService;
	
	@GetMapping(value = "/product-price/{product}")
	public ResponseEntity<Integer> getProductPrice(@PathVariable("product") Product product){		
		return ResponseEntity.ok().body(vendingMachineService.getProductPrice(product));		
	}
	
	@GetMapping(value = "/product-change/{product}/{amount}")
	public ResponseEntity<ProductChange> getProductAndChange(@PathVariable("product") Product product,
			@PathVariable("amount") Integer amount){
		
		return ResponseEntity.ok().body(vendingMachineService.getProductAndChange(product,amount));
		
	}

}
