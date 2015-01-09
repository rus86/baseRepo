package com.ruscorporation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ruscorporation.service.impl.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityApplicationConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserServiceImpl userServiceImpl;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			authorizeRequests().
				antMatchers("/api/**").permitAll()
				.antMatchers("/index.jsp").permitAll()
				.antMatchers("/contact/**").hasRole("ADMIN")
				//.anyRequest().authenticated()
				.and()
				.formLogin().defaultSuccessUrl("/contact/list")
				.and().csrf().disable();
//				.loginPage("/login").failureUrl("/login?error")
//				.usernameParameter("j_username").passwordParameter("j_password");
//				.defaultSuccessUrl("/contact/list")
//				.and()
//				.logout().logoutSuccessUrl("/");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**"); // skip css url protection
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userServiceImpl).passwordEncoder(passwordEncoder());
		auth.userDetailsService(userServiceImpl);
	}
	
//	@Bean
//	public PasswordEncoder passwordEncoder(){
//		PasswordEncoder encoder = new BCryptPasswordEncoder();
//		return encoder;
//	}

}
