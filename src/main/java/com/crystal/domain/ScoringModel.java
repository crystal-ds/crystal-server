package com.crystal.domain;

import java.util.ArrayList;


public class ScoringModel implements IModel {
	private String name = null;
	private String scoringModelDescription = null;
	private double ID = -1;
	private ArrayList<Input> inputParameters = null;
	
	public ScoringModel(String name, String scoringModelDescription, double iD,
			ArrayList<Input> inputParameters) {
		this.name = name;
		this.scoringModelDescription = scoringModelDescription;
		ID = iD;
		this.inputParameters = inputParameters;
	}
	
	
	/**
	 * Makes a call to run this scoringModel with inputParemeters
	 * @return
	 */
	public double score(){
		double score = 0;
		return score;
	}
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScoringModelDescription() {
		return scoringModelDescription;
	}

	public void setScoringModelDescription(String scoringModelDescription) {
		this.scoringModelDescription = scoringModelDescription;
	}

	public double getID() {
		return ID;
	}

	public void setID(double iD) {
		ID = iD;
	}

	public ArrayList<Input> getInputParameters() {
		return inputParameters;
	}

	public void setInputParameters(ArrayList<Input> inputParameters) {
		this.inputParameters = inputParameters;
	}
	
}
