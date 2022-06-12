package com.spring.LAB.config.auth;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.RequiredArgsConstructor;

import com.spring.LAB.member.domain.member.Role;
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final CustomOAuth2MemberService customOAuth2MemberService;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 
		http.csrf().disable()
				.headers().frameOptions().disable()
	.and().authorizeRequests()
				.antMatchers("/", "/article/**", "/mainboard/**", "/css/**", "/js/**", "/img/**").permitAll()
				.antMatchers("/articles/**").hasAnyRole(Role.USER.name(), Role.ADMIN.name())
				.antMatchers("/admin/**").hasRole(Role.ADMIN.name())
				//.anyRequest().authenticated()
	.and().formLogin().loginPage("/nidlogin").defaultSuccessUrl("/")
	.and().logout().logoutSuccessUrl("/")
	.and().oauth2Login().userInfoEndpoint().userService(customOAuth2MemberService);
	}
}
