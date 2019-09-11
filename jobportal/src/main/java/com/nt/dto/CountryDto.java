package com.nt.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.nt.entity.State;

public class CountryDto implements Serializable {
	

	private long cntryId;	

	private String cntryName;

	private List<State> states;

}
