package com.nt.dto;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SkillDto implements Serializable {

	
    private long skillId;
	
	private String skillName;
	
}
