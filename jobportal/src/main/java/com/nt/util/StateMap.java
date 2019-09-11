package com.nt.util;

import java.util.HashMap;

public class StateMap {

	
	private static HashMap<String, Integer> stateMap=new HashMap<String, Integer>(); 
	
	static {
		stateMap.put("Bihar",1);
		stateMap.put("bengal",2);
		stateMap.put("up",3);
		stateMap.put("mp",4);
		stateMap.put("hp",5);
		
		
	}
	
	
	
	public static int getStateCode(String state) {
		System.out.println("state name----"+state);
		if(stateMap.containsKey(state))
			return stateMap.get(state);
		else
			return -1;
	}
}
