package br.multiplayer.accountapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.multiplayer.accountapi.enums.TipoPlanoConta;
import br.multiplayer.accountapi.model.PlanoConta;
import br.multiplayer.accountapi.repository.PlanoContaRepository;

public class PlanoContaService {
	
	@Autowired
	private PlanoContaRepository repoPlanoConta;
	
	public List<PlanoConta> buscarTodos() {
		return repoPlanoConta.findAll();
	}
	
	public Optional<PlanoConta> buscarPorId(Integer id) {
		return repoPlanoConta.findById(id);
	}

	public PlanoConta cadastrarPlanoConta(String descricao, TipoPlanoConta tipo) {
		
		if (descricao == null || tipo == null) {
			throw new IllegalArgumentException();
		}
		
		PlanoConta pc = new PlanoConta(descricao, tipo);
		return repoPlanoConta.save(pc);
	}

}
