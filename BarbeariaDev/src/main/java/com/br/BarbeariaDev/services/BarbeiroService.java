package com.br.BarbeariaDev.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.BarbeariaDev.model.Barbeiro;
import com.br.BarbeariaDev.repository.BarbeiroRepository;


@Service
public class BarbeiroService {
	
	private BarbeiroRepository repository;
	@Autowired
	public BarbeiroService( BarbeiroRepository barbeiroRepository) {
		this.repository =  barbeiroRepository;
	}
	
	public List<Barbeiro> consultar() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			return null;
		}
	}
	public boolean salvar(@Valid Barbeiro barbeiro) {
		try {
			repository.save(barbeiro);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean excluir(Long id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean editar(Long id, Barbeiro barbeiro) {
		try {
			barbeiro.setId(id);
			salvar(barbeiro);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
