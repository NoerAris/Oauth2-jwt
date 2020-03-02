package com.oauth.oauthjwt.repository;

import org.springframework.stereotype.Repository;
import com.oauth.oauthjwt.entity.MasterUser;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository <MasterUser, Long>{
	MasterUser findByUsername(String username);
}
