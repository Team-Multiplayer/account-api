package com.multiplayer.projetoaccountjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multiplayer.projetoaccountjpa.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer>{
	List<Conta> findByNumero(String numero);
}
