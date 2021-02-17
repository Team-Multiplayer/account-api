package br.multiplayer.accountapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.multiplayer.accountapi.dto.PlanoContaDto;
import br.multiplayer.accountapi.model.Conta;
import br.multiplayer.accountapi.model.PlanoConta;
import br.multiplayer.accountapi.service.PlanoContaService;

@RestController
@RequestMapping("/api/plano-conta")
public class PlanoContaController {// faz de conta que é categoria

	@Autowired
	private PlanoContaService planoContaService;
	
	@GetMapping
	public List<PlanoConta> buscarTodos() {
		return planoContaService.buscarTodos();
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public List<PlanoConta> busrcarPorUsuarioId(@PathVariable(value="usuarioId") Integer usuarioId) {
		return planoContaService.buscarPorUsuarioId(usuarioId);
	}
	
	@PostMapping
	public PlanoConta criaPlanoConta(@RequestBody PlanoContaDto planoContaDto) {
		return planoContaService.cadastrarPlanoConta(planoContaDto);
	}
}
