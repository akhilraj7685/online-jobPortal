package com.nt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nt.entity.Verification;

@Repository
public interface VerificationRepo extends JpaRepository<Verification,String>,CrudRepository<Verification, String> {
    
	///get email by token
	@Modifying
	@Query("SELECT v FROM Verification v WHERE v.email=:mail and v.token=:token")
	public List<Verification> findAllByToken(@Param("mail")String mail,@Param("token")String token);
	

}
