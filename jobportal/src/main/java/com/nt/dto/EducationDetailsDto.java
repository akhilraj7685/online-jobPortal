package com.nt.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.nt.entity.JobSeeker;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EducationDetailsDto implements Serializable {
	
	
	
	
	private long userId; 

	//private  JobSeeker jobSeeker;	
	
	private String highestQuali;
	
	private String major;

	private Date startDate;

	private Date completionDate;

	private String institute;

	private float marksPercentage;

	private float cgpa;


}
