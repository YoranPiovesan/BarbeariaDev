package com.br.BarbeariaDev.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.br.BarbeariaDev.model.Barbeiro;
import com.br.BarbeariaDev.model.Horario;
import com.br.BarbeariaDev.repository.HorarioRepository;
@Service
public class HorarioService {
	
	
	private HorarioRepository repository;
	
	public HorarioService(HorarioRepository horarioRepository) {
	this.repository = horarioRepository;	
	}
	
	public List<Horario> consultar() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			return null;
		}
	}
	public boolean salvar(@Valid Horario horario) {
		try {
			repository.save(horario);
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
	public List<Horario> consultarPorBarbeiro(Long id) {
		try {
			return repository.findByBarbeiroId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean editar(Long id, Horario horario) {
		try {
			horario.setId(id);
			salvar(horario);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
		
	
}
