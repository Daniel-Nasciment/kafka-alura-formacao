package com.alura.kafka.config;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.alura.kafka.request.dto.ProdutoRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ConsumidorKafka {

	@KafkaListener(topics = "loja_novo_pedido", groupId = "grupo-x")
    public void listen(String message) {
        System.out.println("Mensagem 01: " + message);
    }
	
	@KafkaListener(topics = "loja_novo_pedido", groupId = "grupo-x")
    public void listen2(String message) throws JsonMappingException, JsonProcessingException {
		
		ProdutoRequest prod = new ObjectMapper().readValue(message, ProdutoRequest.class);
		
		System.out.println("Desserializando com sucesso: " + prod.getNome());
		
        System.out.println("Mensagem 02: " + message);
    }

	
}
