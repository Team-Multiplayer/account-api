package br.multiplayer.accountapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.multiplayer.accountapi.dto.LancamentoDto;
import br.multiplayer.accountapi.model.Lancamento;
import br.multiplayer.accountapi.service.LancamentoService;

@RestController
@RequestMapping("/api/lancamento")
public class LancamentoController {

	@Autowired
	LancamentoService lancamentoService;
	
	@GetMapping
	public List<Lancamento> getLancamentos() {
		return lancamentoService.buscarTodos();
	}

//	@GetMapping
//	public List<Lancamento> getLancamentosPorConta() {
//		return lancamentoService.();
//	}
	
	@PostMapping
	public void realizarLancamento(@RequestBody LancamentoDto lancamentoDto) {
		
		 lancamentoService.novoLancamento(lancamentoDto);
	}
}
