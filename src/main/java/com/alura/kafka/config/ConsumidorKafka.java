package com.alura.kafka.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.alura.kafka.request.dto.ProdutoRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ConsumidorKafka {

	@Autowired
	private ProdutorKafka kafkaProdutor;
	
	@KafkaListener(topics = "fraude", groupId = "grupo-y")
    public void listen(String message) throws JsonMappingException, JsonProcessingException {
		
		ProdutoRequest prod = new ObjectMapper().readValue(message, ProdutoRequest.class);
		
        System.out.println("Mensagem de fraude 01: " + prod.getId());
    }
	
	@KafkaListener(topics = "loja_novo_pedido", groupId = "grupo-x")
    public void listen2(String message) throws JsonMappingException, JsonProcessingException {
		
		ProdutoRequest prod = new ObjectMapper().readValue(message, ProdutoRequest.class);
		
		if(prod.isSimularFraude()) {
			
			System.out.println("Simulando fraude...");
			
			kafkaProdutor.enviarMensagemFraude(prod);
			
			return;
			
		}
		
		System.out.println("Desserializando com sucesso: " + prod.getNome());
		
        System.out.println("Mensagem 02: " + message);
    }

	
}
