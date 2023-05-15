package com.alura.kafka.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.alura.kafka.request.dto.ProdutoRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ProdutorKafka {

	@Autowired
	private KafkaTemplate<String, String> kafka;
	
	public void enviarMensagemNovoPedido(ProdutoRequest request) throws JsonProcessingException {
		kafka.send("loja_novo_pedido", new ObjectMapper().writeValueAsString(request));
	}
	
	public void enviarMensagemFraude(ProdutoRequest request) throws JsonProcessingException {
		kafka.send("fraude", new ObjectMapper().writeValueAsString(request));
	}

	
}
