package com.alura.kafka.config;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.alura.kafka.request.dto.ProdutoRequest;
import com.alura.kafka.service.GerenciadorDeKeyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ConsumidorKafka {
	
	private static final Logger LOG = LoggerFactory.getLogger(ConsumidorKafka.class);

	@Autowired
	private ProdutorKafka kafkaProdutor;

	@Autowired
	private GerenciadorDeKeyService service;

	@KafkaListener(topics = "fraude", groupId = "grupo-y")
	public void listen(String message) throws JsonMappingException, JsonProcessingException {

		ProdutoRequest prod = new ObjectMapper().readValue(message, ProdutoRequest.class);

		System.out.println("Mensagem de fraude 01: " + prod.getId());
	}

//	@KafkaListener(topics = "loja_novo_pedido", groupId = "grupo-x")
//	public void listen2(String message) throws JsonMappingException, JsonProcessingException, InterruptedException, ExecutionException {
//
//		ProdutoRequest prod = new ObjectMapper().readValue(message, ProdutoRequest.class);
//
//		prod.getTrace();
//		
//		if (prod.isSimularFraude()) {
//
//			System.out.println("Simulando fraude...");
//
//			kafkaProdutor.enviarMensagemFraude(prod);
//
//			return;
//
//		}
//
//		System.out.println("Desserializando com sucesso: " + prod.getNome());
//
//		System.out.println("Mensagem 02: " + message);
//	}
	
	@KafkaListener(topics = "loja_novo_pedido", groupId = "grupo-x")
	public void listen2(ConsumerRecord<String, String> record) throws JsonMappingException, JsonProcessingException, InterruptedException, ExecutionException {

		
		Header lastHeader = record.headers().lastHeader("traceId");
		
		System.out.println(new String(lastHeader.value()));
		
		ProdutoRequest prod = new ObjectMapper().readValue(record.value(), ProdutoRequest.class);

		prod.getTrace();
		
		if (prod.isSimularFraude()) {

			System.out.println("Simulando fraude...");

			kafkaProdutor.enviarMensagemFraude(prod);

			return;

		}

		System.out.println("Desserializando com sucesso: " + prod.getNome());

		//System.out.println("Mensagem 02: " + message);
	}

	@KafkaListener(topics = "processar_tudo", groupId = "grupo-z")
	public void listen5(String message) throws InterruptedException  {

		service.gerarKey().forEach(k -> {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
				kafkaProdutor.enviarOrderDeImpressaoDeKey(message, k);
		});
		System.out.println("Mensagem de processar tudo consumida.");
	}

	@KafkaListener(topics = "imprimir_chave", groupId = "grupo-a")
	public void listen6(String message) throws JsonMappingException, JsonProcessingException {


		LOG.info("===========================");
		LOG.info("IMPRIMINDO KEY: " + message);
		LOG.info("===========================");
	}

}
