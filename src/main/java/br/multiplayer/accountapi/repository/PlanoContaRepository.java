package br.multiplayer.accountapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.multiplayer.accountapi.model.PlanoConta;

@Repository
public interface PlanoContaRepository extends JpaRepository<PlanoConta, Integer>{

}
