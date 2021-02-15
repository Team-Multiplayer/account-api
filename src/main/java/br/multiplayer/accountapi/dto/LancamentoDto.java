package br.multiplayer.accountapi.dto;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.multiplayer.accountapi.enums.TipoLancamento;
import br.multiplayer.accountapi.model.Conta;
import br.multiplayer.accountapi.model.PlanoConta;

public class LancamentoDto {
	private String numeroConta;
	private Double valor;
	private String descricao;
	private TipoLancamento tipo;
	private PlanoConta categoria;
	private String contaDestino;
	
//	Default constructor
	public LancamentoDto() {
		
	}
	
	public LancamentoDto(String numeroConta, Double valor, String descricao,
			String contaDestino, TipoLancamento tipo, PlanoConta categoria) {
		super();
		this.numeroConta = numeroConta;
		this.valor = valor;
		this.descricao = descricao;
		this.contaDestino = contaDestino;
		this.tipo = tipo;
		this.categoria = categoria;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(String contaDestino) {
		this.contaDestino = contaDestino;
	}

	public TipoLancamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}

	public PlanoConta getCategoria() {
		return categoria;
	}

	public void setCategoria(PlanoConta categoria) {
		this.categoria = categoria;
	}
	
	
	
	
}
