package com.nt.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.nt.entity.Company;
import com.nt.entity.Location;
import com.nt.entity.Skill;
import com.nt.entity.Users;
import com.nt.entity.Job.JobType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

public class JobDto implements Serializable {
	
	
	private long jobId;
	
	private Users user;
	
	private String jobTitle;
	
	private Date postingDate;
	
	private JobType jobType;
	
	private CompanyDto company;
	
	private int numOfSeats;
	
	private boolean isActive;
	
	private float minSal;
	
	private float maxSal;
	
	private String jobDescription;
    
	private float minExp;
	
	private float maxExp;
	
	private String employmentType;
	
	private List<LocationDto> locations;

	private List<SkillDto> skills;
	
	private List<JsApplicationDto> applications;
	
	
	
	
	
}
