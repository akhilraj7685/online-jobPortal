package com.nt.dto;

import java.io.Serializable;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.nt.entity.Location.AddrType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LocationDto implements Serializable {
	
	
	

	private long locId;
	
	private AddrType locType;
	
	private String streetAddr;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private long pin;
	
}
