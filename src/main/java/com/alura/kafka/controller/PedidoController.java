package com.alura.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoController {

	@Autowired
	KafkaTemplate<String, String> kafka;
	
	@GetMapping(value = "/novoPedido")
	public String novoPedido() {
		
		kafka.send("loja_novo_pedido", "Novo produto");
		
		return "Deu certo";
	}

	
}
