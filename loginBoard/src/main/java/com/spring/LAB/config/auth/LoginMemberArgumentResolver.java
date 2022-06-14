package com.spring.LAB.config.auth;

import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.spring.LAB.config.auth.dto.SessionMember;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Component
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver{
	private final HttpSession session;
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		boolean isLoginMemberAnotation = parameter.getParameterAnnotation(LoginMember.class) != null;
		boolean isMemberClass = SessionMember.class.equals(parameter.getParameterType());
		return isLoginMemberAnotation & isMemberClass;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, 
																ModelAndViewContainer mavContainer,
																NativeWebRequest webRequest, 
																WebDataBinderFactory binderFactory) throws Exception {

		return session.getAttribute("member");
	}

}
