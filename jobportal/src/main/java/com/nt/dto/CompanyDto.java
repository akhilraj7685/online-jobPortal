package com.nt.dto;

import java.io.Serializable;
import java.util.Date;

import com.nt.entity.Company.IndustryType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDto implements Serializable {
	

	private long cmpId;

    private String cmpName;

    private Date cmpEstDate;   //when the company established---company establish date

    private String cmpWebsiteUrl;

    private String cmpDescription;
    
    private IndustryType industryType;

    
    
}
