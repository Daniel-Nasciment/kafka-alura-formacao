package com.alura.kafka.config;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumidorKafka {

	@KafkaListener(topics = "loja_novo_pedido", groupId = "grupo-x")
    public void listen(String message) {
        System.out.println("Mensagem 01: " + message);
    }
	
	@KafkaListener(topics = "loja_novo_pedido", groupId = "grupo-x")
    public void listen2(String message) {
        System.out.println("Mensagem 02: " + message);
    }

	
}
