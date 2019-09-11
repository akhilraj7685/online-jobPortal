package com.nt.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.lang.Nullable;

import com.nt.dto.LocationDto;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class JobSeeker {

	public enum SalCircle{
		M,A;   //M->monthly   ,A->anully
	}
	
	public enum Currency{
		INR,DOLLAR,EURO;
	}
	
	@Id
	@Column(name="userId",length=8)
	private long userId;
	
	
	//@MapsId(value="userId")
	//@OneToOne
	//private Users users;
	
	@Column(name="phn",length=12)
	
	private long phn=0;
	
	@Column(name="email",length=35)
	private String email;
	
	@Column(name="currSal",precision=2,length=10,scale=8)
	private float currSal;
	
	@Column(name="salCircle",length=2)
	private SalCircle salCircle;
	
	@Column(name="currency")
	private Currency currency;
	
	
	@Column(name="isFresher")
	private boolean isFresher;
	
	@OneToOne
	private Users user;
	
    @OneToOne(cascade=CascadeType.ALL)
	private Location currLoc; //current location
	
    @OneToOne(cascade=CascadeType.ALL)
	private Location perLoc;  //permament location
    
    @OneToOne(cascade=CascadeType.ALL)
	private Location    prefLoc; //preffered location
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private EducationDetails eduDtls;
	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Skill> skills;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@Nullable
	private ExperienceDetails expDtls;
	
	
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<JsApplication> appliedJobs;
	
	
}
