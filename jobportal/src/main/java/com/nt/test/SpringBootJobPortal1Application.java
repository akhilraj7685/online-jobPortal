package com.nt.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import com.nt.cgf.AppConfig;


@Import(value=AppConfig.class)
@SpringBootApplication
public class SpringBootJobPortal1Application {

	public static void main(String[] args) {
		ApplicationContext ctx=null;
		ctx=SpringApplication.run(SpringBootJobPortal1Application.class, args);
		
	}

}