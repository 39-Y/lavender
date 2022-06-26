package com.spring.LAB.member.domain.guest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GuestRepository extends JpaRepository<Guest, Long>{
	@Query(value = "SELECT * FROM guestTable g WHERE g.id=?1 and g.pwd=?2", nativeQuery = true)
	public Guest findGuest(String id, String pwd);
	
	@Query(value="SELECT * FROM guestTable g WHERE g.id=?1", nativeQuery = true)
	public Guest findGuestByGuestName(String id);
	
	@Query(value="SELECT guestId FROM guestTable g WHERE g.id=?1", nativeQuery = true)
	public Long findGuestIdByGuestName(String id);
}
