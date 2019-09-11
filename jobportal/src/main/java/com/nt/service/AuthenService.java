package com.nt.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.nt.dto.JobSeekerDto;
import com.nt.dto.UsersDto;
import com.nt.entity.Users;

public interface AuthenService {

	//get all users
	public List<UsersDto> getAllUsers();
	
	//get all users by name
	public List<UsersDto> getAllUsers(String name);
	
	//check email already registered or not
	//return true is email is registered
	public boolean isEmailRegistered(String email);
	
	
	//register new user
	public Users register(UsersDto dto)throws Exception;
	
	
	
	
	//login user based on email and password
	public Users login(String email,String password,String userType)throws Exception;
	
	//login user based on email and password
		public UsersDto login2(String email,String password,String userType)throws Exception;
		

	
	//validate email and token
	public String checkUrl(String url);
	
	
	//update password of user
	public UsersDto updatePswdByEmail(String email,String pswd);
	
	
	
	//create jobSeeker profile
	public JobSeekerDto createJobSeeker(JobSeekerDto jsDto);
	
	
	
	
	
	//apply jobSeeker for a job
	public String applyJob(long userId,long jobId) throws Exception;
	
	
}
