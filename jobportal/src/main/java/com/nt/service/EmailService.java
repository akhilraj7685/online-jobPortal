package com.nt.service;

import java.io.File;

import javax.mail.MessagingException;

public interface EmailService {

	
	public void sendLink(String toEmail) throws MessagingException;
	
	
}
