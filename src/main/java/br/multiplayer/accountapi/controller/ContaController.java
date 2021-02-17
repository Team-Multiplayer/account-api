package br.multiplayer.accountapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.multiplayer.accountapi.enums.TipoConta;
import br.multiplayer.accountapi.model.Conta;
import br.multiplayer.accountapi.model.Usuario;
import br.multiplayer.accountapi.service.ContaService;

@RestController
@RequestMapping("/api/conta")
public class ContaController {

	@Autowired 
	private ContaService contaService;
	
	@GetMapping("todas")
	public List<Conta> getTodasContas() {
		return contaService.buscarTodas();
	}

	@GetMapping
	public ResponseEntity<Conta> getConta(@RequestParam("numero") String numero, @RequestParam("tipoConta") TipoConta tipoConta) {
		Optional<Conta> conta = contaService.buscaPorNumeroETipoConta(numero, tipoConta);

		if (conta.isPresent()) {
			return ResponseEntity.ok().body(conta.get());
		} else {

			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Conta> getConta(@PathVariable(value="id") Integer id) {
		Optional<Conta> conta = contaService.buscarPorId(id);

		if (conta.isPresent()) {
			return ResponseEntity.ok().body(conta.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/usuario/{usuarioId}")
	public List<Conta> getContaPorUsuarioId(@PathVariable(value="usuarioId") Integer usuarioId) {
		return contaService.buscarPorUsuarioId(usuarioId);
	}
}
