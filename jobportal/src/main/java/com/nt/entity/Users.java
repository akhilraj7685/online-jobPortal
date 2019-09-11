package com.nt.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Users {
	
	public enum UserType{
		CANDIDATE,EMPLOYER,ADMIN;
	}
	
	@Id
	@GenericGenerator(name="seq1",strategy="sequence")
	@GeneratedValue(generator="seq1"/*strategy=GenerationType.SEQUENCE*/)
	@Column(name="userId",length=8)
	private long userId;
	
	@Column(name="email",length=30)
	private String email;
	
	@Column(name="password",length=30)
	private String password;
	
	@Column(name="name",length=30)
	private String name;
	
	@Column(name="dob")
	private Date dob;
	
	@Column(name="gender",length=2)
	private char gender;
	
	@Column(name="regDate")
	private Date regDate;
	
	@Column(name="userType")
	private UserType userType;
	
	@Column(name="smsNotiActive")
    private boolean smsNotiActive;
	
	@Column(name="tcAgreed")
	private boolean tcAgreed;
	
	@Column(name="verified" ,nullable=false)
	private boolean verified;
	
	@OneToOne
	private UserLog userLog;
	
	@OneToMany(cascade= {CascadeType.DETACH,CascadeType.REFRESH,CascadeType.REMOVE},mappedBy="user",fetch=FetchType.LAZY)
	private List<Job> job;
	
	@OneToOne(cascade= {CascadeType.REMOVE,CascadeType.DETACH})
	private JobSeeker jobSeeker;
}
