package br.multiplayer.accountapi.dto;

import br.multiplayer.accountapi.model.Conta;

public class DashboardDto {
	private Conta contaBanco;
	private Conta contaCredito;
	
	public DashboardDto(Conta contaBanco, Conta contaCredito) {
		super();
		this.contaBanco = contaBanco;
		this.contaCredito = contaCredito;
	}
	public Conta getContaBanco() {
		return contaBanco;
	}
	public void setContaBanco(Conta contaBanco) {
		this.contaBanco = contaBanco;
	}
	public Conta getContaCredito() {
		return contaCredito;
	}
	public void setContaCredito(Conta contaCredito) {
		this.contaCredito = contaCredito;
	}
	
	
	
}
