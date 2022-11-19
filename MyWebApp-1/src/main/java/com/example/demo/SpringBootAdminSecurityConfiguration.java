package com.example.demo;


import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(1)
public class SpringBootAdminSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("admin").password("{noop}password").roles("ADMIN");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.antMatcher("/admin/**")
			.authorizeRequests().anyRequest().authenticated()
			.and().formLogin().loginPage("/admin/login")
				.defaultSuccessUrl("/admin/dashboard", true)
			.permitAll()
			.and().logout().logoutUrl("/admin/logout").logoutSuccessUrl("/admin/login");
		http.csrf().disable();

	}
}
