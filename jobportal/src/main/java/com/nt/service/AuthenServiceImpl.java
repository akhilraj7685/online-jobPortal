package com.nt.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nt.dto.JobSeekerDto;
import com.nt.dto.UsersDto;
import com.nt.entity.EducationDetails;
import com.nt.entity.ExperienceDetails;
import com.nt.entity.Job;
import com.nt.entity.JobSeeker;
import com.nt.entity.JsApplication;
import com.nt.entity.JsApplication.ApplicationStatus;
import com.nt.entity.Skill;
import com.nt.entity.Users;
import com.nt.entity.Users.UserType;
import com.nt.entity.Verification;
import com.nt.repo.JobRepo;
import com.nt.repo.JobSeekerRepo;
import com.nt.repo.JsApplicationRepo;
import com.nt.repo.UsersRepo;
import com.nt.repo.VerificationRepo;
import com.nt.util.UtilService;

import net.bytebuddy.implementation.bytecode.Throw;

@Service
public class AuthenServiceImpl implements AuthenService {

	@Autowired
	private UsersRepo usersRepo;
	@Autowired
	private VerificationRepo verfyRepo;
	
	@Autowired
	private JobSeekerRepo jobSeekerRepo; 
	
	@Autowired
	private JsApplicationRepo jsAppRepo;
	
	@Autowired
	private JobRepo jobRepo;
	
	
	@Override
	public List<UsersDto> getAllUsers() {
		List<Users> userList=null;
		List<UsersDto> dtoList=new ArrayList<UsersDto>();
		
		userList=usersRepo.findAll();
		userList.forEach(user->{
		UsersDto dto=new UsersDto();
		BeanUtils.copyProperties(user, dto);
		dtoList.add(dto);
		});
	return dtoList;
	}
	
	
	
	@Override
	public List<UsersDto> getAllUsers(String name) {
		List<Users> userList=null;
		List<UsersDto> dtoList=new ArrayList<UsersDto>();
		//userList=usersRepo.findAllByName(name);
		userList=usersRepo.findAllByEmail(name);
		//Users users=new Users();
		//users.setEmail("akh@gmail.com");users.setDob(new Date());users.setName("raghav");users.setPassword("123");
		//users.setUserType(UserType.CANDIDATE);
		//usersRepo.save(users);
		//System.out.println("----"+users.toString());
		userList.forEach(user->{
			UsersDto dto=new UsersDto();
			BeanUtils.copyProperties(user, dto);
			dtoList.add(dto);
			});
		return dtoList;
	}

	
	
	
	

/**
 * 
 */
	@Override
	public boolean isEmailRegistered(String email) {
		int count=0;
		count=usersRepo.findAllByEmail(email).size();
		if(count!=0)
			return true;
		else
			
		return false;
	}


	
	
	
	
	
	

@Override
@Transactional(transactionManager="transactionManager")
public Users register(UsersDto dto)throws Exception {
	Users users=null,users2=null;
	Date dob=null;
	//copy values from dto to entity
	users=new Users();
	
	if(dto.getEmail()!=null)
	   users.setEmail(dto.getEmail());
	
	if(dto.getPassword()!=null)
		users.setPassword(dto.getPassword());
    
	if(dto.getDob()!=null)	
		users.setDob(dob);
	
	if(dto.getName()!=null)
		users.setName(dto.getName());
	
	if(dto.getUserType()!=null) 
		users.setUserType(dto.getUserType());
			
	if(dto.getGender()!=' ')
		users.setGender(dto.getGender());

	if(dto.getRegDate()==null)
	users.setRegDate(new Date());
	else users.setRegDate(dto.getRegDate());
	
	if(dto.isSmsNotiActive()==true)
	users.setSmsNotiActive(true);
	
	if(dto.isTcAgreed()==true)
	users.setTcAgreed(true);
	
	//save entity into database
	
	users2=usersRepo.saveAndFlush(users);
	if(users2==null)
		throw new IllegalArgumentException("entity cant be saved"); 
	return users2;
	
}









   /**
    * 
    */
  @Override
public Users login(String email, String password,String userType) throws Exception {
	int count=0;
	List<Users> usersList=null;
	UserType type=null;
	//decide userType
	type=UtilService.getUserType(userType);
	  //check email is registered or not
	  if(!isEmailRegistered(email)) {
		  throw new IllegalArgumentException("wrong email .Try again");
	  }//if
	  else {
		  //check if user exist for the given email and paswrd and usertype
		usersList=usersRepo.findAllByEmailPaswdType(email, password, type);
		 
		   if(usersList.size()!=1)
			      throw new IllegalArgumentException("wrong email and password. Try again");
		   else {
			return usersList.get(0);   
		   }	  
	  } 
	  
}

  
  
  

  

  
  
  
  
  
  
  /**
   * 
   */
 
public UsersDto login2(String email, String password,String userType) throws Exception {
	int count=0;
	List<Users> usersList=null;
	UsersDto userDto=null;
	Users user=null;
	UserType type=null;
	//decide userType
	type=UtilService.getUserType(userType);
	  //check email is registered or not
	  if(!isEmailRegistered(email)) {
		  throw new IllegalArgumentException("wrong email .Try again");
	  }//if
	  else {
		  //check if user exist for the given email and paswrd and usertype
		usersList=usersRepo.findAllByEmailPaswdType(email, password, type);
		 
		   if(usersList.size()!=1)
			      throw new IllegalArgumentException("wrong email and password. Try again");
		   else {
			   //copy entity into dto
			   userDto=new UsersDto();
			user=usersList.get(0); 
			BeanUtils.copyProperties(user, userDto);
		    return userDto;
		   }//else	  
	  } 
	  
	  
	  	
}
  
  
  



  





  
  /**
   * return true if property is updated successfully
   * else return flase
   */
  @Override
  @Transactional(transactionManager="transactionManager")
public UsersDto updatePswdByEmail(String email,String pswd) {
	UsersDto dto=null;
	Users user=null;
	List<Users> users=null;
	
	int value=0;
    System.out.println("-----2----");
	value=usersRepo.updatePasswordByEmail(email, pswd);
	System.out.println("-----2.1-----");
	if(value!=0) {
		
		users=usersRepo.findAllByEmail(email);
		System.out.println("-----3-----");
	}
	//copy entity to dto
	if(users.size()!=0) {
		System.out.println("------4----");
		dto=new UsersDto();
	    BeanUtils.copyProperties(users.get(0), dto);
	}
	return dto;
	
}
  
  
  
  
  

	//separate email from the url

	public String separateEmail(char beginChar,char endChar,String source) {
		int begin=0,end=0;
		String email=null;
		begin=source.indexOf(beginChar);
		end=source.indexOf(endChar, begin+1);
		email=source.substring(begin+1, end);
		System.out.println("email---"+email);
		return email;
	}
	
	
	//separate token from url
  public String separateToken(char ch,String source) {
	  String token=null;
	  int begin=0,end=0;
	  begin=source.indexOf(ch, source.indexOf(ch)+1);
	  token=source.substring(begin+1);
	  System.out.println("token------>"+token);
	  return token;
  }
  
  
  @Override
public String checkUrl(String url) {
	String email=null,token=null;
	int count=0;
	List<Verification> verList=null;
	  //separate email from url
	  //   url format  ---->url/#email#token
	  email=separateEmail(',',',',url);
	  token=separateToken(',',url);
	  
	  System.out.println("email--"+email+"token---"+token);
	 verList= verfyRepo.findAllByToken(email, token);
	 System.out.println("verlist size---->"+verList.size());
	 if(verList.size()==1)
		 return verList.get(0).getEmail();
	 else return "email matching error";
}
  
  
  
  
  

  
  
  
  
  
  
  
  
  
  
 /**
  * create jobseeker profile 
  */
  @Override
  @Transactional(transactionManager="transactionManager",propagation=Propagation.SUPPORTS)
public JobSeekerDto createJobSeeker(JobSeekerDto jsDto) {
	EducationDetails eduDtls=null;
	ExperienceDetails expDtls=null;
	Users user=null;
	long jobSeekerId=0;
	List<Skill> skillList=new ArrayList<Skill>();
	JobSeeker jobSeeker=null,jobSeeker2=null;
	  
	//change the dto into entity
	  jobSeeker=new JobSeeker();
	  //copy all simple values of dto to entity
	  System.out.println("jsDto------------------------------"+jsDto);
	  BeanUtils.copyProperties(jsDto, jobSeeker);
	  //now copy each object type values of dto to entity after converting each into entity type
	  jobSeekerId=jsDto.getUserId();
	  jobSeeker.setUserId(jobSeekerId);
	  
	  eduDtls=new EducationDetails();
	  BeanUtils.copyProperties(jsDto.getEduDtls(),eduDtls);
	     //set educationdetails id
	  eduDtls.setJsId(jobSeekerId);
	  
	  if(jsDto.getExpDtls()!=null) {
	  expDtls=new ExperienceDetails();
	  BeanUtils.copyProperties(jsDto.getExpDtls(),expDtls);
	  expDtls.setJsId(jobSeekerId);
	  }
	  
	  jsDto.getSkills().forEach(skillDto->{
		  Skill skill=new Skill();
		  BeanUtils.copyProperties(skillDto, skill);
		  skillList.add(skill);
	  });
	  
	  user=new Users();
	  BeanUtils.copyProperties(jsDto.getUser(),user);
	  
	  //add expDtls and eduDtls into the jobSeeker entity
	  jobSeeker.setEduDtls(eduDtls);
	  jobSeeker.setExpDtls(expDtls);
	  jobSeeker.setSkills(skillList);
	  jobSeeker.setUser(user);
	  //save the final entity to database
	  jobSeeker2=jobSeekerRepo.save(jobSeeker);
	  if(jobSeeker2==null)
	  	  throw new IllegalArgumentException("jobseeker can't be saved");
	return jsDto;
}
  
  
  
  /**
   * when user apply for a job add the user id into the job's applicationList
   */
  @Override
  @Transactional(transactionManager="transactionManager",rollbackFor= {Throwable.class})
public String applyJob(long userId, long jobId)throws Exception {
	JsApplication jsApp=null;
	Job job=null,job2;
	int count=0;
	String msg=null;
	JobSeeker js=null;
	
	List<JsApplication> jsAppList=null;
	
	jsApp=new JsApplication();

	 try {
	jsApp.setJobSeeker(jobSeekerRepo.getOne(userId));
	 }catch(EntityNotFoundException enfe) {
		 throw new EntityNotFoundException("<a href='/jobs/createJsp'>first add details about you</a>");
	 }
	 
	
	//jsApp.setJsAppId(userId); 
	jsApp.setAppStatus(ApplicationStatus.PENDING);
	System.out.println("application created-------------------------------------"+jsApp.getJsAppId());
	
	System.out.println("user id---service------------"+userId+"jobId--------"+jobId);
	
	//fetch job by submitting jobId
	 job=jobRepo.getOne(jobId);
	
	 //add this new jobApplication to the jobApplication List
	 if(job==null)  return "job returned is null";
	  jsApp.setJob(job);
		 jsAppList=job.getApplications();
	      if(jsAppList==null) jsAppList=new ArrayList<JsApplication>();
	
	     //check user alreday applied or not if yes,throw error
	 	 for(JsApplication app:jsAppList) {
	 		 System.out.println("------------------------4");
	 		 if(app.getJobSeeker().getUserId()==userId) {
	 			 System.out.println("----------------already applied-------");
	 			  throw new Exception("already applied");
	 		 }
	 	 }
	 	 //if already not applied then allow to apply
	      jsAppList.add(jsApp);
	
	
	 job.setApplications(jsAppList);
	 
	 
	 job2=jobRepo.save(job);
	 System.out.println("job2Id--------"+job2.getJobId());
	 //now save the job entity
	//count=jobRepo.updateJobByapplications(jsAppList, jobId);
	    //  count=jobRepo.updateJobByJsApps(jsAppList, jobId);
	
	if(job2==null) throw new Exception("job is not inserted");
	else return "applied successfully";
}
  
}//class
