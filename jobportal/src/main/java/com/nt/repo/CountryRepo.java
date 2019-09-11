package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.entity.Country;

@Repository
public interface CountryRepo extends JpaRepository<Country, Integer>{
	
	

}
