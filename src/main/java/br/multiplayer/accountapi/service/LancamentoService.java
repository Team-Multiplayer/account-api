package br.multiplayer.accountapi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.multiplayer.accountapi.dto.LancamentoDto;
import br.multiplayer.accountapi.enums.TipoConta;
import br.multiplayer.accountapi.enums.TipoLancamento;
import br.multiplayer.accountapi.exception.SaldoInsuficienteException;
import br.multiplayer.accountapi.model.Conta;
import br.multiplayer.accountapi.model.Lancamento;
import br.multiplayer.accountapi.model.PlanoConta;
import br.multiplayer.accountapi.repository.ContaRepository;
import br.multiplayer.accountapi.repository.LancamentoRepository;
import br.multiplayer.accountapi.repository.PlanoContaRepository;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository repoLancamento;
	@Autowired
	private ContaRepository repoConta;
	@Autowired
	private PlanoContaRepository repoPlanoConta;

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

	public void novoLancamento(LancamentoDto lancamentoDto) {

		// valida os campos passados
		System.out.println(lancamentoDto.toString());
		
		if (lancamentoDto.getIdContaUsuario() == null || lancamentoDto.getValor() == null || lancamentoDto.getValor() < 0
				|| lancamentoDto.getDescricao() == null || lancamentoDto.getTipo() == null
				|| lancamentoDto.getCategoria() == null) {
			throw new IllegalArgumentException();
		}
		// se for transferência precisa da conta de destino
		if (lancamentoDto.getTipo() == TipoLancamento.TRANSFERENCIA && lancamentoDto.getNumeroContaDestino() == null) {
			throw new IllegalArgumentException();
		}

		Optional<PlanoConta> pc = repoPlanoConta.findById(lancamentoDto.getCategoria());

		// busca a conta
		Optional<Conta> c = repoConta.findById(lancamentoDto.getIdContaUsuario());
		// se não achou a conta
		if (c.isEmpty()) {
			throw new IllegalArgumentException();
		}

		// pega a conta do usuário
		Conta contaUsuario = c.get();

		// cria o lançamento do usuário origem
		Lancamento lancOrigem = new Lancamento();
		lancOrigem.setData(LocalDate.now());
		lancOrigem.setNumeroContaUsuario(contaUsuario.getNumero());
		lancOrigem.setDescricao(lancamentoDto.getDescricao());
		lancOrigem.setTipo(lancamentoDto.getTipo());
		lancOrigem.setCategoria(pc.get());
		lancOrigem.setContaId(contaUsuario.getId());

		if (lancamentoDto.getTipo() == TipoLancamento.TRANSFERENCIA) {
			// pega a conta de destino
			Optional<Conta> contaDestinoBuscada = repoConta.findFirstByNumeroAndTipoConta(lancamentoDto.getNumeroContaDestino(), lancamentoDto.getTipoContaDestino());
			// se não achou a conta
			if (contaDestinoBuscada.isEmpty()) {
				throw new IllegalArgumentException();
			}
			// pega a conta destino
			Conta contaDestino = contaDestinoBuscada.get();
			lancOrigem.setNumeroContaDestino(contaDestino.getNumero());
			// faz a transferência
			if (temSaldoSuficiente(contaUsuario, lancamentoDto.getValor())) {
				
				// TODO inicio transação
				contaUsuario.debitar(lancamentoDto.getValor());
				repoConta.save(contaUsuario);
				contaDestino.creditar(lancamentoDto.getValor());
				repoConta.save(contaUsuario);
				
				lancOrigem.setValor(-lancamentoDto.getValor());
				repoLancamento.save(lancOrigem);
				
				Lancamento lancDestino = new Lancamento();
				lancDestino.setData(LocalDate.now());
				lancDestino.setNumeroContaUsuario(contaDestino.getNumero());
				lancDestino.setValor(lancamentoDto.getValor());
				lancDestino.setDescricao(lancamentoDto.getDescricao());
				lancDestino.setTipo(lancamentoDto.getTipo());
				lancDestino.setCategoria(pc.get());
				lancDestino.setContaId(contaDestino.getId());
				repoLancamento.save(lancDestino);

				// fim transação
			} else {
				throw new SaldoInsuficienteException();
			}
		}

		if (lancamentoDto.getTipo() == TipoLancamento.CREDITO) {
			lancOrigem.setValor(lancamentoDto.getValor());
			contaUsuario.creditar(lancamentoDto.getValor());
			repoConta.save(contaUsuario);
			repoLancamento.save(lancOrigem);
		}

		if (lancamentoDto.getTipo() == TipoLancamento.DEBITO) {
			if (temSaldoSuficiente(contaUsuario, lancamentoDto.getValor())) {
				lancOrigem.setValor(-lancamentoDto.getValor());
				contaUsuario.debitar(lancamentoDto.getValor());
				repoConta.save(contaUsuario);
				repoLancamento.save(lancOrigem);
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
