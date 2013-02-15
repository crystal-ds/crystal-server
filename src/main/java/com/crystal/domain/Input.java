package com.crystal.domain;

import java.util.ArrayList;

public class Input {
	private enum parameterType {INTEGER, RANGE, STRING, CHARACTER}
	private parameterType type;

	private ArrayList<varValPair> paremeters;
	
	public Input(parameterType type, ArrayList<varValPair> paremeters) {
		this.type = type;
		this.paremeters = paremeters;
	}
	
	public Input(String type, ArrayList<varValPair> paremeters) {
		this.type = parameterType.valueOf(type);
		this.paremeters = paremeters;
	}

	
	
	public parameterType getType() {
		return type;
	}

	public void setType(parameterType type) {
		this.type = type;
	}

	public ArrayList<varValPair> getParemeters() {
		return paremeters;
	}

	public void setInputs(ArrayList<varValPair> paremeters) {
		this.paremeters = paremeters;
	}

	
	

		
		
}
	

