package com.br.BarbeariaDev.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@SuppressWarnings("deprecation")
@EnableWebSecurity
public class MultiHttpSecurityConfig {
	
	
	
	/*
	@Bean                                                             
    public UserDetailsService userDetailsService() throws Exception {
        // ensure the passwords are encoded properly
        UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("user").password("password").roles("USER").build());
        manager.createUser(users.username("admin").password("password").roles("USER","ADMIN").build());
        return manager;
    }
    */

	//Classe usada para autenticar a api rest com JWT
    @Configuration
    @Order(1)                                                        
    public static class ApiRestWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
    	
    	@Override
    	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    		// cria uma conta default
    		auth.inMemoryAuthentication()
    			.withUser("admin")
    			.password("password")
    			.roles("ADMIN");
    	}
    	
    	
    @Override
    	protected void configure(HttpSecurity httpSecurity) throws Exception {

    		String[] staticResources  =  {
    				"/css/**",
    				"/images/**",
    				"/fonts/**",
    				"/scripts/**",
            	};

    		httpSecurity.antMatcher("/rest/**").csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.GET, "/barbeiro/**").permitAll()
			.antMatchers(HttpMethod.GET, "/horario/**").permitAll()
			//.antMatchers(HttpMethod.POST, "/rest"). permitAll().
    		.antMatchers(staticResources).permitAll()
			.antMatchers(HttpMethod.POST, "/rest/autenticar").permitAll().anyRequest().authenticated()
			.and().cors().and()
			////.formLogin().permitAll().and()
			//.and().formLogin().loginPage("/login").permitAll().and()
			////.logout().permitAll()			
			////.and()
			//.antMatcher("/rest/**")
			// filtra requisições de login
			.addFilterBefore(new JWTLoginFilter("/rest/autenticar", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
			// filtra outras requisições para verificar a presença do JWT no header
			.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    	}
    }
    @Configuration                                                   
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    	@Bean
    	public static NoOpPasswordEncoder passwordEncoder() {
    		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    	}
        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {

    		String[] staticResources  =  {
    				"/css/**",
    				"/images/**",
    				"/fonts/**",
    				"/scripts/**",
            	};
    		httpSecurity.csrf().disable().authorizeRequests()
		    .antMatchers(HttpMethod.GET, "/barbeiro/**").authenticated()
			.antMatchers(HttpMethod.GET, "/horario/**").authenticated()
    		.antMatchers(staticResources).permitAll()
    		.antMatchers(HttpMethod.POST, "/rest/**").permitAll()
    		.antMatchers(HttpMethod.GET, "/rest/**").permitAll()
    		.antMatchers(HttpMethod.PUT, "/rest/**").permitAll()
    		.antMatchers(HttpMethod.DELETE, "/rest/**").permitAll()
			.antMatchers(HttpMethod.POST, "/rest/usuario/recuperar_senha").permitAll()
			.antMatchers(HttpMethod.POST, "/usuario/recuperar_senha").permitAll()
			.antMatchers(HttpMethod.GET, "/rest/licenca/autenticar/**").permitAll()
			.antMatchers(HttpMethod.GET, "/recuperar_senha").permitAll();
			//.and().formLogin().permitAll().and()
			////.and();
			//.antMatcher("/rest/**")
			// filtra requisições de login
			////.addFilterBefore(new JWTLoginFilter("/autenticar", authenticationManager()), UsernamePasswordAuthenticationFilter.class);
			// filtra outras requisições para verificar a presença do JWT no header
			//.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        }
    }

}
