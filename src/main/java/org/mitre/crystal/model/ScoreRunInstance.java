/**
 * 
 */
package org.mitre.crystal.model;

import java.util.Date;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author tmlewis
 *ScoreingRunInstance is the Scoreing Model equilvent of a Model Run instance. The exception
 *being that it does not have a list of inputs as all Scoreing Models are scored the same
 *way
 */
@Entity
@Table(name="scoring_run_instance")
public class ScoreRunInstance {


	//Unique ID for this scoreing run
	private long scoreRunInstanceID;
	//the ID of the ModelRunInstance you are scoreing
	private long mriJobInstanceID;
	//The score(s) that the score engine generated
	private Map <String,String> outputValues;
	//The time it was scored
	private Date timestamp;

	public ScoreRunInstance() {
		setTimestamp(new Date());
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public long getScoreRunInstanceID() {
		return scoreRunInstanceID;
	}
	
	public void setScoreRunInstanceID(long scoreRunInstanceID) {
		this.scoreRunInstanceID = scoreRunInstanceID;
	}

	@Column(name="mri_link")
	public long getMriJobInstanceID() {
		return mriJobInstanceID;
	}
	public void setMriJobInstanceID(long batchJobInstanceID) {
		mriJobInstanceID = batchJobInstanceID;
	}

	@Column(name="output_json_value")
	@ElementCollection(fetch=FetchType.EAGER)
	@MapKeyColumn(name="output_name")
	@CollectionTable(
			name="score_model_run_instance_outputs",
			joinColumns=@JoinColumn(name="model_run_instance_id")		
			)
	public Map<String, String> getOutputValues() {
		return outputValues;
	}
	
	public void setOutputValues(Map<String, String> outputValues) {
		this.outputValues = outputValues;
	}

	@Basic
	@Column(name="time_stamp")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
}
