package br.multiplayer.accountapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.multiplayer.accountapi.dto.PlanoContaDto;
import br.multiplayer.accountapi.enums.TipoPlanoConta;
import br.multiplayer.accountapi.model.PlanoConta;
import br.multiplayer.accountapi.repository.PlanoContaRepository;

@Service
public class PlanoContaService {
	
	@Autowired
	private PlanoContaRepository repoPlanoConta;
	
	public List<PlanoConta> buscarTodos() {
		return repoPlanoConta.findAll();
	}
	
	public Optional<PlanoConta> buscarPorId(Integer id) {
		return repoPlanoConta.findById(id);
	}
	
	public List<PlanoConta> buscarPorUsuarioId(Integer usuarioId) {
		return repoPlanoConta.findByUsuarioId(usuarioId);
	}

	public PlanoConta cadastrarPlanoConta(PlanoContaDto planoContaDto) {
		
		if (planoContaDto.getDescricao() == null || planoContaDto.getTipo() == null || planoContaDto.getUsuarioId() == null) {
			throw new IllegalArgumentException();
		}
		
		PlanoConta pc = new PlanoConta(planoContaDto.getTipo(), planoContaDto.getDescricao(), planoContaDto.getUsuarioId());
		return repoPlanoConta.save(pc);
	}

}
