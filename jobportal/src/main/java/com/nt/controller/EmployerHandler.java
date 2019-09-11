package com.nt.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nt.dto.CompanyDto;
import com.nt.dto.JobDto;
import com.nt.dto.LocationDto;
import com.nt.dto.SkillDto;
import com.nt.entity.Job.JobType;
import com.nt.entity.Skill;
import com.nt.entity.Users;
import com.nt.service.AuthenService;
import com.nt.service.EmployerService;
import com.nt.service.JobService;
import com.nt.util.UtilService;

@Controller
@RequestMapping("/empr")
public class EmployerHandler {

	@Autowired
	private AuthenService authenService;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private EmployerService empService;
	
	
	
	
	

	@RequestMapping("/createJob")
	public String createJobHandler() {
		return "jobPost1";
	}
	
	
	
	
	
	
	@RequestMapping("/form1Processor")
   //process jobPost1.jsp  form data and send control to next form page
	public ModelAndView processJobPost1(HttpServletRequest req) {
		ModelAndView mv=null;
	    JobDto dto=null;	
		HttpSession ses=null;
		
		ses=req.getSession();
	    mv=new ModelAndView();
		dto=new JobDto();
		
		
		//check user is loggedin or not
		if(((String)ses.getAttribute("sesId"))!=ses.getId()) {
			mv.setViewName("error");
			mv.addObject("errMsg","you must login to post job. Login as employer");
			return mv;
		}
			
		
		
		if(req.getParameter("jobType")=="") {
		return UtilService.SetErrorMsg("jobPost1","job type can't be null");
		}
		dto.setJobType(JobType.valueOf(req.getParameter("jobType")));
	    
		if(req.getParameter("jobTitle")=="")
			return UtilService.SetErrorMsg("jobPost1","enter title of job");
		dto.setJobTitle(req.getParameter("jobTitle"));
		
		if(req.getParameter("numOfSeats")=="")
			return UtilService.SetErrorMsg("jobPost1","enter number of openings");
		try {
		dto.setNumOfSeats(Integer.parseInt(req.getParameter("numOfSeats")));
		}
		   catch(Exception e) {
			   return UtilService.SetErrorMsg("jobPost1","enter only digits 0 to 9 in the \"number of seats\" field ");
		    }
		
		
		if(req.getParameter("minSal")!="") {
		try {
			dto.setMinSal(Float.parseFloat(req.getParameter("minSal")));
			}
			   catch(Exception e) {
				   return UtilService.SetErrorMsg("jobPost1","please enter only numbers in minimum salary field ");
			    }
		}//if
		
		
		if(req.getParameter("maxSal")!="") {
		try {
			dto.setMaxSal(Float.parseFloat(req.getParameter("maxSal")));
			}
			   catch(Exception e) {
				   return UtilService.SetErrorMsg("jobPost1","please enter only numbers in maximum salary field ");
			    }
		}
		
		
		if(req.getParameter("minExp")!="") {
			try {
				dto.setMinExp(Float.parseFloat(req.getParameter("minExp")));
				}
				   catch(Exception e) {
					   return UtilService.SetErrorMsg("jobPost1","please enter only numbers in minimum exprience field ");
				    }
			}//if
		
		
		if(req.getParameter("maxExp")!="") {
			try {
				dto.setMaxExp(Float.parseFloat(req.getParameter("maxExp")));
				}
				   catch(Exception e) {
					   return UtilService.SetErrorMsg("jobPost1","please enter only numbers in maximum exprience field ");
				    }
			}//if
		
		//store the dto into session and take other values from next form page
		ses.setAttribute("jobDto",dto);

		List<LocationDto> locList=new ArrayList<>();
		ses.setAttribute("locList",locList);
		
		List<Skill> skillList=new ArrayList<>();
		ses.setAttribute("skillList",skillList);
		
		
		mv.setViewName("jobPost2");
		return mv;
	}
	
	
	
	
	
	
	
	
	
	
	
	//process form data of jobPost2.jsp --it is having location related data
	@RequestMapping("/form2Processor")
	public ModelAndView  processJobPost2(HttpServletRequest req) {
		ModelAndView mv=null;	
		HttpSession ses=null;
		LocationDto locDto=null;
		JobDto jobDto=null;
		List<LocationDto> locList=null;     
		
		ses=req.getSession();
	    mv=new ModelAndView();
		
		
		
		//check user is loggedin or not
		if(((String)ses.getAttribute("sesId"))!=ses.getId()) {
			mv.setViewName("error");
			mv.addObject("errMsg","you must login to post job. Login as employer");
			return mv;
		}
			
		
		
		//process form data     
		
		if(req.getParameter("country")==null)
		    return UtilService.SetErrorMsg("jobPost2"," country can't be empty");
		if(req.getParameter("state")==null)
			return UtilService.SetErrorMsg("jobPost2"," state was not selected");
		if(req.getParameter("city")==null)
			return UtilService.SetErrorMsg("jobPost2"," city was not selected");
		
		locDto=new LocationDto();
		locDto.setCountry(req.getParameter("country"));
		locDto.setState(req.getParameter("state"));
		locDto.setCity(req.getParameter("city"));
		if(req.getParameter("streetAddr")!="") {
			System.out.println("street addr----"+req.getParameter("streetAddr")+"---");
			locDto.setStreetAddr(req.getParameter("streetAddr"));
			}
		if(req.getParameter("pin")!="") {
			System.out.println("street addr----"+req.getParameter("pin"));
			try {
			locDto.setPin(Long.parseLong(req.getParameter("pin")));
			}
		      catch(Exception e) {
		    	  return UtilService.SetErrorMsg("jobPost2"," enter only number in the pin code box");  
		      }
		}//if
		
	  //take jobDto from session and add this locDto to that jobDto
		if(ses.getAttribute("jobDto")==null)
			return UtilService.SetErrorMsg("jobPost1"," somthing error!! try again");
		 jobDto=(JobDto)ses.getAttribute("jobDto");
		
		//add this location to jobDto and again add jobDto to session
		 locList=jobDto.getLocations();
		 if(locList==null)
			 jobDto.setLocations(new ArrayList<LocationDto>());
		jobDto.getLocations().add(locDto);
		ses.setAttribute("jobDto",jobDto);
		
		//add more locations
		if(req.getParameter("moreLoc").equalsIgnoreCase("yes"))
			mv.setViewName("jobPost2");
		else mv.setViewName("jobPost3");
		
		return mv;
	}












   //  process jobPost3 data---it contains job skill
	@RequestMapping("/form3Processor")
	public ModelAndView jobPost3(HttpServletRequest req) {
    	ModelAndView mv=null;
	    JobDto jobDto=null;
		HttpSession ses=null;
		List<SkillDto> skillList=null;
		
		
		System.out.println("-----1---");
		ses=req.getSession();
	    mv=new ModelAndView();
		
		skillList=new ArrayList<SkillDto>();
		System.out.println("-----2---");
		
		//check user is loggedin or not
		if(((String)ses.getAttribute("sesId"))!=ses.getId()) {
			mv.setViewName("error");
			mv.addObject("errMsg","you must login to post job. Login as employer");
			return mv;
		}
		
    	
		
		System.out.println("-----3---");
		
		//take jobDto from session 
		if(ses.getAttribute("jobDto")==null)
			return UtilService.SetErrorMsg("jobPost1"," somthing error!! try again");
		jobDto=(JobDto)ses.getAttribute("jobDto");
		
		
		
		
		
		
		System.out.println("-----4---");
		Enumeration<String> enu=req.getParameterNames();
		while(enu.hasMoreElements()) {
			SkillDto skillDto=null;
			String skill0=null;
			skill0	=req.getParameter(enu.nextElement());
			System.out.println("---skill0"+skill0);
			
			if(skill0!="") {
				skillDto=new SkillDto();
			skillDto.setSkillName(skill0);
			skillList.add(skillDto);
			}
			
		}//while end
		
		
		
		//add skilllist to jobDto and again add jobDto to session
		jobDto.setSkills(skillList);
		ses.setAttribute("jobDto",jobDto);
		
		
		
		
		mv.setViewName("jobPost4");
    	return mv;
    }    
	
	
	
	
	
	
	
	
	/**
	 * process jobPost4 form
	 */
	@RequestMapping("/form4Processor")
	public ModelAndView jobPost4(HttpServletRequest req) {
    	ModelAndView mv=null;
	    JobDto jobDto=null;
		HttpSession ses=null;
		String jobDesc=null;
		
		
		System.out.println("-----1---");
		ses=req.getSession();
	    mv=new ModelAndView();
		
		
		
		
		//check user is loggedin or not
		if(((String)ses.getAttribute("sesId"))!=ses.getId()) {
			mv.setViewName("error");
			mv.addObject("errMsg","you must login to post job. Login as employer");
			return mv;
		}
		
    	
		
		
		
		//take jobDto from session 
		if(ses.getAttribute("jobDto")==null)
			return UtilService.SetErrorMsg("jobPost1"," somthing error!! try again");
		jobDto=(JobDto)ses.getAttribute("jobDto");
		
		
		
		
		
		
		  //process the form
	    	if(req.getParameter("jobDesc")!="") {
		  jobDesc=req.getParameter("jobDesc"); 
		  }//if
		
		
		
		
		
		
		
		//add job description to jobDto and again add jobDto to session
		jobDto.setJobDescription(jobDesc);
		ses.setAttribute("jobDto",jobDto);
		
		
		
		//create job now
	    return	createJob(req);
    	
    }    
	
	
	
	
	
	
	
	
	/**
	 * add all forms data to jobDto and send to service to create job
	 * 
	 */
	private ModelAndView createJob(HttpServletRequest req) {
		ModelAndView mv=null;
	    JobDto jobDto=null,jobDto2=null;
		HttpSession ses=null;
		
		ses=req.getSession();
	    mv=new ModelAndView();
		
		
		
		//check user is loggedin or not
				if(((String)ses.getAttribute("sesId"))!=ses.getId()) {
					mv.setViewName("error");
					mv.addObject("errMsg","you must login to post job. Login as employer");
					return mv;
				}
				
		
				
		//take jobDto from session 
				if(ses.getAttribute("jobDto")==null)
					return UtilService.SetErrorMsg("jobPost1"," somthing error!! try again");
				jobDto=(JobDto)ses.getAttribute("jobDto");
				
				
				//set job as active
				jobDto.setActive(true);
				
				
				//add all objects to jobDto
				
				jobDto.setUser((Users)ses.getAttribute("users"));
				jobDto.setPostingDate(new Date());
				CompanyDto cDto=new CompanyDto();
				cDto.setCmpName("HCL");
				jobDto.setCompany(cDto);
				
				
				
				
				//call service to create this job
				try {
					jobDto2=jobService.createJob(jobDto);
				} catch (Exception e) {
				return	UtilService.SetErrorMsg("error",e.getMessage());
					
				}
				
				mv.setViewName("jobDetails");
				mv.addObject("jobDto",jobDto2);
				
		return mv;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * handle request for the employer to see all his posted jobs
	 */
	@RequestMapping("/postedJobs")
	public ModelAndView postedJobs(HttpServletRequest req){
		ModelAndView mv=null;
		long userId=0;
		List<JobDto> postedJobs=null;
		Map<String,Object> result=null;
		HttpSession ses=req.getSession();
		String viewName="postedJobs";
		
		
				
				//check user is loggedin or not
		  		if(((String)ses.getAttribute("sesId"))!=ses.getId()) {
		  		   return UtilService.SetErrorMsg("error","<a href='/authen/signup0'>first register as employer/recruiter</a>");
		  		}
		  		
		  		
		 //check if user is employer/recruiter or not
		 if( !(((Users)(ses.getAttribute("users"))).getUserType().name()).equalsIgnoreCase("EMPLOYER"))
		 return UtilService.SetErrorMsg("error","<a href='/authen/login0/CANDIDATE'>login  as employer</a>");
		
		 
		 //if user is logged in and he is employer then returncollection of jobDto to him
		 	userId=((Users)ses.getAttribute("users")).getUserId();
		System.out.println("cntrl----------userId"+userId);
		 	try {
		 	result=empService.getPostedJobs(userId);
		} catch (Exception e) {
			mv=UtilService.setMyView(ses);
			mv.addObject("msg",e.getMessage());
			return mv;
		}
		 
		mv=new ModelAndView(viewName);
		mv.addObject("postedJobs",(List<JobDto>)result.get("jobDtoList"));
		mv.addObject("pageSize",((Page)result.get("page")).getNumberOfElements());
		mv.addObject("totalPages",((Page)result.get("page")).getTotalPages());
		mv.addObject("totalJobs",((Page)result.get("page")).getTotalElements());
		mv.addObject("pageNumber",((Page)result.get("page")).getNumber());
		
		return mv;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	* handle request to see details of a particular job
	*/
	@RequestMapping("/jobDetails/{jobId}")
	public ModelAndView getJobDetailsById(@PathVariable("jobId")long jobId,HttpServletRequest req) {
		
		ModelAndView mv=new ModelAndView();
		HttpSession ses=req.getSession();
		JobDto postedJobDetails=null;
		String fromView="postedJobs";
		String toView="postedJobDetails";
		
		
		
		//check user is loggedin or not
  		if(((String)ses.getAttribute("sesId"))!=ses.getId()) {
  		   return UtilService.SetErrorMsg("error","<a href='/authen/signup0'>first register as employer/recruiter</a>");
  		}
  		
  		
      //check if user is employer/recruiter or not
        if( !(((Users)(ses.getAttribute("users"))).getUserType().name()).equalsIgnoreCase("EMPLOYER"))
         return UtilService.SetErrorMsg("error","<a href='/authen/login0/CANDIDATE'>login  as employer</a>");

 
   //get jobDetails
        try {
	     postedJobDetails=empService.getPostedJobDetails(jobId);
        } catch (Exception e) {
	    return UtilService.SetErrorMsg(fromView, e.getMessage());
        }
		
        System.out.println("size-------->"+postedJobDetails.getApplications().size());
        System.out.println("job desc---->"+postedJobDetails.getJobDescription());
       postedJobDetails.getApplications().forEach(app->{
        System.out.println("email---"+app.getJobSeeker().getEmail());
        System.out.println("name---"+app.getJobSeeker().getUser().getName());
        app.getJobSeeker().getSkills().forEach(skill->{
        	System.out.println("skills----"+skill.getSkillName());
        });
        });
		
		mv.setViewName(toView);
		mv.addObject("postedJobDetails",postedJobDetails);
		return mv;
	}
	
	
	
	
	
	
	@RequestMapping("/byCitySkills/{city}/{skills}")
	public ModelAndView test(@PathVariable("city")String city,@PathVariable("skills")String... skills) throws Exception {
		
		jobService.jobsBySkillCity(city,skills);
		return null;
	}

}

