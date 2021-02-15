package br.multiplayer.accountapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.multiplayer.accountapi.dto.LoginDto;
import br.multiplayer.accountapi.model.Conta;
import br.multiplayer.accountapi.model.Usuario;
import br.multiplayer.accountapi.repository.UsuarioRepository;

@Service
public class DashboardService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
//	public List<Conta> getUserDashboard(LoginDto loginDto) {
//		
//		List<Usuario> usuario = usuarioRepository.findByLogin(loginDto.getLogin());
//		
//		if (usuario != null) {
//			
//			Usuario usuarioDash = usuario.get(0);
//			
//			
//			usuarioDash.setContas(null);
//			return null;
//		}
		
		
		
//	}
}
