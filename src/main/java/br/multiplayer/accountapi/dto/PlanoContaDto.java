package br.multiplayer.accountapi.dto;

import br.multiplayer.accountapi.enums.TipoPlanoConta;

public class PlanoContaDto {
	private String descricao;
	private TipoPlanoConta tipo;
	private Integer usuarioId;
	
//	Default constructor
	public PlanoContaDto() {
		
	}
	
	public PlanoContaDto(String descricao, TipoPlanoConta tipo, Integer usuarioId) {
		super();
		this.descricao = descricao;
		this.tipo = tipo;
		this.usuarioId = usuarioId;
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

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
	
}
