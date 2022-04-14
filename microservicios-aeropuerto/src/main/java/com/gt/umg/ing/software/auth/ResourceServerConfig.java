package com.gt.umg.ing.software.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers(HttpMethod.GET,"/existePasaporte/**", "/swagger-ui/**","/v2/**","/swagger-ui.html","/swagger-resources","/swagger-resources/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/crearPasajero","/oauth/token").permitAll()
//		.anyRequest().authenticated();
		http.authorizeRequests().anyRequest().permitAll();
	}

	
}
