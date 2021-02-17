package br.multiplayer.accountapi.dto;

import br.multiplayer.accountapi.model.Conta;

public class DashboardDto {
	private ContaDto contaCorrente;
	private ContaDto contaCredito;
	
	public DashboardDto() {
		
	}
	
	public DashboardDto(ContaDto contaCorrente, ContaDto contaCredito) {
		super();
		this.contaCorrente = contaCorrente;
		this.contaCredito = contaCredito;
	}
	public ContaDto getContaCorrente() {
		return contaCorrente;
	}
	public void setContaCorrente(ContaDto contaCorrente) {
		this.contaCorrente = contaCorrente;
	}
	public ContaDto getContaCredito() {
		return contaCredito;
	}
	public void setContaCredito(ContaDto contaCredito) {
		this.contaCredito = contaCredito;
	}
	
	
	
}
