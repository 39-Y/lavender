package com.spring.LAB.member.domain.GuestProfile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface GuestProfileRepository extends JpaRepository<GuestProfile, Long>{
	
}
