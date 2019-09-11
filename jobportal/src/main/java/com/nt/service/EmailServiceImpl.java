package com.nt.service;

import java.util.Date;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.nt.entity.Verification;
import com.nt.repo.VerificationRepo;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private VerificationRepo verfyRepo;
	
	@Autowired
    private JavaMailSender javaMailSender;

    @Value("${email.from}")
    private String fromAddress;
    
    @Value("${email.link.url}")
    private String url0;
    
    
//generate token
    public String createToken() {
    	String begin="@@",middle="T@",end="&@@";
    	Random rd=new Random();
    	int temp=0;
    	if((temp=rd.nextInt(10000))<10) {
    		begin="$@$"+temp;
    		middle="KK$$";
    		end="PP";
    	}
    	else
    		if((temp=rd.nextInt(10000))<100) {
    	     begin="@$$"+temp;
    	     middle="TSJ";
    	     end="JJ";
    		}
    		else {
    			begin="P$P"+temp;
    			middle="S$$";
    			end="GG";
    		}
    	return begin+middle+end;
    }
    
    
    
    
    
    
    
	public void sendLink(String email) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        String token="@@#@";
        String subject="email verification";
        String message="click on the link below to verify your email address  \n\n\n ";
        String url=null;
        Verification verification=null;
        
        verification=new Verification();
        //generate token 
        token=createToken();
        
        verification.setToken(token);
        verification.setEmail(email);
        verification.setGenrTime((new Date().getTime())/5);  //divided to reduce the length of long value for database easyness
        verification.setValiUptoMinutes(15);
        
       verfyRepo.save(verification);
        
        //create url
        url=url0+"/,"+email+","+token;
        message=message+url;
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(fromAddress);
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(message);

       //send link to the users email
        javaMailSender.send(mimeMessage);
    }
	
	
	
	
	
	
	

	
	
	

   
}
