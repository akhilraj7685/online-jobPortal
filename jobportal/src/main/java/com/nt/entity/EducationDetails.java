package com.nt.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EducationDetails {

	@Id
	@Column(name="userId",length=8)
	private long jsId;   //js->jobSeeker 
	
	
	
	@Column(name="hiQuli",length=15)
	private String highestQuali;
	
	@Column(name="major",length=15)
	private String major;
	
	@Column(name="StartDate")
	private Date startDate;
	
	@Column(name="completionDate",nullable=true)
	private Date completionDate;
	
	@Column(name="institute",length=30)
	private String institute;
	
	@Column(name="marksPercntage",length=5,nullable=true)
	private float marksPercentage;
	
	@Column(name="cgpa",length=4,nullable=true)
	private float cgpa;




}
