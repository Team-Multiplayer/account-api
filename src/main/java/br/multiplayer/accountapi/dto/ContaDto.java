package br.multiplayer.accountapi.dto;

import java.util.List;

import br.multiplayer.accountapi.model.Conta;
import br.multiplayer.accountapi.model.Lancamento;

public class ContaDto {
	Conta conta;
	List<Lancamento> lancamentos;
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}
	public ContaDto(Conta conta, List<Lancamento> lancamentos) {
		super();
		this.conta = conta;
		this.lancamentos = lancamentos;
	}
	public ContaDto() {
		super();
	}
	
	
	
	
	
	
	
}
