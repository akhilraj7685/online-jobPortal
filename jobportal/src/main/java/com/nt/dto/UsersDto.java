package com.nt.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.nt.entity.UserLog;
import com.nt.entity.Users.UserType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsersDto implements Serializable {

    private long userId;
	
	private String email;
	
	private String password;
	
	private String name;
	
	private Date dob;
	
	private char gender;
	
	private Date regDate;
	
	private UserType userType;
	
    private boolean smsNotiActive;
	
    private boolean verified;
    
	private boolean tcAgreed;
	
	private UserLog userLog;

}
