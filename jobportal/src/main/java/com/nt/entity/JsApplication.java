package com.nt.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class JsApplication {

	
	public enum ApplicationStatus{
		
		VIEWED,ACCEPTED,REJECTED,PENDING;
	}
	/**
	 * one user can apply ony submit only one application for one job
	 * 
	 */
	@Id
	@Column(name="jsAppId",length=8)
	@GenericGenerator(name="gen6",strategy="sequence")
	@GeneratedValue(generator="gen6")
	private long jsAppId;       //jobSeekerId
	
	@Column(name="applyDate")
	private Date applyDate;
	
	@Column(name="appStatus")
	private ApplicationStatus appStatus;
	
	@ManyToOne
	private Job job;
	
	@OneToOne
	private JobSeeker jobSeeker;
	
	
	
}
