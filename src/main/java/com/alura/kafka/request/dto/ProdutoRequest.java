package com.alura.kafka.request.dto;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProdutoRequest {

	@JsonIgnore
	private final Long id = new Date().getTime();
	
	private String nome;
	
	private boolean simularFraude = false;
	
	/* A biblioteca do jacks com LocalDateTime da problema para serializar, e com LocalDate, da problema para desserializar */
	private String dataPedido = LocalDateTime.now().toString();
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getDataPedido() {
		return dataPedido;
	}
	
	public boolean isSimularFraude() {
		return simularFraude;
	}
	
}
