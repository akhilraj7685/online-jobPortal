/**
 * akhil  raj
 * version:1.0
 * 
 * this interface have methods that deals with employer/recruiter
 */
package com.nt.service;

import java.util.Map;
import java.util.Set;

import com.nt.dto.JobDto;
import com.nt.entity.Location;

public interface EmployerService {
	
	/**
	 * get all jobs posted by current employer that is logged in
	 */
     public Map<String,Object> getPostedJobs(long userId)throws Exception;
     
     
     
     
     /**
      * get job details posted by the employer for 
      * takes input as jobId
      * return jobDetails
      */
     
     public JobDto getPostedJobDetails(long jobId)throws Exception;
    	 
    	 
    	 
     
     
}
