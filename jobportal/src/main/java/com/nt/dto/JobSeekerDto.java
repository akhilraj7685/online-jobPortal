package com.nt.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.lang.Nullable;

import com.nt.entity.EducationDetails;
import com.nt.entity.ExperienceDetails;
import com.nt.entity.JsApplication;
import com.nt.entity.Location;
import com.nt.entity.Skill;
import com.nt.entity.JobSeeker.Currency;
import com.nt.entity.JobSeeker.SalCircle;
import com.nt.entity.Users;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JobSeekerDto implements Serializable {

	
    private  long userId;
    
	private long phn;
	
	
	private String email;
	
	
	private float currSal;
	
	
	private SalCircle salCircle;
	
	
	private Currency currency;
    
	private LocationDto currLoc; //current location
	
	private LocationDto perLoc;  //permament location
	
	private LocationDto    prefLoc; //preffered location
	
	private boolean isFresher;

	private EducationDetailsDto eduDtls;
	
	private List<SkillDto> skills;
	
	private ExperienceDetailsDto expDtls;
	
	private UsersDto user;
	
	private List<JsApplicationDto> appliedJobs;
	
}
