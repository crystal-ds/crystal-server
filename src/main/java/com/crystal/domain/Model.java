package com.crystal.domain;

import java.util.ArrayList;


public class Model implements IModel {
	private String name;
	private String modelDescription;
	private int ID;
	private ArrayList<Input> inputParameters;
	
	public Model(String name, String modelDescription, int iD,
			ArrayList<Input> inputParameters) {
		this.name = name;
		this.modelDescription = modelDescription;
		ID = iD;
		this.inputParameters = inputParameters;
	}
	
	
	/**
	 * Makes a call to run this model with inputParemeters
	 * @return
	 */
	public double run(){
		double runId = 0;
		return runId;
	}
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModelDescription() {
		return modelDescription;
	}

	public void setModelDescription(String modelDescription) {
		this.modelDescription = modelDescription;
	}

	public double getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public ArrayList<Input> getInputParameters() {
		return inputParameters;
	}

	public void setInputParameters(ArrayList<Input> inputParameters) {
		this.inputParameters = inputParameters;
	}
	
}
