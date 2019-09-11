package com.nt.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter

public class Distric {
   
	@Id
	@GenericGenerator(name="distGen",strategy="sequence")
	@GeneratedValue(generator="distGen")
	private long distId;
	
	
	@Column(name="distName",length=30)
	private String distName;
	
	
	
	@ManyToOne
	private Country country;
	
	@ManyToOne
	private State state;
    
	
	@Column(name="population")
	private double population;
     
}
