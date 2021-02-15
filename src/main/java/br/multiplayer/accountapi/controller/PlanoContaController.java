package br.multiplayer.accountapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.multiplayer.accountapi.dto.PlanoContaDto;
import br.multiplayer.accountapi.model.PlanoConta;
import br.multiplayer.accountapi.service.PlanoContaService;

@RestController
@RequestMapping("/api")
public class PlanoContaController {// faz de conta que é

	@Autowired
	private PlanoContaService planoContaService;
	
	@PostMapping("/plano-conta")
	public PlanoConta criaPlanoConta(@RequestBody PlanoContaDto planoContaDto) {
		return planoContaService.cadastrarPlanoConta(planoContaDto);
	}
}
