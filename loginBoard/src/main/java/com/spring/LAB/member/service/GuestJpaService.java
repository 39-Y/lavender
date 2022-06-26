package com.spring.LAB.member.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.LAB.config.auth.LoginMember;
import com.spring.LAB.config.auth.dto.SessionMember;
import com.spring.LAB.member.DTO.GuestRequestDTO;
import com.spring.LAB.member.domain.guest.Guest;
import com.spring.LAB.member.domain.guest.GuestRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(propagation = Propagation.REQUIRED)
@RequiredArgsConstructor
public class GuestJpaService {
	private final GuestRepository repository;
	private final HttpSession session;
	
	public long save(GuestRequestDTO guestDTO) {
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
			SessionMember m= new SessionMember(guest);
			System.out.println("serviceRole: "+m.getRole());
		}
		return result;
	}

	public void delete(@LoginMember SessionMember sessionGuest) {
		Guest guest = repository.findByGuestName(sessionGuest.getName());
		repository.delete(guest);
	}
}
