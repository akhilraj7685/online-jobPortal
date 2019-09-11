package com.nt.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Address implements Comparable<Address>{
	
    private int aid;     
	private String state;
         private String city;
         
         static int t=5;
      Address(int aid,String state,String city){
    	  this.aid=aid;
    	  this.state=state;
    	  this.city=city;
      }
      
      
    
      @Override
    public boolean equals(Object obj) {
    	  System.out.println("equals");
    	 return true;
    }
      
      
      @Override
    public int hashCode() {
    	  System.out.println("hashcode");
    	 return StateMap.getStateCode(state);
    	 // return ++t;
    	
    }
      
      /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return aid + ", " + state + ", " + city + "]";
	}



	@Override
	public int compareTo(Address o) {
	System.out.println("compareto");
		return 0;
		
	}
      

}
