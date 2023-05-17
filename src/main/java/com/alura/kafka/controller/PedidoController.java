package com.alura.kafka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alura.kafka.config.ConsumidorKafka;
import com.alura.kafka.config.ProdutorKafka;
import com.alura.kafka.request.dto.ProdutoRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class PedidoController {

	private static final Logger LOG = LoggerFactory.getLogger(PedidoController.class);
	
	@Autowired
	ProdutorKafka produtorKafka;
	
	@Autowired
	ConsumidorKafka consumidor;
	
	@GetMapping(value = "/novoPedido")
	public String novoPedido(@RequestBody ProdutoRequest request) throws JsonProcessingException {
		
		produtorKafka.enviarMensagemNovoPedido(request);
		
		return "Deu certo";
	}
	
	@GetMapping(value = "/key")
	public String geradorKey() throws JsonProcessingException {
		
		LOG.info("ENTRANDO NO KEY............................................");
		
		produtorKafka.enviarTopicoParaProcessamentoDeKeys();
		
		return "Deu certo";
	}

	
}
