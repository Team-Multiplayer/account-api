package br.multiplayer.accountapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.multiplayer.accountapi.dto.DashboardDto;
import br.multiplayer.accountapi.dto.ExtratoDto;
import br.multiplayer.accountapi.dto.ExtratoRequestDto;
import br.multiplayer.accountapi.dto.LancamentoDto;
import br.multiplayer.accountapi.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
	
	@Autowired
	private DashboardService dashboardService;
	
	@GetMapping("/{login}")
	public DashboardDto getDashboard(@PathVariable(value="login") String login) {
		return dashboardService.getUsuarioDash(login);
	}
	
	@PostMapping
	public ExtratoDto getExtratoPorDatas(@RequestBody ExtratoRequestDto extratoRequestDto) {
		
		return dashboardService.getExtrato(extratoRequestDto);
	}

}
