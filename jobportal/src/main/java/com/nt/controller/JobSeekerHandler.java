/**
 * this handler handles all the request of jobseeker
 */
package com.nt.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nt.dto.CompanyDto;
import com.nt.dto.EducationDetailsDto;
import com.nt.dto.ExperienceDetailsDto;
import com.nt.dto.JobDto;
import com.nt.dto.JobSeekerDto;
import com.nt.dto.SkillDto;
import com.nt.dto.UsersDto;
import com.nt.entity.Users;
import com.nt.service.AuthenService;
import com.nt.service.JobService;
import com.nt.util.UtilService;

@Controller
@RequestMapping("/jobs")
public class JobSeekerHandler {

	@Autowired
	private AuthenService authenService;
	
	@Autowired
	private JobService jobService;
	
	
	
	
	
	
	/**
	 * get all jobs 
	 * authentication is not required for this request
	 */
	@RequestMapping("/allJobs")
	public ModelAndView getAllJobs(){
		String fromView="redirect:/index.html";
		ModelAndView mv=null;
		List<JobDto> jobDtos=null;
		Map<String,Object> result=null;
		
		
		try {
			result=jobService.getAllJobs();
		} catch (Exception e) {
			UtilService.SetErrorMsg(fromView, e.getMessage());
		}
		
		//get listOf dtos from map
		
			 jobDtos=(List<JobDto>)result.get("jobDtoList");
			
			
				
		 mv=new ModelAndView();
		mv.setViewName("jobs");
		mv.addObject("jobs",jobDtos);
		mv.addObject("pageSize",((Page)result.get("page")).getNumberOfElements());
		mv.addObject("totalPages",((Page)result.get("page")).getTotalPages());
		mv.addObject("totalJobs",((Page)result.get("page")).getTotalElements());
		mv.addObject("pageNumber",((Page)result.get("page")).getNumber());
		
		
		return mv;
	}
	
	
	
	
	
	
	

	/**
	 * get all jobs by paging 
	 * authentication is not required for this request
	 * @return jobs--->List<JobDto>
	 * @return pageSize-->size of each page
	 * @return totalPages-->total number of pages
	 * @return totalJobs-->total number of jobs
	 * @return pageNumber-->size of each page        
	 */
	@RequestMapping("/allJobs/{pageNo}/{pageSize}")
	public ModelAndView getAllJobs(@PathVariable("pageNo")int pageNo,@PathVariable("pageSize")int pageSize){
		String fromView="jobs";
		List<JobDto> jobDtos=null;
		Map<String,Object> result=null;
		//use service
		try {
			result=jobService.getJobsByPage(pageNo, pageSize);
		} catch (Exception e) {
			UtilService.SetErrorMsg(fromView, e.getMessage());
		}
		
		//get listOf dtos from map
		
			 jobDtos=(List<JobDto>)result.get("jobDtoList");
			  result.containsValue("JobDtoList");
			 System.out.println("size jobDtos---------"+jobDtos.size());
			
				
		ModelAndView mv=new ModelAndView();
		mv.setViewName("jobs");
		mv.addObject("jobs",jobDtos);
		mv.addObject("pageSize",((Page)result.get("page")).getNumberOfElements());
		mv.addObject("totalPages",((Page)result.get("page")).getTotalPages());
		mv.addObject("totalJobs",((Page)result.get("page")).getTotalElements());
		mv.addObject("pageNumber",((Page)result.get("page")).getNumber());
		
		
		
		return mv;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * get job by id
	 * 
	 */
	@RequestMapping("/jobDetail/{jobId}")
    public ModelAndView getJobById(@PathVariable("jobId")int jobId) {
    	ModelAndView mv=new ModelAndView();
		JobDto jobDto=jobService.getJobById(jobId);
    	mv.setViewName("jobDetails");
    	mv.addObject("jobDto",jobDto);
		return mv;
    }

	
	
	
	
	
	
	
	
	
	
	
	/**
	 * create jobSeekerProfile request
	 * here jsp means jobSeekerProfile
	 */
	@RequestMapping("/createJsp")   
	public ModelAndView CreateJspHandler() {
		return new ModelAndView("jspForm1");
		
	}
	
	
	
	//process jspForm1 data
	@RequestMapping("/jspForm1")
	public ModelAndView processJspForm1(HttpServletRequest req) {
		ModelAndView mv=null;
	    HttpSession ses=req.getSession();
	    JobSeekerDto jsDto=null;
	    EducationDetailsDto eduDto=null;
	    UsersDto usersDto=null;
	    Users users=null;
	    
	   
	  //check user is loggedin or not
	  		if(((String)ses.getAttribute("sesId"))!=ses.getId()) {
	  		   return UtilService.SetErrorMsg("error","<a href='/authen/signup0'>first register as jobSeeker</a>");
	  		}
	  		
	  		
	 //check if user is jobSeeker or not
	 if( !(((Users)(ses.getAttribute("users"))).getUserType().name()).equalsIgnoreCase("CANDIDATE"))
	 return UtilService.SetErrorMsg("error","<a href='/authen/login0/CANDIDATE'>login  as jobSeeker</a>");
	
	
	
	
	//process form data
	   //check for required attribute
	if(req.getParameter("hQuli")=="")
	    return UtilService.SetErrorMsg("jspForm1", "highest qualification was not filled");	
	
	if(req.getParameter("marks")=="")
	    return UtilService.SetErrorMsg("jspForm1", "marks column was empty");	
	
	jsDto=new JobSeekerDto();
	
	//add user to jsDto from session variable after converting entity to dto
	usersDto=new UsersDto();
	users=( Users)ses.getAttribute("users");
	BeanUtils.copyProperties(users,usersDto);
	jsDto.setUserId(users.getUserId());
		 
	eduDto=new EducationDetailsDto();
    
	if(req.getParameter("hQuli")!="")
		eduDto.setHighestQuali(req.getParameter("hQuli"));
	
	if(req.getParameter("major")!="")
		eduDto.setMajor(req.getParameter("major"));
	
	if(req.getParameter("startDate")!="")
		try {
			eduDto.setStartDate(UtilService.convertToUtil(req.getParameter("startDate")));
		} catch (ParseException e) {
		return	UtilService.SetErrorMsg("jspForm1", "start date was not in correct format");	
		}
	
	if(req.getParameter("endDate")!="")
		try {
			eduDto.setCompletionDate(UtilService.convertToUtil(req.getParameter("endDate")));
		} catch (ParseException e) {
			return	UtilService.SetErrorMsg("jspForm1", "start date was not in correct format");
		}
	
	if(req.getParameter("institute")!="")
		eduDto.setInstitute(req.getParameter("institute"));
	
	if(req.getParameter("marks")!="")
		try {
		eduDto.setMarksPercentage(Float.parseFloat(req.getParameter("marks")));
		}
	    catch(NumberFormatException nfe) {
	     UtilService.SetErrorMsg("jspForm1", "enter only number in marks column");	
	    }//catch
	    
	
	//set this educationDetails dto to jobSeeker dto
	  jsDto.setEduDtls(eduDto);
	//set userdto to jobseeker dto
	  jsDto.setUser(usersDto);
	//now set this jobSeeker dto to session to use on another jsp page
	  ses.setAttribute("jsDto",jsDto);
	  
	  //send control to next form
	  mv=new ModelAndView();
	  mv.setViewName("jspForm2");
	  return mv;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//process jspForm2 data
	@RequestMapping("/jspForm2")
	public ModelAndView processJsForm2(HttpServletRequest req) {
		ModelAndView mv=null;
		HttpSession ses=req.getSession();
		JobSeekerDto jsDto=null;
		ExperienceDetailsDto expDto=null;
		
		
		
		   
		  //check user is loggedin or not
		  		if(((String)ses.getAttribute("sesId"))!=ses.getId()) {
		  		   return UtilService.SetErrorMsg("error","<a href='/authen/signup0'>first register as jobSeeker</a>");
		  		}
		  		
		  		
		 //check if user is jobSeeker or not
		 if( !(((Users)(ses.getAttribute("users"))).getUserType().name()).equalsIgnoreCase("CANDIDATE"))
		 return UtilService.SetErrorMsg("error","<a href='/authen/login0/CANDIDATE'>login  as jobSeeker</a>");
		
		 //get jobSeeker dto from session
		 jsDto=(JobSeekerDto)ses.getAttribute("jsDto");
		
		 
		 
		 //process form data
		    //if jobSeeker is fresher then no need to process the experience datails form
		 if(req.getParameter("rdExp").equalsIgnoreCase("fre"))
		 {
		   	 jsDto.setFresher(true);
		   	 mv=new ModelAndView();
		   	 mv.setViewName("jspForm3");
		   	 return mv;
		 }
		 
		 else {
			 expDto =new ExperienceDetailsDto();
			 if(req.getParameter("desg")!="")
				 expDto.setJobTitle(req.getParameter("desg"));
				 
			 if(req.getParameter("cmpName")!="") {
				 CompanyDto cmpDto=new CompanyDto();
				 cmpDto.setCmpName(req.getParameter("cmpName"));
				 //add cmpDto to expDto 
				 expDto.setCompany(cmpDto);
			 }
			 
			 if(req.getParameter("exp")!="")
				 try {
				 expDto.setExp(Float.parseFloat(req.getParameter("exp")));
				 }
			     catch(Exception e) {
			    	 return UtilService.SetErrorMsg("jspForm2","enter only numbers in experience column");
			     }//catch
			 else expDto.setExp(0);
				 
		 
		     
		 
		 //add this experience details dto to jsDto and then add jsDto to the session scope	 
		 jsDto.setExpDtls(expDto);
		 ses.setAttribute("jsDto",jsDto);
		 
		 mv=new ModelAndView();
		 mv.setViewName("jspForm3");
		 return mv;
		 }//else
		 
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

	//process jspForm3 data
	@RequestMapping("/jspForm3")
	public ModelAndView processJsForm3(HttpServletRequest req) {
		ModelAndView mv=null;
		List<SkillDto> skillList=new ArrayList<SkillDto>();
		HttpSession ses=req.getSession();
		JobSeekerDto jsDto=null;
		
		
		
		
		   
		  //check user is loggedin or not
		  		if(((String)ses.getAttribute("sesId"))!=ses.getId()) {
		  		   return UtilService.SetErrorMsg("error","<a href='/authen/signup0'>first register as jobSeeker</a>");
		  		}
		  		
		  		
		 //check if user is jobSeeker or not
		 if( !(((Users)(ses.getAttribute("users"))).getUserType().name()).equalsIgnoreCase("CANDIDATE"))
		 return UtilService.SetErrorMsg("error","<a href='/authen/login0/CANDIDATE'>login  as jobSeeker</a>");
		
		 //get jobSeeker dto from session
		 jsDto=(JobSeekerDto)ses.getAttribute("jsDto");
		
		 
		 
		
		 
		 //process form data
		 Enumeration<String> enu=req.getParameterNames();
			while(enu.hasMoreElements()) {
				SkillDto skillDto=null;
				String skill0=null;
				skill0	=req.getParameter(enu.nextElement());
				
				if(skill0!="") {
					System.out.println("------------");
					skillDto=new SkillDto();
				skillDto.setSkillName(skill0);
				skillList.add(skillDto);
				}
				
			}//while end
		 
		 
		 
		     
		 
		 //add this skilllist  to jsDto and then add jsDto to the session scope	 
		    if(jsDto!=null)
			jsDto.setSkills(skillList);
		 ses.setAttribute("jsDto",jsDto);
		 
		//create this jobseeker profile
		return createJobSeeker(jsDto,ses);
		
		
	      
	}
	
	
	
	
	
	
	/**
	 * create jobSeeker profile
	 */
	private ModelAndView createJobSeeker(JobSeekerDto jsDto,HttpSession ses) {
		ModelAndView mv=null;
		
		authenService.createJobSeeker(jsDto);
		
		//remove jsDto from session
		ses.removeAttribute("jsDto");
		
		//send user to candiHome page
		mv=new ModelAndView();
		  mv.setViewName("candiHome");
		return mv;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * apply for job
	 */
	@RequestMapping("/applyForJob/{jobId}")
	public ModelAndView apply(HttpServletRequest req, @PathVariable("jobId")long jobId) {
	    long userId=0;
	    ModelAndView mv=null;
	    String msg=null;
		HttpSession ses=req.getSession();
	  //check user is loggedin or not
	  //if logged in check jobSeekerProfile is created or not
	
	  //check user is loggedin or not
		if(((String)ses.getAttribute("sesId"))!=ses.getId()) {
		   return UtilService.SetErrorMsg("error","<a href='/authen/signup0'>first register as jobSeeker</a>");
		}
	  
		//check if user is jobSeeker or not
		 if( !(((Users)(ses.getAttribute("users"))).getUserType().name()).equalsIgnoreCase("CANDIDATE"))
		 return UtilService.SetErrorMsg("error","<a href='/authen/login0/CANDIDATE'>login  as jobSeeker</a>");
		
	     //if pass both then get userid
		 userId=((Users)ses.getAttribute("users")).getUserId();
		
	     System.out.println("user id----controller------------------"+userId);
	  
		try {
			msg=authenService.applyJob(userId, jobId);
		} catch (Exception e) {
			mv=UtilService.setMyView(ses);
			mv.addObject("msg",e.getMessage());
			return mv;
		}
	    mv=UtilService.setMyView(ses);
	    mv.addObject("msg",msg);
	    return mv;
	}
	
	
}//class