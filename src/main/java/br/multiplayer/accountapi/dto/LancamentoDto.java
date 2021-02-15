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
	private String numeroContaUsuario;
	private Double valor;
	private String descricao;
	private TipoLancamento tipo;
	private Integer categoria;
	private String numeroContaDestino;
	
//	Default constructor
	public LancamentoDto() {
		
	}
	
	public LancamentoDto(String numeroContaUsuario, Double valor, String descricao,
			String numeroContaDestino, TipoLancamento tipo, Integer categoria) {
		super();
		this.numeroContaUsuario = numeroContaUsuario;
		this.valor = valor;
		this.descricao = descricao;
		this.numeroContaDestino = numeroContaDestino;
		this.tipo = tipo;
		this.categoria = categoria;
	}

	public String getNumeroContaUsuario() {
		return numeroContaUsuario;
	}

	public void setNumeroContaUsuario(String numeroContaUsuario) {
		this.numeroContaUsuario = numeroContaUsuario;
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

	public TipoLancamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	public String getNumeroContaDestino() {
		return numeroContaDestino;
	}

	public void setNumeroContaDestino(String numeroContaDestino) {
		this.numeroContaDestino = numeroContaDestino;
	}

	@Override
	public String toString() {
		return "LancamentoDto [numeroContaUsuario=" + numeroContaUsuario + ", valor=" + valor + ", descricao="
				+ descricao + ", tipo=" + tipo + ", categoria=" + categoria + ", numeroContaDestino="
				+ numeroContaDestino + "]";
	}
	
	
	
}
