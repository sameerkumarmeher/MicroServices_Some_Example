package com.globallogic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	/**
	 * JWT
	 */
	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}
	

	/**
	 * Authorization
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		.csrf().disable()
		.exceptionHandling() //jwt
		.authenticationEntryPoint(authenticationEntryPoint) //jwt
		.and() //jwt
		.sessionManagement() //jwt
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS) //jwt
		.and() //jwt
		.authorizeRequests()
		.antMatchers("/api/v1/auth/login").permitAll()
		.antMatchers("/api/v1/auth/signup").permitAll()
		.anyRequest()
		.authenticated();
		
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // jwt
	//	.and();
	//	.httpBasic(); we are jwt authentication
	}

	/**
	 * In Memory Authentication
	 * 
	 * If you don't encode password, login will fail
	 */
	/*
	 * @Override
	 * 
	 * @Bean protected UserDetailsService userDetailsService() {
	 * 
	 * UserDetails user =
	 * User.builder().username("kiran").password(passwordEncoder().encode("kiran")).
	 * roles("ADMIN") .build(); UserDetails user1 =
	 * User.builder().username("manoj").password(passwordEncoder().encode("manoj")).
	 * roles("USER") .build();
	 * 
	 * return new InMemoryUserDetailsManager(user, user1); }
	 */

	/**
	 * Authentication
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
