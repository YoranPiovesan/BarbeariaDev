package com.br.BarbeariaDev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.BarbeariaDev.model.Horario;


@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {
	
	List<Horario> findByBarbeiroId(Long barbeiroId);

}
