package com.alura.kafka.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
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
	
		
		MDC.get("traceId");
		
		String id = ProdutorKafka.class.getSimpleName() + "_" + MDC.get("traceId"); 
		
		List<Header> lista = new ArrayList<>();
		Header header = new RecordHeader("traceId", id.getBytes());
		lista.add(header);
		Iterable<Header> iterable = lista;
		
		
		kafka.send(new ProducerRecord<String, String>("loja_novo_pedido", null, null, null, new ObjectMapper().writeValueAsString(request), iterable));
		
		//kafka.send("loja_novo_pedido",new ObjectMapper().writeValueAsString(request));
	}
	
	public void enviarMensagemFraude(ProdutoRequest request) throws JsonProcessingException, InterruptedException, ExecutionException {
		kafka.send("fraude", new ObjectMapper().writeValueAsString(request)).get();
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
