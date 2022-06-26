package com.spring.LAB.member.DTO;

import com.spring.LAB.member.domain.GuestProfile.GuestProfile;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GuestProfileRequestDTO {
	private String mimeType;
	private byte[] fileByte;
	private String guestName;
	private Long guestId;
	
	@Builder
	public GuestProfileRequestDTO(String mimeType, byte[] fileByte) {
		this.mimeType = mimeType;
		this.fileByte = fileByte;
	}
	
	public void setGuestIdAndName(String guestName, Long guestId) {
		this.guestName = guestName;
		this.guestId = guestId;
	}
	
	public GuestProfile toEntity() {
		return GuestProfile.builder()
											 .mimeType(mimeType)
											 .fileByte(fileByte)
											 .guestId(guestId)
											 .gustName(guestName)
											 .build();
	}
}
