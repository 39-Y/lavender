package com.spring.LAB.member.DTO;

import com.spring.LAB.member.domain.GuestProfile.GuestProfile;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GuestProfileResponseDTO {
	private String mimeType;
	private byte[] fileByte;
	private Long guestId;
	private String guestName;
	
	@Builder
	public GuestProfileResponseDTO(GuestProfile guestProfile) {
		this.fileByte = guestProfile.getFileByte();
		this.guestId = guestProfile.getGuestId();
		this.guestName = guestProfile.getGuestName();
		this.mimeType = guestProfile.getMimeType();
	}
}
