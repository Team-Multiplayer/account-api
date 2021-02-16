package br.multiplayer.accountapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.multiplayer.accountapi.enums.TipoConta;
import br.multiplayer.accountapi.model.Conta;
import br.multiplayer.accountapi.model.Usuario;
import br.multiplayer.accountapi.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository repoConta;

	public List<Conta> buscarTodas() {
		return repoConta.findAll();
	}
	
	public Optional<Conta> buscarPorId(Integer id) {
		return repoConta.findById(id);
	}

	public Optional<Conta> buscaPorNumeroETipoConta(String numero, TipoConta tipoConta) {
		return repoConta.findFirstByNumeroAndTipoConta(numero, tipoConta);
	}

	public List<Conta> buscarPorUsuarioId(Integer usuarioId) {
		return repoConta.findByUsuarioId(usuarioId);
	}
}
