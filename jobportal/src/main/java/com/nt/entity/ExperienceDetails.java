package com.nt.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class ExperienceDetails {

	@Id
	@Column(name="userId",length=8)
	private long jsId;     //js->jobSeeker
	
	
	
	@Column(name="isCurrJob")
	private boolean isCurrJob;//is current job
    
	@Column(name="startDate")
    private Date startDate;
    
    @Column(name="endDate")
    private Date endDate;
    
    @Column(name="desg",length=30)
    private String desg;
    
    @OneToOne
    private Company  company;
    
    @OneToOne(cascade=CascadeType.ALL)
    private Location jobLoc;
    
    @Column(name="description",length=30)
    private String description;
    
    @Column(name="exp")
    private float exp;  //total exprience of user


}
