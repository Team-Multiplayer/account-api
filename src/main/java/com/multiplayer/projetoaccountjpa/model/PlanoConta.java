package com.multiplayer.projetoaccountjpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PlanoConta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String contaReceitaDespesa;
	
	@Column(nullable = false)
	private String descricao;
	
	// Default constructor
	public PlanoConta() {}
	
	public Integer getId() {
		return id;
	}
	public String getContaReceitaDespesa() {
		return contaReceitaDespesa;
	}
	public void setContaReceitaDespesa(String contaReceitaDespesa) {
		this.contaReceitaDespesa = contaReceitaDespesa;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
