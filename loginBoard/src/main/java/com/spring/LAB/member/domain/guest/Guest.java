package com.spring.LAB.member.domain.guest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.spring.LAB.board.domain.imgUpload.ImgFiles;
import com.spring.LAB.member.DTO.GuestRequestDTO;
import com.spring.LAB.member.domain.GuestProfile.GuestProfile;
import com.spring.LAB.member.domain.member.Role;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@Table(name="guestTable")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor

public class Guest {
	@SequenceGenerator(
			name="guest_Seq", 
			sequenceName="g_seq",
			initialValue=1,
			allocationSize = 1
	)
	@Id
	@GeneratedValue(generator = "guest_Seq", strategy = GenerationType.SEQUENCE)
	private Long guestId;
	
	@Column
	private String id;
	
	@Column
	private String pwd;
	
	@Column
	private String profile;
	
	@Enumerated(EnumType.STRING)
	@Column
	private Role role;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL )
	@JoinColumn(name="guestId")
	private List<GuestProfile> GuestProfile = new ArrayList<>();
	
	@Builder
	public Guest(String id, String pwd, String profile) {
		this.id = id;
		this.pwd = pwd;
		this.profile = profile;
		this.role = Role.GUEST;
	}
	
	public void profileUpdate(String profileUrl) {
		this.profile = profileUrl;
	}
}
