package com.nt.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Country {

	@Id
	@GenericGenerator(name="cntryGen",strategy="sequence")
	@GeneratedValue(generator="cntryGen")
	private long cntryId;
	
	@Column(name="name",length=30)
	private String cntryName;
	@OneToMany(fetch=FetchType.LAZY)
	private List<State> states;
}
