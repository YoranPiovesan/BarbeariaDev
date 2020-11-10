package com.br.BarbeariaDev.RestController;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.BarbeariaDev.model.Horario;
import com.br.BarbeariaDev.services.HorarioService;
@RestController
@RequestMapping(value = "/rest/horario",
produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class HorarioRestController {
	@Autowired
    private HttpServletResponse response;
	private HorarioService service;
	
	public HorarioRestController(HorarioService service) {
		this.service = service;
	}
	
	@GetMapping("/consultar")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Horario> consultar(){
		return service.consultar();
	}
	@GetMapping("/consultar_barbeiro/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Horario> consultarPorBarbeiro(@PathVariable("id") Long id){
		return service.consultarPorBarbeiro(id);
	}
	@PostMapping("/inserir")
	@ResponseStatus(code = HttpStatus.OK)
	public void inserir(@RequestBody Horario horario){
		try {
			 if(service.salvar(horario)) {
				 response.setStatus(200); 
			 }else {
				 response.setStatus(418);
			 }
		} catch (Exception e) {
			response.setStatus(500);
		}
	}
	@DeleteMapping("/excluir/{id}")
	public void delete(@PathVariable("id") Long id) {
		try {
			if(service.excluir(id)) {
				response.setStatus(200);
			}else {
				response.setStatus(418);
			}
		} catch (Exception e) {
			response.setStatus(500);
		}
	}
	@PostMapping("/editar/{id}")
	public void editar(@PathVariable("id") Long id, @RequestBody Horario horario) {
		try {
			if(service.editar(id, horario)) {
				response.setStatus(200);
			}
			else{
				response.setStatus(418);
			}
		} catch (Exception e) {
			response.setStatus(500);
		}
	}

}
