package br.multiplayer.accountapi.dto;

import br.multiplayer.accountapi.enums.TipoPlanoConta;

public class PlanoContaDto {
	private String descricao;
	private TipoPlanoConta tipo;
	
//	Default constructor
	public PlanoContaDto() {
		
	}
	
	public PlanoContaDto(String descricao, TipoPlanoConta tipo) {
		super();
		this.descricao = descricao;
		this.tipo = tipo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public TipoPlanoConta getTipo() {
		return tipo;
	}
	public void setTipo(TipoPlanoConta tipo) {
		this.tipo = tipo;
	}
	
	
}
