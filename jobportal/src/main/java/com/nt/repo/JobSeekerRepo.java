package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nt.entity.JobSeeker;

@Repository
public interface JobSeekerRepo extends JpaRepository<JobSeeker, Long>,CrudRepository<JobSeeker, Long> {
	
	

}
