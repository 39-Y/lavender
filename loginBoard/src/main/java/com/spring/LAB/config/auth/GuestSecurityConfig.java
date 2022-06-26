package com.spring.LAB.config.auth;

/*import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.spring.LAB.member.domain.member.Role;
import com.spring.LAB.member.service.UserDetailServiceImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
public class GuestSecurityConfig extends WebSecurityConfigurerAdapter{
	private final UserDetailServiceImpl userDetailServiceImple;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		  	.headers().frameOptions().disable()
	.and().authorizeRequests()
		  	.antMatchers("/guest/**").hasAnyRole(Role.GUEST.name())
	.and().formLogin()
				.loginPage("/nidlogin")
				.defaultSuccessUrl("/")
			  .loginProcessingUrl("/glogin")
			  .usernameParameter("id")
			  .passwordParameter("pwd")
			  .permitAll()
	.and().logout().logoutSuccessUrl("/");
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailServiceImple);
	}
}*/
