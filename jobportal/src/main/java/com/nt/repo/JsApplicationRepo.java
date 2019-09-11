package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nt.entity.JsApplication;

@Repository
public interface JsApplicationRepo extends JpaRepository<JsApplication,Long>,CrudRepository<JsApplication, Long> {
 
	
  
}
