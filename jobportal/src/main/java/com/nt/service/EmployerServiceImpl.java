/**
 * this is concrete implementation class of employer service
 */
package com.nt.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nt.dto.CompanyDto;
import com.nt.dto.EducationDetailsDto;
import com.nt.dto.ExperienceDetailsDto;
import com.nt.dto.JobDto;
import com.nt.dto.JobSeekerDto;
import com.nt.dto.JsApplicationDto;
import com.nt.dto.LocationDto;
import com.nt.dto.SkillDto;
import com.nt.dto.UsersDto;
import com.nt.entity.Job;
import com.nt.entity.JobSeeker;
import com.nt.entity.Location;
import com.nt.repo.JobRepo;
import com.nt.repo.LocationRepo;

@Service
public final class EmployerServiceImpl implements EmployerService {

	@Autowired
	private JobRepo jobRepo;
	
	@Autowired
	private LocationRepo locRepo;
	
	
	
	/**
	 * 
	 */
	@Override
	public Map<String,Object> getPostedJobs(long userId)throws Exception {
		int pageSize=25;
		int pageNo=0;
		Map<String,Object> result=new HashMap<String, Object>();
		
		Page<Job> page=Page.empty();
		PageRequest pageRequest=PageRequest.of(pageNo,pageSize, Sort.by("postingDate").descending());
		page=jobRepo.findAllByUserId(userId, pageRequest);
		
		
		List<JobDto> jobDtos=new ArrayList<JobDto>();
		//jobs=jobRepo.findAllByRecruiter(userId);
		System.out.println("userid--------"+userId);
		if(page.isEmpty()) 
			throw new EntityNotFoundException("you have not created any job");
		
		//copy entity values into dto
		page.forEach(job->{
			System.out.println("------job"+job);
			JobDto dto=new JobDto();
			BeanUtils.copyProperties(job, dto);
			 //add dto to list collection
			jobDtos.add(dto);
		});
		
		result.put("jobDtoList",jobDtos);
		result.put("page",page);
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 */
	@Override
	public JobDto getPostedJobDetails(long jobId) throws Exception {
	
		JobDto jobDto=new JobDto();
		Job job=null;
		List<LocationDto> locList=new ArrayList<>();
		List<SkillDto> skillList=new ArrayList<>();
		List<JsApplicationDto> jsAppDtoList=new ArrayList<JsApplicationDto>();
		List<JobSeekerDto> jsDtoList=new ArrayList<JobSeekerDto>();
		CompanyDto cmpDto=null;
		
		
		//get job entity from repository
		job=jobRepo.getOne(jobId);
		
		//convert entity into dto
			
			
			 //copy company entity to company dto
			 cmpDto=new CompanyDto();
			BeanUtils.copyProperties(job.getCompany(),cmpDto);
			
			//copy locations from entity to dto 
			
			System.out.println("----------------"+job.getLocations().size()); 
			job.getLocations().forEach(loc->{ 
				 System.out.println("------------location::--------------------------"+loc.getCity());
				 LocationDto locDto=new LocationDto();
				 BeanUtils.copyProperties(loc, locDto);
				 locList.add(locDto);
			 });
			 
			 //copy skills from entity to dto
			
			 job.getSkills().forEach(skill->{
				 System.out.println("--------------skill-----------------------"+skill.getSkillName());
				 SkillDto skillDto=new SkillDto();
				 if(skill!=null) {
				 BeanUtils.copyProperties(skill,skillDto);
				 skillList.add(skillDto);
				 }
			 });
			 
			 System.out.println("----------------------------------------b4App");
			 //copy jsApplications from entity to dto
			 job.getApplications().forEach(app->{
				 System.out.println("----------------------------------in app");
				  //copy each jobseeker to jobSeekerDto
				 JsApplicationDto jsAppDto=new JsApplicationDto();
				 JobSeekerDto jsDto=new JobSeekerDto();
				   //copy associated entities of job seeker to dto
				   JobSeeker js=app.getJobSeeker();
				   System.out.println("-----------------------------------.o1");
				       UsersDto userDto=new UsersDto();
				       System.out.println("user-------------"+js.getUser());
				       BeanUtils.copyProperties(js.getUser(), userDto);
				       System.out.println("------------------------------------------.1");
				     
				       List<SkillDto> jsSkillDtoList=new ArrayList<>();
				       js.getSkills().forEach(jsSkill->{
				    	  SkillDto jsSkillDto=new SkillDto();
				    	  BeanUtils.copyProperties(jsSkill, jsSkillDto);
				    	  jsSkillDtoList.add(jsSkillDto);
				    	  System.out.println("------------------------------------------.2");
				        });
				     
				       EducationDetailsDto jsEduDtlsDto=new EducationDetailsDto();
				       BeanUtils.copyProperties(js.getEduDtls(), jsEduDtlsDto);
				       System.out.println("------------------------------------------.3");
				       //if jobSeeker (js) is not fresher then get his experience details
				       ExperienceDetailsDto jsExpDtlsDto=null;
				       if(!js.isFresher()) {
				    	  jsExpDtlsDto=new ExperienceDetailsDto();
				    	  BeanUtils.copyProperties(js.getExpDtls(),jsExpDtlsDto);
				    	  System.out.println("------------------------------------------.4");
				       }//if
				       
				     
				       
				       //copy simple values of jobSeeker to dto
				       BeanUtils.copyProperties(app.getJobSeeker(),jsDto);
				       System.out.println("------------------------------------------.5");
				       //copy object type values of jobSeeker to dto
				       jsDto.setEduDtls(jsEduDtlsDto);
				       if(!js.isFresher())
				       jsDto.setExpDtls(jsExpDtlsDto);
				       jsDto.setSkills(jsSkillDtoList);
				       jsDto.setUser(userDto);
				       System.out.println("------------------------------------------.6");
				
				 //copy application values to dto
				 BeanUtils.copyProperties(app,jsAppDto );
				 jsAppDto.setJobSeeker(jsDto);
				 jsAppDtoList.add(jsAppDto);
				 System.out.println("------------------------------------------.7");
			 });//->application completed
			 
			 //copy simple values of entity to dto
			 BeanUtils.copyProperties(job,jobDto);
			 System.out.println("------------------------------------------.8");
			 //copy objects type values from entity to dto
			 jobDto.setCompany(cmpDto);  
			 jobDto.setLocations(locList);
			 jobDto.setSkills(skillList);
			
             if(jsAppDtoList!=null)
			 jobDto.setApplications(jsAppDtoList);
             System.out.println("------------------------------------------.9");
		
		System.out.println("--------->  "+jobDto.getJobDescription());
		return jobDto;
	}
	
	
	
	

	
	
}
