package com.crystal.domain;

import java.util.ArrayList;


public class Result {
	private enum resultType {INTEGER, RANGE, STRING, CHARACTER}
	private resultType type;

	private ArrayList<varValPair> outcomes;
	
	public Result(resultType type, ArrayList<varValPair> outcomes) {
		this.type = type;
		this.outcomes = outcomes;
	}


	public resultType getType() {
		return type;
	}

	public void setType(resultType type) {
		this.type = type;
	}

	public ArrayList<varValPair> getOutcomes() {
		return outcomes;
	}

	public void setOutcomes(ArrayList<varValPair> outcomes) {
		this.outcomes = outcomes;
	}
}
