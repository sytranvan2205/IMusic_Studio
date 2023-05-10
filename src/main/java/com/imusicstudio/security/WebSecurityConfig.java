package com.imusicstudio.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new CustomSuccessHandler();
	}

	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/admin").hasAnyAuthority("ADMIN")
			.antMatchers("/admin/**").hasAnyAuthority("ADMIN")
			.anyRequest().permitAll()
		.and()
			.exceptionHandling().accessDeniedPage("/accessDenied")
		.and()
		.formLogin()
			.loginPage("/login")
			.usernameParameter("username")
			.passwordParameter("password")
			.loginProcessingUrl("/j_spring_security_check")
			.successHandler(authenticationSuccessHandler())
			.failureUrl("/login?incorrectAccount")
		.and()
			.logout()
			.deleteCookies("JSESSIONID")
			.logoutSuccessUrl("/home")
		.and()
			.rememberMe()
				.key("imusic-studio")
				.tokenValiditySeconds(3*24*60*60);
	}
}
