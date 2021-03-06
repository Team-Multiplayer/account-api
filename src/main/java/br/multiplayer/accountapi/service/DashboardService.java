package br.multiplayer.accountapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.multiplayer.accountapi.dto.ContaDto;
import br.multiplayer.accountapi.dto.DashboardDto;
import br.multiplayer.accountapi.dto.ExtratoDto;
import br.multiplayer.accountapi.dto.ExtratoRequestDto;
import br.multiplayer.accountapi.enums.TipoConta;
import br.multiplayer.accountapi.model.Conta;
import br.multiplayer.accountapi.model.Lancamento;
import br.multiplayer.accountapi.model.Usuario;
import br.multiplayer.accountapi.repository.ContaRepository;
import br.multiplayer.accountapi.repository.LancamentoRepository;
import br.multiplayer.accountapi.repository.UsuarioRepository;

@Service
public class DashboardService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private LancamentoRepository lancamentoRepository;

	public DashboardDto getUsuarioDash(String login) {
		
		DashboardDto dashboard = new DashboardDto();
		
		Optional<Usuario> usuarioBuscado = usuarioRepository.findFirstByLogin(login);
		
		if (usuarioBuscado.isPresent()) {

			Usuario usuario = usuarioBuscado.get();
			
			Optional<Conta> contaCorrenteBuscada = contaRepository.findFirstByNumeroAndTipoConta(usuario.getLogin(), TipoConta.CORRENTE);
			Conta ccorrente = contaCorrenteBuscada.get();
			List<Lancamento> lancamentosContaCorrente = lancamentoRepository.findTop10ByContaIdOrderByDataDesc(ccorrente.getId());
			ContaDto contaCorrente = new ContaDto(ccorrente, lancamentosContaCorrente);
			
			Optional<Conta> contaCreditoBuscada = contaRepository.findFirstByNumeroAndTipoConta(usuario.getLogin(), TipoConta.CREDITO);
			Conta ccredito = contaCreditoBuscada.get();
			List<Lancamento> lancamentosContaCredito = lancamentoRepository.findTop10ByContaIdOrderByDataDesc(ccredito.getId());
			ContaDto contaCredito = new ContaDto(ccredito, lancamentosContaCredito);

			dashboard.setContaCorrente(contaCorrente);
			dashboard.setContaCredito(contaCredito);
		}
		
		return dashboard;
	}

	public ExtratoDto getExtrato(ExtratoRequestDto extratoRequestDto) {
		
		ExtratoDto extrato = new ExtratoDto();
		
		Optional<Usuario> usuarioBuscado = usuarioRepository.findFirstByLogin(extratoRequestDto.getLogin());
		
		if (usuarioBuscado.isPresent()) {

			Usuario usuario = usuarioBuscado.get();
			
			Optional<Conta> contaCorrenteBuscada = contaRepository.findFirstByNumeroAndTipoConta(usuario.getLogin(), TipoConta.CORRENTE);
			Conta ccorrente = contaCorrenteBuscada.get();
			List<Lancamento> lancamentosContaCorrente = lancamentoRepository.findAllByDataBetweenAndContaId(extratoRequestDto.getInicio(), extratoRequestDto.getFim(), ccorrente.getId());
			ContaDto contaCorrente = new ContaDto(ccorrente, lancamentosContaCorrente);
			
			Optional<Conta> contaCreditoBuscada = contaRepository.findFirstByNumeroAndTipoConta(usuario.getLogin(), TipoConta.CREDITO);
			Conta ccredito = contaCreditoBuscada.get();
			List<Lancamento> lancamentosContaCredito = lancamentoRepository.findAllByDataBetweenAndContaId(extratoRequestDto.getInicio(), extratoRequestDto.getFim(), ccredito.getId());
			ContaDto contaCredito = new ContaDto(ccredito, lancamentosContaCredito);

			extrato.setContaCorrente(contaCorrente);
			extrato.setContaCredito(contaCredito);
		}
		
		return extrato;
	}

}
