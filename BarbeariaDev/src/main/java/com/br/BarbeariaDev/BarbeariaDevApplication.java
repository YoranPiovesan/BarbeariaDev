package com.br.BarbeariaDev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.br.BarbeariaDev.RestController.BarbeiroRestController;
import com.br.BarbeariaDev.repository.BarbeiroRepository;
import com.br.BarbeariaDev.services.BarbeiroService;

@SpringBootApplication
//@ComponentScan(basePackageClasses = BarbeiroRestController.class)
@ComponentScan(basePackageClasses = BarbeiroService.class)
@ComponentScan(basePackageClasses = BarbeiroRepository.class)
public class BarbeariaDevApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarbeariaDevApplication.class, args);
	}

}
