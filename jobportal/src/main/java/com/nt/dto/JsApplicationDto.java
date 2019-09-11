package com.nt.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
	public class JsApplicationDto {

	    private long jsAppId;  //jobSeekerApplicationId 
		                       
		private long jsId;    //jobSeekerId	
		
		private JobDto job;
		
		private JobSeekerDto jobSeeker;
		
	}

