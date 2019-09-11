package com.nt.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Company {

	public enum IndustryType{
		ACCOUNTING,ADVERTISING,AGRICULTURE,ANIMATION,ARCHITECTURE,AUTOMOBILE,
		AVIATION,BPO,BANK,BREWERY,SANITARY,CHEMICAL,ENGINEERING,CONSUMER_DURABLE,
		COURIER,DEFENCE,TEACHER,ELECTRICAL,EXPORT_IMPORT,FMCG,FACILITY_MANAGEMENT,
		FERTILIZERS,FOOD_PROCESSING,FRESHER,GEMS_JEWELLERY,GLASS,AIR_CONDITIONING,
		AIRLINE,NETWORKING,IT,INDUSTRIAL,INSURANCE,KPO,LEGAL,MEDIA,DOTCOM,ENTERTAINMENT,
		MEDICAL,MINING,NGO,AUTOMATION,OIL_AND_GAS,PAPER,PHARMA,PRINTING,PUBLISHING,REAL_ESTATE,
		RECRUITMENT,RETAIL,SECURITY,ELECTRONICS,SHIPPING,STEEL,CONSULTANT,TELECOM,TEXTILES,
		TYRES,WATER_TREATMENT,FITNESS_TRAINER,ECOMMERCE,INTERNATE;
	}
	
	@Id
	@Column(name="cmpId",length=8)
	@GenericGenerator(name="gen5",strategy="sequence")
	@GeneratedValue(generator="gen5")
	private long cmpId;
	
	@Column(name="cmpName",length=25)
    private String cmpName;
	
	@Column(name="cmpEst")
    private String cmpEstDate;   //when the company established---company establish date
	
	@Column(name="cmpWebsiteUrl",length=30)
    private String cmpWebsiteUrl;
	
	@Column(name="cmpDescription",length=300)
    private String cmpDescription;
	
	@Column(name="industryType")
	private IndustryType industryType;
    
	@OneToMany(cascade=CascadeType.ALL,mappedBy="company")
	private List<Job> job;
    
	@OneToMany(cascade=CascadeType.ALL)
    private List<Location> branchLoc;

    
}
