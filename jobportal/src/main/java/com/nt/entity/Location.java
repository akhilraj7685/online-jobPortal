package com.nt.entity;

import java.util.HashMap;
import java.util.List;

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
public class Location {

	
	
    public	enum AddrType{
		PERMANENT,CURRENT,PREFFERED;
	}
   
	
	@Id
	@Column(name="locId",length=8)
	@GenericGenerator(name="gen3",strategy="sequence")
	@GeneratedValue(generator="gen3")
	private long locId;
	
	
	@Column(name="locType")
	private AddrType locType; 
	
	
	@Column(name="streetAddr",length=100)
	private String streetAddr;
	
	@Column(name="city",length=25)
	private String city;
	
	@Column(name="state",length=25)
	private String state;
	
	@Column(name="country",length=25)
	private String country;
	
	@Column(name="pin",length=8)
	private long pin;

	
	
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return locId + ", " + locType + ", " + streetAddr + ", " + city + ", " + state + ", " + country + ", " + pin
				+ "]";
	}
	






}

