package com.spring.LAB.config.auth.dto;

import java.util.Map;

import com.spring.LAB.member.domain.member.Member;
import com.spring.LAB.member.domain.member.Role;

import lombok.Builder;
import lombok.Getter;
@Getter
public class OAuthAttributes {
	private Map<String, Object> attributes;
	private String nameAttributeKey;
	private String name;
	private String email;
	private String picture;

	@Builder
	public OAuthAttributes(Map<String, Object> attributes,
												 String nameAttributeKey,
												 String name,
												 String email,
												 String picture) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name = name;
		this.email = email;
		this.picture = picture;
	}
	
	public static OAuthAttributes of(String registrationID, 
														String userNameAttributeName,
														Map<String, Object> attributes) {
		if("naver".equals(registrationID))
			return ofNaver("id", attributes);
		return ofGoogle(userNameAttributeName, attributes);
	}

	private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
		Map<String, Object> response = (Map<String, Object>)attributes.get("response");
		return OAuthAttributes.builder()
					 .name((String) response.get("nickname"))
					 .email((String)response.get("email"))
					 .picture((String)response.get("profile_image"))
					 .attributes(response)
					 .nameAttributeKey(userNameAttributeName)
					 .build();
	}

	private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
		return OAuthAttributes.builder()
													.name((String)attributes.get("name"))
													.email((String)attributes.get("email"))
													.picture((String)attributes.get("picture"))
													.attributes(attributes)
													.nameAttributeKey(userNameAttributeName)
													.build();
	}
	
	public Member toEntity() {
		return Member.builder()
								 .name(name)
								 .email(email)
								 .picture(picture)
								 .role(Role.USER)
								 .build();
	}

}
