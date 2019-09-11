package com.nt.util;

import java.lang.ProcessBuilder.Redirect;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.nt.entity.Users;
import com.nt.entity.Users.UserType;

@Service
public class UtilService {

	
	
	
	//change string date into util date
	public static Date convertToUtil(String date) throws ParseException {
		Date finalDate=null;
		SimpleDateFormat formater=new SimpleDateFormat("yyyy-MM-dd");
			finalDate=formater.parse(date);
		return finalDate;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * set appropriate view based on user    ----adminHome,candiHome,recHome
	 * @param ses
	 * @return
	 */
	public static ModelAndView setMyView(HttpSession ses) {
		Users users=null;
		ModelAndView mv=new ModelAndView();
		
		if(ses.getAttribute("sesId")==null) 
			mv.setViewName("redirect:/index.html");
		else{ 
		    users=(Users)ses.getAttribute("users");
		    if(users.getUserType().name().equalsIgnoreCase(UserType.ADMIN.name()))
		    		mv.setViewName("adminHome");
		    else if(users.getUserType().name().equalsIgnoreCase(UserType.CANDIDATE.name()))
		    	mv.setViewName("candiHome");
		    else if(users.getUserType().name().equalsIgnoreCase(UserType.EMPLOYER.name()))
		    	mv.setViewName("recHome");
		}
		return mv;
	}
	
	
	
	
	
	
	
	
	
	
	
	//set appropriate view based on userType------adminLogin,candiLogin,recLogin
		public static ModelAndView setMyView(String userType) {
			
			ModelAndView mv=new ModelAndView();
			
			if(userType==null) {
				mv.setViewName("error");
			    mv.addObject("errMsg","somthing error. Try again");
			}
			else{ 
			    
			    if(userType.equalsIgnoreCase(UserType.ADMIN.name()))
			    		mv.setViewName("adminLogin");
			    else if(userType.equalsIgnoreCase(UserType.CANDIDATE.name()))
			    	mv.setViewName("candiLogin");
			    else if(userType.equalsIgnoreCase(UserType.EMPLOYER.name()))
			    	mv.setViewName("recLogin");
			}
			return mv;
		}
	
	
	
	
	
	
	
	
	
	//decide UserType based by given String
	public static UserType getUserType(String userType) {
		if(userType==null) {
			throw new IllegalArgumentException("usertype is null");
		}
	    
		else {
			if(userType.equalsIgnoreCase(UserType.ADMIN.name()))
				return UserType.ADMIN;
			else if(userType.equalsIgnoreCase(UserType.CANDIDATE.name()))
				return UserType.CANDIDATE;
			else if(userType.equalsIgnoreCase(UserType.EMPLOYER.name()))
				return UserType.EMPLOYER;
			else 
				throw new IllegalArgumentException("userType is not correct");
		}//else
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//set error message for the given view name
	public static ModelAndView SetErrorMsg(@NotNull String viewName,@NotNull String errMsg) {
		ModelAndView mv=null;
		
		mv=new ModelAndView();
		mv.setViewName(viewName);
		mv.addObject("returnMsg",errMsg);
		return mv;
	}
	
	
	
	
	
	
	
	
	
	
	
}
