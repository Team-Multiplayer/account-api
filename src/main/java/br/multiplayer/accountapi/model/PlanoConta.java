package br.multiplayer.accountapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.multiplayer.accountapi.enums.TipoPlanoConta;

@Entity
public class PlanoConta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private TipoPlanoConta tipo;
	
	@Column(nullable = false, length = 100)
	private String descricao;
	
	@Column(name="usuario_id", nullable=false)
	private Integer usuarioId;
	
	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	// Default constructor
	public PlanoConta() {}
	
	public PlanoConta(TipoPlanoConta tipo, String descricao, Integer usuarioId) {
		super();
		this.tipo = tipo;
		this.descricao = descricao;
		this.usuarioId = usuarioId;
	}

	public Integer getId() {
		return id;
	}
	public TipoPlanoConta getTipo() {
		return tipo;
	}
	public void setTipo(TipoPlanoConta tipo) {
		this.tipo = tipo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
