package com.spring.LAB.member.service;

/*import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.LAB.config.auth.dto.SessionMember;
import com.spring.LAB.member.domain.guest.Guest;
import com.spring.LAB.member.domain.guest.GuestRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService{
	private final GuestRepository repository;
	private final HttpSession session;
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Guest guest = repository.findByGuestName(username);
		if(guest == null) 
			return null;
		session.setAttribute("member", new SessionMember(guest));
		return User.builder()
							 .username(username)
							 .password(guest.getPwd())
							 .roles("GUEST")
							 .build();
	}

}*/
