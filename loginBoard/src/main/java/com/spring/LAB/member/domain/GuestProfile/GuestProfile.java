package com.spring.LAB.member.domain.GuestProfile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.spring.LAB.member.DTO.GuestProfileRequestDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name = "guestProfileTable")
public class GuestProfile {
	/*@SequenceGenerator(
			name="pro_seq", 
	    sequenceName="profile_Seq",
	    initialValue=1,
	    allocationSize = 1)*/
	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pro_seq")
	private Long profileNO;
	
	@Column()
	private Long guestId;
	
	@Column()
	private String guestName;
	
	@Column()
	private String mimeType;
	
	@Column()
	private byte[] fileByte;
	
	@Builder 
	public GuestProfile(Long guestId, String mimeType, byte[] fileByte, String gustName){
		this.profileNO = guestId;
		this.guestId = guestId;
		this.mimeType = mimeType;
		this.fileByte = fileByte;
		this.guestName = gustName;
	}
	
	public void update(GuestProfileRequestDTO profileDTO) {
		this.mimeType = profileDTO.getMimeType();
		this.fileByte = profileDTO.getFileByte();
	}
}
