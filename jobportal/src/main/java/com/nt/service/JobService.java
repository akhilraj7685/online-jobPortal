/**
 * this interface is used to solve all the job related queries 
 */
package com.nt.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.nt.dto.CountryDto;
import com.nt.dto.JobDto;
import com.nt.entity.Job;
import com.nt.entity.Job.JobType;
import com.nt.entity.Location;

@Service
public interface JobService {
	
	//create new job
	public JobDto createJob(JobDto dto)throws Exception;
	
	
	
	//get all country
	public List<CountryDto> getAllCountries();
	
	
	//get all jobs
	public Map<String,Object> getAllJobs()throws Exception;
	
	//get job by jobId
	public JobDto getJobById(long jobId);
	
	
	
	//get all jobs by pagination
	public Map<String,Object> getJobsByPage(int pageNo,int pageSize)throws Exception;

	
	
	public Set<Location> getStateCity();
	
	
	//get jobs by city and state names
	public Map<String,Object> jobsByCity(String state,String city)throws Exception;
	
	
	//get jobs by jobType
	public Map<String,Object> jobsByJobType(String jobType)throws Exception;
	
	
	
	//get all jobsType
	public List<JobType> getJobTypes();
	
	
	
	//get all jobs by skill and city
	public Map<String,Object> jobsBySkillCity(String city,String... skill)throws Exception;
	
}
