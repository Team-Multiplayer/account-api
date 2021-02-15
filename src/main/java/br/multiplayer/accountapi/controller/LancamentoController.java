package br.multiplayer.accountapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.multiplayer.accountapi.dto.LancamentoDto;
import br.multiplayer.accountapi.service.LancamentoService;

@RestController
@RequestMapping("/api/lancamento")
public class LancamentoController {

	@Autowired
	LancamentoService lancamentoService;
	
	@PostMapping()
	public void realizarLancamento(@RequestBody LancamentoDto lancamentoDto) {
		
		 lancamentoService.novoLancamento(lancamentoDto);
	}
}
