/**
 *    controller for some common handler for all users e.g.,candidate,admin,employer
 */
package com.nt.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nt.util.UtilService;

@Controller
@RequestMapping("/util")
public class UtilController{
	
	
	
	//home page handler
	@RequestMapping("/home")
	public ModelAndView HomeHandler(HttpServletRequest req) {
		ModelAndView mv=new ModelAndView();
		HttpSession ses=null;
		
		
		
		ses=req.getSession();
		//send controller to service to get view name
		mv=UtilService.setMyView(ses);
		                     
		return mv;
	}
	

	
	
	
	
	/**
	 * login request
	 * redirect the user to index.html where he can click on login button
	 */
	@RequestMapping("/login0")
	public String loginHandler() {
		
		return "redirect:/index.html";
		
	}
	
	
	
}
