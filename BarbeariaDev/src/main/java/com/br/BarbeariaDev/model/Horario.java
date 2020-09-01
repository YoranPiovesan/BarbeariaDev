package com.br.BarbeariaDev.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
@Data
@Entity(name = "horario")
public class Horario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "email")
	private String email;
	@Column(name = "telefone")
	private String telefone;	
	@Column(name = "barbeiro")
	private String barbeiro;
	@Column(name = "horario")
	private String horario;
}
