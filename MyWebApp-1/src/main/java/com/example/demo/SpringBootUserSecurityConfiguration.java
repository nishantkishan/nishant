package com.example.demo;


import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(2)
public class SpringBootUserSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user").password("{noop}password").roles("USER");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.antMatcher("/**")
			.authorizeRequests().anyRequest().authenticated()
			.and().formLogin().loginPage("/login")
				.defaultSuccessUrl("/dashboard", true)
			.permitAll()
			.and().logout().logoutSuccessUrl("/login");
		
		http.csrf().disable();
	}
}
