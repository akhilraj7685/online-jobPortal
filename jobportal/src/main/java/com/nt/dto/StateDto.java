package com.nt.dto;

import java.io.Serializable;
import java.util.List;

import com.nt.entity.Country;
import com.nt.entity.Distric;

public class StateDto implements Serializable {
	
	
private long stateId;
	
	private String stateName;
	
	private Country country;
	
	private List<Distric> districs;


}
