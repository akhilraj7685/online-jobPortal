package com.nt.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class State {
	
	@Id
	@GenericGenerator(name="stateGen",strategy="sequence")
	@GeneratedValue(generator="stateGen")
	private long stateId;
	
	@Column(name="stateName",length=30)
	private String stateName;
	
	@ManyToOne
	private Country country;
	
	@OneToMany(fetch=FetchType.LAZY)
	private List<Distric> districs;

}
