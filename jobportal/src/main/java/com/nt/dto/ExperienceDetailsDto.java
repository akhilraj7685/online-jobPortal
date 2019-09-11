package com.nt.dto;

import java.io.Serializable;
import java.util.Date;

import com.nt.entity.Company;
import com.nt.entity.JobSeeker;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ExperienceDetailsDto implements Serializable {
	
	
	
	private long jsId;
	
	//private JobSeeker jobSeeker;

	private boolean isCurrJob;//is current job

    private Date startDate;

    private Date endDate;

    private String jobTitle;
    
    private CompanyDto  company;

    private LocationDto jobLoc; //jobLocation 

    private String description;
    
    private float exp;   //total experience

}
