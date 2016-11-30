package com.perso.mouseclicker.exceptions;

import java.util.ArrayList;

public class ListIllegalArgumentException extends IllegalArgumentException {

	private static final long serialVersionUID = 4125198248019574755L;

	ArrayList<String> listErrors;
	
	public ListIllegalArgumentException(String s, ArrayList<String> listErrors) {
		super(s);
		setListErrors(listErrors);
	}

	public ArrayList<String> getListErrors() {
		return listErrors;
	}

	public void setListErrors(ArrayList<String> listErrors) {
		this.listErrors = listErrors;
	}
	
}
