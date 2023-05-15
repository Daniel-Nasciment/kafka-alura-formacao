package com.alura.kafka.request.dto;

import java.time.LocalDateTime;

public class ProdutoRequest {

	private String nome;
	
	/* A biblioteca do jacks com LocalDateTime da problema para serializar, e com LocalDate, da problema para desserializar */
	private String dataPedido = LocalDateTime.now().toString();
	
	public String getNome() {
		return nome;
	}
	
	public String getDataPedido() {
		return dataPedido;
	}
	
}
