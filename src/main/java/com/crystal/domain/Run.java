package com.crystal.domain;

import java.util.ArrayList;

public class Run {
	private ArrayList<Input> parameters;
	private ArrayList<Result> results;
	
	public Run(ArrayList<Input> parameters, ArrayList<Result> results) {
		this.parameters = parameters;
		this.results = results;
	}

	public ArrayList<Input> getParameters() {
		return parameters;
	}

	public void setParameters(ArrayList<Input> parameters) {
		this.parameters = parameters;
	}

	public ArrayList<Result> getResults() {
		return results;
	}

	public void setResults(ArrayList<Result> results) {
		this.results = results;
	}
	
	
}
