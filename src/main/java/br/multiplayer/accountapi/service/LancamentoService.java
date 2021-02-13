package br.multiplayer.accountapi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.multiplayer.accountapi.enums.TipoLancamento;
import br.multiplayer.accountapi.exception.SaldoInsuficienteException;
import br.multiplayer.accountapi.model.Conta;
import br.multiplayer.accountapi.model.Lancamento;
import br.multiplayer.accountapi.model.PlanoConta;
import br.multiplayer.accountapi.repository.ContaRepository;
import br.multiplayer.accountapi.repository.LancamentoRepository;
import br.multiplayer.accountapi.repository.PlanoContaRepository;

public class LancamentoService {
	
	@Autowired private LancamentoRepository repoLancamento;
	@Autowired private ContaRepository repoConta;
	@Autowired private PlanoContaRepository repoPlanoConta;
	
	public List<Lancamento> buscarTodos() {
		return repoLancamento.findAll();
	}

	public Optional<Lancamento> buscarPorId(Integer id) {
		return repoLancamento.findById(id);
	}
	
	public List<Lancamento> extratoPorPeriodo(LocalDate inicio, LocalDate fim) {
		
		// valida os campos passados
		if (inicio == null || fim == null) {
			throw new IllegalArgumentException();
		}
		// verifica se a data de inicio é posterior a data de fim
		if (fim.isBefore(inicio)) {
			// lançar exceção
			throw new IllegalArgumentException();
		}
		
		return repoLancamento.findAllByDataBetween(inicio, fim);
	}
	
	public void novoLancamento(String numeroConta, Double valor, String descricao, TipoLancamento tipo, Integer categoria_id, String numeroContaDestino) {
		
		// valida os campos passados
		if (numeroConta == null || valor == null || valor < 0 || descricao == null || tipo == null || categoria_id == null) {
			throw new IllegalArgumentException();
		}
		// se for transferência precisa da conta de destino
		if (tipo == TipoLancamento.TRANSFERENCIA && numeroContaDestino == null) {
			throw new IllegalArgumentException();
		}
		
		Optional<PlanoConta> pc = repoPlanoConta.findById(categoria_id);
		
		// busca a conta
		List<Conta> c = repoConta.findByNumero(numeroConta);
		// se não achou a conta
		if (c == null) {
			throw new IllegalArgumentException();
		}
		
		//pega a conta do usuário
		Conta contaUsuario = c.get(0);
		
		// cria o lançamento
		Lancamento l = new Lancamento();
		l.setData(LocalDate.now());
		l.setNumeroConta(contaUsuario.getNumero());
		l.setValor(valor);
		l.setDescricao(descricao);
		l.setTipo(tipo);
		l.setCategoria(pc.get());
		
		if (tipo == TipoLancamento.TRANSFERENCIA) {
			// pega a conta de destino
			List<Conta> listaContaDestino = repoConta.findByNumero(numeroContaDestino);
			// se não achou a conta
			if (listaContaDestino == null) {
				throw new IllegalArgumentException();
			}
			// pega a conta destino
			Conta contaDestino = listaContaDestino.get(0);
			// faz a transferência
			if (temSaldoSuficiente(contaUsuario, valor)) {
				//TODO inicio transação
				contaUsuario.debitar(valor);
				repoConta.save(contaUsuario);
				contaDestino.creditar(valor);
				repoConta.save(contaUsuario);
				repoLancamento.save(l);
				// fim transação
			} else {
				throw new SaldoInsuficienteException();
			}
		}
		
		if (tipo == TipoLancamento.CREDITO) {
			contaUsuario.creditar(valor);
			repoConta.save(contaUsuario);
			repoLancamento.save(l);
		} 
		
		if (tipo == TipoLancamento.DEBITO) {
			if (temSaldoSuficiente(contaUsuario, valor)) {
				contaUsuario.debitar(valor);
				repoConta.save(contaUsuario);
				repoLancamento.save(l);
			} else {
				throw new SaldoInsuficienteException();
			}
		}
		
	}
	
	private Boolean temSaldoSuficiente(Conta conta, Double valor) {
		Double saldoPrevisao = conta.getSaldo() - valor;
		if (saldoPrevisao < 0) {
			return false;
		}
		return true;
	}

}
