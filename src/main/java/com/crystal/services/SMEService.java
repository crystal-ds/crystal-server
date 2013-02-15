package com.crystal.services;


import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.crystal.domain.Input;
import com.crystal.domain.ScoringModel;
import com.crystal.domain.varValPair;

/**
 * The Class smeService.
 * 
 */
@Service
public class SMEService {
	 
	 

	private static final Logger logger_c = Logger.getLogger(SMEService.class);
	/**
	 * Substitute for a real database
	 * @return List<ScoringModel>
	 */
	private ArrayList<ScoringModel> theScoringModels(){
		
		//ScoringModel 0 
		varValPair<Integer> parameter0 = new varValPair<Integer>("Integer", 0);
		ArrayList<varValPair> parameters0= new ArrayList<varValPair>();
		parameters0.add(parameter0);
		Input input0 = new Input("INTEGER" , parameters0);
		ArrayList<Input> inputs0= new ArrayList<Input>();
		inputs0.add(input0);
		ScoringModel model0 = new ScoringModel("model0", "this is model0", 0, inputs0);		
		
		
		//ScoringModel 1 
		varValPair<Integer> parameter1 = new varValPair<Integer>("Integer", 1);
		ArrayList<varValPair> parameters1= new ArrayList<varValPair>();
		parameters1.add(parameter1);
		Input input1 = new Input("INTEGER" , parameters1);
		ArrayList<Input> inputs1= new ArrayList<Input>();
		inputs1.add(input1);
		ScoringModel model1 = new ScoringModel("model1", "this is model1", 1, inputs1);
		
		
		
		//ScoringModel 2 
		varValPair<Integer> parameter2 = new varValPair<Integer>("Integer", 2);
		ArrayList<varValPair> parameters2 = new ArrayList<varValPair>();
		parameters2.add(parameter2);
		Input input2 = new Input("INTEGER", parameters2);
		ArrayList<Input> inputs2 = new ArrayList<Input>();
		inputs2.add(input2);
		ScoringModel model2 = new ScoringModel("model2", "this is model2", 2, inputs2);
		
		ArrayList<ScoringModel> models= new ArrayList<ScoringModel>();
		models.add(model0);
		models.add(model1);
		models.add(model2);
		
		return models;

	}
	

	/**
	 * Get the model by id.
	 *
	 * @param modelID
	 * 
	 * @return the model by id
	 */
	public ScoringModel getScoringModelById(String modelID) {
		int modelidnum = Integer.parseInt(modelID) % 3;

		

		return theScoringModels().get(modelidnum);
	}

	/**
	 * Gets all models.
	 *
	 * @return all models
	 */
	public List<ScoringModel> getAllScoringModels() {

		return theScoringModels();
	}

	/**
	 * Creates the ScoringModel.
	 *
	 * @param ScoringModel
	 * @return the ScoringModel
	 */
	public ScoringModel createScoringModel(ScoringModel model) {
		//TODO MAKE THIS SAVE
		logger_c.debug("Persisting model in database: " + model.getName());

		

		return model;
	}

	/**
	 * Update ScoringModel.
	 *
	 * @param model
	 *            
	 * @return ScoringModel
	 */
	public ScoringModel updateScoringModel(ScoringModel model) {
		//TODO MAKE THIS SAVE
		logger_c.debug("Updating fund in database: " + model.toString());


		return model;
	}

	/**
	 * Delete ScoringModel.
	 *
	 * @param ScoringModel
	 */
	public void deleteScoringModel(String modelId) {
		int modelidnum = Integer.parseInt(modelId) % 3;
		ScoringModel model = theScoringModels().remove(modelidnum % 3);
		logger_c.debug("Deleting fund from database: " + model.getName());

	}

}
