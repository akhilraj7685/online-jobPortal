package com.nt.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserLog {
	
	@Id
	@Column(name="userId",length=8)
	private long userId;
	
	@Column(name="lastLogin")
	private Date lastLogin;
	
	@OneToOne
	@MapsId
	private Users users;
	 
     
}
