package com.nt.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter


public class Job  {
	/**
	 * jobs by industry/sector
	 */
	public enum JobType {
		ACCOUNTING,ADVERTISING,AGRICULTURE,ANIMATION,ARCHITECTURE,TECHNECIAN,
		AVIATION,BPO,BANK,BREWERY,SANITARY,CHEMICAL,ENGINEERING,CONSUMER_DURABLE,
		COURIER,DEFENCE,TEACHER,ELECTRICAL,EXPORT_IMPORT,FMCG,FACILITY_MANAGEMENT,
		FERTILIZERS,FOOD_PROCESSING,FRESHER,GEMS_JEWELLERY,GLASS,AIR_CONDITIONING,
		AIRLINE,NETWORKING,IT,INDUSTRIAL,INSURANCE,KPO,LEGAL,MEDIA,DOTCOM,ENTERTAINMENT,
		MEDICAL,MINING,NGO,AUTOMATION,OIL_AND_GAS,PAPER,PHARMA,PRINTING,PUBLISHING,REAL_ESTATE,
		RECRUITMENT,RETAIL,SECURITY,ELECTRONICS,SHIPPING,STEEL,CONSULTANT,TELECOM,TEXTILES,
		TYRES,WATER_TREATMENT,FITNESS_TRAINER,ECOMMERCE,INTERNATE,PROGRAMMER,CODER,SALESMAN;
	
       
		
		
	}

	@Id
	@Column(name="jobId",length=8)
	@GenericGenerator(name="gen4",strategy="sequence")
	@GeneratedValue(generator="gen4")
	private long jobId;
	
	@ManyToOne(cascade= {CascadeType.MERGE,CascadeType.PERSIST})
	private Users user;
	
	@Column(name="jobTitle",length=35)
	private String jobTitle;
	
	@Column(name="postingDate")
	private Date postingDate;
	
	@Column(name="jobType")
	private JobType jobType;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Company company;
	
	@Column(name="numOfSeats")
	private int numOfSeats;
	
	
	
	@Column(name="isActive")
	private boolean isActive;
	
	
	
	@Column(name="maxsal",length=10)
	private float maxSal;
	
	@Column(name="minSal",length=10)
	private float minSal;
    
	@Column(name="jobDescription",length=500)
	private String jobDescription;
    
	@Column(name="minExp")
	private float minExp; //minimum exprience required for this job
    
	@Column(name="maxExp")
	private float maxExp;
    
	@Column(name="employmentType",length=30)
	private String employmentType;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<JsApplication> applications;
	
	
	@OneToMany(cascade= {CascadeType.ALL})
	
	private List<Location> locations;

	@OneToMany(cascade=CascadeType.ALL,mappedBy="job")
	private List<Skill> skills;

}
