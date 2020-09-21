package com.br.BarbeariaDev.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity(name = "barbeiro")
public class Barbeiro {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "nome")
	private String nome;
	//@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email Invalido")
	@Column(name = "email")
	private String email;
	//@Size(min=11, max=11, message = "{phone.not.valid}")
	@Column(name = "cpf")
	private String cpf;	
	@Column(name = "senha")
	private String senha;	
}
