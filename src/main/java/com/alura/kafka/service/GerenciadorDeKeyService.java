package com.alura.kafka.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class GerenciadorDeKeyService {

	
	public List<String> gerarKey() throws InterruptedException{
		
		List<String> keys = new ArrayList<>();
		
		int cont = 0;
		
		while(cont <= 5) {
			
			LocalDate now = LocalDate.now();
			double random = Math.random();
			
			
			String key = "KEY: " + now.toString() + " " + String.valueOf(random);
			keys.add(key);
					
			cont++;
			
		}
		
		return keys;
		
	}
	
}
