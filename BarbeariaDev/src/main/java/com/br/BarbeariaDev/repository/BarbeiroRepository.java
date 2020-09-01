package com.br.BarbeariaDev.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.br.BarbeariaDev.model.Barbeiro;


@Repository
public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {

	@Query(value = "select * from barbeiro order by nome ASC", nativeQuery = true)
	List<Barbeiro> findAll();
	
	@Query(value = "select * from barbeiro where nome LIKE :nome%", nativeQuery = true)
	List<Barbeiro> findAllFiltro(String nome);	
}
