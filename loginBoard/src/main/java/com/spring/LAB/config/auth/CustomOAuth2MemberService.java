package com.spring.LAB.config.auth;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.spring.LAB.config.auth.dto.OAuthAttributes;
import com.spring.LAB.config.auth.dto.SessionMember;
import com.spring.LAB.member.domain.member.Member;
import com.spring.LAB.member.domain.member.MemberRepository;
import com.spring.LAB.member.domain.member.Role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomOAuth2MemberService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{
	private final MemberRepository memberRepository;
	private final HttpSession session;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = delegate.loadUser(userRequest);
		String registrationID = userRequest.getClientRegistration()
																			 .getRegistrationId();
		String userNameAttributeName = userRequest.getClientRegistration()
																							.getProviderDetails()
																							.getUserInfoEndpoint()
																							.getUserNameAttributeName();
		OAuthAttributes attributes= OAuthAttributes.of(registrationID, 
																									 userNameAttributeName, 
																									 oAuth2User.getAttributes());
		Member member = saveOrUpdate(attributes);
		session.setAttribute("member", new SessionMember(member));
		return new DefaultOAuth2User(
							Collections.singleton(new SimpleGrantedAuthority(member.getRoleKey())), 
							attributes.getAttributes(), 
							attributes.getNameAttributeKey());
	}

	private Member saveOrUpdate(OAuthAttributes attributes) {
		Member member = memberRepository.findByEmail(attributes.getEmail())
																		.map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
																		.orElse(attributes.toEntity());
		return memberRepository.save(member);
	}

}
