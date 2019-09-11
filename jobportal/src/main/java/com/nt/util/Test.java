package com.nt.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class Test {
	Object o1;
	
	public static void main(String[] args) {
		/*
		TreeSet<Address> ts=new TreeSet<Address>();
		Address a1=new Address(1,"bihar", "saharsa");
		Address a2=new Address(1,"bihar", "saharsa");
		Address a3=new Address(1,"bihar", "saharsa");
		ts.add(a1);
		ts.add(a1);
		ts.add(a3);
		System.out.println("ts"+ts);
		
		*/
		  HashSet<Address> hs=new HashSet<Address>();
		  Address a1=new Address(1,"bihar", "saharsa");
		  Address a2=new Address(1,"bihar", "saharsa");
		  Address a3=new Address(1,"bihar", "saharsa");
		System.out.println("--------");
		  hs.add(a1); 
		System.out.println("--hs"+hs);
		
		  hs.add(a2); 
		  System.out.println("--hs"+hs);  
		  hs.add(a3);
		  System.out.println("--hs"+hs);
		 
		
		 	}

}
