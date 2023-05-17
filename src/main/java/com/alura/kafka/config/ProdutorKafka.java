package com.alura.kafka.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.alura.kafka.request.dto.ProdutoRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ProdutorKafka {

	private static final Logger LOG = LoggerFactory.getLogger(ProdutorKafka.class);
	
	@Autowired
	private KafkaTemplate<String, String> kafka;
	
	public void enviarMensagemNovoPedido(ProdutoRequest request) throws JsonProcessingException {
	
		String traceId = MDC.get("traceId");
		
	    Message<String> message = MessageBuilder
	            .withPayload(new ObjectMapper().writeValueAsString(request))
	            .setHeader("traceId", traceId)
	            .build();
	    
		kafka.send("loja_novo_pedido", message.getPayload());
	}
	
	public void enviarMensagemFraude(ProdutoRequest request) throws JsonProcessingException {
		kafka.send("fraude", new ObjectMapper().writeValueAsString(request));
	}
	
	public void enviarTopicoParaProcessamentoDeKeys() {
		LOG.info("ENVIANDO TÓPICO PARA IMPRIMIR KEY............");
		kafka.send("processar_tudo", "imprimir_chave");
	}
	
	public void enviarOrderDeImpressaoDeKey(String topic, String key) {
		
		LOG.info("ENVIANDO MENSAGEM PARA GERAR KEY.............");
		
		kafka.send(topic, key);
	}

	
}
