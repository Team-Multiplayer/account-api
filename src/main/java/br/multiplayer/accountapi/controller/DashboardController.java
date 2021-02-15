package br.multiplayer.accountapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.multiplayer.accountapi.dto.LoginDto;
import br.multiplayer.accountapi.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
	
	@Autowired
	private DashboardService dashboardService;
	
//	@GetMapping
//	public void getDashboard(@Validated LoginDto loginDto) {
//		return dasboardService.
//	}
}
