package com.spring.LAB.member.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.LAB.config.auth.LoginMember;
import com.spring.LAB.config.auth.dto.SessionMember;
import com.spring.LAB.member.DTO.GuestProfileRequestDTO;
import com.spring.LAB.member.DTO.GuestProfileResponseDTO;
import com.spring.LAB.member.DTO.GuestRequestDTO;
import com.spring.LAB.member.domain.GuestProfile.GuestProfile;
import com.spring.LAB.member.domain.GuestProfile.GuestProfileRepository;
import com.spring.LAB.member.domain.guest.Guest;
import com.spring.LAB.member.domain.guest.GuestRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(propagation = Propagation.REQUIRED)
@RequiredArgsConstructor
public class GuestJpaService {
	private final GuestRepository repository;
	private final GuestProfileRepository profileRepository;
	private final HttpSession session;
	
	public long save(GuestRequestDTO guestDTO) {
		guestDTO.setProfile("/img/ghost.png");
		Guest guest = guestDTO.toEntity();
		long guestID = repository.save(guest).getGuestId();
		session.setAttribute("member", new SessionMember(guest));
		return guestID;
	}
	
	public boolean findGuest(GuestRequestDTO guestDTO) {
		Guest guest = repository.findGuest(guestDTO.getId(), guestDTO.getPwd());
		boolean result = guest!= null? true : false;
		if(result) {
			session.setAttribute("member", new SessionMember(guest));
		}
		return result;
	}

	public void delete(String guestName) {
		Guest guest = findGuestByName(guestName);
		repository.delete(guest);
	}
	
	private void profileSave(String guestName, 
													GuestProfileRequestDTO profileDTO) {
		Long guestId = findIdByName(guestName);
		profileDTO.setGuestIdAndName(guestName, guestId);
		GuestProfile guestProfile = profileDTO.toEntity();
		profileRepository.save(guestProfile);
		updateGuestProfile(guestName);
	}
	
	public void profileUpdate(String guestName, 
														GuestProfileRequestDTO profileDTO) {
		Long guestId = findIdByName(guestName);
		GuestProfile guestProfile= profileRepository.findById(guestId).orElse(null);
		if(guestProfile == null)
			profileSave(guestName, profileDTO);
		else 
			guestProfile.update(profileDTO);
		Guest guest = findGuestByName(guestName);
		session.setAttribute("member", new SessionMember(guest));
	}
	
	
	public void profileReset(String guestName) {
		Guest guest = findGuestByName(guestName);
		GuestProfile guestProfile = profileRepository
																.findById(findIdByName(guestName)).orElse(null);
		profileRepository.delete(guestProfile);
		guest.profileUpdate("/img/ghost.png");
		session.setAttribute("member", new SessionMember(guest));
	}
	
	public GuestProfileResponseDTO findProfileByName(String guestName) {
		GuestProfile guestProfile = profileRepository
																.findById(findIdByName(guestName)).orElse(null);
		return new GuestProfileResponseDTO(guestProfile);
	}
	
	private void updateGuestProfile(String guestName) {
		Guest guest = findGuestByName(guestName);
		guest.profileUpdate("/profile/img/"+guestName);
	}
	
	private Guest findGuestByName (String guestName) {
		return repository.findGuestByGuestName(guestName);
	}
	private Long findIdByName (String guestName) {
		return repository.findGuestIdByGuestName(guestName);
	}
	
}
