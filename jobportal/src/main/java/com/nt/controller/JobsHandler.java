package com.nt.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nt.dto.JobDto;
import com.nt.entity.Job;
import com.nt.entity.Job.JobType;
import com.nt.service.EmployerService;
import com.nt.service.JobService;

@Controller
@RequestMapping("/job")
public class JobsHandler {
	
	static {
		
		System.out.println("-----------------------------------------------");
		
	}
	
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private EmployerService empService;
	
	
	
	
	
	@RequestMapping("/jobsLoc")  
	public ModelAndView getStateCity() {
		System.out.println("JobsHandler.test()------------------------");
		String toView="jobsLoc";
		System.out.println("EmployerHandler.test()");
		ModelAndView mv=new ModelAndView();
		mv.setViewName(toView);
		mv.addObject("loc",jobService.getStateCity());
		
		return mv;
	    }
	
	
	
     //get all jobs by city
	@SuppressWarnings("unchecked")
	@RequestMapping("/jobsByLoc/{state}/{city}")
	public ModelAndView getJobsByCity(@PathVariable("state")String state,@PathVariable("city")String city) {
		ModelAndView mv=new ModelAndView();
		String fromView="jobsLoc";
		String toView="jobs";
		
		
		
		List<JobDto> postedJobs=null;
		Map<String,Object> result=null;
		
		
		  		
		 	try {
		 	result=jobService.jobsByCity(state, city);
		} catch (Exception e) {
			mv.setViewName(fromView);
			mv.addObject("msg",e.getMessage());
			return mv;
		}
		 
		mv.setViewName(toView);
		mv.addObject("jobs",(List<JobDto>)result.get("jobDtoList"));
		mv.addObject("pageSize",((Page<JobDto>)result.get("page")).getNumberOfElements());
		mv.addObject("totalPages",((Page<JobDto>)result.get("page")).getTotalPages());
		mv.addObject("totalJobs",((Page<JobDto>)result.get("page")).getTotalElements());
		mv.addObject("pageNumber",((Page<JobDto>)result.get("page")).getNumber());
		
		return mv;
		
		
	}
	
	
	
	
	//get jobs by skillname
	@RequestMapping("/byJobType/{jobType}")
	public ModelAndView getJobsByJobType(@PathVariable("jobType")String jobType) {
		
		ModelAndView mv=new ModelAndView();
		String fromView="jobsSkill";
		String toView="jobs";
		
		
		
		List<JobDto> postedJobs=null;
		Map<String,Object> result=null;
		
		
		  		
		 	try {
		 	result=jobService.jobsByJobType(jobType);
		} catch (Exception e) {
			mv.setViewName(fromView);
			mv.addObject("msg",e.getMessage());
			return mv;
		}
		 
		mv.setViewName(toView);
		mv.addObject("jobs",(List<JobDto>)result.get("jobDtoList"));
		mv.addObject("pageSize",((Page<JobDto>)result.get("page")).getNumberOfElements());
		mv.addObject("totalPages",((Page<JobDto>)result.get("page")).getTotalPages());
		mv.addObject("totalJobs",((Page<JobDto>)result.get("page")).getTotalElements());
		mv.addObject("pageNumber",((Page<JobDto>)result.get("page")).getNumber());
		
		return mv;
		
		
	}
	
	
	
	
	
	//get all jobTypes
	@RequestMapping("/jobTypes")
	public ModelAndView getJobTypes() {
		String toView="jobTypes";
		List<JobType> jobTypes =null;
		ModelAndView mv=new ModelAndView();
		
		jobTypes=jobService.getJobTypes();
		mv.setViewName(toView);
		mv.addObject("jobTypes", jobTypes);
		return mv;
	}
	
	

}
