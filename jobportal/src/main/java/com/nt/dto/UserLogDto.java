package com.nt.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.nt.entity.Users;

public class UserLogDto implements Serializable {
	
	private long userId;
	private Date lastLogin;
	 

}
