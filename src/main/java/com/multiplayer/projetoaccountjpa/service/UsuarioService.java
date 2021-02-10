package com.multiplayer.projetoaccountjpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multiplayer.projetoaccountjpa.model.Usuario;
import com.multiplayer.projetoaccountjpa.repository.UsuarioRepository;

@Service
public class UsuarioService {

	public Boolean validaUsuario(Usuario usuario) throws Exception {
		if (
				usuario.getCpf() == null || 
				usuario.getNome() == null || 
				usuario.getLogin() == null || 
				usuario.getSenha() == null ||
				usuario.getContas() == null) {
			
			throw new Exception("Usuário não pode ser criado.");
		}
		
		if (usuario.getLogin().length() > 20) {
			throw new Exception("O login não deve conter mais que 20 caracteres.");
		}
		
		if (usuario.getCpf().length() > 11) {
			throw new Exception("O cpf é inválido.");
		}
		
		return true;
	}
}
