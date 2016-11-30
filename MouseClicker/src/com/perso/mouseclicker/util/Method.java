package com.perso.mouseclicker.util;

import java.awt.GridBagConstraints;

public abstract class Method {

	public static boolean isPositiveInteger(String str) {
	    if (str == null) {
	        return false;
	    }
	    int length = str.length();
	    if (length == 0) {
	        return false;
	    }
	    for (int i=0; i < length; i++) {
	        char c = str.charAt(i);
	        if (c < '0' || c > '9') {
	            return false;
	        }
	    }
	    return true;
	}
	
	public static boolean isPositiveDouble(String str) {
	    if (str == null) {
	        return false;
	    }
	    int length = str.length();
	    if (length == 0) {
	        return false;
	    }
	    
	    
	    boolean findPoint = false;
	    
	    for (int i=0; i < length; i++) {
	        char c = str.charAt(i);
	        
	        //first digit must be a digit beetwen 0 and 9
	        if (i == 0 && (c < '0' || c > '9')){
	        	return false;
	        }
	        
	        if (c < '0' || c > '9') {
	           if ( c == '.' && !findPoint){
	        	   findPoint = true;
	           } else {
	        	   return false;
	           }
	        }
	    }
	    
	    char lastChar = str.charAt(length - 1);
	    //check if a point was found in the String and the last character is a digit
	    if (!findPoint || lastChar < '0' || lastChar > '9' ){
	    	return false;
	    } else {
	    	return true;
	    }
	}
	
	public static GridBagConstraints createGridConstraints(int anchor, int weightx, int weighty, int gridx, int gridy ){

		GridBagConstraints gridConstraints = new GridBagConstraints();

		gridConstraints.anchor = anchor;
		gridConstraints.weightx = weightx;
		gridConstraints.weighty = weighty;
		gridConstraints.gridx = gridx;
		gridConstraints.gridy = gridy;
		
		return gridConstraints;
	}
}
