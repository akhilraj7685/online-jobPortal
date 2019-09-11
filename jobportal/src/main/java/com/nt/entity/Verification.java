package com.nt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.validator.constraints.br.TituloEleitoral;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Verification {

	@Id
	@Column(name="token",length=50)
	private String token;
	
	@Column(name="email",length=35)
	private String email;
	
	/**
	 * this time is miliseconds
	 */
	@Column(name="genrTime",length=50)
	private Long genrTime; //generation time 
	
	@Column(name="valiUptoMinutes",length=10)
	private int valiUptoMinutes;
	
	 
	
	
}
