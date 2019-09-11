package com.nt.repo;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nt.entity.Job;
import com.nt.entity.Job.JobType;

@Repository
public interface JobRepo extends CrudRepository<Job, Long>,JpaRepository<Job, Long>,PagingAndSortingRepository<Job,Long> {

	
	@Query("FROM Job j WHERE j.user.userId=:userId0")
	public List<Job> findAllByRecruiter(@Param("userId0")long userId0);
	
	@Query("FROM Job j WHERE j.user.userId=:userId0")
	public Page<Job> findAllByUserId(@Param("userId0")long userId0,org.springframework.data.domain.Pageable pageable);

    @Query("SELECT j FROM  Job j right join j.locations jl  WHERE jl.state=:state0 AND jl.city=:city0")
    public Page<Job> findAllByLocations(@Param("state0")String state0,@Param("city0")String city0,Pageable pageable);

    
    
    
	 public Page<Job> findAllByJobType(JobType jobType,Pageable pageable);
    
    @Query("SELECT  DISTINCT j.jobType FROM Job j")
    public List<JobType> findAllJobType();
    
    @Query("SELECT j from Job j left outer join j.locations l left outer join j.skills s WHERE l.city = :city0 OR s.skillName in :skills")
    public Page<Job> findAllBySkillsAndCityIn(@Param("city0")String city0,Pageable pageable ,@Param("skills")String... skills);
    
    
    
}
