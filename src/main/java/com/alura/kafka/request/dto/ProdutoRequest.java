package com.alura.kafka.request.dto;

import java.time.LocalDate;

public class ProdutoRequest {

	private String nome;
	
	private LocalDate dataPedido = LocalDate.now();
	
	public String getNome() {
		return nome;
	}
	
	public String getDataPedido() {
		return dataPedido.toString();
	}
	
}
