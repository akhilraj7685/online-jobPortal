package com.nt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Skill {

	@Id
	@GenericGenerator(name="seq2",strategy="sequence")
	@GeneratedValue(generator="seq2"/*strategy=GenerationType.SEQUENCE*/)
	@Column(name="skillId",length=8)
	private long skillId;
	
	@Column(name="skillName",length=35)
	private String skillName;
	
	@ManyToOne
	private Job job;
}
