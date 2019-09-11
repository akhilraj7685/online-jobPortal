package com.nt.controller;               

import java.text.ParseException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nt.dto.UsersDto;
import com.nt.entity.Users;
import com.nt.entity.Users.UserType;
import com.nt.service.AuthenService;
import com.nt.service.EmailService;
import com.nt.util.UtilService;

@Controller
@RequestMapping("/authen")
public class AuthenController {
	
	@Autowired
	private AuthenService authenService;
	
	@Autowired
	private EmailService emailService;
	
	
	
	
	
	
	
	
	//------------------------------------signup---------------------------------------
	
	
	
	//signup request handler
	@RequestMapping("/signup0")
	public String signUp() {
		return "signup0";
	}
	
	
	//handle request from page signup0.jsp
	//only post mapping allowed
	@RequestMapping("/signup1")   
	public ModelAndView signup1(HttpServletRequest req) {
		ModelAndView  mv=new ModelAndView();
		//save usertype in session scope
		HttpSession  ses=req.getSession();
		
		//check if any value is null
		if(req.getParameter("userType")==null || req.getParameter("email")==null || req.getParameter("password")==null || req.getParameter("dob")==null) {
			mv.setViewName("signup0");
			mv.addObject("returnMsg","fill all details correctly. All fields are mendatory");
		    return mv;
		}
	      
		ses.setAttribute("userType",req.getParameter("userType"));
		ses.setAttribute("email",req.getParameter("email"));
		ses.setAttribute("password", req.getParameter("password"));
		ses.setAttribute("dob",req.getParameter("dob"));
		
		//check email already registered or not
		if(authenService.isEmailRegistered(req.getParameter("email"))) {
			mv.setViewName("signup0");
			mv.addObject("returnMsg","this email is already registered. Try with another email");
			return mv;
		}//if
			
		else {
		//send control to take remaining values from form page--signup1.jsp
			//for candidate send control to candiSignup1
		mv.setViewName("signup1");
		return mv;
		}
	}
    
	
	
	
	
	
	
	
	
	
	
	
	//handle request from page signup1.jsp
	//only post mapping allowed
	/**
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/signup2")
    public ModelAndView Signup2(HttpServletRequest req) {
		Users users=null;                 
		UsersDto dto=null;
		HttpSession ses=null;
		ModelAndView mv=new ModelAndView();
		ses=req.getSession();        
		
		//process values coming from form
		dto=new UsersDto();
		if(req.getParameter("userType")==null) {
			mv.setViewName("signup1");
			mv.addObject("returnMsg","please fill user type");
		}
			
		
		//validate and store all values to dto
		dto.setUserType(UserType.valueOf((String)ses.getAttribute("userType")));
		dto.setEmail((String)ses.getAttribute("email"));	
		
		try {
			dto.setDob(UtilService.convertToUtil((String)ses.getAttribute("dob")));
		} catch (ParseException e) {
			mv.setViewName("signup1");
			mv.addObject("returnMsg", "dob format is wrong. Try again");
            return mv;
		}
		
		dto.setPassword((String)(ses.getAttribute("password")));
		
		//validate form datas
		if(req.getParameter("name")==null || req.getParameter("gender")==null) {
			mv.setViewName("signup1");
			mv.addObject("returnMsg","fill all fields");
		}
		
		dto.setName(req.getParameter("name"));
		dto.setGender(req.getParameter("gender").charAt(0));
		
		//send values to service to create new user
		try {
			users=authenService.register(dto);
		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("signup0");
	        mv.addObject("returnMsg","problem in registration. Please try again");
	        return mv;
		}
		//remove all attribute values from session
		ses.removeAttribute("userType");
		ses.removeAttribute("email");
		ses.removeAttribute("password");
		ses.removeAttribute("dob");
		
		//set returned user in session object
		ses.setAttribute("sesId",ses.getId());
		ses.setAttribute("users",users);
		System.out.println("users name----"+users.getName());
		//set appropriate view
		return UtilService.setMyView(ses);
		
	}//signup2	
	
	
	
	
	
	
	//------------------------------------signup ends---------------------------------------
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//--------------------------------------login-----------------------------------
	//handle login request
	@RequestMapping("/login0/{userType}")
	public String login0(@PathVariable(name="userType")String userType) {
		
		if(userType==null)
		return "candiLogin";
	
	  //decide usertype
	   if(userType.equalsIgnoreCase(UserType.ADMIN.name()))
		return "adminLogin";
	   else if(userType.equalsIgnoreCase(UserType.CANDIDATE.name()))
		   return "candiLogin";
	   else if(userType.equalsIgnoreCase(UserType.EMPLOYER.name()))
		   return "recLogin";
	   else 
		   return "candiLogin";
	}
	
	
	
	
	//login request handler for login form eg., candiLogin.jsp,recLogin.jsp,adminLogin.jsp
	@RequestMapping("/login1")
	public ModelAndView login(HttpServletRequest req) {
		String type=null;
		ModelAndView mv=null;
		Users users=null;
		HttpSession ses=null;
		type=req.getParameter("userType");
		
		mv=new ModelAndView();
		if(req.getParameter("email")==null || req.getParameter("password")==null || req.getParameter("userType")==null) {
	    		throw new IllegalArgumentException("one value missing. Try again");
		}
			
	     try {
			users=authenService.login(req.getParameter("email"),req.getParameter("password"),type);
		} catch (Exception e) {
			mv=UtilService.setMyView(type);
			mv.addObject("returnMsg",e.getMessage());
			return mv;
			
		}
	     
	     //set returned "users" object to session scope to 
	     ses=req.getSession();
	     ses.setAttribute("sesId",ses.getId());
	     ses.setAttribute("users",users);
	     mv=UtilService.setMyView(ses);
	     return mv;
	}
	
	
	//--------------------------------------login end-----------------------------------
	
	
	
	
	
	
	
	
	
	
	
	
	//logout request
	@RequestMapping("/logout")
	public String logout(HttpServletRequest req) {
		HttpSession ses=req.getSession();
		//invalidate the session
		ses.invalidate();
		return "redirect:/index.html";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//check if email is registered
		@RequestMapping("/checkMail/{email}")
		@ResponseBody
		public boolean validateMail(@PathVariable(name="email")String mail) {
			boolean flag=false;
			flag= authenService.isEmailRegistered(mail);
			if(flag) {
				sendEmailLink(mail);
			}
			
				return flag;
		}
		
	
	
	
	
		
		
		
		
		
		
		//send email link
		public boolean sendEmailLink(String toEmail) {
	        
			try {
			emailService.sendLink(toEmail);
		} catch (MessagingException e) {
			
			return false;
		}	
			return true;
		}
		
		
		
		
		
		
		
		
		
	    
		
		//when user click on the link then match the token and email
		@RequestMapping("/validateToken/{mailToken}")
		public ModelAndView ValidateMailToken(@PathVariable(name="mailToken")String mailToken,HttpServletRequest req) {
		String email=null;
		ModelAndView mv=null;
		HttpSession ses=null;
			//send service to validate for validation
			email=authenService.checkUrl(mailToken);
			
			if(email!=null) {
				//check returned string from service is email or error message
				if(email.contains("error")) {
					mv=new ModelAndView("error");
					mv.addObject("errMsg","this email id does not exist. try again or contact us");
				    return mv;
				}//if
				else  //update password
				{
					//set email in session variable
					ses=req.getSession();
					ses.removeAttribute("email");
					ses.setAttribute("email",email);
					//send on view page to take new password
					mv=new ModelAndView("changePswd");
					return mv;
				}//else
			
			}//if		 
		 return null;
		}
	  

	//password recovery request handler
	@RequestMapping("/pswdRecover")
	public String pswdRecoveryHandler() {
		return "pswdRecovery";
	}
	
	
	
	
	
	
	
	
	
	
	
	//update password of user
	@RequestMapping("/update")
	public ModelAndView updatePswd(HttpServletRequest req) {
		ModelAndView mv=null;
		boolean updated=false;
		String email=null;
		UsersDto userDto=null;
		String userType=null;
		HttpSession ses=req.getSession();
		String newPswd=req.getParameter("password1");
		email=(String)ses.getAttribute("email");
		if(email!=null)
		userDto=authenService.updatePswdByEmail(email,newPswd);
		System.out.println("-----1-----");
		if(userDto!=null) {
			userType=userDto.getUserType().name();
			System.out.println("----1.1------");
			//login
			try {
				authenService.login2(email, newPswd, userType);
			} catch (Exception e) {
				mv=UtilService.setMyView(userType);
				mv.addObject("returnMsg",e.getMessage());
				ses.invalidate();
				return mv;
				
			}
		}//if
			
		mv=new ModelAndView();
		mv=UtilService.setMyView(userType);
		mv.addObject("advice","login with your new password");
		ses.removeAttribute("email");
		return mv;
		
	}
	
}//class
