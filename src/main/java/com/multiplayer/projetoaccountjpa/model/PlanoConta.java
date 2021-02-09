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
	private Integer idplano;
	
	@Column(nullable = false)
	private String contaReceitaDespesa;
	
	@Column(nullable = false)
	private String descricao;
	
	public PlanoConta() {
		
	}
	
	public void incluir() {
		
	}
	
	public void excuir() {
		
	}
	
	public void alterar() {
		
	}
	
	public void consultar() {
		
	}
	
	public Integer getIdplano() {
		return idplano;
	}
	public void setIdplano(Integer idplano) {
		this.idplano = idplano;
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
