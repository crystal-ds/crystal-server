package com.crystal.domain;

import java.util.ArrayList;

public class ResultSet {
	private enum Status {READY, NOTREADY, NOTFOUND, ERROR}
	private double id;
	private Status status;
	private ArrayList<Run> runs;
	
	
	public ResultSet(double id, Status status, ArrayList<Run> runs) {
		super();
		this.id = id;
		this.status = status;
		this.runs = runs;
	}
	
	public double getId() {
		return id;
	}
	public void setId(double id) {
		this.id = id;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public ArrayList<Run> getRuns() {
		return runs;
	}
	public void setRuns(ArrayList<Run> runs) {
		this.runs = runs;
	}
	
}
