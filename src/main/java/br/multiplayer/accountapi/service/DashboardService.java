package br.multiplayer.accountapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.multiplayer.accountapi.dto.DashboardDto;
import br.multiplayer.accountapi.model.Conta;
import br.multiplayer.accountapi.model.Usuario;
import br.multiplayer.accountapi.repository.UsuarioRepository;

@Service
public class DashboardService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public DashboardDto getUsuarioDash(String login) {
		DashboardDto dashboard = new DashboardDto();
		Optional<Usuario> usuarios = usuarioRepository.findFirstByLogin(login);
		
//		if (usuarios != null) {
//			Usuario usuario = usuarios.get(0);
//			dashboard.setContaBanco(usuario.getContaCorrente());
//			dashboard.setContaCredito(usuario.getContaCredito());
//		}
		
		return dashboard;
	}
}
